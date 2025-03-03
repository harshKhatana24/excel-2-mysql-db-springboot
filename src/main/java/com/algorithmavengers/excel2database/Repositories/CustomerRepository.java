package com.algorithmavengers.excel2database.Repositories;

import com.algorithmavengers.excel2database.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
