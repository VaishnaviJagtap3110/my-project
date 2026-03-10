package com.wheelshare.client.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponseDto {
	private String message;
    private Long userId;
    private String email;
    private String token;

}
