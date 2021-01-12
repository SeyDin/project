package com.greenbox.test.demo.repository;

import com.greenbox.test.demo.entity.growParametrs.TemperaturePoints;
import com.greenbox.test.demo.entity.growParametrs.mapers.TemperaturePointsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@Component
public class TemperaturePointsRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TemperaturePointsRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public TemperaturePoints findOne(long id){
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT name, arr from temperatures where id = ?", id);
        if (maps.size() != 0) {
            Map<String, Object> stringObjectMap = maps.get(0);
            Object tempArray = stringObjectMap.get("arr");
            String name = (String) stringObjectMap.get("name");
            List<Double> arrayOfTemperature = sqlArrayToListDoubleConverter(tempArray);
            return new TemperaturePoints(id, name, arrayOfTemperature);
        }
        return null;
    }

    public Set<TemperaturePoints> findAll(){
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT id, name, arr from temperatures");
        Set<TemperaturePoints> temperaturePointsSet = new HashSet<>();
        for (Map<String, Object> stringObjectMap:
             maps) {
            TemperaturePoints temperaturePoints = new TemperaturePoints();
            temperaturePoints.setId((Long) stringObjectMap.get("id"));
            temperaturePoints.setName((String) stringObjectMap.get("name"));
            Object tempArray = stringObjectMap.get("arr");
            temperaturePoints.setArrayOfTemperature(sqlArrayToListDoubleConverter(tempArray));
            temperaturePointsSet.add(temperaturePoints);
        }
        return temperaturePointsSet;
    }

    // Далее идёт очень страшный метод по мотивам ссылки:
    // https://www.programcreek.com/java-api-examples/?class=java.sql.Array&method=getArray
    List<Double> sqlArrayToListDoubleConverter(Object tempArray){
        Array tempArray1 = (Array) tempArray;
        Object obj = null;
        try {
            obj = tempArray1.getArray();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        Object [] objectArray = (Object []) obj;
        List<Double> arrayOfTemperature= new ArrayList<>();
        for (int i=1; i < objectArray.length; i++) {
            Double value = (Double) objectArray[i];
            arrayOfTemperature.add(value);
        }
        return arrayOfTemperature;
    }

    public void save(TemperaturePoints temperaturePoints){
        String query = "INSERT INTO temperatures (name, arr) VALUES (?,?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, temperaturePoints.getName());
            Array aArray = connection.createArrayOf("float8", temperaturePoints.getArrayOfTemperature().toArray());
            preparedStatement.setArray(2, aArray);
            return preparedStatement;
        });
    }

    public void update(TemperaturePoints temperaturePoints){
        String query = "UPDATE temperatures SET name = ?, arr = ? WHERE id = ?";
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, temperaturePoints.getName());
            Array aArray = connection.createArrayOf("float8", temperaturePoints.getArrayOfTemperature().toArray());
            preparedStatement.setArray(2, aArray);
            preparedStatement.setLong(3, temperaturePoints.getId());
            return preparedStatement;
        });
    }
}
