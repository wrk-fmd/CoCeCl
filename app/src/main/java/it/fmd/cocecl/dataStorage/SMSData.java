package it.fmd.cocecl.dataStorage;

/**
 * Alert SMS Data
 */

public class SMSData {

    String phonenbr;
    String smscontent;
    String smssender;

    public String getPhonenbr() {
        return phonenbr;
    }

    public void setPhonenbr(String phonenbr) {
        this.phonenbr = phonenbr;
    }

    public String getSmscontent() {
        return smscontent;
    }

    public void setSmscontent(String smscontent) {
        this.smscontent = smscontent;
    }

    public String getSmssender() {
        return smssender;
    }

    public void setSmssender(String smssender) {
        this.smssender = smssender;
    }
}
