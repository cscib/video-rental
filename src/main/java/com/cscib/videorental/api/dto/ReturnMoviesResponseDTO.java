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
public class ReturnMoviesResponseDTO {

    private String clientId;

    private PaymentDTO surcharge;

    private List<String> returned;

}