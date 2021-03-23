package com.cscib.videorental.core.service;

import com.cscib.videorental.api.dto.*;
import com.cscib.videorental.core.manager.PriceRuleManager;
import com.cscib.videorental.data.model.Client;
import com.cscib.videorental.data.model.Payment;
import com.cscib.videorental.data.model.Rental;
import com.cscib.videorental.data.model.mapper.RentMoviesMapper;
import com.cscib.videorental.data.repository.ClientRepository;
import com.cscib.videorental.data.repository.PaymentRepository;
import com.cscib.videorental.data.repository.RentalRepository;
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
    private MovieService movieService;



    public RentMoviesResponseDTO rentMovies(RentMoviesDTO rentMoviesDTO) throws Exception {
        List<Rental> rentals = rentalRepository.saveAll(rentMoviesDTO.getRented().stream()
                .map(tw-> saveRental(tw))
                .collect(Collectors.toList()));

        Payment payment = paymentRepository.save(Payment.builder()
                .rentals(rentals)
                .build());

        Client client = clientRepository.save(Client.builder()
                .id(rentMoviesDTO.getClientId())
                .rentals(rentals)
                .build());

        return RentMoviesMapper.mapToRentMoviesResponseDTO(rentals);
    }

    public ReturnMoviesResponseDTO returnMovies(ReturnMoviesDTO returnMoviesDTO) throws Exception {


//        List<Rental> rentals = rentalRepository.saveAll(rentMoviesDTO.getRented().stream()
//                .map(tw-> saveRental(tw))
//                .collect(Collectors.toList()));
//
//        Payment payment = paymentRepository.save(Payment.builder()
//                .rentals(rentals)
//                .build());
//
//        Client client = clientRepository.save(Client.builder()
//                .id(rentMoviesDTO.getClientId())
//                .currentRentals(rentals)
//                .build());
//
//        return RentMoviesMapper.mapToRentMoviesDTO(rentals);

        return null;
    }

    public Rental saveRental(RentalDTO rentalDTO) {
        Rental rental = Rental.builder()
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
