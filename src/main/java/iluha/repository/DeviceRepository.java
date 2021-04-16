package iluha.repository;

import iluha.model.Device;

import java.util.List;

public interface DeviceRepository extends CrudRepository<Device> {
    List<Device> findByProgrammerId(int programmerId);
}
