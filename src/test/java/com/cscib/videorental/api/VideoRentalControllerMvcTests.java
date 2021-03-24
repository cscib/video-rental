package com.cscib.videorental.api;

import com.cscib.videorental.api.dto.PaymentDTO;
import com.cscib.videorental.api.dto.RentMoviesDTO;
import com.cscib.videorental.api.dto.RentMoviesResponseDTO;
import com.cscib.videorental.api.dto.RentalDTO;
import com.cscib.videorental.core.service.MovieService;
import com.cscib.videorental.core.service.RentMoviesService;
import com.cscib.videorental.data.model.Movie;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RunWith(SpringRunner.class)
@WebMvcTest(value = VideoRentalController.class)
public class VideoRentalControllerMvcTests extends AbstractControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RentMoviesService rentMoviesService;

    private PaymentDTO paymentDTO = new PaymentDTO(new BigDecimal(10.50),"EURO");

    private RentMoviesDTO rentMoviesDTO = mapFromJson("{\"clientId\":\"555666M\", \"rented\":[{\"movie\":\"Matrix 11\",\"rentedOn\":null,\"expectedReturnOn\":null},{\"movie\":\"Spider Man\",\"rentedOn\":null,\"expectedReturnOn\":null}]}"
            ,RentMoviesDTO.class);
    private RentMoviesResponseDTO rentMoviesDTOResponse = new RentMoviesResponseDTO(rentMoviesDTO.getClientId(),paymentDTO,rentMoviesDTO.getRented());


    public VideoRentalControllerMvcTests() throws IOException {
    }

    @Test
    public void rentMovieTest() throws Exception {

        Mockito.when(
                rentMoviesService.rentMovies(Mockito.any(RentMoviesDTO.class))).thenReturn(rentMoviesDTOResponse);

        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/v1/rental/rent")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapToJson(rentMoviesDTO)))
                .andReturn();

        log.info("Test getMovie result - {}.",result.getResponse()
                .getContentAsString());

        JSONAssert.assertEquals(mapToJson(rentMoviesDTOResponse), result.getResponse()
                .getContentAsString(), false);
    }


}
