package com.cscib.videorental.data.model.mapper;

import com.cscib.videorental.api.dto.PaymentDTO;
import com.cscib.videorental.api.dto.RentMoviesResponseDTO;
import com.cscib.videorental.api.dto.RentalDTO;
import com.cscib.videorental.data.model.Payment;
import com.cscib.videorental.data.model.Rental;
import com.cscib.videorental.exception.VideoRentalsException;
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

}
