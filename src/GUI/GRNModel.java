package GUI;

public class GRNModel {

    String id,description,type,sNo,sName,user,grnNo,date,company;
    double price,amount,grAmount,discountSub,discountFull;
    int quantity,stock,arrId;


    public String getGrnNo() {
        return grnNo;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getArrId() {
        return arrId;
    }

    public void setArrId(int arrId) {
        this.arrId = arrId;
    }

    public void setGrnNo(String grnNo) {
        this.grnNo = grnNo;
    }

    public String getId() {
        return id;

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getsNo() {
        return sNo;
    }

    public void setsNo(String sNo) {
        this.sNo = sNo;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getGrAmount() {
        return grAmount;
    }

    public void setGrAmount(double grAmount) {
        this.grAmount = grAmount;
    }

    public double getDiscountSub() {
        return discountSub;
    }

    public void setDiscountSub(double discountSub) {
        this.discountSub = discountSub;
    }

    public double getDiscountFull() {
        return discountFull;
    }

    public void setDiscountFull(double discountFull) {
        this.discountFull = discountFull;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
