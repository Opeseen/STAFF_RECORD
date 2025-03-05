package com.munstaff.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.munstaff.model.Users;

public interface UserRepository extends CrudRepository<Users, Long> {
  Optional<Users> findByEmail(String string);
}
