package it.plainvalue.spring.chess;

import static it.plainvalue.spring.chess.Chess.ROLE_PLAYER;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

  @Autowired protected PlayerRepository players;
  @Autowired protected PieceRepository pieces;

  @Override
  public void run(String... args) throws Exception {
    for (Color color : Color.values()) {

      Player player = player(color);

      List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(ROLE_PLAYER);
      Authentication authentication =
          new UsernamePasswordAuthenticationToken(player.getName(), "unknown", authorities);
      SecurityContextHolder.getContext().setAuthentication(authentication);

      for (PieceType type : PieceType.values()) {
        for (int index = 0; index < type.getCount(); index++) {
          piece(color, type, index + 1, player);
        }
      }

      SecurityContextHolder.clearContext();
    }
  }

  protected Player player(Color color) {
    String playerName = color.name().toLowerCase();
    Player player = new Player(playerName, playerName, ROLE_PLAYER);
    player = players.save(player);
    return player;
  }

  protected Piece piece(Color color, PieceType type, int number, Player player) {
    String pieceName = color.name().toLowerCase() + "-" + type.name().toLowerCase();
    if (type.getCount() > 1) {
      pieceName += "-" + number;
    }
    Piece piece = new Piece(pieceName, player);
    piece = pieces.save(piece);
    return piece;
  }
}
