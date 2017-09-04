package msp.ramadan.adminpanel.MyClassea;

/**
 * Created by ramadan on 6/7/2017.
 */
public class Users {
    String id;
    String FName;
    String TypeOfUser;

    public Users() {
    }

    public Users(String id, String FName, String typeOfUser) {
        this.id = id;
        this.FName = FName;
        TypeOfUser = typeOfUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getTypeOfUser() {
        return TypeOfUser;
    }

    public void setTypeOfUser(String typeOfUser) {
        TypeOfUser = typeOfUser;
    }
}
