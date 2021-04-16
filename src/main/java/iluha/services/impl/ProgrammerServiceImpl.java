package iluha.services.impl;

import iluha.exceptions.InvalidParameters;
import iluha.exceptions.NotFoundException;
import iluha.exceptions.SomethingWrongWithDatabaseException;
import iluha.model.Programmer;
import iluha.repository.DeviceRepository;
import iluha.repository.ProgrammerRepository;
import iluha.services.ProgrammerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProgrammerServiceImpl implements ProgrammerService {

    private ProgrammerRepository programmerRepository;
    private DeviceRepository deviceRepository;

    public ProgrammerServiceImpl(ProgrammerRepository programmerRepository, DeviceRepository deviceRepository) {
        this.programmerRepository = programmerRepository;
        this.deviceRepository = deviceRepository;
    }

    @Override
    public void save(Programmer programmer) throws InvalidParameters {
        Optional<Programmer> optionalProgrammer = programmerRepository.save(programmer);
        if(optionalProgrammer.isPresent() == false) {
            throw new InvalidParameters("Invalid parameters -_- Try again");
        }

    }

    @Override
    public Programmer getById(int id) throws NotFoundException {
        Optional<Programmer> optionalProgrammer = programmerRepository.findById(id);
        if(optionalProgrammer.isPresent() == false) {
            throw new NotFoundException("Programmer with id " + id + " not found");
        }
        Programmer programmer = optionalProgrammer.get();
        programmer.setDevices(deviceRepository.findByProgrammerId(id));
        return programmer;
    }

    @Override
    public void update(Programmer programmer) throws NotFoundException {
        Optional<Programmer> optionalProgrammer = programmerRepository.update(programmer);
        if(optionalProgrammer.isPresent() == false) {
            throw new NotFoundException("Programmer with id " + programmer.getId() + " not found");
        }
    }

    @Override
    public List<Programmer> findAll() throws SomethingWrongWithDatabaseException {
        List<Programmer> programmers = new ArrayList<>();
        programmers = programmerRepository.findAll();
        if(programmers.isEmpty()) {
            throw new SomethingWrongWithDatabaseException("Database is empty or i don't know");
        }
        return programmers;

    }

    @Override
    public void deleteById(int id) throws NotFoundException {
        int result = programmerRepository.deleteById(id);
        if(result == 0) {
            throw new NotFoundException("Programmer with id " + id + " not found");
        }

    }
}
