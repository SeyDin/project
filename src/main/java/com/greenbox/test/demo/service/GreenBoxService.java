package com.greenbox.test.demo.service;

import com.greenbox.test.demo.entity.GreenBox;

import java.util.List;

public interface GreenBoxService {
    /**Создает новый greenBox
     * @param greenBox - greenBox для создания*/
    void create(GreenBox greenBox);

    /**Возвращает список всех имеющихся greenBox
     * @return список greenBox*/
    List<GreenBox> readAll();

    /**Возвращает greenBox по его ID
     * @param id - ID greenBox
     * @return - объект greenBox с заданным ID*/
    GreenBox read(Long id);

    /**Обновляет greenBox с заданным ID,
     * в соответствии с переданным greenBoxом
     * @param greenBox - greenBox в соответсвии с которым нужно обновить данные
     * @param id - id greenBox которого нужно обновить
     * @return - true если данные были обновлены, иначе false*/
    boolean update(GreenBox greenBox, Long id);

    /**Удаляет greenBox с заданным ID
     * @param id - id greenBox, которого нужно удалить
     * @return - true если greenBox был удален, иначе false*/
    boolean delete(Long id);
}
