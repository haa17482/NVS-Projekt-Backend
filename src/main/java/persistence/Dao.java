package persistence;


import java.util.List;

public interface Dao<P extends BasePersistable, PK> {

    P findById(PK id);

    List<P> findAll();

    P save(P persistable);

    void delete(P persistable);
}
