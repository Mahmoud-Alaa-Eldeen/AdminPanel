package msp.ramadan.adminpanel;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import msp.ramadan.adminpanel.MyClassea.Event;
import msp.ramadan.adminpanel.MyClassea.EventAdapter;
import msp.ramadan.adminpanel.MyClassea.EventKies;
import msp.ramadan.adminpanel.MyClassea.eventTable;

public class ShowAllEvents extends AppCompatActivity {
    RecyclerView eventsRecyclerView;
    EventAdapter adapter;
    Event event;
    int numberofcontextMenu;
    Dialog dialogDelete;
    ListView listView;
    ArrayList<Event> events=new ArrayList<>();
    List<eventTable> list;
    String keyOFEvent;
    eventTable table=new eventTable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_events);
       // stringRequest();

//        list=table.getallRules();
//        for (eventTable pro:list)
//        {
//            Event event=new Event(pro.eventTitle,pro.eventDescription,pro.creationDateAndTime,pro.startDate,pro.endDate,pro.eventImage);
//            events.add(event);
//        }
//        eventsRecyclerView = (RecyclerView) findViewById(R.id.event_list);
//        registerForContextMenu(eventsRecyclerView);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        eventsRecyclerView.setLayoutManager(layoutManager);
        listView= (ListView) findViewById(R.id.allevents);
        registerForContextMenu(listView);
        stringRequest();


        AlertDialog.Builder builderconfirm=new AlertDialog.Builder(this);
        builderconfirm.setTitle("Delete:?");
        builderconfirm.setMessage("Are you sure you want to  delete  this Event");
        builderconfirm.setIcon(android.R.drawable.ic_dialog_alert);
        builderconfirm.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {



                event=events.get(numberofcontextMenu);
             keyOFEvent=event.getEventTitle();
               events.remove(numberofcontextMenu);
                adapter.notifyDataSetChanged();
                stringdeleteitem();
//               /*
//               Write code to delete in webService.....
//                */

            }
        });
        builderconfirm.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialogDelete=builderconfirm.create();
    }
    /*void stringRequest() {



        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        ///////// the url to  webService
        String url = "http://192.168.43.128/connectfcis/v1/user/getEvents";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.d("responseAfterInserting", response);

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray= jsonObject.getJSONArray("events");
                            Toast.makeText(ShowAllEvents.this, ""+jsonArray.length(), Toast.LENGTH_SHORT).show();
                            for (int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject object= (JSONObject) jsonArray.get(i);
                              //  Event event=new Event(object.getString(EventKies.eventName),object.getString(EventKies.eventDesc),object.getString(EventKies.creatTime),object.getString(EventKies.startTime),object.getString(EventKies.finishTime),object.getString(EventKies.eventImage),object.getInt(EventKies.eventKey));
                                events.add(event);
                            }
                            //       Toast.makeText(HomeActivity.this, "MyEvent"+events.size(), Toast.LENGTH_SHORT).show();

                            eventsRecyclerView = (RecyclerView) findViewById(R.id.event_list);
                            registerForContextMenu(eventsRecyclerView);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            eventsRecyclerView.setLayoutManager(layoutManager);

                            adapter = new EventAdapter(events, getApplicationContext(),ShowAllEvents.this);

                            eventsRecyclerView.setAdapter(adapter);
                            eventsRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                            adapter.notifyDataSetChanged();
                            /////////////////////set to the ui of home page
//                            for(int i =0;i<events.size();i++) {
//                                Toast.makeText(HomeActivity.this, "L22222", Toast.LENGTH_SHORT).show();
//                                Toast.makeText(getApplicationContext(),events.get(i).getEventDescription() , Toast.LENGTH_LONG).show();
//                                Toast.makeText(getApplicationContext(),events.get(i).getEventTitle() , Toast.LENGTH_LONG).show();
//                                Toast.makeText(getApplicationContext(),events.get(i).getStartDate() , Toast.LENGTH_LONG).show();
//                                Toast.makeText(getApplicationContext(),events.get(i).getCreationDateAndTime() ,Toast.LENGTH_LONG).show();
//                                Toast.makeText(getApplicationContext(),events.get(i).getEndDate() , Toast.LENGTH_LONG).show();
//                                Toast.makeText(getApplicationContext(),events.get(i).getEventImage() , Toast.LENGTH_LONG).show();
//
//                                if (events.get(i).getEventImage()==null)
//
//                                {///////////////////////////////////////////ijjjjjjjjjjjjjjjjj
//                                    // event_image.
//                                    // hide the imageview
//                                    //else, show it in the imageview
//                                }
//
//                            }

                            //here if returned not error, then registered correct
                            if (!jsonObject.getBoolean("Error")) {

                                //       Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                                //     intent.putExtra("name", "Ahmed");
//                                startActivity(intent);

                            }

//                            Contact contact = new Gson().fromJson(jsonObject.toString(),Contact.class);

//                            DisplayContext.Type type = new TypeToken<List<Contact>>() {
//                            }.getType();
//
//                            List<Contact> contacts = new Gson().fromJson(jsonObject.getJSONArray("contacts").toString(), type);
//
//                            Log.d("response", contacts.get(0).getPhoneData().getMobileNumber());
//                            Log.d("response", contacts.get(0).getName());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("response", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("responce", "hat didn't work!");
            }
        }) {

            //define params to be sent when connection opened
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
//                String firstname = firstNameEditText.getText().toString();
//                String lname = secondNameEditText.getText().toString();
//                String email = emailEditText.getText().toString();
//                String pass = passswordEditText.getText().toString();
//                // fname is the name in thw webrevice
//                params.put("fName", firstname);
//                params.put("lName", lname);
//                params.put("email", email);
//                params.put("password", pass);


                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }*/

    public ShowAllEvents() {
        super();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater=new MenuInflater(this);
        inflater.inflate(R.menu.menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        numberofcontextMenu=info.position;
        switch (item.getItemId())
        {
            case R.id.Update:
                event=events.get(numberofcontextMenu);
                String startDate=event.getStartDate();
                String EndDate=event.getEndDate();
                String ImageEvent=event.getEventImage();
                String EventDesc=event.getEventDescription();
                String EventTitle=event.getEventTitle();
                String EventCreation=event.getCreationDateAndTime();
               // int keyEventItem=event.getKeyofItem();

                Intent J=new Intent(ShowAllEvents.this,updateEvent.class);
                J.putExtra("startD",startDate);
                J.putExtra("endD",EndDate);
                J.putExtra("ImageE",ImageEvent);
                J.putExtra("DescE",EventDesc);
                J.putExtra("TitleE",EventTitle);
                J.putExtra("evevntc",EventCreation);
                startActivity(J);

                return true;
            case R.id.cooment:
                event=events.get(numberofcontextMenu);
                String EventTit=event.getEventTitle();
                Intent i=new Intent(ShowAllEvents.this,Comments.class);

                i.putExtra("TitleE",EventTit);
                startActivity(i);

                return true;
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
        String url = "http://192.168.1.9/connectfcis/v1/user/getEvents";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.d("responseAfterInserting", response);

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray= jsonObject.getJSONArray("events");
                           // Toast.makeText(HomeActivity.this, ""+jsonArray.length(), Toast.LENGTH_SHORT).show();
                            for (int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject object= (JSONObject) jsonArray.get(i);
                                Event event=new Event(object.getString(EventKies.eventName),object.getString(EventKies.eventDesc),object.getString(EventKies.creatTime),object.getString(EventKies.startTime),object.getString(EventKies.finishTime),object.getString(EventKies.eventImage));
                                events.add(event);
                            }


                            adapter = new EventAdapter(events, getApplicationContext(),ShowAllEvents.this);

                            listView.setAdapter(adapter);
                            //eventsRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                            adapter.notifyDataSetChanged();



                            //       Toast.makeText(HomeActivity.this, "MyEvent"+events.size(), Toast.LENGTH_SHORT).show();

//                            eventsRecyclerView = (RecyclerView) findViewById(R.id.event_list);
//                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//                            eventsRecyclerView.setLayoutManager(layoutManager);
//
//                            adapter = new EventAdapter(events, getApplicationContext(),HomeActivity.this);
//
//                            eventsRecyclerView.setAdapter(adapter);
//                            eventsRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
//                            adapter.notifyDataSetChanged();
                            /////////////////////set to the ui of home page
//                            for(int i =0;i<events.size();i++) {
//                                Toast.makeText(HomeActivity.this, "L22222", Toast.LENGTH_SHORT).show();
//                                Toast.makeText(getApplicationContext(),events.get(i).getEventDescription() , Toast.LENGTH_LONG).show();
//                                Toast.makeText(getApplicationContext(),events.get(i).getEventTitle() , Toast.LENGTH_LONG).show();
//                                Toast.makeText(getApplicationContext(),events.get(i).getStartDate() , Toast.LENGTH_LONG).show();
//                                Toast.makeText(getApplicationContext(),events.get(i).getCreationDateAndTime() ,Toast.LENGTH_LONG).show();
//                                Toast.makeText(getApplicationContext(),events.get(i).getEndDate() , Toast.LENGTH_LONG).show();
//                                Toast.makeText(getApplicationContext(),events.get(i).getEventImage() , Toast.LENGTH_LONG).show();
//
//                                if (events.get(i).getEventImage()==null)
//
//                                {///////////////////////////////////////////ijjjjjjjjjjjjjjjjj
//                                    // event_image.
//                                    // hide the imageview
//                                    //else, show it in the imageview
//                                }
//
//                            }

                            //here if returned not error, then registered correct
                            if (!jsonObject.getBoolean("Error")) {

                                //       Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                                //     intent.putExtra("name", "Ahmed");
//                                startActivity(intent);

                            }

//                            Contact contact = new Gson().fromJson(jsonObject.toString(),Contact.class);

//                            DisplayContext.Type type = new TypeToken<List<Contact>>() {
//                            }.getType();
//
//                            List<Contact> contacts = new Gson().fromJson(jsonObject.getJSONArray("contacts").toString(), type);
//
//                            Log.d("response", contacts.get(0).getPhoneData().getMobileNumber());
//                            Log.d("response", contacts.get(0).getName());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("response", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("responce", "hat didn't work!");
            }
        }) {

            //define params to be sent when connection opened
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
//                String firstname = firstNameEditText.getText().toString();
//                String lname = secondNameEditText.getText().toString();
//                String email = emailEditText.getText().toString();
//                String pass = passswordEditText.getText().toString();
//                // fname is the name in thw webrevice
//                params.put("fName", firstname);
//                params.put("lName", lname);
//                params.put("email", email);
//                params.put("password", pass);


                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    void stringdeleteitem() {


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        ///////// the url to  webService
        String url = "http://192.168.1.9/connectfcis/v1/user/deleteEvent";

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

                params.put("eventName",keyOFEvent);
                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


}
