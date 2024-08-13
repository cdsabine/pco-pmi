package com.pco.pco.controller;

import com.pco.pco.entities.Client;
import com.pco.pco.entities.ClientOrder;
import com.pco.pco.entities.Product;
import com.pco.pco.repository.ClientOrderRepository;
import com.pco.pco.repository.ClientRepository;
import com.pco.pco.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/clientorder")
public class ClientOrderController {
    @Autowired
    private ClientOrderRepository clientOrderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ClientRepository clientRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody ClientOrder addNewClientOrder (@RequestParam long clientOrderCode, @RequestParam String dateOfOrder, @RequestParam boolean shipped) {
        ClientOrder co = new ClientOrder();
        co.setClientOrderCode(clientOrderCode);
        co.setDateOfOrder(LocalDate.parse(dateOfOrder));
        co.setShipped(shipped);
        clientOrderRepository.save(co);
        return co;
    }

    @Transactional
    @PostMapping(path="/addProduct") // Map ONLY POST Requests
    public @ResponseBody ClientOrder addProductToCO (@RequestParam long clientOrderCode) {
        Product p = productRepository.findById("00026604 4-A-1").get();
        List<Product> productList = new ArrayList<>();
        productList.add(p);

        int code = (int) clientOrderCode;

        ClientOrder co = clientOrderRepository.findById(code).get();

        co.setProductList(productList);

        clientOrderRepository.save(co);
        return co;
    }

    @GetMapping(path="/all")
    public @ResponseBody List<ClientOrder> getAllCOs() {
        return (List<ClientOrder>) clientOrderRepository.findAll();
    }

    @Transactional
    public ClientOrder convertSKUtoProduct(ClientOrder co){
        List<String> productSKU = co.getProductSKUList();
        List<Product> products = new ArrayList<>();
        double totalPrice = 0;
        for(String sku : productSKU){
            if(productRepository.findById(sku).isPresent()) {
                Product p = productRepository.findById(sku).get();
                p.setClientOrder(co);
                products.add(p);
                productRepository.save(p);
                totalPrice = totalPrice + p.getPrice();
            }
        }
        co.setProductList(products);
        co.setTotalOrderValue(totalPrice);
        clientOrderRepository.save(co);
        return co;
    }

    @Transactional
    public ClientOrder decideClient(ClientOrder co){
        int clientID = co.getClientNumber();
        if(clientRepository.findById(clientID).isPresent()){
            Client c = clientRepository.findById(clientID).get();
            co.setClient(c);
            c.addToTotalValueAchieved(co.getTotalOrderValue());
            c.addToRepeatedTransactions();
            clientRepository.save(c);
        }
        clientOrderRepository.save(co);

        return co;
    }
}
