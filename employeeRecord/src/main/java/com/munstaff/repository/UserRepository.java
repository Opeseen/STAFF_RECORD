package com.munstaff.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.munstaff.entity.Users;

public interface UserRepository extends CrudRepository<Users, Long> {
  Optional<Users> findByEmail(String email);
  Boolean existsByEmail(String email);
}
