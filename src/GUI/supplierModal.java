package GUI;

public class supplierModal {

    private String name;
    private String email;
    private String telNo;
    private String sid;
    private String address;
    private double balance;

    public supplierModal(String sid,String name,String address,String telNo,double balance,String email) {
        this.name = name;
        this.email = email;
        this.telNo = telNo;
        this.address=address;
        this.sid=sid;
        this.balance=balance;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getTelNo() {
        return telNo;
    }

    public String getSid() {
        return sid;
    }

    public String getAddress() {
        return address;
    }

    public double getBalance() {
        return balance;
    }
}
