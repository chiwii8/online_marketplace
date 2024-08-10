package service;

import domain.actor.Seller;
import exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repository.SellerRepository;

import java.util.Optional;

@Transactional
@Service
public class SellerService {

    private SellerRepository sellerRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository){
        this .sellerRepository = sellerRepository;
    }

    ///CRUD Operations

    public Seller create(){
        Seller seller;

        seller = new Seller();

        return seller;
    }

    public void save(Seller seller){
        Seller seller1;
        if(seller==null)
            throw new NullPointerException(Seller.class.getSimpleName() + ":The object is null");

        seller1 = this.sellerRepository.save(seller);
    }

    public void delete(Seller seller){
        if(seller==null)

        this.sellerRepository.delete(seller);
    }

    public Seller findById(Long sellerId){
        Seller seller;

        Optional<Seller> optional = this.sellerRepository.findById(sellerId);

        if(optional.isEmpty()){
            throw new NotFoundException(Seller.class);
        }

        seller = optional.get();

        return seller;
    }

}
