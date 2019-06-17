package io.dycraft.samples.springbootdemo.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 * @author Dayang Li on 17/06/2019
 */
@MappedSuperclass
@Data
public class BaseEntity {

    @Generated(GenerationTime.INSERT)
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @Generated(GenerationTime.ALWAYS)
    @Column(nullable = false)
    private Date updatedAt;
}
