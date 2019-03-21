package at.spengergasse.haas.pos.planner.persistence;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public abstract class AbstractDao<P extends BasePersistable, PK> implements Dao<P, PK> {

    protected final EntityManager entityManager;

    @Override
    public P findById(PK id) {
        log.debug("Find persistable with this: {} ID",id);
        return entityManager.find(getPClass(), id);
    }

    @Override
    public List<P> findAll() {
        log.debug("Find all persistables from {}",getPClass().getSimpleName());

        return entityManager.createQuery(
                "select p from " + getPClass().getSimpleName() + " p",
                 getPClass())
                .getResultList();
    }

    @Override
    public P save(P persistable) {
        log.debug("Save persistable {}", persistable);
        entityManager.persist(persistable);
        return persistable;
    }

    @Override
    public void delete(P persistable) {
        log.debug("Delete persistable {}",persistable);

        entityManager.remove(persistable);
        persistable.setId(null);
    }


    protected abstract Class<P> getPClass();
}
