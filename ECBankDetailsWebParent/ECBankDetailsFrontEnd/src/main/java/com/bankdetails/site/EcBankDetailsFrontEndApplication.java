package com.bankdetails.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.bankdetails.entity"})
public class EcBankDetailsFrontEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcBankDetailsFrontEndApplication.class, args);
	}

}
