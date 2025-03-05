package com.munstaff.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.munstaff.model.PayGroup;

@Repository
public interface PayGroupRepository extends CrudRepository<PayGroup, Long> {
  Boolean existsByCategory(String category);
};