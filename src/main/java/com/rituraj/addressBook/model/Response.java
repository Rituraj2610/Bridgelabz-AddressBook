package com.rituraj.addressBook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Response<T> {
    private String status;
    private String message;
    private T data;
    private LocalDateTime time;
}
