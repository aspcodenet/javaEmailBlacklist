package se.systementor.DTO;

public class UpdatePersonDTO {
    private String name;
    private boolean isOk;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isOk() {
        return isOk;
    }
    public void setOk(boolean isOk) {
        this.isOk = isOk;
    }
}
