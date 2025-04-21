package com.rituraj.addressBook.dto.insertion_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactAddDTO {
    private String fname;
    private String lname;
    private String street;
    private String city;
    private String zip;
    private String state;
    private String email_add;
    private String phone_number;
    private String value;
}
