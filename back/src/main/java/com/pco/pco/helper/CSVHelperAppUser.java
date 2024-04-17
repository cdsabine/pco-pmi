package com.pco.pco.helper;

import com.pco.pco.entities.AppUser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVHelperAppUser {

    public static String TYPE = "text/csv";
    //static String[] HEADERs = { "userCode", "appUsername", "emailAddress", "address", "country" }; Removed user code from CSV. Assigned automatically
    static String[] HEADERs = {"appUsername", "emailAddress", "address", "country" };

    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }
    public static List<AppUser> csvToAppUser(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<AppUser> appusers = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                AppUser appuser = new AppUser(
                        //Long.parseLong(csvRecord.get("userCode")),
                        csvRecord.get("appUsername"),
                        csvRecord.get("emailAddress"),
                        csvRecord.get("address"),
                        csvRecord.get("country")
                );

                appusers.add(appuser);
            }

            return appusers;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
