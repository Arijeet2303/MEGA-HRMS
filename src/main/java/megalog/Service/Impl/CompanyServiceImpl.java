package megalog.Service.Impl;

import megalog.Exceptions.UserAlreadyExistAuthenticationException;
import megalog.Request.CompanyRequest;
import megalog.Security.PasswordSecure;
import megalog.Service.CompanyService;
import megalog.entity.CompanyEntity;
import megalog.entity.CompanyValidation;
import megalog.enums.CompanyEnums;
import megalog.repository.CompanyValidationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import megalog.repository.CompanyRepo;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepo companyRepo;

    @Autowired
    PasswordSecure passwordSecure;

    @Autowired
    CompanyValidationRepo companyValidationRepo;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TemplateEngine templateEngine;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public CompanyEntity addCompany(CompanyRequest companyRequest) throws UserAlreadyExistAuthenticationException, MessagingException, UnsupportedEncodingException {
        final MimeMessage message = javaMailSender.createMimeMessage();
        final MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        final Context context = new Context();
        int OTP = (int)(Math.random()*(1000000-100000+1)+100000);
        context.setVariable("OTP" , OTP);
        context.setVariable("Company" , companyRequest.getBusinessName());

        helper.setFrom(new InternetAddress("anujbhargav3@gmail.com", "Megalog Softwares"));
        helper.setTo(companyRequest.getBusinessEmail());
        helper.setSubject(" One-Time Password (OTP) - " + companyRequest.getBusinessName());
        String html = templateEngine.process("CompanyVerification", context);
        CompanyEntity company = convertToEntity(companyRequest);
        Date now = Calendar.getInstance().getTime();
        company.setCreationDate(now);
        company.setLastModifiedDate(now);
        company.setPassword(passwordSecure.passwordEncoder().encode(company.getPassword()));
        helper.setText(html , true);
        CompanyValidation validation = new CompanyValidation(companyRequest.getBusinessEmail() , OTP);
        companyValidationRepo.save(validation);
        companyRepo.save(company);
        javaMailSender.send(message);
        return company;
    }

    @Transactional
    public boolean verifyOtp(String Email, int otp){
        CompanyValidation userValidation = companyValidationRepo.findUserByEmail(Email);
        System.out.println(userValidation.getEmail());
        int PIN = userValidation.getOtp();
        if(PIN == otp){
            String Status = String.valueOf(CompanyEnums.SUCCESS);
            companyValidationRepo.deletePIN(Email);
            companyRepo.updateStatus(Email , Status);
            return true;
        }
        else return false;
    }


    private CompanyEntity convertToEntity(CompanyRequest companyRequest) {
        CompanyEntity company = new CompanyEntity();
        company.setBusinessName(companyRequest.getBusinessName());
        company.setBusinessEmail(companyRequest.getBusinessEmail());
        company.setBusinessPhone(companyRequest.getBusinessPhone());
        company.setTotalEmployee(companyRequest.getTotalEmployee());
        company.setBusinessType(companyRequest.getBusinessType());
        company.setPassword(companyRequest.getPassword());
        company.setEmailVerificationStatus(String.valueOf(CompanyEnums.PENDING));
        company.setProfileStatus(String.valueOf(CompanyEnums.PENDING));
        return company;
    }
}
