package app.modelAsembler;

import app.controller.SectionController;
import app.domain.Section;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class SectionModelAssembler implements RepresentationModelAssembler<Section, EntityModel<Section>> {

    @Override
    public EntityModel<Section> toModel(Section section) {
        return EntityModel.of(section, //
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SectionController.class).section(section.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SectionController.class).allSection()).withRel("sections"));
    }

}