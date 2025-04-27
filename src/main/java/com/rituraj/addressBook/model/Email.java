package com.rituraj.addressBook.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Email {
    @Id
    @JoinColumn(name = "+")
    private int person_id;
    private String email_add;
}
