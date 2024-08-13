package com.pco.pco.controller.subclassappuser;

import com.pco.pco.entities.AppUser;
import com.pco.pco.entities.Client;
import com.pco.pco.repository.ClientOrderRepository;
import com.pco.pco.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/user/client")
public class ClientController {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ClientOrderRepository clientOrderRepository;
    @GetMapping(path="/all")
    public @ResponseBody List<Client> getAllUsers() {
        // This returns a JSON or XML with the users
        return (List<Client>) clientRepository.findAll();
    }
}
