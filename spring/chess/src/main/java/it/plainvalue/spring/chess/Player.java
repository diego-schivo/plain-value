package it.plainvalue.spring.chess;

import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Player {

  public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

  private @Id @GeneratedValue Long id;

  private String name;

  private @JsonIgnore String password;

  private String[] roles;

  public Player() {}

  public Player(String name, String password, String... roles) {
    this.name = name;
    this.setPassword(password);
    this.roles = roles;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password != null ? PASSWORD_ENCODER.encode(password) : null;
  }

  public String[] getRoles() {
    return roles;
  }

  public void setRoles(String[] roles) {
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
    Player player = (Player) o;
    return Objects.equals(id, player.id)
        && Objects.equals(name, player.name)
        && Objects.equals(password, player.password)
        && Arrays.equals(roles, player.roles);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(id, name, password);
    result = 31 * result + Arrays.hashCode(roles);
    return result;
  }

  @Override
  public String toString() {
    return "Player{" + "id=" + id + ", name='" + name + "', roles=" + Arrays.toString(roles) + "}";
  }
}
