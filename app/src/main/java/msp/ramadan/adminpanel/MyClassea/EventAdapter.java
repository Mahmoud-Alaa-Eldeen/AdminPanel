package msp.ramadan.adminpanel.MyClassea;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import msp.ramadan.adminpanel.R;
import msp.ramadan.adminpanel.ShowAllEvents;

/**
 * Created by ramadan on 4/7/2017.
 */
public class EventAdapter extends BaseAdapter{


    ArrayList<Event> eventsList;
    Context context;
    ShowAllEvents showAllEvents;
     private LayoutInflater inf;
    int indexx=0;
    Event event=new Event();
            ImageView imageevent;
        TextView adminandcreateTime;
        TextView titleevet;
        TextView titleDesc;
        TextView startandFinshTime;

    public EventAdapter(ArrayList<Event> eventsList, Context context, ShowAllEvents homeActivity) {
        this.eventsList = eventsList;
        this.context = context;
        this.showAllEvents = homeActivity;
            inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return eventsList.size();
    }

    @Override
    public Object getItem(int position) {
        return eventsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View v = inf.inflate(R.layout.event_row, null);


                    imageevent= (ImageView) v.findViewById(R.id.event_image);
            adminandcreateTime= (TextView) v.findViewById(R.id.event_initiator);
            titleevet= (TextView) v.findViewById(R.id.event_title);

            titleDesc= (TextView) v.findViewById(R.id.event_desc);
            startandFinshTime= (TextView) v.findViewById(R.id.event_start_end_date);

                event=eventsList.get(position);

        adminandcreateTime.setText("Admin Created the event at: "+event.getCreationDateAndTime());
        titleDesc.setText(event.getEventDescription());
        titleevet.setText(event.getEventTitle());
        startandFinshTime.setText("From: "+event.getStartDate()+" To: "+event.getEndDate());

        if(event.getEventImage()!=null)
        {

            imageevent.setVisibility(View.VISIBLE);
            byte[]arrayofByte= Base64.decode(event.getEventImage(), Base64.DEFAULT);
            Bitmap bitmap1= BitmapFactory.decodeByteArray(arrayofByte,0,arrayofByte.length);
            imageevent.setImageBitmap(bitmap1);

        }


        return v;
    }
//
//
//    ArrayList<Event> eventsList;
//    Context context;
//    ShowAllEvents showAllEvents;
//
//    int indexx=0;
//    Event event=new Event();
//    public EventAdapter(ArrayList<Event> eventsList, Context context, ShowAllEvents homeActivity) {
//        this.eventsList = eventsList;
//        this.context = context;
//        this.showAllEvents = homeActivity;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.event_row,parent,false);
//        ViewHolder viewHolder=new ViewHolder(view);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(final ViewHolder holder, int position) {
//     // indexx=position;
//        event=eventsList.get(position);
//
//        holder.adminandcreateTime.setText("Admin Created the event at: "+event.getCreationDateAndTime());
//        holder.titleDesc.setText(event.getEventDescription());
//        holder.titleevet.setText(event.getEventTitle());
//        holder.startandFinshTime.setText("From: "+event.getStartDate()+" To: "+event.getEndDate());
//
////        holder.listView.setTag(position);
////        holder.imageButton.setTag(position);
////        adapter=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1);
////        ListView listView2= (ListView) holder.listView.getTag();
////        listView2.setAdapter(adapter);
//        if(event.getEventImage()!=null)
//        {
//
//            holder.imageevent.setVisibility(View.VISIBLE);
//            byte[]arrayofByte= Base64.decode(event.getEventImage(), Base64.DEFAULT);
//            Bitmap bitmap1= BitmapFactory.decodeByteArray(arrayofByte,0,arrayofByte.length);
//            holder.imageevent.setImageBitmap(bitmap1);
//
//        }
//        else if (event.getEventImage().equals("one"))
//        {
//            //holder.imageevent.setVisibility(View.INVISIBLE);
//            holder.imageevent.setImageResource(R.drawable.ic_event);
//        }
//
//
////
////        holder.imageButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////
////                String comment=holder.editText.getText().toString();
////                Toast.makeText(context,comment, Toast.LENGTH_SHORT).show();
////
////                adapter.add(comment);
////                adapter.notifyDataSetChanged();
////                holder.editText.getText().clear();
////            }
////        });
//
//
//    }
//
//    /*
//    private Button commentButton;
//    private Button confirmCommentButton;
//    private EditText eventCommentEditText;
//    private ListView eventCommentsListView;
//      eventCommentEditText = (EditText) findViewById(R.id.event_comment);
//        eventCommentsListView = (ListView) findViewById(R.id.event_comments_list);
//        commentButton = (Button) findViewById(R.id.event_comment_btn);
//     */
//
//    @Override
//    public int getItemCount() {
//        return eventsList.size();
//    }
//
//    class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView imageevent;
//        TextView adminandcreateTime;
//        TextView titleevet;
//        TextView titleDesc;
//        TextView startandFinshTime;
//        EditText editText;
//        ImageButton imageButton;
//        ListView listView;
//
//        public ViewHolder(View layout) {
//            super(layout);
//
//            imageevent= (ImageView) layout.findViewById(R.id.event_image);
//            adminandcreateTime= (TextView) layout.findViewById(R.id.event_initiator);
//            titleevet= (TextView) layout.findViewById(R.id.event_title);
//
//            titleDesc= (TextView) layout.findViewById(R.id.event_desc);
//            startandFinshTime= (TextView) layout.findViewById(R.id.event_start_end_date);
////            imageButton=(ImageButton)layout.findViewById(R.id.eventCommentSubmitBtn);
////            listView=(ListView)layout.findViewById(R.id.event_comments_list);
////            editText=(EditText)layout.findViewById(R.id.event_comment);
//        }
//    }
}
