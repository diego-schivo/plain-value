package it.plainvalue.spring.chess;

import static it.plainvalue.spring.chess.Chess.ROLE_PLAYER;
import static it.plainvalue.spring.chess.WebSocketConfiguration.MESSAGE_PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Piece.class)
public class PieceEventHandler {

  @Autowired private PlayerRepository players;

  @Autowired private SimpMessagingTemplate messaging;

  @Autowired private EntityLinks links;

  @HandleBeforeCreate
  @HandleBeforeSave
  public void assignPlayer(Piece piece) {
    String playerName = SecurityContextHolder.getContext().getAuthentication().getName();
    Player player = players.findByName(playerName);
    if (player == null) {
      player = players.save(new Player(playerName, null, new String[] {ROLE_PLAYER}));
    }
    piece.setPlayer(player);
  }

  @HandleAfterCreate
  public void sendCreate(Piece piece) {
    messaging.convertAndSend(MESSAGE_PREFIX + "/createPiece", getPath(piece));
  }

  @HandleAfterSave
  public void sendSave(Piece piece) {
    messaging.convertAndSend(MESSAGE_PREFIX + "/savePiece", getPath(piece));
  }

  @HandleAfterDelete
  public void sendDelete(Piece piece) {
    messaging.convertAndSend(MESSAGE_PREFIX + "/deletePiece", getPath(piece));
  }

  private String getPath(Piece piece) {
    return links.linkForItemResource(piece.getClass(), piece.getId()).toUri().getPath();
  }
}
