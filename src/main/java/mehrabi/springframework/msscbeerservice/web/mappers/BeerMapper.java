package mehrabi.springframework.msscbeerservice.web.mappers;

import mehrabi.springframework.msscbeerservice.domain.Beer;
import mehrabi.springframework.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
    BeerDto beerToBeerDTO(Beer beer);

    Beer beerDTOToBeer(BeerDto beerDTO);
}
