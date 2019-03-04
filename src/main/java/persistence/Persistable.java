package persistence;

public interface Persistable<PK> {

    PK getId();

    void afterInsert(PK id);

    void afterDelete();
}
