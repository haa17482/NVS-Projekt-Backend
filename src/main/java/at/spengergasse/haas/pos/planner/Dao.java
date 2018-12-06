package at.spengergasse.haas.pos.planner;


import java.util.List;

public interface Dao<P extends Persistable<PK>, PK> {

    P findById(PK id);

    List<P> findAll();

    P save(P persistable);

    P delete(P persistable);
}
