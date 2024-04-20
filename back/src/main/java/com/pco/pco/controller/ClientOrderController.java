package com.pco.pco.controller;

import com.pco.pco.entities.ClientOrder;
import com.pco.pco.repository.ClientOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/clientorder")
public class ClientOrderController {
    @Autowired
    private ClientOrderRepository clientOrderRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody ClientOrder addNewClientOrder (@RequestParam long clientOrderCode, @RequestParam String dateOfOrder, @RequestParam boolean shipped) {
        ClientOrder co = new ClientOrder();
        co.setClientOrderCode(clientOrderCode);
        co.setDateOfOrder(dateOfOrder);
        co.setShipped(shipped);
        clientOrderRepository.save(co);
        return co;
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<ClientOrder> getAllBrands() {
        return clientOrderRepository.findAll();
    }
}
