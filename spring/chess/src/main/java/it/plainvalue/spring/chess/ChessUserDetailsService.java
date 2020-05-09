package it.plainvalue.spring.chess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ChessUserDetailsService implements UserDetailsService {

  private final PlayerRepository players;

  @Autowired
  public ChessUserDetailsService(PlayerRepository players) {
    this.players = players;
  }

  @Override
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    Player player = players.findByName(name);
    if (player == null) {
      throw new UsernameNotFoundException(name);
    }
    return new User(
        player.getName(),
        player.getPassword(),
        AuthorityUtils.createAuthorityList(player.getRoles()));
  }
}
