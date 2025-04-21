package com.rituraj.addressBook.repo;

import com.rituraj.addressBook.model.PersonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonTypeRepo extends JpaRepository<PersonType, Integer> {
}
