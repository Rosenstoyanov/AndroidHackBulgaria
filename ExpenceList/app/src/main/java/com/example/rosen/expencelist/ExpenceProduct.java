package com.example.rosen.expencelist;

/**
 * Created by rosen on 22.11.14.
 */
public class ExpenceProduct {
    private String label;
    private String price;
    ExpenceProduct(String label, String price)
    {
        this.setLabel(label);
        this.setPrice(price);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
