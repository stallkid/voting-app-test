package com.cooperativism.voting.domain;

import com.cooperativism.voting.domain.enums.VoteEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Votes {
    @ApiModelProperty(notes = "CPF do colaborador", required = true)
    @NotBlank(message = "Associate cpf is required")
    @Indexed(unique = true, sparse = true)
    @Max(11)
    private String cpf;
    @ApiModelProperty(notes = "Voto do colaborador", required = true)
    @Pattern(regexp = "SIM|NAO", message = "Vote must be SIM or NAO")
    @NotBlank(message = "Associate vote is required")
    private VoteEnum vote;
}
