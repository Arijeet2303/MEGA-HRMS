package megalog.repository;

import megalog.entity.CompanyValidation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyValidationRepo extends JpaRepository<CompanyValidation , String> {

    @Query(value = "SELECT * from company_validation where company_email= :email",nativeQuery = true)
    CompanyValidation findUserByEmail(String email);

    @Modifying
    @Query(value = "DELETE from company_validation where company_email= :email",nativeQuery = true)
    void deletePIN(String email);
}
