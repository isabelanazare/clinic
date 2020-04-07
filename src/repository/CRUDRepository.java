package repository;

import java.util.*;

public class CRUDRepository<T> {
    List<T> entities;

    public CRUDRepository() {
        entities = new ArrayList<T>();
    }

    public List<T> getEntities() {
        return this.entities;
    }

    public ListIterator<T> findAll() {
        return entities.listIterator();
    }

    public void save(T entity) {
        entities.add(entity);
    }

}
