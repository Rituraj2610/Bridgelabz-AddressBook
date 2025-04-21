package com.rituraj.addressBook.repo;

import com.rituraj.addressBook.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepo extends JpaRepository<Email, Integer> {
}
