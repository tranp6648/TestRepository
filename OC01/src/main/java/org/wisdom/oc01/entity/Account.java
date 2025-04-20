package org.wisdom.oc01.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Setter
@Getter
@Entity
public class Account implements UserDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_account", nullable = false)
    private int idAccount;
    @Basic
    @Column(name = "username", nullable = false, length = 225, unique = true)
    private String username;
    @Basic
    @Column(name = "password", length = 225)
    private String password;
    @Basic
    @Column(name = "email", unique = true)
    private String email;
    @Basic
    @Column(name = "provider")
    private String provider;
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private User user;
    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private Cv cv;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<HocVienDangKyVideo> hocVienDangKyVideo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_" + role.getRoleName());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
