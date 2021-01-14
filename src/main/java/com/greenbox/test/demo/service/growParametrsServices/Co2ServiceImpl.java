package com.greenbox.test.demo.service.growParametrsServices;

import com.greenbox.test.demo.entity.growParametrs.Points;
import com.greenbox.test.demo.repository.growParametrsRepo.PointsRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class Co2ServiceImpl implements Co2Service {
     private final PointsRepository pointsRepository;

    
   public Co2ServiceImpl(PointsRepository pointsRepository) {
       this.pointsRepository = pointsRepository;
       this.pointsRepository.setTableName("co2_values");
   }


    @Override
    public void create(Points co2Points) {
        pointsRepository.save(co2Points);
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
    public void update(Points co2Points, Long id) {
        co2Points.setId(id);
        pointsRepository.update(co2Points);
    }

    @Override
    public void delete(long id) {
        pointsRepository.delete(id);
    }
}
