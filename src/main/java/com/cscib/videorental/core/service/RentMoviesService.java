package com.cscib.videorental.core.service;

import com.cscib.videorental.api.dto.*;
import com.cscib.videorental.core.manager.BonusRuleManager;
import com.cscib.videorental.data.model.Client;
import com.cscib.videorental.data.model.Rental;
import com.cscib.videorental.data.model.mapper.RentMoviesMapper;
import com.cscib.videorental.data.repository.ClientRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;


@NoArgsConstructor
@Component
public class RentMoviesService {


    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BonusRuleManager bonusRuleManager;

    @Autowired
    private RentalService rentalService;


    /**
     * A method called by the api to rent movies. It updates db with client, rental, payment and bonus information
     * @param rentMoviesDTO
     * @return an object with the movies rented and the total payment required.
     * @throws Exception
     */
    public RentMoviesResponseDTO rentMovies(RentMoviesDTO rentMoviesDTO) throws Exception {

        // Save / Update client with new bonus info
        Client client = clientRepository.findById(rentMoviesDTO.getClientId())
                .orElse(clientRepository.save(Client.builder()
                        .id(rentMoviesDTO.getClientId())
                        .bonusPoints(0)
                        .build()));
        List<Rental> rentals = rentalService.rentMovies(rentMoviesDTO, client);

        int bonusPoints = client.getBonusPoints();
        Client updatedClient = clientRepository.save(Client.builder()
                .id(rentMoviesDTO.getClientId())
                .bonusPoints(bonusPoints + bonusRuleManager.calculateBonus(rentals))
                .build());

        return RentMoviesMapper.mapToRentMoviesResponseDTO(rentals);
    }


    /**
     * A method called by the api to return movies. It updates db with surcharge and returned on information
     * @param returnMoviesDTO
     * @return A list of the movies returned together with any surcharge amount required to be paid.
     * @throws Exception
     */
    public ReturnMoviesResponseDTO returnMovies(ReturnMoviesDTO returnMoviesDTO) throws Exception {
        List<Rental> rentals = rentalService.returnMovies(returnMoviesDTO);

        return RentMoviesMapper.mapToReturnMoviesResponseDTO(rentals);
    }
}
