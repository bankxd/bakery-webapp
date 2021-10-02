package com.bakerysystem.Model;

public class Customer {

    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String telephonehome;
    private String telephonemobile;
    private String ID;
    private String addressId;
    private String password;
    
    
    public Customer() {}

    public Customer(int customerId, String firstName, String lastName, String email, String telephonehome, String telephonemobile, String ID, String addressID, String password ){
       this(firstName, lastName, email, telephonehome, telephonemobile, ID, addressID, password);
       this.customerId = customerId;
    }
    
    public Customer(String firstName, String lastName, String email, String telephonehome, String telephonemobile, String ID, String addressId, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephonehome = telephonehome;
        this.telephonemobile = telephonemobile;
        this.ID = ID;
        this.addressId = addressId;
        this.password = password;
    }
    
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephonehome() {
        return telephonehome;
    }

    public void setTelephonehome(String telephonehome) {
        this.telephonehome = telephonehome;
    }

    public String getTelephonemobile() {
        return telephonemobile;
    }

    public void setTelephonemobile(String telephonemobile) {
        this.telephonemobile = telephonemobile;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
