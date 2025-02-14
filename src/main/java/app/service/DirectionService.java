package app.service;

import app.domain.Direction;
import app.exceptions.NotFoundException;
import app.repository.DirectionRepository;
import app.service.interfaces.ICommonService;
import jakarta.transaction.Transactional;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DirectionService implements ICommonService<Direction,Long> {

    private final DirectionRepository directionRepository;

    @Autowired
    public DirectionService(DirectionRepository directionRepository) {
        this.directionRepository = directionRepository;
    }

    @Override
    public Direction create() {
        return new Direction();
    }

    @Override
    public Direction findById(Long id) throws IllegalArgumentException, NotFoundException {
        Optional<Direction> optionalDirection;

        if(id==null){
            throw new IllegalArgumentException("The id Element recive is null");
        }
        optionalDirection = directionRepository.findById(id);
        if(optionalDirection.isEmpty()){
            throw new NotFoundException(Direction.class);
        }

        return optionalDirection.get();
    }

    @Override
    public List<Direction> findAll() {
        List<Direction> directions;

        directions = directionRepository.findAll();

        return directions;
    }

    @Override
    public void delete(Direction entity) throws IllegalArgumentException, OptimisticEntityLockException {
        this.directionRepository.delete(entity);
    }

    @Override
    public void deleteById(Long id)throws IllegalArgumentException, OptimisticEntityLockException  {
        if(id==null){
            throw new IllegalArgumentException("The id Element receive is null");
        }

        if(!this.directionRepository.existsById(id)){
            throw new NotFoundException(Direction.class);
        }

        this.directionRepository.deleteById(id);
    }

    @Override
    public Direction save(Direction entity) throws IllegalArgumentException, OptimisticEntityLockException{
        return this.directionRepository.save(entity);
    }
}