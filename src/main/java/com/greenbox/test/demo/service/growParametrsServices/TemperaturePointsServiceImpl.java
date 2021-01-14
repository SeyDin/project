package com.greenbox.test.demo.service.growParametrsServices;

import com.greenbox.test.demo.entity.growParametrs.Points;
import com.greenbox.test.demo.repository.growParametrsRepo.PointsRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TemperaturePointsServiceImpl implements TemperaturePointsService {

    private final PointsRepository pointsRepository;

    public TemperaturePointsServiceImpl(PointsRepository pointsRepository) {
        this.pointsRepository = pointsRepository;
        this.pointsRepository.setTableName("temperature_values");
    }

    @Override
    public void create(Points temperaturePoints) {
        pointsRepository.save(temperaturePoints);
    }

    @Override
    public Set<Points> readAll() {
        return pointsRepository.findAll();
    }

    @Override
    public Points read(long id) {
        return pointsRepository.findOne(id);
    }

    @Override
    public void update(Points temperaturePoints, Long id) {
        temperaturePoints.setId(id);
        pointsRepository.update(temperaturePoints);
    }

    @Override
    public void delete(long id) {
        pointsRepository.delete(id);
    }

}
