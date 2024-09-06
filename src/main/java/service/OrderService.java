package service;

import domain.Order;
import domain.enumeration.OrderStatus;
import exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.OrderRepository;
import utils.ExceptionUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
    ///CRUD Operations
    public Order create(){
        Order order;

        order = new Order();

        return order;
    }

    public Order findById(Long orderId){
        if(orderId==null){
            throw new NullPointerException(ExceptionUtils.getNullMessage(Order.class));
        }

        Optional<Order> optionalOrder = this.orderRepository.findById(orderId);

        if(optionalOrder.isEmpty()){
            throw new NotFoundException(Order.class);
        }

        return optionalOrder.get();
    }

    public List<Order> findAll(){
        List<Order> orderList;

        orderList = this.orderRepository.findAll();

        return  orderList;
    }

    public Order save(Order order){
        if(order==null)
            throw new NullPointerException(ExceptionUtils.getNullMessage(Order.class));

        return this.orderRepository.save(order);
    }

    public void delete(Order order){
        if(order==null){
            throw new NullPointerException(ExceptionUtils.getNullMessage(Order.class));
        }
        if(order.getIdOrder()==null)
            throw new NotFoundException(Order.class);

        this.orderRepository.delete(order);
    }

    ///Extra queries
    public Order findByIdentifier(UUID identifier){
        if(identifier == null)
            throw new NullPointerException(ExceptionUtils.getNullMessage(Order.class));

        return this.orderRepository.findByIdentifier(identifier);

    }

    public List<Order> findByStatus(OrderStatus status){
        if(status == null)
            throw new NullPointerException(ExceptionUtils.getNullMessage(Order.class));

        return this.orderRepository.findByStatus(status);
    }
}
