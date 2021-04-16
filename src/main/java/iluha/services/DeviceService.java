package iluha.services;

import iluha.exceptions.InvalidParameters;
import iluha.exceptions.NotFoundException;
import iluha.exceptions.SomethingWrongWithDatabaseException;
import iluha.model.Device;

import java.util.List;

public interface DeviceService {
    void save(Device device) throws InvalidParameters;
    Device getById(int id) throws NotFoundException;
    void update(Device device) throws InvalidParameters;
    List<Device> findAll() throws SomethingWrongWithDatabaseException;
    void deleteById(int id) throws NotFoundException;
}
