package de.androidcrypto.wertpapierkurse;

public class StockModelV1 {

    String isin;
    String isinName;
    boolean active;
    String group;

    public StockModelV1(String isin, String isinName, boolean active, String group) {
        this.isin = isin;
        this.isinName = isinName;
        this.active = active;
        this.group = group;
    }

    public String getIsin() {
        return isin;
    }

    public String getIsinName() {
        return isinName;
    }

    public boolean getActive() {
        return active;
    }

    public String getGroup() {
        return group;
    }
}