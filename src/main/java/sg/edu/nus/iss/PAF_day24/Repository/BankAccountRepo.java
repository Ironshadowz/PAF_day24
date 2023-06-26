package sg.edu.nus.iss.PAF_day24.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
        BankAccount bankAccount = jdbcTemplate.queryForObject(GET_ACCOUNT_SQL, BeanPropertyRowMapper.newInstance(BankAccount.class), bankAccountId);
        return bankAccount;
    }
    public Boolean withdrawAmount(Integer withdrawAccountId, Float withdrawAmount)
    {
        Boolean balanceWithdraw = false;
        Integer result = jdbcTemplate.update(WITHDRAW_SQL, withdrawAmount, withdrawAccountId);
        return result>0 ? true:false;
    }


}
