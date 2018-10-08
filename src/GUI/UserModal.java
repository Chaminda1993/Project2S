package GUI;

public class UserModal {
    private String uid;
    private String name;
    private String address;
    private String telNo;
    private String type;
    private String nic;
    private String email;

    public UserModal(String uid, String name, String address, String telNo, String type, String nic, String email) {
        this.uid = uid;
        this.name = name;
        this.address = address;
        this.telNo = telNo;
        this.type = type;
        this.nic = nic;
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTelNo() {
        return telNo;
    }

    public String getType() {
        return type;
    }

    public String getNic() {
        return nic;
    }

    public String getEmail() {
        return email;
    }
}
