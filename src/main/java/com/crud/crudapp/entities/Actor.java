package com.crud.crudapp.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import  java.util.Calendar;

@Entity
@Table(name="actor")
@NoArgsConstructor
@AllArgsConstructor
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Short id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name="last_update")
    private Calendar lastUpdate;

    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
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
