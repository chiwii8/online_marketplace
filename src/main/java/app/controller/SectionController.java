package app.controller;

import app.domain.Section;
import app.exceptions.NotFoundException;
import app.modelAsembler.SectionModelAssembler;
import app.service.interfaces.ICommonService;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//@RestController
public class SectionController {

    private final ICommonService<Section,Long> sectionService;
    private final SectionModelAssembler assembler;

    @Autowired
    public SectionController(ICommonService<Section,Long> sectionService, SectionModelAssembler assembler) {
        this.sectionService = sectionService;
        this.assembler = assembler;
    }

    @GetMapping("/section/{id}")
    public EntityModel<Section> section(@PathVariable Long id){
        Section section;

        section = this.sectionService.findById(id);
        return assembler.toModel(section);
    }


    @GetMapping("/sections")
    public CollectionModel<EntityModel<Section>> allSection(){
        List<EntityModel<Section>> sections = this.sectionService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return  CollectionModel.of(sections, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SectionController.class).allSection()).withSelfRel());
    }

    @PutMapping("/section/{id}")
    public ResponseEntity<Section> updateSection(@PathVariable Long id,@RequestBody Section section){

        try {
            Section result = this.sectionService.save(section);
            return new ResponseEntity<>(result, HttpStatus.OK);

        }catch (IllegalArgumentException | OptimisticEntityLockException ex){
            return ResponseEntity.badRequest().body(section);
        }

    }

    @PostMapping("/section/")
    public ResponseEntity<Section> newSection(@RequestBody Section section){
        try {
            Section result = this.sectionService.save(section);
            return new ResponseEntity<>(result, HttpStatus.OK);

        }catch (IllegalArgumentException | OptimisticEntityLockException ex){
            return ResponseEntity.badRequest().body(section);
        }
    }

    @DeleteMapping("/section/{id}")
    public ResponseEntity<Section> deleteSection(@PathVariable Long id){
        try{
            this.sectionService.deleteById(id);

            return ResponseEntity.noContent().build();
        }catch (NotFoundException | IllegalArgumentException | OptimisticEntityLockException ex){
            return ResponseEntity.badRequest().build();
        }
    }


}