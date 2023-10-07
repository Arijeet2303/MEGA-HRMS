package megalog.controller;
import megalog.Request.CompanyRequest;
import megalog.Response.ApiResponse;
import megalog.Service.CompanyService;
import megalog.entity.CompanyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PostMapping("/addCompany")
    public ResponseEntity<?> addCompany(@RequestBody CompanyRequest companyRequest , HttpSession session){
        try {
            CompanyEntity company = companyService.addCompany(companyRequest);
            session.setAttribute("Email" , company.getBusinessEmail());
        }
        catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, "Exception occurred"),
                    HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(new ApiResponse(true, "Company registered successfully"));
    }

    @PostMapping("/verifyOTP")
    public String verifyOtp(int otp , HttpSession httpSession){
        String email = (String) httpSession.getAttribute("Email");
        System.out.println(email);
        boolean result = companyService.verifyOtp(email,otp);
        if(result){
            return "logged in";
        }
        else return "nooooo";
    }
}
