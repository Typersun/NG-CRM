package iluha.services.impl;

import iluha.exceptions.InvalidParameters;
import iluha.exceptions.NotFoundException;
import iluha.exceptions.SomethingWrongWithDatabaseException;
import iluha.model.Device;
import iluha.repository.DeviceRepository;
import iluha.repository.ProgrammerRepository;
import iluha.services.DeviceService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeviceServiceImpl implements DeviceService {
    private ProgrammerRepository programmerRepository;
    private DeviceRepository deviceRepository;

    public DeviceServiceImpl(ProgrammerRepository programmerRepository, DeviceRepository deviceRepository) {
        this.programmerRepository = programmerRepository;
        this.deviceRepository = deviceRepository;
    }

    @Override
    public void save(Device device) throws InvalidParameters {
        Optional<Device> optionalDevice = deviceRepository.save(device);
        if(optionalDevice.isPresent() == false) {
            throw new InvalidParameters();
        }
    }

    @Override
    public Device getById(int id) throws NotFoundException {
        Optional<Device> optionalDevice = deviceRepository.findById(id);
        if (optionalDevice.isPresent() == false) {
            throw new NotFoundException();
        }
        Device device = optionalDevice.get();
        return device;
    }

    @Override
    public void update(Device device) throws InvalidParameters {
        Optional<Device> optionalDevice = deviceRepository.update(device);
        if (optionalDevice.isPresent() == false) {
            throw new InvalidParameters();
        }
    }

    @Override
    public List<Device> findAll() throws SomethingWrongWithDatabaseException {
        List<Device> devices = new ArrayList<>();
        devices = deviceRepository.findAll();
        if (devices.isEmpty()) {
            throw new SomethingWrongWithDatabaseException();
        }
        return devices;
    }

    @Override
    public void deleteById(int id) throws NotFoundException {
        int result = deviceRepository.deleteById(id);
        if (result == 0) {
            throw new NotFoundException();
        }

    }
}
