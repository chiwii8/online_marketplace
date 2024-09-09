package service;

import domain.Direction;
import exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.DirectionRepository;
import utils.ExceptionUtils;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class DirectionService {

    private final DirectionRepository directionRepository;
    
    @Autowired
    public DirectionService(DirectionRepository directionRepository){
        this.directionRepository=directionRepository;
    }
    
    ///CRUD Operations
    public Direction create(){
        Direction direction;

        direction = new Direction();

        return direction;
    }

    public Direction findById(Long directionId){
        if(directionId==null){
            throw new NullPointerException(ExceptionUtils.getNullMessage(Direction.class));
        }

        Optional<Direction> optionalDirection = this.directionRepository.findById(directionId);

        if(optionalDirection.isEmpty()){
            throw new NotFoundException(Direction.class);
        }

        return optionalDirection.get();
    }

    public List<Direction> findAll(){
        List<Direction> directionList;

        directionList = this.directionRepository.findAll();

        return  directionList;
    }

    public Direction save(Direction direction){
        if(direction==null)
            throw new NullPointerException(ExceptionUtils.getNullMessage(Direction.class));

        return this.directionRepository.save(direction);
    }

    public void delete(Direction direction){
        if(direction==null){
            throw new NullPointerException(ExceptionUtils.getNullMessage(Direction.class));
        }
        if(direction.getIdDirection()==null)
            throw new NotFoundException(Direction.class);

        this.directionRepository.delete(direction);
    }

    public void deleteById(Long id){
        if(id==null)
            throw new NullPointerException(ExceptionUtils.getNullMessage(Direction.class));

        this.directionRepository.deleteById(id);

    }
}
