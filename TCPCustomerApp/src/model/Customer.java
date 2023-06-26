package model;

import java.io.Serializable;

public class Customer implements Serializable {

    // ATTRIBUTE ARE PRIVATE
    private int customerId;
    private String customerName;

    // GETTER AND SETTERS FOR ALL ATTRIBUTE
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

}
