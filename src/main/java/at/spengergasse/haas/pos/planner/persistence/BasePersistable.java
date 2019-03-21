package at.spengergasse.haas.pos.planner.persistence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Setter
@Getter
@EqualsAndHashCode
public abstract class BasePersistable {

    @Id
    @GeneratedValue
    private Long id;

}
