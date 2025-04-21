package com.rituraj.addressBook.service;

import com.rituraj.addressBook.dto.insertion_dto.ContactAddDTO;
import com.rituraj.addressBook.dto.update_dto.EmailUpdateDTO;
import com.rituraj.addressBook.dto.update_dto.NameUpdateDTO;
import com.rituraj.addressBook.dto.update_dto.PhoneUpdateDTO;
import com.rituraj.addressBook.model.*;
import com.rituraj.addressBook.repo.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AddressBookService {

    private PersonRepo personRepo;
    private AddressRepo addressRepo;
    private EmailRepo emailRepo;
    private PersonAddressRepo personAddressRepo;
    private PersonTypeRepo personTypeRepo;
    private PhoneRepo phoneRepo;

    public AddressBookService(PersonRepo personRepo, AddressRepo addressRepo,
                              EmailRepo emailRepo, PersonAddressRepo personAddressRepo,
                              PersonTypeRepo personTypeRepo, PhoneRepo phoneRepo) {
        this.personRepo = personRepo;
        this.addressRepo = addressRepo;
        this.emailRepo = emailRepo;
        this.personAddressRepo = personAddressRepo;
        this.personTypeRepo = personTypeRepo;
        this.phoneRepo = phoneRepo;
    }

    public ResponseEntity<Response> getAllPerson() {
        List<Person> personList = personRepo.findAll();
        if(!personList.isEmpty()){
        Response response = new Response<List<Person>>("SUCCESS", "Found people in the address book!", personList, LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
        }
        Response response = new Response<List<Person>>("SUCCESS", "No people in the address book!", personList, LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Response> addContact(ContactAddDTO contactAddDTO) {
        Optional<Person> optionalPerson = personRepo.findByFnameAndLname(contactAddDTO.getFname(), contactAddDTO.getLname());
        if(optionalPerson.isEmpty()){
            Person person = new Person();
            person.setFname(contactAddDTO.getFname());
            person.setLname(contactAddDTO.getLname());
            person = personRepo.save(person);

            int person_id = person.getId();
            Address address = new Address();
            address.setCity(contactAddDTO.getCity());
            address.setState(contactAddDTO.getState());
            address.setZip(contactAddDTO.getZip());
            address.setStreet(contactAddDTO.getStreet());

            address = addressRepo.save(address);
            int address_id = address.getAddress_id();

            Email email = new Email(person_id, contactAddDTO.getEmail_add());
            PersonAddress personAddress = new PersonAddress(person_id, address_id);
            PersonType personType = new PersonType(person_id, contactAddDTO.getValue());
            Phone phone = new Phone(person_id, contactAddDTO.getPhone_number());

            emailRepo.save(email);
            personAddressRepo.save(personAddress);
            personTypeRepo.save(personType);
            phoneRepo.save(phone);

            Response response = new Response<>("SUCCESS", "Contact added successfully", null, LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        Response response = new Response<>("FAILED", "Contact already exists", null, LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Response> updateEmail(EmailUpdateDTO emailUpdateDTO) {
        Optional<Person> optionalPerson = personRepo.findById(emailUpdateDTO.getPerson_id());
        if(optionalPerson.isPresent()){
            if(!optionalPerson.get().getIsActive()){
                Response response = new Response<>("FAILED", "Contact not found", null, LocalDateTime.now());
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            Email email = new Email(emailUpdateDTO.getPerson_id(), emailUpdateDTO.getEmail_add());
            emailRepo.save(email);
        }
            Response response = new Response<>("SUCCESS", "Contact updated successfully", null, LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Response> updatePhone(PhoneUpdateDTO phoneUpdateDTO) {
        Optional<Person> optionalPerson = personRepo.findById(phoneUpdateDTO.getPerson_id());
        if(optionalPerson.isPresent()){
            if(!optionalPerson.get().getIsActive()){
                Response response = new Response<>("FAILED", "Contact not found", null, LocalDateTime.now());
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            Phone phone = new Phone(phoneUpdateDTO.getPerson_id(), phoneUpdateDTO.getPhone());
            phoneRepo.save(phone);
        }
        Response response = new Response<>("SUCCESS", "Contact updated successfully", null, LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Response> updateName(NameUpdateDTO nameUpdateDTO) {
        Optional<Person> optionalPerson = personRepo.findById(nameUpdateDTO.getId());
        if(optionalPerson.isPresent()){
            if(!optionalPerson.get().getIsActive()){
                Response response = new Response<>("FAILED", "Contact not found", null, LocalDateTime.now());
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            Person person = new Person(nameUpdateDTO.getId(), nameUpdateDTO.getNewFName(), nameUpdateDTO.getNewLName(), true);
            personRepo.save(person);
        }
        Response response = new Response<>("SUCCESS", "Contact updated successfully", null, LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Response> deleteContact(int id) {
        Optional<Person> optionalPerson = personRepo.findById(id);
        if(optionalPerson.isEmpty()){
            Response response = new Response<>("FAILED", "Contact not found", null, LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Person person = optionalPerson.get();
        person.setIsActive(false);
        personRepo.save(person);
        Response response = new Response<>("SUCCESS", "Contact deleted successfully", null, LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
