package com.pco.pco.repository;

import com.pco.pco.entities.AppUser;
import org.springframework.data.repository.CrudRepository;
public interface AppUserRepository extends CrudRepository<AppUser, Integer> {

}
