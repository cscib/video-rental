package com.cscib.videorental.data.model.mapper;

import com.cscib.videorental.api.dto.PaymentDTO;
import com.cscib.videorental.api.dto.RentMoviesResponseDTO;
import com.cscib.videorental.api.dto.RentalDTO;
import com.cscib.videorental.api.dto.ReturnMoviesResponseDTO;
import com.cscib.videorental.data.model.Payment;
import com.cscib.videorental.data.model.Rental;
import com.cscib.videorental.exception.VideoRentalsException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RentMoviesMapper {
    public static RentMoviesResponseDTO mapToRentMoviesResponseDTO(List<Rental> rentals)
            throws VideoRentalsException {

        Payment payment = Optional.ofNullable(rentals.get(0).getPayment())
                .orElseThrow(VideoRentalsException::new);

        RentMoviesResponseDTO rentMoviesResponseDTO =
                Optional.of(payment)
                        .map(p -> mapToRentMoviesResponseDTO(p))
                        .get();

        return rentMoviesResponseDTO;
    }

    private static RentMoviesResponseDTO mapToRentMoviesResponseDTO(Payment p)  {
        return RentMoviesResponseDTO
                .builder()
                .clientId(p.getClient().getId())
                .payment(PaymentDTO.builder()
                        .amount(p.getAmount())
                        .currency(p.getCurrency())
                        .build())
                .rented(mapToRentalsDTO(p))
                .build();
    }

    private static List<RentalDTO> mapToRentalsDTO(Payment p) {

        return p.getRentals().stream()
                .map(r-> mapToRentalDTO(r))
                .collect(Collectors.toList());
    }

    private static RentalDTO mapToRentalDTO(Rental r) {
        return RentalDTO.builder()
                .movie(r.getMovie().getName())
                .expectedReturnOn(r.getExpectedReturnOn())
                .rentedOn(r.getRentedOn())
                .build();
    }

    public static ReturnMoviesResponseDTO mapToReturnMoviesResponseDTO(List<Rental> rentals)
            throws VideoRentalsException {

        // Throw Exceptions if there are no returns
        Rental rental = Optional.ofNullable(rentals.get(0))
                .orElseThrow(VideoRentalsException::new);


        // Extract the list of movie names returned
        List<String> returned = rentals.stream()
                .map(r->r.getMovie().getName())
                .collect(Collectors.toList());

        // Calculate Total Surcharge & Map RentMoviesResponseDTO
        BigDecimal surcharge = new BigDecimal(0);
        for (Rental r: rentals) {
            if (r.getSurcharge() != null) {
                surcharge = surcharge.add(r.getSurcharge().getAmount());
            }
        }

        // Return ReturnMoviesResponseDTO
        return ReturnMoviesResponseDTO
                .builder()
                .clientId(rental.getClient().getId())
                .surcharge(PaymentDTO.builder()
                        .amount(surcharge)
                        .currency(rental.getPayment().getCurrency())
                        .build())
                .returned(returned)
                .build();
    }


}
