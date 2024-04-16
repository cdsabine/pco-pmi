package com.pco.pco.service;

import com.pco.pco.entities.AppUser;
import com.pco.pco.helper.CSVHelperAppUser;
import com.pco.pco.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Service
public class CSVServiceAppUser {
    @Autowired
    AppUserRepository appUserRepository;

    public void save(MultipartFile file) {
        try {
            List<AppUser> appusers = CSVHelperAppUser.csvToAppUser(file.getInputStream());
            appUserRepository.saveAll(appusers);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public Iterable<AppUser> getAllTutorials() {
        return appUserRepository.findAll();
    }
}
