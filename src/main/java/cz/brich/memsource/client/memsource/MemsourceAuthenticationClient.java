package cz.brich.memsource.client.memsource;

import cz.brich.memsource.client.memsource.api.authentication.AuthenticationRequest;
import cz.brich.memsource.client.memsource.api.authentication.AuthenticationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "memsourceAuthentication", url = "${app.services.memsource.url}")
public interface MemsourceAuthenticationClient {

    @PostMapping("/api2/v1/auth/login")
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
