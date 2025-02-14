package app.modelAsembler;

import app.controller.PersonController;
import app.domain.actor.Person;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class PersonModelAssembler implements RepresentationModelAssembler<Person, EntityModel<Person>> {

    @Override
    public EntityModel<Person> toModel(Person person) {
        return EntityModel.of(person, //
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController.class).person(person.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController.class).allPerson()).withRel("persons"));
    }

}