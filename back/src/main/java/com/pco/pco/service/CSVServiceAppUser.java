package com.pco.pco.service;

import com.pco.pco.controller.CountryController;
import com.pco.pco.entities.AppUser;
import com.pco.pco.helper.CSVHelperAppUser;
import com.pco.pco.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CSVServiceAppUser {
    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    private CountryController cc;

    public void save(MultipartFile file) {
        try {
            List<AppUser> appusers = CSVHelperAppUser.csvToAppUser(file.getInputStream());
            for(AppUser aux : appusers){
                if(!cc.findCountry(aux.getCountryName()).isPresent()){
                    aux.setCountry(cc.addNewCountry(aux.getCountryName()));
                }
                else{
                    aux.setCountry(cc.findCountry(aux.getCountryName()).get());
                }
            }
            appUserRepository.saveAll(appusers);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public Iterable<AppUser> getAllAppUsers() {
        return appUserRepository.findAll();
    }
}
