package com.laptrinhweb.model;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fistname;
    private String lastname;
    private String address;
    private int age;


    public Customer(){

    }

    public Customer(String fistname, String lastname,String address,int age) {
        this.fistname = fistname;
        this.lastname = lastname;
        this.address = address;
        this.age = age;

    }

    @Override
    public String toString() {
        return String.format("Customer[id=%d, firstName='%s', lastName='%s',address='%s',age=%d]", id, fistname, lastname,address,age);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFistname() {
        return fistname;
    }

    public void setFistname(String fistname) {
        this.fistname = fistname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
