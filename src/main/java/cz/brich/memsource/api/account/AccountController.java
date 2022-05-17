package cz.brich.memsource.api.account;

import cz.brich.memsource.api.account.dto.AccountRequest;
import cz.brich.memsource.api.account.dto.AccountResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public AccountResponse createAccount(@RequestBody @Valid final AccountRequest accountRequest) {
        return accountService.createAccount(accountRequest);
    }

    @PutMapping("/{id}")
    public AccountResponse updateAccount(@RequestBody @Valid final AccountRequest accountRequest, @PathVariable Long id) {
        return accountService.updateAccount(accountRequest, id);
    }
}
