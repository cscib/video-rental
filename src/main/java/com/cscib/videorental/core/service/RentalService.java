package com.cscib.videorental.core.service;

import com.cscib.videorental.api.dto.*;
import com.cscib.videorental.core.manager.PriceRuleManager;
import com.cscib.videorental.data.model.Client;
import com.cscib.videorental.data.model.Payment;
import com.cscib.videorental.data.model.Rental;
import com.cscib.videorental.data.model.Surcharge;
import com.cscib.videorental.data.repository.PaymentRepository;
import com.cscib.videorental.data.repository.RentalRepository;
import com.cscib.videorental.data.repository.SurchargeRepository;
import com.cscib.videorental.exception.VideoRentalsException;
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
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private SurchargeRepository surchargeRepository;

    @Autowired
    private PriceRuleManager priceRuleManager;

    @Autowired
    private MovieService movieService;

    public Rental getRental(int rentalId){
        return Optional.of(rentalRepository.findById(rentalId).get()).orElseThrow(PersistenceException::new);
    }

    public List<Rental > getRentals() {
        return Optional.of(rentalRepository.findAll()).orElseThrow(PersistenceException::new);
    }

    public List<Rental> rentMovies(RentMoviesDTO rentMoviesDTO, Client client) {
        // Save payment with calculated payment info
        Payment payment = Payment.builder()
                .client(client)
                .currency(priceRuleManager.getCurrency())
                .paidAmountOn(DateUtils.getCurrentTime())
                .build();

        // Save new video rentals
        List<Rental> rentals = saveRentals(rentMoviesDTO, client, payment);

        // Save payment with calculated payment info
        payment.setAmount(priceRuleManager.calculatePrice(rentals));
        payment.setRentals(rentals);
        payment = paymentRepository.save(payment);
        return rentals;
    }



    public List<Rental> returnMovies(ReturnMoviesDTO returnMoviesDTO) throws VideoRentalsException {
        // Update RentalRepository with the info when the movies where returned.
        List<Rental> rentals = returnMoviesDTO.getReturned().stream()
                .map(r-> returnRental(r, returnMoviesDTO.getClientId()))
                .collect(Collectors.toList());

               // Calculate and save surcharges
        rentals = updateRentalsWithSurcharges(rentals);

        return rentals;
    }

    private List<Rental> saveRentals(RentMoviesDTO rentMoviesDTO, Client client, Payment payment) {
        return rentMoviesDTO.getRented().stream()
                .map(r-> rentRental(r, client, payment))
                .collect(Collectors.toList());
    }


    private Rental rentRental(RentalDTO rentalDTO, Client client, Payment payment) {
        Rental rental = Rental.builder()
                .client(client)
                .payment(payment)
                .movie(movieService.getMovie(rentalDTO.getMovie()))
                .rentedOn(rentalDTO.getRentedOn())
                .expectedReturnOn(rentalDTO.getExpectedReturnOn())
                .build();

        return rentalRepository.save(rental);
    }

    private Rental returnRental(String movie, String client) {
        Rental rental = rentalRepository.find(movie)
                .stream()
                .filter(r->r.getClient().getId().equals(client) && r.getReturnedOn() == null)
                .findAny().get();

        rental.setReturnedOn(DateUtils.getCurrentTime());
        return rentalRepository.save(rental);
    }

    private List<Rental> updateRentalsWithSurcharges(List<Rental> rentals) throws VideoRentalsException {
        Payment payment = Optional.ofNullable(rentals.get(0).getPayment()).
                orElseThrow(VideoRentalsException::new);

        rentals = rentals.stream()
                .filter(r->DateUtils.calculateSurchargeDays(r.getExpectedReturnOn(), r.getReturnedOn()) > 0)
                .map(r-> saveSurcharge(r).getRental())
                .collect(Collectors.toList());

        return rentalRepository.saveAll(rentals);
    }


    private Surcharge saveSurcharge(Rental r) {

        Surcharge surcharge = Surcharge.builder()
                .currency(priceRuleManager.getCurrency())
                .paidSurchargeOn(DateUtils.getCurrentTime())
                .amount(priceRuleManager.calculateSurcharge(r))
                .payment(r.getPayment())
                .build();
        r.setSurcharge(surcharge);
        surcharge.setRental(r);

        return surchargeRepository.save(surcharge);
    }
}
