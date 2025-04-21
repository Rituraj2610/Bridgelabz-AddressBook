package com.rituraj.addressBook.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String fname;
    private String lname;
    private Boolean isActive;

    @PrePersist
    private void setDefaultValue(){
        if(isActive == null){
            isActive = true;
        }
    }
}
