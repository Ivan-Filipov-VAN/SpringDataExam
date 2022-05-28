package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AgentSeedDto;
import softuni.exam.models.entity.Agent;
import softuni.exam.repository.AgentRepository;
import softuni.exam.service.AgentService;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class AgentServiceImpl implements AgentService {

    private static final String AGENTS_FILE_PATH = "src/main/resources/files/json/agents.json";

    private final AgentRepository agentRepository;
    private final TownService townService;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public AgentServiceImpl(AgentRepository agentRepository,
                            TownService townService,
                            ValidationUtil validationUtil,
                            ModelMapper modelMapper,
                            Gson gson) {
        this.agentRepository = agentRepository;
        this.townService = townService;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return agentRepository.count() > 0;
    }

    @Override
    public String readAgentsFromFile() throws IOException {
        return Files
                .readString(Path.of(AGENTS_FILE_PATH));
    }

    @Override
    public String importAgents() throws IOException {
        StringBuilder sb = new StringBuilder();

        AgentSeedDto[] agentSeedDtos = gson
                .fromJson(readAgentsFromFile(), AgentSeedDto[].class);

        Arrays.stream(agentSeedDtos)
                .filter(agentSeedDto -> {
                    boolean isValid = validationUtil.isValid(agentSeedDto)
                            && townService.townExists(agentSeedDto.getTown())
                            && !firstNameExists(agentSeedDto.getFirstName());

                    sb
                            .append(isValid
                                    ? String.format("Successfully imported agent - %s %s",
                                    agentSeedDto.getFirstName(), agentSeedDto.getLastName())
                                    : "Invalid agent")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(agentSeedDto -> {
                    Agent agent = modelMapper.map(agentSeedDto, Agent.class);
                    agent.setTown(townService.findByTownName(agentSeedDto.getTown()));
                    return agent;
                })
                .forEach(agentRepository::save);


        return sb.toString().trim();
    }

    @Override
    public boolean firstNameExists(String firstName) {
        return agentRepository.existsByFirstName(firstName);
    }

    @Override
    public Agent findByFirstName(String name) {
        return agentRepository.findByFirstName(name).orElse(null);
    }
}
