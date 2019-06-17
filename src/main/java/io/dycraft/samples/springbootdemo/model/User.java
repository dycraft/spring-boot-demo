package io.dycraft.samples.springbootdemo.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 * @author Dayang Li on 12/06/2019
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Generated(GenerationTime.INSERT)
    private Date createdAt;

    @Generated(GenerationTime.ALWAYS)
    private Date updatedAt;

    public User(String username, String encodedPassword) {
        this.username = username;
        this.password = encodedPassword;
    }
}
