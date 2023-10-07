package megalog.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

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

    @NotNull
    @NotBlank
    private String businessName;

    @Email(message = "email required")
    @NotNull
    @NotBlank
    private String businessEmail;

    @NotNull
    @NotBlank
    private String businessPhone;

    @NotNull
    @NotBlank
    private String totalEmployee;

    @NotNull
    @NotBlank
    private String businessType;

    @NotNull
    @NotBlank
    private String password;

    private String emailVerificationStatus;

    private String profileStatus;

    public CompanyEntity(Long id, String email, List<String> roles) {
    }
}
