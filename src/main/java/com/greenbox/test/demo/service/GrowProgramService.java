package com.greenbox.test.demo.service;

import com.greenbox.test.demo.entity.GrowProgram;
import com.greenbox.test.demo.entity.GrowProgram;
import com.greenbox.test.demo.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface GrowProgramService {
    /**Создает новый growProgram
     * @param growProgram - growProgram для создания*/
    void create(GrowProgram growProgram);

    /**Возвращает список всех имеющихся growProgram
     * @return список growProgram*/
    List<GrowProgram> readAll();

    /**Возвращает growProgram по его ID
     * @param id - ID growProgram
     * @return - объект growProgram с заданным ID*/
    Optional<GrowProgram> read(Long id);

    /**Удаляет growProgram с заданным ID
     * @param id - id growProgram, которого нужно удалить
     * @return - true если growProgram был удален, иначе false*/
    boolean delete(Long id);

    /**Возвращает список всех избранных growProgram
     * @param user - user, у которого надо получить список избранных программ
     * @return - список избранных, а есл их нет, то пустой список*/
    List<GrowProgram> findFavoritesProgramsForUser(User user);
}
