package com.greenbox.test.demo.repository.growParametrsRepo;

import com.greenbox.test.demo.entity.growParametrs.Points;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@Component
@Scope(value="prototype") /*Необходимо, т.к. этот репозиторий используется
 тремя сервисами (у каждого должен быть свой экземпляр)*/
public class PointsRepository {
    private final JdbcTemplate jdbcTemplate;
    private String tableName;

    @Autowired
    public PointsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Points findOne(long id){
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT name, arr from " + tableName +" where id = ?", id);
        if (maps.size() != 0) {
            Map<String, Object> stringObjectMap = maps.get(0);
            Object tempArray = stringObjectMap.get("arr");
            String name = (String) stringObjectMap.get("name");
            List<Double> arrayOfTemperature = sqlArrayToListDoubleConverter(tempArray);
            return new Points(id, name, arrayOfTemperature);
        }
        return null;
    }

    public Set<Points> findAll(){
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT id, name, arr from " + tableName);
        Set<Points> temperaturePointsSet = new HashSet<>();
        for (Map<String, Object> stringObjectMap:
                maps) {
            Points temperaturePoints = new Points();
            temperaturePoints.setId((Long) stringObjectMap.get("id"));
            temperaturePoints.setName((String) stringObjectMap.get("name"));
            Object tempArray = stringObjectMap.get("arr");
            temperaturePoints.setArray(sqlArrayToListDoubleConverter(tempArray));
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
        for (int i=0; i < objectArray.length; i++) {
            Double value = (Double) objectArray[i];
            arrayOfTemperature.add(value);
        }
        return arrayOfTemperature;
    }

    public void save(Points temperaturePoints){
        String query = "INSERT INTO " + tableName + " (name, arr) VALUES (?,?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, temperaturePoints.getName());
            Array aArray = connection.createArrayOf("float8", temperaturePoints.getArray().toArray());
            preparedStatement.setArray(2, aArray);
            return preparedStatement;
        });
    }

    public void update(Points temperaturePoints){
        String query = "UPDATE " + tableName + " SET name = ?, arr = ? WHERE id = ?";
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, temperaturePoints.getName());
            Array aArray = connection.createArrayOf("float8", temperaturePoints.getArray().toArray());
            preparedStatement.setArray(2, aArray);
            preparedStatement.setLong(3, temperaturePoints.getId());
            return preparedStatement;
        });
    }

    public void delete(long id){
        String query = "DELETE FROM " + tableName + " WHERE id = ?";
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            return preparedStatement;
        });
    }
}
