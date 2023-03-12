package com.test.api.security;

import lombok.Data;

@Data
public class AuthCredentials {
	
	private String email;
	private String password;
}
