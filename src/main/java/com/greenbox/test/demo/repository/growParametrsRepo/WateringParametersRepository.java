package com.greenbox.test.demo.repository.growParametrsRepo;

import com.greenbox.test.demo.entity.growParametrs.WateringParameters;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface WateringParametersRepository extends CrudRepository<WateringParameters, Long> {
    Set<WateringParameters> findAll();
}
