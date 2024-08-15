package com.pco.pco.service;

import com.pco.pco.controller.ClientOrderController;
import com.pco.pco.entities.ClientOrder;
import com.pco.pco.helper.CSVHelperClientOrder;
import com.pco.pco.repository.ClientOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CSVServiceClientOrder {
    @Autowired
    ClientOrderRepository clientOrderRepository;

    @Autowired
    private ClientOrderController coc;

    @Transactional
    public void save(MultipartFile file) {
        try {
            List<ClientOrder> clientOrders = CSVHelperClientOrder.csvToClientOrder(file.getInputStream());
            for(ClientOrder co : clientOrders){
                coc.convertSKUtoProduct(co);
                coc.decideClient(co);
                if(co.getTotalOrderValue() < 100.0) co.setShipped(true);
            }
            clientOrderRepository.saveAll(clientOrders);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
}
