package com.grupoagoc.test.persist.repository;

import com.grupoagoc.test.persist.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findUsersByEmail(String email);
}
