package it.plainvalue.spring.chess;

import static it.plainvalue.spring.chess.Chess.ROLE_PLAYER;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('" + ROLE_PLAYER + "')")
public interface PieceRepository extends PagingAndSortingRepository<Piece, Long> {

  @Override
  @PreAuthorize("#piece?.player == null or #piece?.player?.name == authentication?.name")
  <S extends Piece> S save(@Param("piece") S piece);

  @Override
  @PreAuthorize("@pieceRepository.findById(#id)?.player?.name == authentication?.name")
  void deleteById(@Param("id") Long id);

  @Override
  @PreAuthorize("#piece?.player?.name == authentication?.name")
  void delete(@Param("piece") Piece piece);
}
