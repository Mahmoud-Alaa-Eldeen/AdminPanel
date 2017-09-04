package msp.ramadan.adminpanel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import msp.ramadan.adminpanel.MyClassea.UserPicture;
import msp.ramadan.adminpanel.MyClassea.eventTable;

public class EventDetials extends AppCompatActivity {

    EditText startTime;
    EditText endTime;
    EditText Desc;
    EditText Title;
    ImageView imageEventt;
    Button SaveData;
    String startDate;
    String EndDate;
    String EventDesc;
    String EventTitle;
    String baseofImageOne;
    String DCreationTime;
    String ImageEvent;
    private static final int SELECT_SINGLE_PICTUREone = 101;
    public static final String IMAGE_TYPE = "image/*";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detials);

        baseofImageOne="one";
        startTime = (EditText) findViewById(R.id.event_startTime);
        endTime = (EditText) findViewById(R.id.event_endtime);
        Title = (EditText) findViewById(R.id.event_title);
        Desc = (EditText) findViewById(R.id.event_desc);
        imageEventt = (ImageView) findViewById(R.id.imgEv);
        SaveData = (Button) findViewById(R.id.button3);

        imageEventt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType(IMAGE_TYPE);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        getString(R.string.select_pictureone)), SELECT_SINGLE_PICTUREone);
            }
        });

        SaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final java.util.Calendar c= java.util.Calendar.getInstance();
                int year=c.get(java.util.Calendar.YEAR);
                int month=c.get(java.util.Calendar.MONTH);
                int day=c.get(java.util.Calendar.DAY_OF_MONTH);
                int hour=c.get(Calendar.HOUR);
                int minute=c.get(Calendar.MINUTE);
                DCreationTime=year+"/"+(month+1)+"/"+day+" "+hour+":"+minute;
                startDate = startTime.getText().toString();
                EndDate = endTime.getText().toString();

                 ImageEvent = baseofImageOne;

                EventDesc = Desc.getText().toString();

                EventTitle = Title.getText().toString();

                stringRequest();

             //   Toast.makeText(EventDetials.this, "Done", Toast.LENGTH_SHORT).show();









                // key to update data .....
                //    keyEventItem
            }
        });





    }






    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_SINGLE_PICTUREone) {

                Uri selectedImageUri = data.getData();
                try {
                    imageEventt.setImageBitmap(new UserPicture(selectedImageUri, getContentResolver()).getBitmap());
                    Bitmap bitmap = new UserPicture(selectedImageUri, getContentResolver()).getBitmap();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                    byte[] bytes = byteArrayOutputStream.toByteArray();
                    baseofImageOne = Base64.encodeToString(bytes, Base64.DEFAULT);

//                    byte[]arrayofByte=Base64.decode(baseofImage,Base64.DEFAULT);
//                    Bitmap bitmap1= BitmapFactory.decodeByteArray(arrayofByte,0,arrayofByte.length);
//                    selectedImagePreview.setImageBitmap(bitmap1);
//                    Toast.makeText(MainActivity.this, ""+baseofImage, Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    Log.e(updateEvent.class.getSimpleName(), "Failed to load image", e);
                }

            }
        }
    }


// 1 9  7
/////

    void stringRequest() {


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        ///////// the url to  webService
        String url = "http://192.168.1.9/connectfcis/v1/user/addEvent";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.d("responseAfterLogin", response);


                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(EventDetials.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            /*Intent intent=new Intent(EventDetials.this,HomeActivity.class);
                            startActivity(intent);
                            finish();*/
//                            Toast.makeText(EventDetials.this, "error in try", Toast.LENGTH_SHORT).show();


                        } catch (JSONException e) {
                            Log.d("responseAfterLogin", response);
                            e.printStackTrace();
                            //     Toast.makeText(LoginActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            String [] str=response.split(":");
                          String messgae=  str[2].replace('}',' '); messgae= messgae.replace('"',' ');

                            Toast.makeText(EventDetials.this,messgae , Toast.LENGTH_SHORT).show();
                //            Toast.makeText(EventDetials.this, "error in catch", Toast.LENGTH_SHORT).show();

                        }
                        // Toast.makeText(LoginActivity.this, , Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("responce", "hat didn't work!");
                Toast.makeText(EventDetials.this, "error! check Network or try again ", Toast.LENGTH_SHORT).show();
            }
        }) {

            //define params to be sent when connection opened
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("eventDesc", EventDesc);
                params.put("eventImage", ImageEvent );
                params.put("creatTime", DCreationTime );
                params.put("startTime", startDate);
                params.put("finishTime",EndDate );
                params.put("eventName",EventTitle);

                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    /////

}
