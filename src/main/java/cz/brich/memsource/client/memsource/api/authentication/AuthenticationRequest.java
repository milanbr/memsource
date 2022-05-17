package cz.brich.memsource.client.memsource.api.authentication;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String userName;

    private String password;
}
