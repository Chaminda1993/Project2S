package GUI;

public class DashTblModal {
    private String pid,pName;
    private int stock,minLvl;

    public DashTblModal(String pid, String name, int stock, int minLvl) {
        this.pid = pid;
        this.pName = name;
        this.stock = stock;
        this.minLvl = minLvl;
    }

    public String getPid() {
        return pid;
    }

    public String getPName() {
        return pName;
    }

    public int getStock() {
        return stock;
    }

    public int getMinLvl() {
        return minLvl;
    }
}
