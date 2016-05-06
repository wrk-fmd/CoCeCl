package it.fmd.cocecl.dataStorage;

import java.io.Serializable;

/*
App User
 */
public class UserData implements Serializable {
    private static final long serialVersionUID = 1L;

    String UserFName; //firstname
    String UserLName; //lastname
    String UserPwd;
    String UserID;
    String UserGCMID;
    String UserEmail;
    String UserPhnnbr;

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getUserLName() {
        return UserLName;
    }

    public void setUserLName(String userLName) {
        UserLName = userLName;
    }

    public String getUserFName() {
        return UserFName;
    }

    public void setUserFName(String userFName) {
        UserFName = userFName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserGCMID() {
        return UserGCMID;
    }

    public void setUserGCMID(String userGCMID) {
        UserGCMID = userGCMID;
    }

    public String getUserPhnnbr() {
        return UserPhnnbr;
    }

    public void setUserPhnnbr(String userPhnnbr) {
        UserPhnnbr = userPhnnbr;
    }

    public String getUserPwd() {
        return UserPwd;
    }

    public void setUserPwd(String userPwd) {
        UserPwd = userPwd;
    }
}
