package iluha.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    Optional<T> save (T entity);
    Optional<T> findById (int id);
    Optional<T> update (T entity);
    int deleteById (int id);
    List<T> findAll();
}
