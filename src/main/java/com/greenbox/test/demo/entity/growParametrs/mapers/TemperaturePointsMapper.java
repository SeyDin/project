package com.greenbox.test.demo.entity.growParametrs.mapers;

import com.greenbox.test.demo.entity.growParametrs.TemperaturePoints;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TemperaturePointsMapper implements RowMapper<TemperaturePoints> {
    public TemperaturePoints mapRow(ResultSet resultSet, int i) throws SQLException {
        System.out.println("mapper method");
        return TemperaturePoints.builder().
                id(resultSet.getLong(1)).
                name(resultSet.getString(2)).
                //arrayOfTemperature(resultSet.getFloat(3)).
                build();
    }
}
