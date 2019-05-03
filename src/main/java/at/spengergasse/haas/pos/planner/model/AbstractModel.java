package at.spengergasse.haas.pos.planner.model;

import at.spengergasse.haas.pos.planner.service.AbstractDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.MappedSuperclass;
import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
@Getter
public class AbstractModel extends AbstractPersistable<Long> {

    protected String identifier;

    AbstractModel(AbstractDto dto) {
        this.identifier = Optional.ofNullable(dto.getIdentifier())
                .orElse(UUID.randomUUID().toString());
    }
}
