package com.example.sweater.dao;

import com.example.sweater.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("mysqlDao")
public class MySqlPersonDataAccess implements PersonDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MySqlPersonDataAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        final String sql = "INSERT INTO person values(?, ?)";
        return jdbcTemplate.update(sql, id.toString(), person.getName());
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "SELECT * from person";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(id, name);
        });
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sql = "SELECT name FROM person WHERE id = ?";
        Person person = jdbcTemplate.queryForObject(sql, new Object[]{id.toString()}, (resultSet, i) -> {
            String name = resultSet.getString("name");
            return new Person(id, name);
        });
        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(UUID id) {
        final String sql = "DELETE FROM person WHERE id = ?";
        return jdbcTemplate.update(sql, id.toString());
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        final String sql = "UPDATE person " +
                "SET name = ?" +
                "WHERE id = ?";
        return jdbcTemplate.update(sql, person.getName(), id.toString());
    }
}
