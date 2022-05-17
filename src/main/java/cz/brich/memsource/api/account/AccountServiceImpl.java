package cz.brich.memsource.api.account;

import cz.brich.memsource.api.account.dto.AccountRequest;
import cz.brich.memsource.api.account.dto.AccountResponse;
import cz.brich.memsource.api.account.mapper.AccountMapper;
import cz.brich.memsource.api.account.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Transactional
    @Override
    public AccountResponse createAccount(AccountRequest accountRequest) {
        accountRepository.deleteAll(); // supporting only 1 user
        Account account = accountMapper.remapRequestToEntity(accountRequest);
        Account saved = accountRepository.save(account);
        return accountMapper.remapEntityToResponse(saved);
    }

    @Transactional
    @Override
    public AccountResponse updateAccount(AccountRequest accountRequest, Long id) {
        Account account = accountRepository.findById(id).orElseThrow();
        accountMapper.remapRequestWithEntity(accountRequest, account);
        return accountMapper.remapEntityToResponse(account);
    }

    @Override
    public Account getAccount() {
        List<Account> accounts = accountRepository.findAll();

        if (accounts.size() != 1) {
            throw new IllegalArgumentException("There should be one account. It is: " + accounts.size());
        }

        return accounts.get(0);
    }

}
