package com.greenbox.test.demo.service;

import com.greenbox.test.demo.entity.GrowProgram;
import com.greenbox.test.demo.entity.GrowProgram;
import com.greenbox.test.demo.entity.User;

import java.util.List;
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
    GrowProgram read(Long id);

    /**Обновляет growProgram с заданным ID,
     * в соответствии с переданным growProgram'ом
     * @param growProgram - growProgram в соответсвии с которым нужно обновить данные
     * @param id - id growProgram которого нужно обновить
     * @return - true если данные были обновлены, иначе false*/
    boolean update(GrowProgram growProgram, Long id);

    /**Удаляет growProgram с заданным ID
     * @param id - id growProgram, которого нужно удалить
     * @return - true если growProgram был удален, иначе false*/
    boolean delete(Long id);

    List<GrowProgram> findFavoritesProgramsForUser(User user);
}
