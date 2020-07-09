package mehrabi.springframework.msscbeerservice.services;

import lombok.RequiredArgsConstructor;
import mehrabi.springframework.msscbeerservice.domain.Beer;
import mehrabi.springframework.msscbeerservice.repositories.BeerRepository;
import mehrabi.springframework.msscbeerservice.web.controller.NotFoundException;
import mehrabi.springframework.msscbeerservice.web.mappers.BeerMapper;
import mehrabi.springframework.msscbeerservice.web.model.BeerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getById(UUID beerId) {
        return beerMapper.beerToBeerDTO(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return beerMapper.beerToBeerDTO(beerRepository.save(beerMapper.beerDTOToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return beerMapper.beerToBeerDTO(beerRepository.save(beer));
    }
}
