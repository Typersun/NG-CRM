package iluha.repository.impl;

import iluha.model.Device;
import iluha.model.Programmer;
import iluha.repository.DeviceRepository;
import iluha.utils.DbConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeviceRepositoryImpl implements DeviceRepository {
    @Override
    public List<Device> findByProgrammerId(int programmerId) {
        Connection connection = DbConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM devices WHERE programmer_id = ?;");
            preparedStatement.setInt(1, programmerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Device> devices = new ArrayList<>();
            while (resultSet.next()) {
                devices.add(Device.builder()
                        .id(resultSet.getInt("id"))
                        .device(resultSet.getString("device"))
                        .build());
            }
            return devices;

    } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<Device> save(Device entity) {
        Connection connection = DbConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO devices(device) VALUES (?);");
            preparedStatement.setString(1, entity.getDevice());
            preparedStatement.execute();
            return Optional.of(entity);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Device> findById(int id) {
        Connection connection = DbConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM devices LEFT JOIN programmer p on devices.programmer_id = p.id WHERE devices.id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Programmer programmer = null;
                resultSet.getInt("programmer_id");
                if (resultSet.wasNull() == false) {
                    programmer = Programmer.builder()
                            .id(resultSet.getInt("programmer_id"))
                            .firstName(resultSet.getString("first_name"))
                            .lastName(resultSet.getString("last_name"))
                            .age(resultSet.getInt("age"))
                            .build();
                }
                return Optional.of(Device.builder()
                        .id(resultSet.getInt("id"))
                        .device(resultSet.getString("device"))
                        .programmer(programmer)
                        .build()
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Device> update(Device entity) {
        Connection connection = DbConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE devices SET device = ? WHERE id = ?;");
            preparedStatement.setString(1, entity.getDevice());
            preparedStatement.setInt(2, entity.getId());
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
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM devices WHERE id = (?);");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return id;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0;
    }

    @Override
    public List<Device> findAll() {
        Connection connection = DbConnectionFactory.getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM devices;");
            List<Device> devices = new ArrayList<>();
            while (resultSet.next()) {
                devices.add(Device.builder()
                        .device(resultSet.getString("device"))
                        .id(resultSet.getInt("id"))
                        .build());
            }
            return devices;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return new ArrayList<>();
    }
}
