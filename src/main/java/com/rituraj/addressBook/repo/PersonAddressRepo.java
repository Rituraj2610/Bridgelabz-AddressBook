package com.rituraj.addressBook.repo;

import com.rituraj.addressBook.model.PersonAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonAddressRepo extends JpaRepository<PersonAddress, Integer> {
}
