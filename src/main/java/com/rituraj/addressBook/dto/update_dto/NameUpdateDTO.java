package com.rituraj.addressBook.dto.update_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NameUpdateDTO {
    private int id;
    private String newFName;
    private String newLName;
}
