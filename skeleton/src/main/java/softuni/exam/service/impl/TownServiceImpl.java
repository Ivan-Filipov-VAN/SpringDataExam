package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.TownSeedDto;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Service
public class TownServiceImpl implements TownService {

    private static final String TOWNS_FILE_PATH = "src/main/resources/files/json/towns.json";

    private final TownRepository townRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public TownServiceImpl(TownRepository townRepository,
                           ValidationUtil validationUtil,
                           ModelMapper modelMapper,
                           Gson gson) {
        this.townRepository = townRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files
                .readString(Path.of(TOWNS_FILE_PATH));
    }

    @Override
    public String importTowns() throws IOException {
        StringBuilder sb = new StringBuilder();

        TownSeedDto[] townSeedDtos = gson
                .fromJson(readTownsFileContent(), TownSeedDto[].class);

        Arrays.stream(townSeedDtos)
                .filter(townSeedDto -> {
                    boolean isValid = validationUtil.isValid(townSeedDto)
                            && !townExists(townSeedDto.getTownName());
                    sb
                            .append(isValid
                                    ? String.format("Successfully imported town %s - %d",
                                    townSeedDto.getTownName(), townSeedDto.getPopulation())
                                    : "Invalid town")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(townSeedDto -> modelMapper.map(townSeedDto, Town.class))
                .forEach(townRepository::save);

        return sb.toString().trim();
    }

    @Override
    public boolean townExists(String townName) {
        return townRepository.existsByTownName(townName);
    }

    @Override
    public Town findByTownName(String town) {
        return townRepository.findByTownName(town).orElse(null);
    }
}
