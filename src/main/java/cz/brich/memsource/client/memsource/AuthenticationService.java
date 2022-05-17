package cz.brich.memsource.client.memsource;

import cz.brich.memsource.api.account.AccountService;
import cz.brich.memsource.api.account.entity.Account;
import cz.brich.memsource.api.account.mapper.AccountMapper;
import cz.brich.memsource.client.memsource.api.authentication.AuthenticationRequest;
import cz.brich.memsource.client.memsource.api.authentication.AuthenticationResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final MemsourceAuthenticationClient memsourceAuthenticationClient;

    private final AccountService accountService;

    private final AccountMapper accountMapper;

    public AuthenticationService(MemsourceAuthenticationClient memsourceAuthenticationClient,
                                 AccountService accountService, AccountMapper accountMapper) {

        this.memsourceAuthenticationClient = memsourceAuthenticationClient;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @Cacheable("token")
    public String getToken() {
        Account account = accountService.getAccount();
        AuthenticationRequest authenticationRequest = accountMapper.remapEntityToAuthenticationRequest(account);

        AuthenticationResponse authenticationResponse = memsourceAuthenticationClient.authenticate(authenticationRequest);

        if (authenticationResponse == null) {
            throw new IllegalArgumentException("authenticationResponse is null!");
        }

        return authenticationResponse.getToken();
    }
}
