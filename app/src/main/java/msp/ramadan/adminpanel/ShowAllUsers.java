package msp.ramadan.adminpanel;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import msp.ramadan.adminpanel.MyClassea.CommT;
import msp.ramadan.adminpanel.MyClassea.EventAdapter;
import msp.ramadan.adminpanel.MyClassea.Users;
import msp.ramadan.adminpanel.MyClassea.UsersKeys;

public class ShowAllUsers extends AppCompatActivity {
    Dialog dialogDelete;

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<Users>uSersArrayList;
    Users userData;
    int numberofcontextMenu;
    String USERID;
    String UserTYPE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_users);
        userData=new Users();
        uSersArrayList=new ArrayList<>();
        GetAllUsers();
        AlertDialog.Builder builderconfirm=new AlertDialog.Builder(this);
        builderconfirm.setTitle("Delete:?");
        builderconfirm.setMessage("Are you sure you want to  delete  this User?!");
        builderconfirm.setIcon(android.R.drawable.ic_dialog_alert);
        builderconfirm.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {



                userData=uSersArrayList.get(numberofcontextMenu);
                 USERID=userData.getId();
                 UserTYPE=userData.getTypeOfUser();

                DeleteUser();
              ///  deleteComment();
                uSersArrayList.remove(numberofcontextMenu);
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













    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater=new MenuInflater(this);
        inflater.inflate(R.menu.usermenu,menu);
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





    void GetAllUsers() {


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        ///////// the url to  webService
        String url = "http://192.168.1.9/connectfcis/v1/user/getUsers";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.d("responseAfterLogin", response);


                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray= jsonObject.getJSONArray("users");
                             //   Toast.makeText(ShowAllUsers.this, ""+jsonArray.length(), Toast.LENGTH_SHORT).show();
                            for (int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject object= (JSONObject) jsonArray.get(i);
                                // we need simple user class of 2 atts. firstName, type.
                                Users user=new Users (object.getString(UsersKeys.UserID),object.getString(UsersKeys.FirstName),object.getString(UsersKeys.TypeUser));

                              //  Toast.makeText(ShowAllUsers.this, ""+user.getFName(), Toast.LENGTH_SHORT).show();
                                uSersArrayList.add(user);
                            }
                           // Toast.makeText(ShowAllUsers.this, "uSersArrayList"+uSersArrayList.size(), Toast.LENGTH_SHORT).show();
                            for (int j=0;j<uSersArrayList.size();j++)
                            {
                                String FirstNamee=uSersArrayList.get(j).getFName();
                                String UserType=uSersArrayList.get(j).getTypeOfUser();
                                String UserDataa="FName :"+" "+FirstNamee+"\n"+"\n"+"UserTpye : "+" "+UserType;
                                arrayList.add(UserDataa);
                            }

                            ListView listView =(ListView)findViewById(R.id.allusers);

                            registerForContextMenu(listView);
                            adapter=new ArrayAdapter<String>(ShowAllUsers.this,android.R.layout.simple_list_item_1,arrayList);
                            listView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

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




                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }





    void DeleteUser() {


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        ///////// the url to  webService
        String url = "http://192.168.1.9/connectfcis/v1/user/deleteUser";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response);
//


                        } catch (JSONException e) {

                            e.printStackTrace();

                            String [] str=response.split(":");
                            String messgae=  str[2].replace('}',' '); messgae= str[2].replace('"',' ');


                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            //define params to be sent when connection opened
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("userID",USERID );


                params.put("type", UserTYPE);




                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }





}
