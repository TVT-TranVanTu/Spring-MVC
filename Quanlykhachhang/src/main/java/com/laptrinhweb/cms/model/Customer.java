package com.laptrinhweb.cms.model;


import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fistname;
    private String lastname;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;
    public Customer(){

    }
    public Customer(String fistname,String lastname){
        this.fistname= fistname;
        this.lastname= lastname;
    }
    @Override
    public String toString(){
    return String.format("Customer[id=%d,fistname = '%s', lastname ='%s' ]",id,fistname,lastname);
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

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}
