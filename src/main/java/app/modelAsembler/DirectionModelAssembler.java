package app.modelAsembler;

import app.controller.DirectionController;
import app.domain.Direction;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class DirectionModelAssembler implements RepresentationModelAssembler<Direction, EntityModel<Direction>> {

    @Override
    public EntityModel<Direction> toModel(Direction direction) {
        return EntityModel.of(direction, //
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DirectionController.class).direction(direction.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DirectionController.class).allDirection()).withRel("directions"));
    }

}