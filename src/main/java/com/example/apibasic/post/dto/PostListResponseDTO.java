package com.example.apibasic.post.dto;

import lombok.*;

import java.util.List;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder

public class PostListResponseDTO {
    private int count;
    private List<PostResponseDTO> posts;
}
