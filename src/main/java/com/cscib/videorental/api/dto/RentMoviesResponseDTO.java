package com.cscib.videorental.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class RentMoviesResponseDTO {

    private String clientId;

    private PaymentDTO payment;

    private List<RentalDTO> rented;

}