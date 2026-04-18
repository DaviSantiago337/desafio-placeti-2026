package com.placeti.avaliacao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CidadeDTO {

    private Long id;

    @NotBlank(message = "O nome da cidade é obrigatório")
    @Size(max = 100)
    private String nome;

    @NotBlank(message = "A UF da cidade é obrigatória")
    @Size(min = 2, max = 2, message = "A UF deve ter exatamente 2 caracteres (ex: SP, SC)")
    private String uf;

    @NotNull(message = "É obrigatório informar se a cidade é capital")
    private Boolean capital;
}
