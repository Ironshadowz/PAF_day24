package sg.edu.nus.iss.PAF_day24.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.PAF_day24.Exception.AccountBlockedAndDisabledException;
import sg.edu.nus.iss.PAF_day24.Exception.AmountNotSufficientException;
import sg.edu.nus.iss.PAF_day24.Exception.BankAccountNotFoundException;
import sg.edu.nus.iss.PAF_day24.Model.BankAccount;
import sg.edu.nus.iss.PAF_day24.Repository.BankAccountRepo;

@Service
public class BankAcountService 
{
    @Autowired
    BankAccountRepo bankAccountRepo;

    public BankAccount retrieveAccountById(Integer accountId)
    {
        BankAccount bankAccount = bankAccountRepo.getAccountById(accountId);
        System.out.println("BankAccountService>retrieveAccountById>"+bankAccount.toString());
        
        return bankAccount;
        //return bankAccountRepo.getAccountById(accountId);
    }
    public Boolean createAccount(BankAccount bankAccount)
    {
        return bankAccountRepo.createAccount(bankAccount);
    }
    //transactional: encompass in a single unit of work
    //writing of records to more than one tables or update more than one records in the same table
    @Transactional
    public Boolean transferMoney(Integer withdrawAccountId, Integer depositAccountId, Float transferAmount)
    {
        // 1. check that transferer exists
        boolean transfererExist = false;
        BankAccount transfererBA = bankAccountRepo.getAccountById(withdrawAccountId);
        if(transfererBA!=null)
        {
            transfererExist=true;
        }
        // 2. check that receiver exists
        boolean receiverExist = false;
        BankAccount receiverBA = bankAccountRepo.getAccountById(depositAccountId);
        if(receiverBA!=null)
        {
            receiverExist=true;
        }
        // 3. check that transferer is active
        // 4. check that receiver is active
        // 5. check that transferer is not blocked
        boolean transfererAllowed = false;
        if(transfererBA.getIsActive() && !transfererBA.getIsBlocked())
        {
            transfererAllowed = true;
        }
        // 6. check that receiver is not blocked
        boolean receiverAllowed = false;
        if(receiverBA.getIsActive() && !receiverBA.getIsBlocked())
        {
            receiverAllowed = true;
        }
        // 7. check that transferer has enough money to transfer to receiver
        boolean enoughMoney = false;
        if(transfererBA.getBalance()>=transferAmount)
        {
            enoughMoney = true;
        }
        if(transfererExist && transfererAllowed && receiverExist && receiverAllowed && enoughMoney)
        {
            //Carry out transfer operation
            //Withdraw the amount from transferer
            bankAccountRepo.withdrawAmount(withdrawAccountId, transferAmount);
            //Deposit amount to receiver
            bankAccountRepo.depositAmmount(depositAccountId, transferAmount);
        } else
        {
            if(!transfererAllowed)
            {
                throw new AccountBlockedAndDisabledException("Transferer is either blocked or inactive.");
            }
            if(!enoughMoney)
            {
                throw new AmountNotSufficientException("Transferer does not have enough money.");
            }
            if(!receiverAllowed)
            {
                throw new AccountBlockedAndDisabledException("Receiver is either blocked or inactive.");
            }
        }
        return true;
    }
}
