package com.bankdetails.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.bankdetails.entity"})
public class EcBankDetailsBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcBankDetailsBackEndApplication.class, args);
	}

}
