package com.rituraj.addressBook.repo;

import ch.qos.logback.core.model.INamedModel;
import com.rituraj.addressBook.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepo extends JpaRepository<Phone, Integer> {
}
