package com.pco.pco.repository;

import com.pco.pco.entities.Country;
import org.springframework.data.repository.CrudRepository;
public interface CountryRepository extends CrudRepository<Country, Integer> {

}
