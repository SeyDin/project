package com.greenbox.test.demo.entity.growParametrs.mapers;

import com.greenbox.test.demo.entity.growParametrs.TemperaturePoints;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TemperaturePointsMapper {
    public TemperaturePoints mapRow(ResultSet resultSet, int i) throws SQLException {
        return TemperaturePoints.builder().
                id(resultSet.getLong(1)).
                name(resultSet.getString(2)).
                arrayOfTemperature(resultSet.getDouble(3)).
                build();
    }
}
