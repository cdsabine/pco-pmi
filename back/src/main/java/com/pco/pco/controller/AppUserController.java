package com.pco.pco.controller;

import com.pco.pco.entities.AppUser;
import com.pco.pco.entities.Country;
import com.pco.pco.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/user")
public class AppUserController {
    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private CountryController cc;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String emailAddress, @RequestParam String address, @RequestParam String countryName) {
        AppUser u = new AppUser();
        u.setName(name);
        u.setEmailAddress(emailAddress);
        u.setAddress(address);

        if(!cc.findCountry(countryName).isPresent()){
            u.setCountry(cc.addNewCountry(countryName));
        }
        else{
            u.setCountry(cc.findCountry(countryName).get());
        }
        appUserRepository.save(u);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<AppUser> getAllUsers() {
        // This returns a JSON or XML with the users
        return appUserRepository.findAll();
    }

    @GetMapping(path="/delete")
    public @ResponseBody String deleteUser (@RequestParam int id){
        if(cc.deleteAppUser(appUserRepository.findById(id).get().getCountry(), id)){
            appUserRepository.delete(appUserRepository.findById(id).get());
            return "Deleted";
        }
        else return "User not found";
    }

}
