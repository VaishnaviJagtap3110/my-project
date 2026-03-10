package com.wheelshare.client.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestDto {
	private String name;
    private String email;
    private String phone;
    private String password;
    private String role;

}
