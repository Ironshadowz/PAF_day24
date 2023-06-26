package sg.edu.nus.iss.PAF_day24.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.PAF_day24.Exception.BankAccountNotFoundException;
import sg.edu.nus.iss.PAF_day24.Model.BankAccount;

@Repository
public class BankAccountRepo 
{
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String GET_ACCOUNT_SQL = "select * from bank_account where id=?";
    private final String WITHDRAW_SQL = "update bank_account set balance = balance - ? where id = ?";
    private final String DEPOSIT_SQL = "update bank_account set balance = balance + ? where id = ?";
    private final String CREATE_ACCOUNT_SQL = "insert into bank_account (full_name, is_blocked, is_active, account_type, balance) values ( ?, ? , ? , ? , ?)";
    //private final String CREATE_ACCOUNT_SQL = "insert into bank_account values ( ? , ?, ? , ? , ? , ?)";
    
    public BankAccount getAccountById(Integer bankAccountId)
    {
        //BankAccount bankAccount = jdbcTemplate.queryForObject(GET_ACCOUNT_SQL, BeanPropertyRowMapper.newInstance(BankAccount.class), bankAccountId);
        List<BankAccount> bankAccounts = jdbcTemplate.query(GET_ACCOUNT_SQL, BeanPropertyRowMapper.newInstance(BankAccount.class), bankAccountId);
        if(bankAccounts.isEmpty())
        {
            throw new BankAccountNotFoundException("Account does not exist");
        }
        BankAccount bankAccount = bankAccounts.get(0);
        return bankAccount;
    }
    public Boolean withdrawAmount(Integer withdrawAccountId, Float withdrawAmount)
    {
        Integer result = jdbcTemplate.update(WITHDRAW_SQL, withdrawAmount, withdrawAccountId);
        return result>0 ? true:false;
    }
    public Boolean depositAmmount(Integer depositAccountId, Float depositAmount)
    {
        Integer result = jdbcTemplate.update(DEPOSIT_SQL, depositAmount, depositAccountId);
        return result>0 ? true:false;
    }
    public Boolean createAccount(BankAccount bankAccount)
    {
        Integer result = jdbcTemplate.update(CREATE_ACCOUNT_SQL, bankAccount.getFullName(), 
            bankAccount.getIsBlocked(), bankAccount.getIsActive(), bankAccount.getAccountType(), bankAccount.getBalance());
        return result>0 ? true:false;
    }
    //transactional: encompass in a single unit of work
    //writing of records to more than one tables or update more than one records in the same table
    // @Transactional
    // public Boolean transferMoney(Integer withdrawAccountId, Integer depositAccountId, Float transferAmount)
    // {
        // 1. check that transferer exists
        // 2. check that receiver exists
        // 3. check that transferer is active
        // 4. check that receiver is active
        // 5. check that transferer is not blocked
        // 6. check that receiver is not blocked
        // 7. check that transferer has enough money to transfer to receiver
    //     return false;
    // }
}
