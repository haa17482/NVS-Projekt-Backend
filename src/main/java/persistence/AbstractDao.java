package persistence;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@AllArgsConstructor
public abstract class AbstractDao<P extends BasePersistable, PK> implements Dao<P, PK> {

    private EntityManager entityManager;

    @Override
    public P findById(PK id) {
        return entityManager.find(getPClass(), id);
    }

    @Override
    public List<P> findAll() {
        return entityManager.createQuery(
                "select p from " + getPClass().getSimpleName() + " p",
                 getPClass())
                .getResultList();
    }

    @Override
    public P save(P persistable) {
        entityManager.persist(persistable);
        return persistable;
    }

    @Override
    public void delete(P persistable) {
        entityManager.remove(persistable);
        persistable.setId(null);
    }


    protected abstract Class<P> getPClass();
}
