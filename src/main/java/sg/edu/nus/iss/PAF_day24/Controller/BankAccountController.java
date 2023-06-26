package sg.edu.nus.iss.PAF_day24.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.PAF_day24.Model.BankAccount;
import sg.edu.nus.iss.PAF_day24.Service.BankAcountService;

@RestController
@RequestMapping("/api/bankaccounts")
public class BankAccountController 
{
    @Autowired
    BankAcountService bankAcountService;

    @PostMapping
    public ResponseEntity<Boolean> createAccount(@RequestBody BankAccount bankAccount)
    {
        Boolean bankAccountCreated = bankAcountService.createAccount(bankAccount);
        if(bankAccountCreated)
        {
            return ResponseEntity.ok().body(bankAccountCreated);
        } else
        {
            return ResponseEntity.internalServerError().body(bankAccountCreated);
        }
    }
    @GetMapping ("/{accountId}")
    public ResponseEntity<BankAccount> getAccountById(@PathVariable("accountId") Integer id)
    {
        BankAccount bankAccount = bankAcountService.retrieveAccountById(id);
        return new ResponseEntity<BankAccount>(bankAccount, HttpStatus.OK);
    }

}
