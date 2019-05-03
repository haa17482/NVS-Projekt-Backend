package at.spengergasse.haas.pos.planner.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public abstract class AbstractDto extends ResourceSupport {

    private String identifier;

}