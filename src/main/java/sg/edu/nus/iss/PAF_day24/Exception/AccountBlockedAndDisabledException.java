package sg.edu.nus.iss.PAF_day24.Exception;

public class AccountBlockedAndDisabledException extends RuntimeException
{
    public AccountBlockedAndDisabledException(String message)
    {
        super(message);
    }
}
