package com.pco.pco;

import com.pco.pco.controller.BrandController;
import com.pco.pco.controller.TeamController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class PcoApplication {
	public static void main(String[] args) {
		SpringApplication.run(PcoApplication.class, args);
	}

}
