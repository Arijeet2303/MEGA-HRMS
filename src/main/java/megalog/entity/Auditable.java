package megalog.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public class Auditable implements Serializable {

    @Column(name = "Created_By")
    private Long createdBy;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "Creation_Date")
    private Date creationDate;

    @Column(name = "last_modified_by")
    protected Long lastModifiedBy;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    public void copy(Auditable source, Auditable target) {
        target.setCreatedBy(source.getCreatedBy());
        target.setCreationDate(source.getCreationDate());
        target.setLastModifiedBy(source.getLastModifiedBy());
        target.setLastModifiedDate(source.getLastModifiedDate());
    }
}
