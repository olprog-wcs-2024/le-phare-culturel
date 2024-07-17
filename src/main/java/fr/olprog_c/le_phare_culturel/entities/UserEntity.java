package fr.olprog_c.le_phare_culturel.entities;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import fr.olprog_c.le_phare_culturel.enums.UserRoleEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
public class UserEntity extends BaseCommonEntity implements UserDetails {

  @Enumerated(EnumType.STRING)
  // @Column(name = "role", nullable = false)
  private UserRoleEnum userRole;

  @Column(name = "profile_nickname", unique = true, nullable = false, length = 32)
  @ColumnDefault("\"New User nickname\"")
  private String profileNickname;

  @Column(name = "profile_description", nullable = false, length = 320)
  @ColumnDefault("\"New User profile description\"")
  private String profileDescription;

  @Column(name = "first_name", nullable = false, length = 55)
  @ColumnDefault("\"New User firstname\"")
  private String firstName;

  @Column(name = "last_name", nullable = false, length = 55)
  @ColumnDefault("\"New User lastname\"")
  private String lastName;

  @Column(nullable = false, length = 245)
  // @ColumnDefault("SHA2(\"new_password\", 512)")
  private String password;
  // @CreationTimestamp

  @UpdateTimestamp
  @Column(name = "last_connection", nullable = false, length = 20)
  private LocalDate lastConnection;

  @Column(name = "user_enabled", nullable = false)
  @ColumnDefault(value = "false")
  private boolean userEnabled;

  @Column(unique = true, nullable = false, length = 128)
  private String email;

  @Column(nullable = false, length = 255)
  @ColumnDefault(value = "\"/assets/images/avatars/avatar1.svg\"")
  private String avatar;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("ROLE_" + this.userRole));
  }

  @Override
  public String getUsername() {
    return this.email;
  }

  public boolean isEnabled() {
    return userEnabled;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return UserDetails.super.isCredentialsNonExpired();
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;// UserDetails.super.isAccountNonLocked();
  }

  public boolean isAccountNonExpired() {
    return true;
  }

}
