package com.rituraj.addressBook.controller;

import com.rituraj.addressBook.dto.insertion_dto.ContactAddDTO;
import com.rituraj.addressBook.dto.update_dto.EmailUpdateDTO;
import com.rituraj.addressBook.dto.update_dto.NameUpdateDTO;
import com.rituraj.addressBook.dto.update_dto.PhoneUpdateDTO;
import com.rituraj.addressBook.service.AddressBookService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address/book")
public class AddressBookController {

    private AddressBookService addressBookService;

    public AddressBookController(AddressBookService addressBookService) {
        this.addressBookService = addressBookService;
    }

    @GetMapping("/contacts")
    public void getAllContacts(){
        return addressBookService.getAllContacts();
    }

    @PostMapping("/contacts")
    public void addContact(@RequestBody ContactAddDTO contactAddDTO){
        return addressBookService.addContact(contactAddDTO);
    }

    @PutMapping("/email")
    public void updateEmail(@RequestBody EmailUpdateDTO emailUpdateDTO){
        return addressBookService.updateEmail(emailUpdateDTO);
    }

    @PutMapping("/phone")
    public void updatePhone(@RequestBody PhoneUpdateDTO phoneUpdateDTO){
        return addressBookService.updatePhone(phoneUpdateDTO);
    }

    @PutMapping("/name")
    public void updatePhone(@RequestBody NameUpdateDTO nameUpdateDTO){
        return addressBookService.updateName(nameUpdateDTO);
    }

    @DeleteMapping("/contact")
    public void deleteContact(@RequestParam int id){
        return addressBookService.deleteContact(id);
    }
}
