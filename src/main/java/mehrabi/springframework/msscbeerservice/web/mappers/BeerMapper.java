package mehrabi.springframework.msscbeerservice.web.mappers;

import mehrabi.springframework.msscbeerservice.domain.Beer;
import mehrabi.springframework.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
    @Mapping(target = "quantityOnHand", source = "")
    BeerDto beerToBeerDTO(Beer beer);

    @Mapping(target = "quantityToBrew", source = "")
    @Mapping(target = "minOnHand", source = "")
    Beer beerDTOToBeer(BeerDto beerDTO);
}
