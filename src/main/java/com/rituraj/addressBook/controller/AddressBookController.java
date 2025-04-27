package com.rituraj.addressBook.controller;

import com.rituraj.addressBook.dto.insertion_dto.ContactAddDTO;
import com.rituraj.addressBook.dto.update_dto.EmailUpdateDTO;
import com.rituraj.addressBook.dto.update_dto.NameUpdateDTO;
import com.rituraj.addressBook.dto.update_dto.PhoneUpdateDTO;
import com.rituraj.addressBook.model.Person;
import com.rituraj.addressBook.model.Response;
import com.rituraj.addressBook.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/address/book")
public class AddressBookController {

    private AddressBookService addressBookService;

    public AddressBookController(AddressBookService addressBookService) {
        this.addressBookService = addressBookService;
    }

    @GetMapping("/contacts")
    public ResponseEntity<Response> getAllPerson(){
        return addressBookService.getAllPerson();
    }

    @PostMapping("/contacts")
    public ResponseEntity<Response> addContact(@RequestBody ContactAddDTO contactAddDTO){
        return addressBookService.addContact(contactAddDTO);
    }

    @PutMapping("/email")
    public ResponseEntity<Response> updateEmail(@RequestBody EmailUpdateDTO emailUpdateDTO){
        return addressBookService.updateEmail(emailUpdateDTO);
    }

    @PutMapping("/phone")
    public ResponseEntity<Response> updatePhone(@RequestBody PhoneUpdateDTO phoneUpdateDTO){
        return addressBookService.updatePhone(phoneUpdateDTO);
    }

    @PutMapping("/name")
    public ResponseEntity<Response> updateName(@RequestBody NameUpdateDTO nameUpdateDTO){
        return addressBookService.updateName(nameUpdateDTO);
    }

    @DeleteMapping("/contact")
    public ResponseEntity<Response> deleteContact(@RequestParam int id){
        return addressBookService.deleteContact(id);
    }
}
