package com.wheelshare.client.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerifyOtpRequestDto {
	private Long userId;
    private String otpCode;

}
