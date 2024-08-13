package com.pco.pco.helper;

import com.pco.pco.entities.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVHelperClientOrder {
    public static String TYPE = "text/csv";
    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }
    public static List<ClientOrder> csvToClientOrder(InputStream is) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is));
            CSVParser parser = new CSVParser(br, CSVFormat.DEFAULT.withTrim())) {
            List<ClientOrder> clientOrders = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] firstLine = line.split(",\\s+");
                int clientNumber = Integer.parseInt(firstLine[0]);
                String aux = firstLine[1];
                LocalDate date = LocalDate.parse(aux);

                if ((line = br.readLine()) != null) {
                    List<String> skus = List.of(line.split(",\\s+"));
                    ClientOrder clientOrder = new ClientOrder(clientNumber, skus, date);
                    clientOrders.add(clientOrder);
                }
            }
            return clientOrders;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
