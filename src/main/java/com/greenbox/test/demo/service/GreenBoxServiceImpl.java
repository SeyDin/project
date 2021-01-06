package com.greenbox.test.demo.service;

import com.greenbox.test.demo.entity.GreenBox;
import com.greenbox.test.demo.repository.GreenBoxRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GreenBoxServiceImpl implements GreenBoxService{
    private final GreenBoxRepository greenBoxRepository;

    public GreenBoxServiceImpl(GreenBoxRepository greenBoxRepository) {
        this.greenBoxRepository = greenBoxRepository;
    }

    @Override
    public void create(GreenBox greenBox) {
        greenBoxRepository.save(greenBox);
    }

    @Override
    public List<GreenBox>  readAll() {
        return greenBoxRepository.findAll();
    }

    @Override
    public GreenBox read(Long id) {
        return greenBoxRepository.getOne(id);
    }

    @Override
    public boolean update(GreenBox greenBox, Long id) {
        if (greenBoxRepository.existsById(id)) {
            greenBox.setId(id);
            greenBoxRepository.save(greenBox);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (greenBoxRepository.existsById(id)) {
            greenBoxRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
