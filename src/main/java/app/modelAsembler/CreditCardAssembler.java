package app.modelAsembler;

import app.controller.CreditCardController;
import app.domain.CreditCard;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class CreditCardAssembler implements RepresentationModelAssembler<CreditCard,EntityModel<CreditCard>> {


    @Override
    public EntityModel<CreditCard> toModel(CreditCard creditCard) {
        return EntityModel.of(creditCard,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CreditCardController.class).creditCard(creditCard.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CreditCardController.class).allCreditCards()).withRel("creditcards")
        );
    }
}