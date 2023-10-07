package megalog.repository;

import megalog.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends JpaRepository<CompanyEntity , Long> {

    @Modifying
    @Query(value = "UPDATE company_entity SET email_verification_status = :Status where business_email= :email",nativeQuery = true)
    void updateStatus(String email , String Status);
}
