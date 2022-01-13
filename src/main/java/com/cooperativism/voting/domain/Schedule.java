package com.cooperativism.voting.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@Document
@CompoundIndex(name = "id_cpf", def = "{'id': 1, 'votes.cpf': 1}")
public class Schedule {
    @Id
    @ApiModelProperty(notes = "ID da Pauta", required = true)
    private String id;
    @ApiModelProperty(notes = "Nome da Pauta", required = true)
    @NotBlank(message = "Schedule name is required")
    private String name;
    @ApiModelProperty(notes = "Descrição da Pauta", required = true)
    @NotBlank(message = "Schedule description is required")
    private String description;
    @ApiModelProperty(notes = "Data de criação do registro feito automático pelo Banco de Dados")
    @CreatedDate
    private LocalDateTime createdDate;
    @ApiModelProperty(notes = "Data de Fechamento da Pauta")
    private LocalDateTime endDate;
    @ApiModelProperty(notes = "Lista de Votos")
    private List<Votes> votes;
    @ApiModelProperty(notes = "Resultado dos votos da Pauta")
    private VoteResults voteResults;
    @ApiModelProperty(notes = "Estado da Sessão da Pauta")
    private Boolean status;
}
