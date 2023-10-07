package megalog.Service;

import megalog.Exceptions.UserAlreadyExistAuthenticationException;
import megalog.Request.CompanyRequest;
import megalog.entity.CompanyEntity;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface CompanyService {

      public CompanyEntity addCompany(CompanyRequest companyRequest) throws UserAlreadyExistAuthenticationException, MessagingException, UnsupportedEncodingException;
      public boolean verifyOtp(String email ,int otp);

}
