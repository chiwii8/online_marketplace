package service;

import domain.Section;
import exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.SectionRepository;
import utils.ExceptionUtils;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class SectionService {

    private final SectionRepository sectionRepository;

    @Autowired
    public SectionService(SectionRepository sectionRepository){
        this.sectionRepository = sectionRepository;
    }

    ///CRUD Operations
    public Section create(){
        Section section;

        section = new Section();

        return section;
    }

    public Section findById(Long sectionId){
        if(sectionId==null){
            throw new NullPointerException(ExceptionUtils.getNullMessage(Section.class));
        }

        Optional<Section> optionalSection = this.sectionRepository.findById(sectionId);

        if(optionalSection.isEmpty()){
            throw new NotFoundException(Section.class);
        }

        return optionalSection.get();
    }

    public List<Section> findAll(){
        List<Section> sectionList;

        sectionList = this.sectionRepository.findAll();

        return  sectionList;
    }

    public void save(Section section){
        if(section==null)
            throw new NullPointerException(ExceptionUtils.getNullMessage(Section.class));

        this.sectionRepository.save(section);
    }

    public void delete(Section section){
        if(section==null){
            throw new NullPointerException(ExceptionUtils.getNullMessage(Section.class));
        }
        if(section.getId()==null)
            throw new NotFoundException(Section.class);

        this.sectionRepository.delete(section);
    }

    ///Extra Queries

    public Section findByName(String name){
        if(name==null){
            throw new NullPointerException(ExceptionUtils.getNullMessage(Section.class));
        }
        if(name.isBlank())
            throw new NotFoundException(Section.class);

        return this.sectionRepository.findByName(name);

    }
}
