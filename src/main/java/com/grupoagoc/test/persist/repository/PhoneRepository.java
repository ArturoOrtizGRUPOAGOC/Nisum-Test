package com.grupoagoc.test.persist.repository;

import com.grupoagoc.test.persist.Phone;
import org.springframework.data.repository.CrudRepository;

public interface PhoneRepository extends CrudRepository<Phone, Long> {
}
