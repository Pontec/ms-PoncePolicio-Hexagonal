package com.codigo.ms_PoncePolicio_Hexagonal.domain.aggregates.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
public class ResponseBase {
    private int code;
    private String message;
    private Optional data;
}
