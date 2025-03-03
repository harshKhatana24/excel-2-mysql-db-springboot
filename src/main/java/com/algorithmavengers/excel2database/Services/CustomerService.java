package com.algorithmavengers.excel2database.Services;


import com.algorithmavengers.excel2database.Entities.Customer;
import com.algorithmavengers.excel2database.Repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Iterator;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void saveCustomerToDatabase(MultipartFile file){
        if (ExcelUploadService.isValidExcelFile(file)){
            try {
                List<Customer> customers=ExcelUploadService.getCustomerDataFromExcel(file.getInputStream());
                customerRepository.saveAll(customers);
            }catch (Exception e){
                throw new IllegalArgumentException("This file is not a valid excel file");
            }
        }
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

}
