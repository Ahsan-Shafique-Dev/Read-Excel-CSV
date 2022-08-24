package com.task.readexcel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ReadExcelRepo
{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReadExcelRepo(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addRecord(ModelClass modelClass)
    {
        String querry = "INSERT INTO excel (id, names, department) VALUES (?,?,?)";
        return jdbcTemplate.update(querry, modelClass.getSrNo(), modelClass.getName(), modelClass.getDepartment());
    }
}
