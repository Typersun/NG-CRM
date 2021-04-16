package iluha.repository.impl;

import iluha.model.Programmer;
import iluha.repository.ProgrammerRepository;
import iluha.utils.DbConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProgrammerRepositoryImpl implements ProgrammerRepository {
    @Override
    public Optional<Programmer> save(Programmer pr)  {
        Connection connection = DbConnectionFactory.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO programmer(first_name, last_name, age) VALUES (?, ?, ?);");
            preparedStatement.setString(1, pr.getFirstName());
            preparedStatement.setString(2, pr.getLastName());
            preparedStatement.setInt(3, pr.getAge());
            preparedStatement.execute();
            return Optional.of(pr);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Programmer> findById(int id) {
        Connection connection = DbConnectionFactory.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM programmer WHERE id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(
                        Programmer.builder()
                                .id(resultSet.getInt("id"))
                                .firstName(resultSet.getString("first_name"))
                                .lastName(resultSet.getString("last_name"))
                                .age(resultSet.getInt("age"))
                                .build()
                );
            } else {
                return Optional.empty();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Programmer> update(Programmer entity) {
        Connection connection = DbConnectionFactory.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE programmer SET first_name = ?, last_name = ?, age = ? WHERE id = ?;");
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setInt(3, entity.getAge());
            preparedStatement.setInt(4, entity.getId());
            preparedStatement.execute();
            return Optional.of(entity);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();

    }

    @Override
    public int deleteById(int id) {
        Connection connection = DbConnectionFactory.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM programmer WHERE id = (?);");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return id; // Возвращаем айди, если всё прошло по маслу
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;  // Или ноль, если всё не очень

    }

    @Override
    public List<Programmer> findAll() {
        Connection connection = DbConnectionFactory.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM programmer");
            List<Programmer> programmers = new ArrayList<>();
            while (resultSet.next()) {
                programmers.add(Programmer.builder()
                                    .id(resultSet.getInt("id"))
                                    .firstName(resultSet.getString("first_name"))
                                    .lastName(resultSet.getString("last_name"))
                                    .age(resultSet.getInt("age"))
                                    .build());
            }
            return programmers;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return new ArrayList<>();
    }
}
