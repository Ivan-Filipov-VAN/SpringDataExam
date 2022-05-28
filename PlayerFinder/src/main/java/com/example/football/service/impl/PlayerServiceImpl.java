package com.example.football.service.impl;

import com.example.football.models.dto.PlayerPrintDto;
import com.example.football.models.dto.PlayerSeedRootDto;
import com.example.football.models.entity.Player;
import com.example.football.repository.PlayerRepository;
import com.example.football.service.PlayerService;
import com.example.football.service.StatService;
import com.example.football.service.TeamService;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    private static final String PLAYERS_FILE_PATH = "src/main/resources/files/xml/players.xml";

    private final PlayerRepository playerRepository;
    private final TeamService teamService;
    private final TownService townService;
    private final StatService statService;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;

    public PlayerServiceImpl(PlayerRepository playerRepository, TeamService teamService, TownService townService, StatService statService, ValidationUtil validationUtil, XmlParser xmlParser, ModelMapper modelMapper) {
        this.playerRepository = playerRepository;
        this.teamService = teamService;
        this.townService = townService;
        this.statService = statService;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return Files
                .readString(Path.of(PLAYERS_FILE_PATH));
    }

    @Override
    public String importPlayers() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        PlayerSeedRootDto playerSeedRootDto = xmlParser
                .fromFile(PLAYERS_FILE_PATH, PlayerSeedRootDto.class);

        playerSeedRootDto
                .getPlayers()
                .stream()
                .filter(playerSeedDto -> {
                    boolean isValid = validationUtil.isValid(playerSeedDto)
                            && !emailExists(playerSeedDto.getEmail());

                    sb
                            .append(isValid
                                    ? String.format("Successfully imported Player %s %s - %s",
                                    playerSeedDto.getFirstName(), playerSeedDto.getLastName(), playerSeedDto.getPosition())
                                    : "Invalid Player")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(playerSeedDto -> {
                    Player player = modelMapper.map(playerSeedDto, Player.class);
                    player.setTown(townService.findByName(playerSeedDto.getTown().getName()));
                    player.setTeam(teamService.findByName(playerSeedDto.getTeam().getName()));
                    player.setStat(statService.findById(playerSeedDto.getStat().getId()).orElse(null));
                    return player;
                })
                .forEach(playerRepository::save);

        return sb.toString().trim();
    }

    private boolean emailExists(String email) {
        return playerRepository.existsByEmail(email);
    }

    @Override
    public String exportBestPlayers() {
        return playerRepository.findTheBestPlayers()
                .stream()
                .map(PlayerPrintDto::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
