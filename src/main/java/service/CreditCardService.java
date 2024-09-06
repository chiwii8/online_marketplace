package service;

import domain.CreditCard;
import exceptions.IllegalBlankException;
import exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CreditCardRepository;
import utils.ExceptionUtils;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CreditCardService {
    
    private final CreditCardRepository creditCardRepository;
    
    @Autowired
    public CreditCardService(CreditCardRepository creditCardRepository){
        this.creditCardRepository = creditCardRepository;
    }

    ///CRUD Operations
    public CreditCard create(){
        CreditCard creditCard;

        creditCard = new CreditCard();

        return creditCard;
    }

    public CreditCard findById(Long creditCardId){
        if(creditCardId==null){
            throw new NullPointerException(ExceptionUtils.getNullMessage(CreditCard.class));
        }

        Optional<CreditCard> optionalCreditCard = this.creditCardRepository.findById(creditCardId);

        if(optionalCreditCard.isEmpty()){
            throw new NotFoundException(CreditCard.class);
        }

        return optionalCreditCard.get();
    }

    public List<CreditCard> findAll(){
        List<CreditCard> creditCardList;

        creditCardList = this.creditCardRepository.findAll();

        return  creditCardList;
    }

    public CreditCard save(CreditCard creditCard){
        if(creditCard==null)
            throw new NullPointerException(ExceptionUtils.getNullMessage(CreditCard.class));

        return this.creditCardRepository.save(creditCard);
    }

    public void delete(CreditCard creditCard){
        if(creditCard==null){
            throw new NullPointerException(ExceptionUtils.getNullMessage(CreditCard.class));
        }
        if(creditCard.getId()==null)
            throw new NotFoundException(CreditCard.class);

        this.creditCardRepository.delete(creditCard);
    }

    ///Extra queries
    public CreditCard findByOwner(String owner) {
        if (owner == null)
            throw new NullPointerException(ExceptionUtils.getNullMessage(CreditCard.class));
        if (owner.isBlank())
            throw new IllegalBlankException(CreditCard.class);

        return this.creditCardRepository.findByOwner(owner);

    }

}
