package com.rituraj.addressBook;

import org.apache.catalina.core.ApplicationContext;
import org.hibernate.annotations.processing.Pattern;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AddressBookApplication {
	public static void main(String[] args) {
		SpringApplication.run(AddressBookApplication.class, args);
	}
}

//@NotBlank
//@Pattern
//@Size