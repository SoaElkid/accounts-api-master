package unsl.services;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unsl.entities.Account;
import unsl.entities.UserAccounts;
import unsl.entities.Account.Status;
import unsl.repository.AccountRepository;


@Service
public class AccountServices {

    @Autowired
    AccountRepository accountRepository;
    
    public List<Account> getAll() {
        return accountRepository.findAll();
    }
    
    public Account getAccount(Long id){
       return accountRepository.findById(id).orElse(null);
    }
    
    public UserAccounts findByHolder(Long holder){
        return accountRepository.findByHolder(holder);
    }

    public Account saveAccount(Account account){
       
       account.setStatus(Status.ACTIVA); 
       account.setAccount_balance(new BigDecimal(100.0));

       return accountRepository.save(account); 
    }
    
    public Account updateStatus(long id){
       Account account = accountRepository.findById(id).orElse(null);
       if(account.getStatus() == Status.ACTIVA){
           account.setStatus(Account.Status.BAJA);
        }else{
           account.setStatus(Account.Status.ACTIVA);
        }
        return accountRepository.save(account);
    }

    public Account updateBalance(long id,BigDecimal new_amount_balance){              
        Account account= accountRepository.findById(id).orElse(null);
        account.setAccount_balance(new_amount_balance);

        return accountRepository.save(account);   
    }
}
