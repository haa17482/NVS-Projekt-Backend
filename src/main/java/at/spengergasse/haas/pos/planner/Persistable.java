package at.spengergasse.haas.pos.planner;

public interface Persistable<PK> {

    PK getId();

    void afterInsert(PK id);

    void afterDelete();
}
