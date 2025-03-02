
package com.kentkart.api.boarding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.kentkart.api.boarding.Boarding;

public interface BoardingRepository extends JpaRepository<Boarding, String>, JpaSpecificationExecutor<Boarding> {

}