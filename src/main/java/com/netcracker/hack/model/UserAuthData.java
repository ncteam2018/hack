package com.netcracker.hack.model;

import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
  
  @Column(name = "role", length = 30)
  private String role;
  
  public UserAuthData() {}

  public UserAuthData(UUID uuid, String login, String password, String role) {
    PasswordEncoder encoder = new BCryptPasswordEncoder();
    
    this.uuid = uuid;
    this.login = login;
    if(encoder != null)
      this.password = encoder.encode(password);
      
    this.role = role;
  
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

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
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

    return Objects.hash(uuid, login, password, role);
  }
}
