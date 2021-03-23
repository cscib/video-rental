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
public class RentMoviesDTO {

    private String clientId;

    private List<RentalDTO> rented;
}