package com.pco.pco.controller;

import com.pco.pco.entities.Product;
import com.pco.pco.entities.Box;
import com.pco.pco.entities.productchildren.Tops;
import com.pco.pco.repository.BoxRepository;
import com.pco.pco.repository.ProductRepository;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.*;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BoxRepository boxRepository;
    @Autowired
    private BrandController bc;
    @Autowired
    private TeamController tc;
    @Autowired
    private ClientOrderController coc;
    @Autowired
    private BoxController boxc;

    private List<String> colours = Arrays.asList("black","white","red","green","blue","yellow","grey","brown","purple","gold","orange","pink");

    @GetMapping(path="/all")
    public @ResponseBody List<Product> getAllProducts() {
         return (List<Product>) productRepository.findAll();
    }

    @GetMapping(path="/allTypeCount")
    public @ResponseBody Map<String,Integer> getProductTypeCount() {
        Map<String, Integer> classCountMap = new HashMap<>();
        List<Product> aux = (List<Product>) productRepository.findAll();
        for(Product p : aux){
            classCountMap.put(p.getClass().toString(), classCountMap.getOrDefault(p.getClass().toString(), 0) + 1);
        }
        return classCountMap;
    }
    @GetMapping(path="/totalProductValue")
    public @ResponseBody Map<String,Double> getTotalValue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Double> classCountMap = new HashMap<>();
        List<Product> aux = (List<Product>) productRepository.findAll();
        Double totalPrice = 0.0;
        for(Product p : aux){
            String name = p.getClass().toString();
            String delimiter = "productchildren.";
            name = name.substring(name.indexOf("productchildren.") + delimiter.length());
            double price;
            price = (double) p.getClass().getMethod("getPrice").invoke(p);
            classCountMap.put(name, classCountMap.getOrDefault(name, 0.0) + price);
            totalPrice = totalPrice + price;
        }
        //classCountMap.put("Total Product Value", totalPrice);
        return classCountMap;
    }

    public Product decideBrand(Product p, String title){
        if(bc.findBrandNameInTitle(title).equals("N/A")){
            p.setBrand(bc.addNewBrand("Unknown Brand", "Undefined"));
        }
        else{
            p.setBrand(bc.findBrand(bc.findBrandNameInTitle(title)).get());
        }
        return p;
    }
    public Product decideTeam(Product p, String title){
        if(tc.findTeamNameInTitle(title).equals("N/A")){
            p.setTeam(tc.addNewTeam("Unknown Team", "Undefined"));
        }
        else{
            p.setTeam(tc.findTeam(tc.findTeamNameInTitle(title)).get());
        }
        return p;
    }
    public Product decideCoC(Product p, Long coCode){
        if(coCode == -1) {
            p.setClientOrder(null);
        }
        else{
            p.setClientOrder(coc.addNewClientOrder(coCode,"2099-01-01",false));
        }
        return p;
    }
    public Product decideBox(Product p, String SKU){
        p.setBox(boxc.findBox(decideBoxNumber(SKU)));
        p.setBoxNumber(decideBoxNumber(SKU));

        return p;
    }

    public String decideBoxNumber(String SKU){
        String box = SKU.substring(SKU.indexOf(' ') + 1);
        box = box.replace(" ", "");
        return box;
    }

    public Product decideColour(Product p, String title){
        String aux = "No colour";
        for(String colour : colours){
            if (title.toLowerCase().contains(colour)) aux = colour;
        }
        p.setColour(aux);
        return p;
    }

    @GetMapping(path="/allProductsInBox")
    public @ResponseBody List<Product> getAllProductsInBox(@RequestParam String boxNumber) {
        List<Product> productList = new ArrayList<>();
        Iterable<Product> products = productRepository.findAll();
        for(Product product : products){
            if(product.getBox().getBoxNumber().equals(boxNumber)) productList.add(product);
        }
        return productList;
    }

    @PostMapping(path="/testProduct")
    public @ResponseBody Product testSetBox(@RequestParam String SKU){
        Product p = new Tops(SKU, "Rapha Cycling Australia Test Top", 16.05, false, "Yellow", "S", 1, "New", "vc56", "Short", false, false);

        Box b = new Box("2-A-1",0,0,0,false);

        //productRepository.save(p);
        boxRepository.save(b);

        p = decideBrand(p, "Rapha Cycling Australia Test Top");
        p = decideTeam(p, "Rapha Cycling Australia Test Top");
        p = decideCoC(p, -1l);
        p.setBox(b);

        productRepository.save(p);
        b.setProducts(p);
        boxRepository.save(b);

        return p;
    }


    @PostMapping(path="/classifyProduct")
    public @ResponseBody Class classifyProduct(@RequestParam String productType) throws ClassNotFoundException {
        String entityName = "";
        String entityPath = "com.pco.pco.entities.";

        if(productType.equals("Jackets") || productType.contains("Jersey") || productType.contains("shirt") || productType.contains("tops") || productType.contains("Vest") || productType.contains("Hoodies")){
            entityName = entityPath+"Tops";
        }
        else if(productType.contains("Bib") || productType.contains("Shorts")){
            entityName = entityPath+"Bottoms";
        }
        else if(productType.contains("suit")){
            entityName = entityPath+"Skinsuit";
        }
        else if(productType.equals("Component")){
            entityName = entityPath+"Component";
        }
        else if(productType.equals("layer")){
            entityName = entityPath+"Baselayer";
        }
        else if(productType.equals("Warmer")){
            entityName = entityPath+"Warmer";
        }
        else if(productType.contains("Shoe") || productType.contains("Sock")){
            entityName = entityPath+"Footwear";
        }
        else if(productType.equals("Gloves")){
            entityName = entityPath+"Gloves";
        }
        else if(productType.equals("Helmet") || productType.contains("Cap")){
            entityName = entityPath+"Headwear";
        }
        else if(productType.equals("Healthnutrition")){
            entityName = entityPath+"Healthnutrtion";
        }
        return Class.forName(entityName);
    }

    @PostMapping(path="/returnObject")
    public @ResponseBody Object returnObject(@RequestParam String productType) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //Object aux = classifyProduct(productType).getDeclaredConstructor().newInstance();
        Product aux = (Product) classifyProduct(productType).getDeclaredConstructor().newInstance();

        return aux.getClass();
    }

    @PostMapping(path="/findProduct")
    public @ResponseBody Class returnProduct(@RequestParam String SKU) {
        return productRepository.findById(SKU).get().getClass();
    }
}
