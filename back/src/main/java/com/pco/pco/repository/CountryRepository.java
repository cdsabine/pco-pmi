package com.pco.pco.repository;

import com.pco.pco.entities.Country;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CountryRepository extends CrudRepository<Country, Integer> {

}
