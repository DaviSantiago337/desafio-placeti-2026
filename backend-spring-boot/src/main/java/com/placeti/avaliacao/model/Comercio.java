package com.placeti.avaliacao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Comercio")
public class Comercio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "O nome do comércio é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    @Column(name = "NOME")
    private String nome;

    @NotBlank(message = "O nome do responsável é obrigatório")
    @Size(max = 100, message = "O nome do responsável deve ter no máximo 100 caracteres")
    @Column(name = "RESPONSAVEL")
    private String responsavel;

    @NotNull(message = "O tipo de comércio é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO")
    private TipoComercio tipo;

    @NotNull(message = "A cidade é obrigatória")
    @ManyToOne
    @JoinColumn(name = "CIDADE_ID")
    private Cidade cidade;
}