package mehrabi.springframework.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mehrabi.springframework.msscbeerservice.bootstrap.BeerLoader;
import mehrabi.springframework.msscbeerservice.services.BeerService;
import mehrabi.springframework.msscbeerservice.web.model.BeerDto;
import mehrabi.springframework.msscbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    @Test
    void getBeerById() throws Exception {
        given(beerService.getById(any())).willReturn(getValidBeerDto());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/beer/"+ UUID.randomUUID()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {


        BeerDto beerDto = getValidBeerDto();
        String value = objectMapper.writeValueAsString(beerDto);

        given(beerService.saveNewBeer(any())).willReturn(getValidBeerDto());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(value)).andExpect(status().isCreated());
    }

    @Test
    void updateBeerById() throws Exception {
        given(beerService.updateBeer(any(),any())).willReturn(getValidBeerDto());

        BeerDto beerDto = getValidBeerDto();
        String value = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/beer/"+ UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(value)).andExpect(status().isNoContent());

    }

    BeerDto getValidBeerDto(){
        return BeerDto.builder()
                .beerName("My Beer")
                .beerStyle(BeerStyleEnum.ALE)
                .price(new BigDecimal("2.99"))
                .upc(BeerLoader.BEER_1_UPC)
                .build();
    }
}