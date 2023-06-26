package server.controller;

import model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerData{
	
    private List<Customer> customerList;

    public CustomerData() {
        customerList = new ArrayList<>();
        createSampleCustomers();
    }

    private void createSampleCustomers() {
        // Method 1: Create a list of samples of customer data
        // The list will contain 10 customers

        // Sample data for demonstration purposes
        Customer customer1 = new Customer();
        customer1.setCustomerId(1);
        customer1.setCustomerName("John Doe");
        customerList.add(customer1);

        Customer customer2 = new Customer();
        customer2.setCustomerId(2);
        customer2.setCustomerName("Jane Smith");
        customerList.add(customer2);

        Customer customer3 = new Customer();
        customer3.setCustomerId(3);
        customer3.setCustomerName("Jonion Daniel");
        customerList.add(customer3);

        Customer customer4 = new Customer();
        customer4.setCustomerId(4);
        customer4.setCustomerName("Jam Rose");
        customerList.add(customer4);
        
        Customer customer5 = new Customer();
        customer5.setCustomerId(5);
        customer5.setCustomerName("Jessi Deri");
        customerList.add(customer5);

        Customer customer6 = new Customer();
        customer6.setCustomerId(6);
        customer6.setCustomerName("Jerry Siew");
        customerList.add(customer6);
        
        Customer customer7 = new Customer();
        customer7.setCustomerId(7);
        customer7.setCustomerName("Joah Dora");
        customerList.add(customer7);

        Customer customer8 = new Customer();
        customer8.setCustomerId(8);
        customer8.setCustomerName("Junny Shan");
        customerList.add(customer8);
        
        Customer customer9 = new Customer();
        customer9.setCustomerId(9);
        customer9.setCustomerName("Jirisan Demi");
        customerList.add(customer9);

        Customer customer10 = new Customer();
        customer10.setCustomerId(10);
        customer10.setCustomerName("Jabby Smeath");
        customerList.add(customer10);
    }

    public Customer searchCustomerByName(String name) {
        // Method 2: Search a customer based on the customer's name from a list of customers
        // The method will return a Customer object if the name exists, otherwise null

        for (Customer customer : customerList) {
            if (customer.getCustomerName().toLowerCase().contains(name.toLowerCase())) {
                return customer;
            }
        }

        return null;
    }
    public Customer searchCustomerById(int id) {
        // Method 3: Search a customer based on the customer's id from a list of customers
        // The method will return a Customer object if the id exists, otherwise null

        for (Customer customer : customerList) {
            if (customer.getCustomerId() == id) {
                return customer;
            }
        }

        return null;
    }

    public List<Customer> getAllCustomers() {
        // Method 4: Return a list of customers
        return customerList;
    }
}