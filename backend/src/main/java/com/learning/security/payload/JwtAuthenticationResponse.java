package com.learning.security.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
}
