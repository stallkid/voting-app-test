package com.cooperativism.voting.domain;

import com.mongodb.lang.NonNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
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
public class Schedule {
    @Id
    private String id;
    @NotBlank(message = "Schedule name is required")
    private String name;
    @NotBlank(message = "Schedule description is required")
    private String description;
    @CreatedDate
    private LocalDateTime createdDate;
    private LocalDateTime endDate;
    private List<Votes> votes;
}
