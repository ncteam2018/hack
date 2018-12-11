package com.netcracker.hack.model;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "userAuth")
public class UserAuthData {

  @Id
  @Column(name = "uuid")
  private UUID uuid;

  @Column(name = "login", length = 32, unique = true)
  private String login;

  @Column(name = "password", length = 60)
  private String password;

  @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "userAuth_role", joinColumns = @JoinColumn(name = "uuid"))
  @Enumerated(EnumType.STRING)
  private Set<Role> roles;
  
  public UserAuthData() {}

  public UserAuthData(UUID uuid, String login, String password, Set<Role> roles) {
    PasswordEncoder encoder = new BCryptPasswordEncoder();
    
    this.uuid = uuid;
    this.login = login;
    if(encoder != null)
      this.password = encoder.encode(password);
      
    this.roles = roles;
  
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserAuthData profile = (UserAuthData) o;
    return Objects.equals(uuid, profile.uuid) && Objects.equals(login, profile.login); 
  }

  @Override
  public int hashCode() {

    return Objects.hash(uuid, login, password, roles);
  }
}
