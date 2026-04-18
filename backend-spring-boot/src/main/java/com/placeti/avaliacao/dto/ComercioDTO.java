package com.placeti.avaliacao.dto;

import com.placeti.avaliacao.model.TipoComercio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ComercioDTO {
    
    private Long id;

    @NotBlank(message = "O nome do comércio é obrigatório")
    @Size(max = 100)
    private String nome;

    @NotBlank(message = "O nome do responsável é obrigatório")
    @Size(max = 100)
    private String responsavel;

    @NotNull(message = "O tipo de comércio é obrigatório")
    private TipoComercio tipo;

    @NotNull(message = "A cidade é obrigatória")
    private Long cidadeId;
}