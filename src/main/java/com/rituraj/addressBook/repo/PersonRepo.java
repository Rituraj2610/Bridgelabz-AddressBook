package com.rituraj.addressBook.repo;

import com.rituraj.addressBook.model.Person;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {
    Optional<Person> findByFnameAndLname(String fname, String lname);
}
