package msp.ramadan.adminpanel.MyClassea;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;

import java.util.List;

/**
 * Created by ramadan on 5/30/2017.
 */
@Table(name = "eventT")
public class eventTable extends Model {
    @Column(name ="title")

    public String eventTitle;
    @Column(name ="desc")

    public String eventDescription;
    @Column(name ="createT")

    public String creationDateAndTime;
    @Column(name ="startT")

    public String startDate;
    @Column(name ="EndT")

    public String endDate;
    @Column(name ="Img")

    public String eventImage;

    public eventTable()
    {
        super();
    }


    public List<eventTable> getallRules()
    {
        return new Select().from(eventTable.class).execute();
    }


//    public  void deleteItem(String Itemdata)
//    {
//        try {
//            new Delete()
//                    .from(eventTable.class)
//                    .where("game = ?",
//                            Itemdata).execute();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

//    public void updateItem(String Fitness,String Sp,String TA,String Wei,String Ga,String GameName)
//    {
//        new Update(Rules.class).set("fit  = ?," +
//                "speed = ?," + "tall = ?," + "weight = ?," +
//                "game = ? ",Fitness,Sp,TA,Wei,Ga).where("game = ?",GameName).execute();
//    }
//


}
