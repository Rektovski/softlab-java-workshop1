package ge.softgen.softlab.workshop.softlabjavaworkshop1.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "users")
@SequenceGenerator(name = "userIdGenerator", sequenceName = "users_id_seq", allocationSize = 1)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userIdGenerator")
    Integer Id;
    String username;
    String password;
    String email;
    @Column(name = "create_Date")
    LocalDate createDate;
    Boolean active;

}
