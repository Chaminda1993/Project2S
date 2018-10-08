package GUI;

public class ProductModel {
    private String proID;
    private String description;
    private String company;
    private String type;
    private String supplier;
    private double price;
    private int stock;
    private int stockMin;
    private int stockMax;

    public String getProID() {
        return proID;
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

    public int getStockMin() {
        return stockMin;
    }

    public int getStockMax() {
        return stockMax;
    }

    public ProductModel(String proID, String description, String company, String type, String supplier, double price, int stock, int stockMin, int stockMax) {

        this.proID = proID;
        this.description = description;
        this.company = company;
        this.type = type;
        this.supplier = supplier;
        this.price = price;
        this.stock = stock;
        this.stockMin = stockMin;
        this.stockMax = stockMax;
    }


}
