package repository;

import java.io.IOException;

public abstract class FILERepository<T> extends CRUDRepository<T>{
    protected String filename;

    public FILERepository(String filename) {
        this.filename = filename;
    }

    protected abstract void loadFromFile();
    public abstract void writeOne(T entity);
    public abstract void writeAll() throws IOException;

}
