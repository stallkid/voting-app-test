package com.cooperativism.voting.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoteResults {
    private Long yesVotes;
    private Long noVotes;
}
