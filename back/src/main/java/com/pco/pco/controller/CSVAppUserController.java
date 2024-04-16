package com.pco.pco.controller;

import com.pco.pco.service.CSVServiceAppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(path="/csvAppUser")
public class CSVAppUserController {
    @Autowired
    CSVServiceAppUser csvServiceAppUser;

    @PostMapping("/upload")
    public @ResponseBody String uploadFile(@RequestParam("file") MultipartFile file) {

    }
}
