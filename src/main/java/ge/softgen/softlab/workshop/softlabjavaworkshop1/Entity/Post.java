package ge.softgen.softlab.workshop.softlabjavaworkshop1.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "posts")
@SequenceGenerator(name = "postIdGenerator", sequenceName = "posts_id_seq", allocationSize = 1)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postIdGenerator")
    Integer id;
    String title;
    String body;
    Timestamp createDate;
    @Column(name = "user_id")
    Integer userId;
    Boolean active;
}