package at.spengergasse.haas.pos.planner.presentation;

import at.spengergasse.haas.pos.planner.service.AbstractDto;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public abstract class AbstractController <D extends AbstractDto> {

    abstract ResponseEntity<List<D>> findAll();
    abstract ResponseEntity<D> findById(@PathVariable String identifier);
    abstract ResponseEntity<D> create(@RequestBody D dto);
    abstract ResponseEntity<Void> delete(@PathVariable String identifier);

    D addSelfLink(D dto) {
        Link selfLink = linkTo(methodOn(this.getClass()).findById(dto.getIdentifier())).withSelfRel();
        dto.add(selfLink);
        return dto;
    }
}
