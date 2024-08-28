package service;

import domain.actor.Admin;
import domain.actor.Seller;
import exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AdminRepository;
import utils.ExceptionUtils;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    ///CRUD Operations
    public Admin create(){
        Admin admin;

        admin = new Admin();

        return admin;
    }

    public Admin findById(Long adminId){
        if(adminId==null){
            throw new NullPointerException(ExceptionUtils.getNullMessage(Admin.class));
        }

        Optional<Admin> optionalAdmin = this.adminRepository.findById(adminId);

        if(optionalAdmin.isEmpty()){
            throw new NotFoundException(Admin.class);
        }

        return optionalAdmin.get();
    }

    public List<Admin> findAll(){
        List<Admin> adminList;

        adminList = this.adminRepository.findAll();

        return  adminList;
    }

    public void save(Admin admin){
        if(admin==null)
            throw new NullPointerException(ExceptionUtils.getNullMessage(Seller.class));

        this.adminRepository.save(admin);
    }

    public void delete(Admin admin){
        if(admin==null){
            throw new NullPointerException(ExceptionUtils.getNullMessage(Seller.class));
        }
        if(admin.getId()==null)
            throw new NotFoundException(Admin.class);

        this.adminRepository.delete(admin);
    }
}
