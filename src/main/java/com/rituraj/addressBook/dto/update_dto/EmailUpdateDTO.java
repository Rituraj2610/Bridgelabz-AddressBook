package com.rituraj.addressBook.dto.update_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailUpdateDTO {
    private int person_id;
    private String email_add;
}
