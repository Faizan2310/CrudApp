package com.crud.crudApp.Entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import  java.util.Calendar;

@Entity
@Table(name="actor")
public class Actor {
    @Id
    @Column(name = "actor_id")
    private Short id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name="last_update")
    private Calendar lastUpdate;

    public short getId(){
        return id;
    }
    public void setId(Short id){
        this.id = id;
    }
    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String first_name){
        this.firstName = firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public Calendar getLastUpdate(){
        return lastUpdate;
    }
    public void setLastUpdate(Calendar lastUpdate){
        this.lastUpdate = lastUpdate;
    }

}
