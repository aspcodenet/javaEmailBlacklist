package se.systementor.blacklist.controllers;

public class BlacklistStatus {
    private boolean isOk;
    private String statusText;
    public boolean isOk() {
        return isOk;
    }
    public void setOk(boolean isOk) {
        this.isOk = isOk;
    }
    public String getStatusText() {
        return statusText;
    }
    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }
    
}
