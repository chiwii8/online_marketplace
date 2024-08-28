package security;

import exceptions.IllegalBlankException;
import org.springframework.beans.factory.annotation.Autowired;
import domain.actor.Seller;
import exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import utils.ExceptionUtils;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserAccountService {
    
    private final UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    ///CRUD Operations
    public UserAccount create(){
        UserAccount userAccount;

        userAccount = new UserAccount();

        return userAccount;
    }

    public UserAccount findById(Long userAccountId){
        if(userAccountId==null){
            throw new NullPointerException(ExceptionUtils.getNullMessage(UserAccount.class));
        }

        Optional<UserAccount> optionalUserAccount = this.userAccountRepository.findById(userAccountId);

        if(optionalUserAccount.isEmpty()){
            throw new NotFoundException(UserAccount.class);
        }

        return optionalUserAccount.get();
    }

    public List<UserAccount> findAll(){
        List<UserAccount> userAccountList;

        userAccountList = this.userAccountRepository.findAll();

        return  userAccountList;
    }

    public void save(UserAccount userAccount){
        if(userAccount==null)
            throw new NullPointerException(ExceptionUtils.getNullMessage(UserAccount.class));

        this.userAccountRepository.save(userAccount);
    }

    public void delete(UserAccount userAccount){
        if(userAccount==null){
            throw new NullPointerException(ExceptionUtils.getNullMessage(UserAccount.class));
        }
        if(userAccount.getId()==null)
            throw new NotFoundException(UserAccount.class);

        this.userAccountRepository.delete(userAccount);
    }

    public UserAccount findByUserName(String username){
        if(username==null)
            throw new NullPointerException(ExceptionUtils.getNullMessage(UserAccount.class));
        if(username.isBlank())
            throw new IllegalBlankException(UserAccount.class);

        return this.userAccountRepository.findByUsername(username);
    }

    public boolean isValidUserAccount(UserAccount userAccount){
        if(userAccount==null)
            throw new NullPointerException(ExceptionUtils.getNullMessage(UserAccount.class));

        UserAccount userAccount1 = this.findByUserName(userAccount.getUsername());

        if(userAccount1==null){
            throw new NotFoundException(UserAccount.class);
        }

        return userAccount1.getPassword().equals(userAccount.getPassword());
    }
    
}