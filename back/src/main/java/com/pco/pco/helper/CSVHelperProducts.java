package com.pco.pco.helper;

import com.pco.pco.entities.Bottoms;
import com.pco.pco.entities.Product;
import com.pco.pco.entities.Tops;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVHelperProducts {
    public static String TYPE = "text/csv";
    static String[] HEADERs = {"Handle", "Title", "Body (HTML)", "vendor", "Product Category", "Type", "Tags", "Published", "Option1 Name", "Option1 Value", "Option2 Name", "Option2 Value", "Option3 Name", "Option3 Value",
            "Variant SKU", "Variant Grams", "Variant Inventory Tracker", "Variant Inventory Qty", "Variant Inventory Policy", "Variant Fulfillment Service", "Variant Price", "Variant Compare At Price", "Variant Requires Shipping", "Variant Taxable", "Variant Barcode",
            "Image Src", "Image Position", "Image Alt Text", "Gift Card", "SEO Title", "SEO Description", "Google Shopping / Google Product Category", "Google Shopping / Gender", "Google Shopping / Age Group", "Google Shopping / MPN", "Google Shopping / Condition",
            "Google Shopping / Custom Product", "Google Shopping / Custom Label 0", "Google Shopping / Custom Label 1", "Google Shopping / Custom Label 2", "Google Shopping / Custom Label 3", "Google Shopping / Custom Label 4", "Variant Image", "Variant Weight Unit", "Variant Tax Code",
            "Cost per item", "Included / Spain", "Price / Spain", "Compare At Price / Spain", "Included / France", "Price / France", "Compare At Price / France", "Included / Germany", "Price / Germany", "Compare At Price / Germany", "Included / International",
            "Price / International", "Compare At Price / International", "Included / Japan", "Price / Japan", "Compare At Price / Japan", "Included / Portugal", "Price / Portugal", "Compare At Price / Portugal", "Included / italy", "Price / italy", "Compare At Price / italy", "Status"};

    //Title,Vendor,Type,Tags,option1 Name(size),option1 Value(xs,s,...),variant sku,variant grams,variant inventory,variant price,variant barcode,cost per item,status
    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }
    public static List<Product> csvToProduct(InputStream is) throws UnsupportedEncodingException {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Product> products = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                String title = csvRecord.get("Title");
                String brand = csvRecord.get("Vendor");
                String size = csvRecord.get("Option1 Value");
                String SKU = csvRecord.get("Variant SKU");
                Double price = Double.parseDouble(csvRecord.get("Variant Price"));
                Product aux = classifyProduct(csvRecord.get("Type"), title, brand, size, SKU, price);

                products.add(aux);

                /*
                csvRecord.get("Option1 Name");
                csvRecord.get("Option1 Value");
                csvRecord.get("Variant SKU");
                csvRecord.get("Variant Grams");
                csvRecord.get("Variant Inventory Qty");
                csvRecord.get("Variant Price");
                csvRecord.get("Variant Barcode");
                csvRecord.get("Cost per item");
                csvRecord.get("Status");
                */
            }

            return products;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Product classifyProduct(String productType, String title, String brand, String size, String SKU, double price) {
        Product p = null;

        if(productType.equals("Jackets") || productType.contains("Jersey") || productType.contains("shirt") || productType.contains("tops") || productType.contains("Vest") || productType.contains("Hoodies")){
            p = new Tops(SKU, title, price, false, "" ,size, "", false, false);
            p.setBrandName(brand);
        }
        else if(productType.contains("Bib") || productType.contains("Shorts")){
            p = new Bottoms(SKU, title, price, false, "" ,size, true, false, false);
            p.setBrandName(brand);
        }
        else if(productType.contains("suit")){
            //p = new Skinsuits();
        }
        else if(productType.equals("Component")){
            //p = new Components();
        }
        else if(productType.equals("layer")){
            //p = new Baselayers();
        }
        else if(productType.equals("Warmer")){
            //p = new Warmers();
        }
        else if(productType.contains("Shoe") || productType.contains("Sock")){
            //p = new Footwear();
        }
        else if(productType.equals("Gloves")){
            //p = new Gloves();
        }
        else if(productType.equals("Helmet") || productType.contains("Cap")){
            //p = new Headwear();
        }
        else if(productType.equals("Healthnutrition")){
            //p = new Healthnutrition();
        }
        return p;
    }
}
