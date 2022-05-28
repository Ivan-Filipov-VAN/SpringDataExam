package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.OfferSeedRootDto;
import softuni.exam.models.entity.Offer;
import softuni.exam.models.entity.enums.ApartmentType;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.AgentService;
import softuni.exam.service.ApartmentService;
import softuni.exam.service.OfferService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private static final String OFFERS_FILE_PATH = "src/main/resources/files/xml/offers.xml";
    private static final String SEARCHED_APARTMENT_TYPE = "three_rooms";

    private final OfferRepository offerRepository;
    private final AgentService agentService;
    private final ApartmentService apartmentService;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;

    public OfferServiceImpl(OfferRepository offerRepository,
                            AgentService agentService,
                            ApartmentService apartmentService,
                            ValidationUtil validationUtil,
                            XmlParser xmlParser,
                            ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.agentService = agentService;
        this.apartmentService = apartmentService;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files
                .readString(Path.of(OFFERS_FILE_PATH));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        OfferSeedRootDto offerSeedRootDto = xmlParser
                .fromFile(OFFERS_FILE_PATH, OfferSeedRootDto.class);

        offerSeedRootDto
                .getOffers()
                .stream()
                .filter(offerSeedDto -> {
                    boolean isValid = validationUtil.isValid(offerSeedDto)
                            && agentService.firstNameExists(offerSeedDto.getAgent().getName());

                    sb
                            .append(isValid
                                    ? String.format("Successfully imported offer %s",
                                    offerSeedDto.getPrice())
                                    : "Invalid offer")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(offerSeedDto -> {
                    Offer offer = modelMapper.map(offerSeedDto, Offer.class);
                    offer.setAgent(agentService.findByFirstName(offerSeedDto.getAgent().getName()));
                    offer.setApartment(apartmentService.findById(offerSeedDto.getApartment().getId()));
                    return offer;
                })
                .forEach(offerRepository::save);

        return sb.toString().trim();
    }

    @Override
    public String exportOffers() {
        ApartmentType apartmentType = ApartmentType.valueOf(SEARCHED_APARTMENT_TYPE);
        return offerRepository.findByApartmentApartmentTypeOrderByApartmentAreaDescPrice(apartmentType)
                .stream()
                .map(e -> String.format("Agent %s %s with offer №%d:%n" +
                        "   -Apartment area: %.2f%n" +
                        "   --Town: %s%n" +
                        "   ---Price: %s$",
                        e.getAgent().getFirstName(), e.getAgent().getLastName(),e.getId(),
                        e.getApartment().getArea(),
                        e.getApartment().getTown().getTownName(),
                        e.getPrice()
                ))
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
