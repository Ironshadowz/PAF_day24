package sg.edu.nus.iss.PAF_day24.Exception;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
@ControllerAdvice
public class GlobalExceptionHandler 
{   
    //@ExceptionHandler(BankAccountNotFoundException.class)
//     public ResponseEntity<ErrorMessage> handleBankAccountNotFoundException(BankAccountNotFoundException ex, HttpServletRequest request)
//     {
//         ErrorMessage errMsg = new ErrorMessage();
//         errMsg.setStatusCode(404);
//         errMsg.setMessage("Bank account does not exist or have been created.");
//         errMsg.setTimeStamp(new Date());
//         errMsg.setDescription(request.getRequestURI());
        
//         return ResponseEntity.ok().body(errMsg);
//     }
    //Another way to display custom error message
    @ExceptionHandler(BankAccountNotFoundException.class)
    public ModelAndView handleBankAccountNotFoundException(BankAccountNotFoundException ex, HttpServletRequest request)
    {
        ErrorMessage errMsg = new ErrorMessage();
        errMsg.setStatusCode(404);
        errMsg.setMessage("Bank account does not exist or have been created.");
        errMsg.setTimeStamp(new Date());
        errMsg.setDescription(request.getRequestURI());
        ModelAndView mav = new ModelAndView("error.html");
        mav.addObject("errorMessage", errMsg);
        return mav;
    }

    @ExceptionHandler(AccountBlockedAndDisabledException.class)
    public ModelAndView handleAccountBlockedAndDisabledException(AccountBlockedAndDisabledException ex, HttpServletRequest request)
    {
        ErrorMessage errMsg = new ErrorMessage();
        errMsg.setStatusCode(404);
        errMsg.setMessage("");
        errMsg.setTimeStamp(new Date());
        errMsg.setDescription(request.getRequestURI());
        ModelAndView mav = new ModelAndView("error.html");
        mav.addObject("errorMessage", errMsg);
        return mav;
    }

    @ExceptionHandler(AmountNotSufficientException.class)
    public ModelAndView handleAmountNotSufficientException(AmountNotSufficientException ex, HttpServletRequest request)
    {
        ErrorMessage errMsg = new ErrorMessage();
        errMsg.setStatusCode(404);
        errMsg.setMessage("");
        errMsg.setTimeStamp(new Date());
        errMsg.setDescription(request.getRequestURI());
        ModelAndView mav = new ModelAndView("error.html");
        mav.addObject("errorMessage", errMsg);
        return mav;
    }
}
