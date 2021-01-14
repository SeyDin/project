package com.greenbox.test.demo.service.growParametrsServices;

import com.greenbox.test.demo.entity.growParametrs.WateringParameters;
import com.greenbox.test.demo.repository.growParametrsRepo.WateringParametersRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class WateringParametersServiceImpl implements CommonPointsService<WateringParameters>{

    private final WateringParametersRepository wateringParametersRepository;

    public WateringParametersServiceImpl(WateringParametersRepository wateringParametersRepository) {
        this.wateringParametersRepository = wateringParametersRepository;
    }

    @Override
    public void create(WateringParameters wateringParameters) {
        wateringParametersRepository.save(wateringParameters);
    }

    @Override
    public Set<WateringParameters> readAll() {
        return wateringParametersRepository.findAll();
    }

    @Override
    public WateringParameters read(long id) {
        return wateringParametersRepository.findById(id).orElse(null);
    }

    @Override
    public void update(WateringParameters wateringParameters, Long id) {
        if (wateringParametersRepository.existsById(id)) {
            wateringParameters.setId(id);
            wateringParametersRepository.save(wateringParameters);
            //return true;
        }
        //return false;
    }

    @Override
    public void delete(long id) {
        wateringParametersRepository.deleteById(id);
    }
}
