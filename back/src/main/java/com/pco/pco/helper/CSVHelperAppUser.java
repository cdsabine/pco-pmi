package com.pco.pco.helper;

import com.pco.pco.entities.AppUser;
import com.pco.pco.entities.Client;
import com.pco.pco.entities.Employee;
import com.pco.pco.entities.Vendor;
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
    static String[] HEADERs = {"appUsername", "emailAddress", "address", "country", "userType"};

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
                String username = csvRecord.get("appUsername");
                String emailAddress = csvRecord.get("emailAddress");
                String address = csvRecord.get("address");
                String country = csvRecord.get("country");
                String userType = csvRecord.get("userType");

                AppUser aux = classifyUser(username, emailAddress, address, country, userType);
                aux.setUserType(userType);

                appusers.add(aux);
            }

            return appusers;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static AppUser classifyUser(String userName, String emailAddress, String address, String country, String userType){
        AppUser a = null;
        if(userType.equals("Vendor")){
            a = new Vendor(userName, emailAddress, address, country, userName+"Vendor");
        }
        else if(userType.equals("Employee")){
            a = new Employee(userName, emailAddress, address, country);
        }
        else if(userType.equals("Client")){
            a = new Client(userName, emailAddress, address, country, 0, 0);
        }
        return a;
    }
}
