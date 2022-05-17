package cz.brich.memsource.api.account;

import cz.brich.memsource.api.account.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {

    List<Account> findAll();
}
