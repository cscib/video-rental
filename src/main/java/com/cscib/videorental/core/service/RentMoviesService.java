package com.cscib.videorental.core.service;

import com.cscib.videorental.api.dto.*;
import com.cscib.videorental.core.manager.BonusRuleManager;
import com.cscib.videorental.core.manager.PriceRuleManager;
import com.cscib.videorental.data.model.Client;
import com.cscib.videorental.data.model.Payment;
import com.cscib.videorental.data.model.Rental;
import com.cscib.videorental.data.model.Surcharge;
import com.cscib.videorental.data.model.enums.MovieCategoryEnum;
import com.cscib.videorental.data.model.mapper.RentMoviesMapper;
import com.cscib.videorental.data.repository.ClientRepository;
import com.cscib.videorental.data.repository.PaymentRepository;
import com.cscib.videorental.data.repository.RentalRepository;
import com.cscib.videorental.data.repository.SurchargeRepository;
import com.cscib.videorental.util.DateUtils;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor
@Component
public class RentMoviesService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private SurchargeRepository surchargeRepository;

    @Autowired
    private PriceRuleManager priceRuleManager;

    @Autowired
    private BonusRuleManager bonusRuleManager;

    @Autowired
    private MovieService movieService;



    public RentMoviesResponseDTO rentMovies(RentMoviesDTO rentMoviesDTO) throws Exception {

        // Save / Update client with new bonus info
        Client client = Optional.ofNullable(clientRepository.getOne(rentMoviesDTO.getClientId()))
                .orElse(clientRepository.save(Client.builder()
                        .id(rentMoviesDTO.getClientId())
                        .bonusPoints(0)
                        .build()));

        // Save new video rentals
        List<Rental> rentals = rentalRepository.saveAll(rentMoviesDTO.getRented().stream()
                .map(r-> saveRental(r, client))
                .collect(Collectors.toList()));

        // Save payment with calculated payment info
        Payment payment = paymentRepository.save(Payment.builder()
                .rentals(rentals)
                .amount(priceRuleManager.calculatePrice(rentals))
                .currency(priceRuleManager.getCurrency())
                .paidAmountOn(DateUtils.getCurrentTime())
                .build());


        int bonusPoints = client.getBonusPoints();
        Client updatedClient = clientRepository.save(Client.builder()
                .id(rentMoviesDTO.getClientId())
                .bonusPoints(bonusPoints + bonusRuleManager.calculateBonus(rentals))
                .build());

        return RentMoviesMapper.mapToRentMoviesResponseDTO(rentals);
    }

    public ReturnMoviesResponseDTO returnMovies(ReturnMoviesDTO returnMoviesDTO) throws Exception {

        // Update RentalRepository with the info when the movies where returned.
        List<Rental> rentals = rentalRepository.saveAll(returnMoviesDTO.getReturned().stream()
                .map(r->updateRental(r))
                .collect(Collectors.toList()));

        // Calculate and save surcharges
        List<Surcharge> surcharges = surchargeRepository.saveAll(
                priceRuleManager.calculateSurcharges(rentals));

        // Update Payment Info
        Payment payment = paymentRepository.save(Payment.builder()
                .surcharges(surcharges)
                .rentals(rentals)
                .build());

        return RentMoviesMapper.mapToReturnMoviesResponseDTO(rentals);
    }

    private Rental updateRental(RentalDTO r) {
        return null;
    }

    public Rental saveRental(RentalDTO rentalDTO, Client client) {
        Rental rental = Rental.builder()
                .client(client)
                .movie(movieService.getMovie(rentalDTO.getMovie()))
                .rentedOn(rentalDTO.getRentedOn())
                .expectedReturnOn(rentalDTO.getExpectedReturnOn())
                .build();

        return rentalRepository.save(rental);
    }

    public Rental getRental(int rentalId){
        return Optional.of(rentalRepository.findById(rentalId).get()).orElseThrow(PersistenceException::new);
    }

    public List<Rental > getPrices() {
        return Optional.of(rentalRepository.findAll()).orElseThrow(PersistenceException::new);
    }

}
