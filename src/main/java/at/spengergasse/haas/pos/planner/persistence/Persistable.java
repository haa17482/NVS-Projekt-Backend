package at.spengergasse.haas.pos.planner.persistence;

public interface Persistable<PK> {

    PK getId();

    void afterInsert(PK id);

    void afterDelete();
}
