package iluha.services;

import iluha.exceptions.InvalidParameters;
import iluha.exceptions.NotFoundException;
import iluha.exceptions.SomethingWrongWithDatabaseException;
import iluha.model.Programmer;

import java.util.List;

public interface ProgrammerService {
    void save(Programmer programmer) throws InvalidParameters;
    Programmer getById(int id) throws NotFoundException;
    void update(Programmer programmer) throws NotFoundException;
    List<Programmer> findAll() throws SomethingWrongWithDatabaseException;
    void deleteById(int id) throws NotFoundException;
}
