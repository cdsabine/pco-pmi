package com.pco.pco.repository;

import com.pco.pco.entities.productLocationChanges;
import org.springframework.data.repository.CrudRepository;

public interface ProductLocationChangeRepository  extends CrudRepository<productLocationChanges, Integer> {
}
