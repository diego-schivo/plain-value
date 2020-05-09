package it.plainvalue.spring.chess;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Piece {

  private @Id @GeneratedValue Long id;
  private String name;
  private @Version @JsonIgnore Long version;
  private @ManyToOne Player player;

  public Piece() {}

  public Piece(String name, Player player) {
    this.name = name;
    this.player = player;
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

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Piece piece = (Piece) o;
    return Objects.equals(id, piece.id)
        && Objects.equals(name, piece.name)
        && Objects.equals(version, piece.version)
        && Objects.equals(player, piece.player);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, version, player);
  }

  @Override
  public String toString() {
    return "Piece{"
        + "id="
        + id
        + ", name='"
        + name
        + "', version="
        + version
        + ", player="
        + player
        + "}";
  }
}
