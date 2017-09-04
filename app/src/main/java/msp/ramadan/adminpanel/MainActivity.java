package msp.ramadan.adminpanel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button AddEvent;
    Button ShowEvents;
    Button ShowUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddEvent=(Button)findViewById(R.id.button);
        ShowEvents=(Button)findViewById(R.id.button2);
        ShowUsers=(Button)findViewById(R.id.button3);

        AddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,EventDetials.class);
                startActivity(i);
            }
        });
        ShowEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,ShowAllEvents.class);
                startActivity(i);
            }
        });
        ShowUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,ShowAllUsers.class);
                startActivity(i);
            }
        });
    }
}
