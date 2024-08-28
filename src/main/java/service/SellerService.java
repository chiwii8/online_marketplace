package service;

import domain.actor.Seller;
import exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.SellerRepository;
import utils.ExceptionUtils;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class SellerService {

    private final SellerRepository sellerRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository){
        this.sellerRepository = sellerRepository;
    }

    ///CRUD Operations

    public Seller create(){
        Seller seller;

        seller = new Seller();

        return seller;
    }

    public Seller findById(Long sellerId){
        Seller seller;

        if(sellerId == null){
            throw new NullPointerException(ExceptionUtils.getNullMessage(Seller.class));
        }

        Optional<Seller> optional = this.sellerRepository.findById(sellerId);

        if(optional.isEmpty()){
            throw new NotFoundException(Seller.class);
        }

        seller = optional.get();

        return seller;
    }

    public List<Seller> findAll(){
        List<Seller> sellerList;

        sellerList = this.sellerRepository.findAll();

        return sellerList;
    }

    public void save(Seller seller){
        if(seller==null)
            throw new NullPointerException(ExceptionUtils.getNullMessage(Seller.class));

        this.sellerRepository.save(seller);
    }

    public void delete(Seller seller){
        if(seller==null)
            throw new NullPointerException(ExceptionUtils.getNullMessage(Seller.class));

        this.sellerRepository.delete(seller);
    }

    ///Extra queries
    public List<Seller> findByCommercialName(String commercialName){
        List<Seller> sellerList;
        if(commercialName==null){
            throw new NullPointerException(ExceptionUtils.getNullMessage(Seller.class));
        }

         sellerList = this.sellerRepository.findByCommercialName(commercialName);

        return sellerList;
    }


}
