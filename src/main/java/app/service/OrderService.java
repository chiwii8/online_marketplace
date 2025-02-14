package app.service;

import app.domain.Order;
import app.exceptions.IllegalBlankException;
import app.exceptions.NotFoundException;
import app.repository.OrderRepository;
import app.service.interfaces.ICommonService;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements ICommonService<Order,Long> {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order create() {
        return new Order();
    }

    @Override
    public Order findById(Long id)throws NotFoundException, IllegalArgumentException {
        Optional<Order> optionalOrder;
        if(id==null){
            throw new IllegalArgumentException("The id must not be null");
        }

        optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isEmpty()){
            throw new NotFoundException(Order.class);
        }

        return optionalOrder.get();
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders;

        orders = orderRepository.findAll();

        return orders;
    }

    @Override
    public void delete(Order entity) throws IllegalArgumentException, OptimisticLockingFailureException {
        this.orderRepository.delete(entity);
    }

    @Override
    public void deleteById(Long id) throws NotFoundException,IllegalArgumentException{
        if(id==null){
            throw new IllegalArgumentException("the id must not be null");
        }

        if(!this.orderRepository.existsById(id)){
            throw new NotFoundException(Order.class);
        }

        this.orderRepository.deleteById(id);
    }

    @Override
    public Order save(Order entity) throws IllegalArgumentException, OptimisticLockingFailureException{
        return this.orderRepository.save(entity);
    }
}