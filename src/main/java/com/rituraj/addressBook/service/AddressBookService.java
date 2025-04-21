package com.rituraj.addressBook.service;

import com.rituraj.addressBook.dto.insertion_dto.ContactAddDTO;
import com.rituraj.addressBook.dto.update_dto.EmailUpdateDTO;
import com.rituraj.addressBook.dto.update_dto.NameUpdateDTO;
import com.rituraj.addressBook.dto.update_dto.PhoneUpdateDTO;
import com.rituraj.addressBook.model.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressBookService {
    public void getAllContacts() {
        return addressBookRepo.findAll();
    }

    public void addContact(ContactAddDTO contactAddDTO) {
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
        }
            System.out.println("Person Already exists");
    }

    public void updateEmail(EmailUpdateDTO emailUpdateDTO) {
        Optional<Person> optionalPerson = personRepo.findById(emailUpdateDTO.getPerson_id());
        if(optionalPerson.isPresent()){
            if(!optionalPerson.get().getIsActive()){
                //exception
            }

            Email email = new Email(emailUpdateDTO.getPerson_id(), emailUpdateDTO.getEmail_add());
            emailRepo.save(email);
        }
    }

    public void updatePhone(PhoneUpdateDTO phoneUpdateDTO) {
        Optional<Person> optionalPerson = personRepo.findById(phoneUpdateDTO.getPerson_id());
        if(optionalPerson.isPresent()){
            if(!optionalPerson.get().getIsActive()){
                //exception
            }

            Phone phone = new Phone(phoneUpdateDTO.getPerson_id(), phoneUpdateDTO.getPhone());
            phoneRepo.save(phone);
        }
    }

    public void updateName(NameUpdateDTO nameUpdateDTO) {
        Optional<Person> optionalPerson = personRepo.findById(nameUpdateDTO.getPerson_id());
        if(optionalPerson.isPresent()){
            if(!optionalPerson.get().getIsActive()){
                //exception
            }

            Person person = new Person(nameUpdateDTO.getId(), nameUpdateDTO.getNewFName(), nameUpdateDTO.getNewLName(), true);
            personRepo.save(person);
        }
    }

    public void deleteContact(int id) {
        Optional<Person> optionalPerson = personRepo.findById(nameUpdateDTO.getPerson_id());
        if(optionalPerson.isEmpty()){
            //exception
        }

        Person person = optionalPerson.get();
        person.setIsActive(false);
        personRepo.save(person);
    }
}
