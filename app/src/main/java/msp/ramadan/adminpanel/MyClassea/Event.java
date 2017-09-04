package msp.ramadan.adminpanel.MyClassea;

/**
 * Created by Khaled Ahmed on 2/10/2017.
 */
public class Event {
    private String eventTitle;
    private String eventDescription;
    private String creationDateAndTime;
    private String startDate;
    private String endDate;
    private String eventImage;

    public Event() {

    }

    public Event(String eventTitle, String eventDescription, String creationDateAndTime, String startDate, String endDate, String eventImage) {
        this.eventTitle = eventTitle;
        this.eventDescription = eventDescription;
        this.creationDateAndTime = creationDateAndTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventImage = eventImage;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getCreationDateAndTime() {
        return creationDateAndTime;
    }

    public void setCreationDateAndTime(String creationDateAndTime) {
        this.creationDateAndTime = creationDateAndTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }

}
