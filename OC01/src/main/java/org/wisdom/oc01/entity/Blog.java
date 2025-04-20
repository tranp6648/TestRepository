package org.wisdom.oc01.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@Entity
public class Blog {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_blog")
    private int idBlog;
    @Basic
    @Column(name = "noi_dung")
    private String noiDung;
    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;
}
