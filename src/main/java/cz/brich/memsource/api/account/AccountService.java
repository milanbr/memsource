package cz.brich.memsource.api.account;

import cz.brich.memsource.api.account.dto.AccountRequest;
import cz.brich.memsource.api.account.dto.AccountResponse;
import cz.brich.memsource.api.account.entity.Account;

public interface AccountService {

    AccountResponse createAccount(AccountRequest accountRequest);

    AccountResponse updateAccount(AccountRequest accountRequest, Long id);

    Account getAccount();
}
