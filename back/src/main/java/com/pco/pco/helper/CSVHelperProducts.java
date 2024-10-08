package com.pco.pco.helper;

import com.pco.pco.entities.productchildren.*;
import com.pco.pco.entities.Product;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVHelperProducts {
    public static String TYPE = "text/csv";
    static String[] HEADERs = {"Handle", "Title", "Body (HTML)", "vendor", "Product Category", "Type", "Tags", "Published", "Option1 Name", "Option1 Value", "Option2 Name", "Option2 Value", "Option3 Name", "Option3 Value",
            "Variant SKU", "Variant Grams", "Variant Inventory Tracker", "Variant Inventory Qty", "Variant Inventory Policy", "Variant Fulfillment Service", "Variant Price", "Variant Compare At Price", "Variant Requires Shipping",
            "Variant Taxable", "Variant Barcode", "Image Src", "Image Position", "Image Alt Text", "Gift Card", "SEO Title", "SEO Description", "Google Shopping / Google Product Category", "Gender", "adult", "Google Shopping / MPN",
            "Google Shopping / Condition", "Google Shopping / Custom Product", "Google Shopping / Custom Label 0", "Google Shopping / Custom Label 1", "Google Shopping / Custom Label 2", "Google Shopping / Custom Label 3",
            "Google Shopping / Custom Label 4", "Variant Image", "Variant Weight Unit", "Variant Tax Code", "Cost per item", "Included / Spain", "Price / Spain", "Compare At Price / Spain", "Included / France", "Price / France",
            "Compare At Price / France", "Included / Germany", "Price / Germany", "Compare At Price / Germany", "Included / International", "Price / International", "Compare At Price / International", "Included / Japan", "Price / Japan",
            "Compare At Price / Japan", "Included / Portugal", "Price / Portugal", "Compare At Price / Portugal", "Included / italy", "Price / italy", "Compare At Price / italy", "Status"

    };

    //Title,Vendor,Type,Tags,option1 Name(size),option1 Value(xs,s,...),variant sku,variant grams,variant inventory,variant price,variant barcode,cost per item,status

    //FIANL VERSION OF SPREADSHEET CONTAINS THE FOLLOWING COLUMNS: Handle (Product Title + Barcode), Title, vendor (vcXX), Type (Identifies product), Option 1 Value (Size), Variant SKU (SKU + Box Location), Variant Grams,
    // Variant Inventory, Variant Price, Google Shopping / Condition (Used slightly used etc.)

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
                String vendor = csvRecord.get("Vendor");
                String size = csvRecord.get("Option1 Value");
                String SKU = csvRecord.get("Variant SKU"); //also gives box
                int quantity = Integer.parseInt(csvRecord.get("Variant Inventory Qty"));
                Double price = Double.parseDouble(csvRecord.get("Variant Price"));
                String prodCondition = csvRecord.get("Google Shopping / Condition");
                Product aux = classifyProduct(csvRecord.get("Type"), title, vendor, size, quantity, prodCondition, SKU, price);

                products.add(aux);
            }

            return products;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Product classifyProduct(String productType, String title, String vendor, String size, int quantity, String prodCondition, String SKU, double price) {
        Product p = null;

        if(productType.toLowerCase().contains("jackets") || productType.toLowerCase().contains("jersey") || productType.toLowerCase().contains("shirt") || productType.toLowerCase().contains("tops") || productType.toLowerCase().contains("vest") || productType.toLowerCase().contains("hoodies")){
            String sleeves = decideSleeves(title);
            boolean thermal = decideThermal(title);
            boolean aero = decideAero(title);
            p = new Tops(SKU, title, price, false, "" ,size, quantity, prodCondition, vendor, sleeves, thermal, aero);
        }
        else if(productType.toLowerCase().toLowerCase().contains("bib") || productType.toLowerCase().contains("shorts") || productType.toLowerCase().contains("pants")){
            boolean thermal = decideThermal(title);
            boolean aero = decideAero(title);
            p = new Bottoms(SKU, title, price, false, "" ,size, quantity, prodCondition, vendor, true, thermal, aero);
        }
        else if(productType.toLowerCase().contains("suit")){
            String sleeves = decideSleeves(title);
            boolean thermal = decideThermal(title);
            boolean aero = decideAero(title);
            boolean tt = decideTT(title);
            p = new Skinsuits(SKU, title, price, false, "" ,size, quantity, prodCondition, vendor, sleeves, thermal, aero, tt);
        }
        else if(productType.toLowerCase().contains("component") || productType.toLowerCase().contains("bags")){
            p = new Components(SKU, title, price, false, "" ,size, quantity, prodCondition, vendor);
        }
        else if(productType.toLowerCase().contains("layer")){
            String sleeves = decideSleeves(title);
            boolean thermal = decideThermal(title);
            boolean merino = decideMerino(title);
            p = new Baselayers(SKU, title, price, false, "" ,size, quantity, prodCondition, vendor, sleeves, thermal, merino);
        }
        else if(productType.toLowerCase().contains("warmer")){
            boolean thermal = decideThermal(title);
            boolean waterproof = decideWaterproof(title);
            p = new Warmers(SKU, title, price, false, "" ,size, quantity, prodCondition, vendor, waterproof, "", thermal);
        }
        else if(productType.toLowerCase().contains("shoe") || productType.toLowerCase().contains("sock") || productType.toLowerCase().contains("cover")){
            p = new Footwear(SKU, title, price, false, "" ,size, quantity, prodCondition, vendor, "");
        }
        else if(productType.toLowerCase().contains("gloves")){
            boolean thermal = decideThermal(title);
            boolean waterproof = decideWaterproof(title);
            boolean neoprene = decideNeoprene(title);
            p = new Gloves(SKU, title, price, false, "" ,size, quantity, prodCondition, vendor, waterproof, thermal, neoprene);
        }
        else if(productType.toLowerCase().contains("helmet") || productType.toLowerCase().contains("cap") || productType.toLowerCase().contains("eyewear")){
            boolean thermal = decideThermal(title);
            boolean waterproof = decideWaterproof(title);
            p = new Headwear(SKU, title, price, false, "" ,size, quantity, prodCondition, vendor, waterproof, true, thermal);
        }
        else if(productType.toLowerCase().contains("health") || productType.toLowerCase().contains("nutrition")){
            p = new HealthNutrition(SKU, title, price, false, "" ,size, quantity, prodCondition, vendor, LocalDate.parse("2030-01-01"), "Flavour");
        }
        return p;
    }

    public static List<String> sleeves = Arrays.asList("long sleeve","short sleeve","sleeveless");
    public static String decideSleeves(String title){
        String aux = "No sleeves";
        for(String sleeve : sleeves){
            if (title.toLowerCase().contains(sleeve)) aux = sleeve;
        }
        return aux;
    }
    public static boolean decideThermal(String title){
        return title.toLowerCase().contains("thermal");
    }
    public static boolean decideAero(String title){
        return title.toLowerCase().contains("aero") || title.toLowerCase().contains("speed");
    }
    public static boolean decideTT(String title){
        return title.toLowerCase().contains("speed");
    }
    public static boolean decideMerino(String title){
        return title.toLowerCase().contains("merino");
    }
    public static boolean decideWaterproof(String title){
        return title.toLowerCase().contains("waterproof") || title.toLowerCase().contains("water");
    }
    public static boolean decideNeoprene(String title){
        return title.toLowerCase().contains("neoprene");
    }
}
