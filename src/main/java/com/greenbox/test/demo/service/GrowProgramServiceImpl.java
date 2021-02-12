package com.greenbox.test.demo.service;

import com.greenbox.test.demo.entity.GrowProgram;
import com.greenbox.test.demo.entity.User;
import com.greenbox.test.demo.repository.GrowProgramRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class GrowProgramServiceImpl implements GrowProgramService{
    private final GrowProgramRepository growProgramRepository;

    public GrowProgramServiceImpl(GrowProgramRepository growProgramRepository) {
        this.growProgramRepository = growProgramRepository;
    }

    @Override
    public void create(GrowProgram growProgram) {
        growProgramRepository.save(growProgram);
    }

    @Override
    public List<GrowProgram>  readAll() {
        return growProgramRepository.findAll();
    }

    @Override
    public Optional<GrowProgram> read(Long id) {
        return growProgramRepository.findById(id);
    }

    @Override
    public boolean delete(Long id) {
        if (growProgramRepository.existsById(id)) {
            growProgramRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<GrowProgram> findFavoritesProgramsForUser(User user) {
        return growProgramRepository.findAllByUserCreator(user);
    }

}
