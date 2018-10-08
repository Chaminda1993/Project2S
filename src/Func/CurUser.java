package Func;

public class CurUser {
    private String userID,userName,userType,userEmail;

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserType() {
        return userType;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void clear(){
        userID="";
        userName="";
        userType="";
        userEmail="";
    }
}
