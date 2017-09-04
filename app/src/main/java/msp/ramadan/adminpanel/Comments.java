package msp.ramadan.adminpanel;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Comment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import msp.ramadan.adminpanel.MyClassea.CommT;
import msp.ramadan.adminpanel.MyClassea.CommentKies;

public class Comments extends AppCompatActivity {
    int numberofcontextMenu;

    String EventTitle;
    Dialog dialogDelete;

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<CommT>commTArrayList;
    static  String AdminName="Admin";
    String Commentcontent;
    JSONObject jsonObject;
    JSONArray jsonArray;
    CommT commT;
    String DATime;
    String commentId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        // write comment here
        commT=new CommT();
        Intent i = getIntent();

        commTArrayList=new ArrayList<>();
        EventTitle = i.getExtras().getString("TitleE");


        AlertDialog.Builder builderconfirm=new AlertDialog.Builder(this);
        builderconfirm.setTitle("Delete:?");
        builderconfirm.setMessage("Are you sure you want to  delete  this Comment");
        builderconfirm.setIcon(android.R.drawable.ic_dialog_alert);
        builderconfirm.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {



                commT=commTArrayList.get(numberofcontextMenu);
                 commentId=commT.getCommTIDD();
                deleteComment();
                commTArrayList.remove(numberofcontextMenu);
                arrayList.remove(numberofcontextMenu);

                adapter.notifyDataSetChanged();

            }
        });
        builderconfirm.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialogDelete=builderconfirm.create();

        requestComments();

        Toast.makeText(Comments.this, "arrayList"+arrayList.size(), Toast.LENGTH_SHORT).show();
        final EditText editText=(EditText)findViewById(R.id.event_comment);
        ImageButton imageButton=(ImageButton)findViewById(R.id.eventCommentSubmitBtn);




        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Commentcontent=editText.getText().toString();


                final java.util.Calendar c= java.util.Calendar.getInstance();
                int year=c.get(java.util.Calendar.YEAR);
                int month=c.get(java.util.Calendar.MONTH);
                int day=c.get(java.util.Calendar.DAY_OF_MONTH);
                int hour=c.get(Calendar.HOUR);
                int minute=c.get(Calendar.MINUTE);
                 DATime=year+"/"+(month+1)+"/"+day+" "+hour+":"+minute;
                stringRequest();
                adapter.add(Commentcontent +"\n"+"\n"+"Date"+" : "+DATime+"\n"+"\n"+"Name"+": "+AdminName);
                adapter.notifyDataSetChanged();

            }
        });

    }




    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater=new MenuInflater(this);
        inflater.inflate(R.menu.menucomment,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        numberofcontextMenu=info.position;
        switch (item.getItemId())
        {

            case R.id.Delete:
                dialogDelete.show();

                return true;
        }
        return false;
    }













    void stringRequest() {


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        ///////// the url to  webService
        String url = "http://192.168.1.9/connectfcis/v1/user/addComment";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.d("responseAfterLogin", response);


                            JSONObject jsonObject = new JSONObject(response);
                            //      Toast.makeText(EventDetials.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            /*Intent intent=new Intent(EventDetials.this,HomeActivity.class);
                            startActivity(intent);
                            finish();*/
//                            Toast.makeText(EventDetials.this, "error in try", Toast.LENGTH_SHORT).show();


                        } catch (JSONException e) {
                            Log.d("responseAfterLogin", response);
                            e.printStackTrace();
                            //     Toast.makeText(LoginActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            String [] str=response.split(":");
                            String messgae=  str[2].replace('}',' '); messgae= str[2].replace('"',' ');

                            //          Toast.makeText(EventDetials.this,messgae , Toast.LENGTH_SHORT).show();
                            //            Toast.makeText(EventDetials.this, "error in catch", Toast.LENGTH_SHORT).show();

                        }
                        // Toast.makeText(LoginActivity.this, , Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("responce", "hat didn't work!");
                //  Toast.makeText(EventDetials.this, "error! check Network or try again ", Toast.LENGTH_SHORT).show();
            }
        }) {

            //define params to be sent when connection opened
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("commContent", Commentcontent);
                params.put("createdTime", DATime);
                params.put("EventName", EventTitle);
                params.put("createdUser",AdminName);



                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    void requestComments() {


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        ///////// the url to  webService
        String url = "http://192.168.1.9/connectfcis/v1/user/getEventComments";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.d("responseAfterLogin", response);


                             jsonObject = new JSONObject(response);
                             jsonArray= jsonObject.getJSONArray("eventComments");
                            Toast.makeText(Comments.this, ""+jsonArray.length(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(Comments.this, "D5l", Toast.LENGTH_SHORT).show();

                            for (int i=0;i<jsonArray.length();i++)
                            {
                              //  Toast.makeText(Comments.this, " befor commTArrayListtttttttttt"+commTArrayList.size(), Toast.LENGTH_LONG).show();

                                JSONObject object= (JSONObject) jsonArray.get(i);
                              //  Toast.makeText(Comments.this, " befor commTArrayListtttttttttt2222"+commTArrayList.size(), Toast.LENGTH_LONG).show();

                                CommT comment=new CommT(object.getString(CommentKies.CommentID),object.getString(CommentKies.CommentContentkey),object.getString(CommentKies.CommentCrTimekey),object.getString(CommentKies.CommentUserkey));

                               // Toast.makeText(Comments.this, "Commentdata******"+comment.getCommentContent(), Toast.LENGTH_SHORT).show();

                                commTArrayList.add(comment);
                               // Toast.makeText(Comments.this, " befor commTArrayList22222"+commTArrayList.size(), Toast.LENGTH_LONG).show();

                            }

                            for (int j=0;j<commTArrayList.size();j++)
                            {
                                String CommentContent=commTArrayList.get(j).getCommentContent();
                                String CommentCrTime=commTArrayList.get(j).getCommentCrTime();
                                String CommentUser=commTArrayList.get(j).getCommentUser();
                                String commentData=CommentContent +"\n"+"\n"+"Date"+" : "+CommentCrTime+"\n"+"\n"+"Name"+": "+CommentUser;
                                arrayList.add(commentData);
                            }

                            ListView listView =(ListView)findViewById(R.id.event_comments_list);

                            registerForContextMenu(listView);
                            adapter=new ArrayAdapter<String>(Comments.this,android.R.layout.simple_list_item_1,arrayList);
                            listView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();






                            Toast.makeText(Comments.this, " after commTArrayList"+commTArrayList.size(), Toast.LENGTH_LONG).show();
                            //       Toast.makeText(HomeActivity.this, "MyComments"+comments.size(), Toast.LENGTH_SHORT).show();

//                            eventsRecyclerView = (RecyclerView) findViewById(R.id.event_list);
//                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//                            eventsRecyclerView.setLayoutManager(layoutManager);
//
//                            adapter = new EventAdapter(comments, getApplicationContext(),HomeActivity.this);
//
//                            eventsRecyclerView.setAdapter(adapter);
//                            eventsRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
//                            adapter.notifyDataSetChanged();



                            //      Toast.makeText(EventDetials.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            /*Intent intent=new Intent(EventDetials.this,HomeActivity.class);
                            startActivity(intent);
                            finish();*/
//                            Toast.makeText(EventDetials.this, "error in try", Toast.LENGTH_SHORT).show();


                        } catch (JSONException e) {
                            Log.d("responseAfterLogin", response);
                            e.printStackTrace();
                            //     Toast.makeText(LoginActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            String [] str=response.split(":");
                            String messgae=  str[2].replace('}',' '); messgae= str[2].replace('"',' ');
                            Toast.makeText(Comments.this, "JSONException", Toast.LENGTH_SHORT).show();
//
//                            for (int i=0;i<jsonArray.length();i++)
//                            {
//                                JSONObject object= null;
//                                try {
//                                    object = (JSONObject) jsonArray.get(i);
//                                } catch (JSONException e1) {
//                                    e1.printStackTrace();
//                                }
//                                CommT comment= null;
//                                try {
//                                    comment = new CommT(object.getString(CommentKies.CommentContentkey),object.getString(CommentKies.CommentCrTimekey),object.getString(CommentKies.CommentUserkey),object.getString(CommentKies.EventNamekey));
//                                } catch (JSONException e1) {
//                                    e1.printStackTrace();
//                                }
//                                commTArrayList.add(comment);
//                                Toast.makeText(Comments.this, " befor commTArrayList"+commTArrayList.size(), Toast.LENGTH_LONG).show();

                          //  }







                            //          Toast.makeText(EventDetials.this,messgae , Toast.LENGTH_SHORT).show();
                            //            Toast.makeText(EventDetials.this, "error in catch", Toast.LENGTH_SHORT).show();

                        }
                        // Toast.makeText(LoginActivity.this, , Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("responce", "hat didn't work!");
                //  Toast.makeText(EventDetials.this, "error! check Network or try again ", Toast.LENGTH_SHORT).show();
            }
        }) {

            //define params to be sent when connection opened
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();



                params.put("EventName", EventTitle);

                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }


    void deleteComment() {


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        ///////// the url to  webService
        String url = "http://192.168.1.9/connectfcis/v1/user/deleteComment";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
//                            Toast.makeText(EventDetials.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                            Log.d("responseAfterLogin", response);



                        } catch (JSONException e) {
                            Log.d("responseAfterLogin", response);
                            e.printStackTrace();
                            //     Toast.makeText(LoginActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            String [] str=response.split(":");
                            String messgae=  str[2].replace('}',' '); messgae= str[2].replace('"',' ');

                            //          Toast.makeText(EventDetials.this,messgae , Toast.LENGTH_SHORT).show();
                            //            Toast.makeText(EventDetials.this, "error in catch", Toast.LENGTH_SHORT).show();

                        }
                        // Toast.makeText(LoginActivity.this, , Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("responce", "hat didn't work!");
                //  Toast.makeText(EventDetials.this, "error! check Network or try again ", Toast.LENGTH_SHORT).show();
            }
        }) {

            //define params to be sent when connection opened
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("commID", commentId);



                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }



}
