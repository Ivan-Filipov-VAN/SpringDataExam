package com.example.football.repository;

import com.example.football.models.dto.PlayerPrintDto;
import com.example.football.models.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    boolean existsByEmail(String email);


    @Query("SELECT new com.example.football.models.dto.PlayerPrintDto(" +
            "CONCAT(p.firstName, ' ', p.lastName) AS fullName, p.position, p.team.name, p.team.stadiumName)" +
            " FROM Player p" +
            " WHERE p.birthDate BETWEEN '1995-01-01' AND '2003-01-01' " +
            " ORDER BY p.stat.shooting DESC, p.stat.passing DESC, p.stat.endurance DESC" )
    List<PlayerPrintDto> findTheBestPlayers();
}
