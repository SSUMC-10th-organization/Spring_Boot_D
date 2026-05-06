package com.example.umc10th.domain.address.repository;

import com.example.umc10th.domain.address.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
