package io.muzoo.ssc.springwebapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchingResponse {
    String username;
    String displayName;
    String biography;
    int age;
    String contacts;
    Set<String> dislikes;

}
