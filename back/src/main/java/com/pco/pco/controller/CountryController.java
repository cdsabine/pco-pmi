package com.pco.pco.controller;

import com.pco.pco.entities.AppUser;
import com.pco.pco.entities.Country;
import com.pco.pco.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path="/country")
public class CountryController {
    @Autowired
    private CountryRepository countryRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody Country addNewCountry (@RequestParam String countryName) {
        Country c = new Country();
        c.setCountryName(countryName);
        countryRepository.save(c);
        //return "Saved";
        return c;
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Country> getAllCountries() {
        // This returns a JSON or XML with the users
        return countryRepository.findAll();
    }

    @GetMapping(path="/findCountry")
    public @ResponseBody Country returnCountry(@RequestParam String countryName) {
        // This returns a JSON or XML with the users
        return findCountry(countryName).get();
    }

    public Optional<Country> findCountry (String countryName) {
        Long code = -1L;
        Iterable<Country> countries = getAllCountries();
        for(Country aux : countries){
            if(aux.getCountryName().equals(countryName)) {
                code = aux.getCountryCode();
                return countryRepository.findById(code.intValue());
            }
        }
        return countryRepository.findById(code.intValue());
    }

    public boolean deleteAppUser (Country c, int id){
        boolean found = false;
        List<AppUser> appusers = c.getAppUserList();
        int i = 0;
        for(AppUser aux : appusers){
            if(aux.getUserCode() == id){
                appusers.remove(i);
                c.setappUserList(appusers);
                countryRepository.save(c);
                return found = true;
            }
            i++;
        }
        return found;
    }

    @GetMapping(path="/deleteUserFromCountry")
    public @ResponseBody Country deleteUserFromCountry (@RequestParam String country, @RequestParam int id){
        Country c = findCountry(country).get();
        List<AppUser> appusers = c.getAppUserList();
        int i = 0;
        for(AppUser aux : appusers){
            if(aux.getUserCode() == id){
                appusers.remove(i);
                c.setappUserList(appusers);
                countryRepository.save(c);
                return c;
            }
            i++;
        }
        return c;
    }
}
