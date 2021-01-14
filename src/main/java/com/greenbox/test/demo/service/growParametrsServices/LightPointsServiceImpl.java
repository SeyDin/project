package com.greenbox.test.demo.service.growParametrsServices;

import com.greenbox.test.demo.entity.growParametrs.Points;
import com.greenbox.test.demo.repository.growParametrsRepo.PointsRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LightPointsServiceImpl implements LightPointsService {
    private final PointsRepository pointsRepository;

    public LightPointsServiceImpl(PointsRepository pointsRepository) {
        this.pointsRepository = pointsRepository;
        this.pointsRepository.setTableName("light_values");
    }

    @Override
    public void create(Points lightPoints) {
        pointsRepository.save(lightPoints);
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
    public void update(Points lightPoints, Long id) {
        lightPoints.setId(id);
        pointsRepository.update(lightPoints);
    }

    @Override
    public void delete(long id) {
        pointsRepository.delete(id);
    }

}
