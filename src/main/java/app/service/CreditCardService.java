package app.service;

import app.domain.CreditCard;
import app.exceptions.NotFoundException;
import app.repository.CreditCardRepository;
import app.service.interfaces.ICommonService;
import org.aspectj.weaver.ast.Not;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CreditCardService implements ICommonService<CreditCard,Long> {

    private final CreditCardRepository creditCardRepository;

    @Autowired
    public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    public CreditCard create() {
        return new CreditCard();
    }

    @Override
    public CreditCard findById(Long id) throws IllegalArgumentException,NotFoundException{
        Optional<CreditCard> optionalCreditCard;

        if(id==null){
            throw new IllegalArgumentException("The id Element recive is null");
        }

        optionalCreditCard = creditCardRepository.findById(id);
        if(optionalCreditCard.isEmpty()){
            throw new NotFoundException(CreditCard.class);
        }

        return optionalCreditCard.get();
    }

    @Override
    public List<CreditCard> findAll() {
        List<CreditCard> creditCards;

        creditCards = creditCardRepository.findAll();

        return creditCards;
    }

    @Override
    public void delete(CreditCard entity)throws IllegalArgumentException, OptimisticEntityLockException {
        this.creditCardRepository.delete(entity);
    }

    @Override
    public void deleteById(Long id) throws NotFoundException,IllegalArgumentException,OptimisticEntityLockException {
        if(id==null || !this.creditCardRepository.existsById(id)){
            throw new NotFoundException(CreditCard.class);
        }

        this.creditCardRepository.deleteById(id);
    }

    @Override
    public CreditCard save(CreditCard entity) throws IllegalArgumentException,OptimisticEntityLockException{
        return this.creditCardRepository.save(entity);
    }

}