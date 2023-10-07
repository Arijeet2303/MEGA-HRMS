package entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity(name = "companyEntity")
public class CompanyEntity extends Auditable implements Serializable {

    @Id
    @Column(name = "businessId", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long businessId;

    private String businessName;

    private String businessEmail;

    private String businessPhone;

    private String totalEmployee;

    private String businessType;

    private String password;

    private int emailVerificationStatus;

}
