package app.service;

import app.domain.Section;
import app.exceptions.IllegalBlankException;
import app.exceptions.NotFoundException;
import app.repository.SectionRepository;
import app.service.interfaces.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionService implements ICommonService<Section,Long> {

    private final SectionRepository sectionRepository;

   @Autowired
    public SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }


    @Override
    public Section create() {
        return new Section();
    }

    @Override
    public Section findById(Long id) throws NotFoundException,IllegalArgumentException{
        Optional<Section> optionalSection;
        if(id==null)
            throw new IllegalArgumentException("The id must not be null");

        optionalSection = sectionRepository.findById(id);

        if(optionalSection.isEmpty())
            throw new NotFoundException(Section.class);

       return optionalSection.get();
    }

    @Override
    public List<Section> findAll() {
       List<Section> sections;

       sections = sectionRepository.findAll();

        return sections;
    }

    @Override
    public void delete(Section entity) throws IllegalArgumentException, OptimisticLockingFailureException {
        this.sectionRepository.delete(entity);
    }

    @Override
    public void deleteById(Long id) throws NotFoundException, IllegalBlankException{
       if(id==null)
            throw new IllegalArgumentException("the id must not be null");

       if(!this.sectionRepository.existsById(id)){
           throw new NotFoundException(Section.class);
       }

       this.sectionRepository.deleteById(id);

    }

    @Override
    public Section save(Section entity) throws IllegalArgumentException,OptimisticLockingFailureException{
        return this.sectionRepository.save(entity);
    }
}