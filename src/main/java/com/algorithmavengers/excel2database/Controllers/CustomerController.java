package com.algorithmavengers.excel2database.Controllers;


import com.algorithmavengers.excel2database.Entities.Customer;
import com.algorithmavengers.excel2database.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/upload-customer-data")
    public ResponseEntity<String> uploadCustomerData(@RequestParam("file")MultipartFile file){
        this.customerService.saveCustomerToDatabase(file);
        return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
    }

    @GetMapping("/get-data")
    public ResponseEntity<List<Customer>> getCustomers(){
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.ACCEPTED);
    }

}
