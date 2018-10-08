package GUI;

public class productModal {

    String pid,description,company,type,supplier;
    double price,amount;
    int stock,minStock,maxStock;


    public productModal(String pid, String description, String company, String type, String supplier, double price, int stock, int minStock, int maxStock) {
        this.pid = pid;
        this.description = description;
        this.company = company;
        this.type = type;
        this.supplier = supplier;
        this.price = price;
        this.stock = stock;
        this.minStock = minStock;
        this.maxStock = maxStock;
    }

    public String getPid() {
        return pid;
    }

    public String getDescription() {
        return description;
    }

    public String getCompany() {
        return company;
    }

    public String getType() {
        return type;
    }

    public String getSupplier() {
        return supplier;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getMinStock() {
        return minStock;
    }

    public int getMaxStock() {
        return maxStock;
    }

    public void setAmount(double amount){
        this.amount=amount;
    }
    public double getAmount(){
        return amount;
    }
}
