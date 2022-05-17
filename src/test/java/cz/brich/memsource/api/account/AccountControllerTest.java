package cz.brich.memsource.api.account;

import cz.brich.memsource.MemsourceServiceApplication;
import cz.brich.memsource.api.account.dto.AccountRequest;
import cz.brich.memsource.api.account.dto.AccountResponse;
import cz.brich.memsource.db.PrepareDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = MemsourceServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import({PrepareDatabase.class})
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // allows use non-static beforeAll method
public class AccountControllerTest {

    @Autowired
    private AccountController accountController;

    @Test
    public void createUpdateAccount() {
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setUserName("testUser");
        accountRequest.setPassword("testPass");

        AccountResponse createAccountResponse = accountController.createAccount(accountRequest);
        assertThat(createAccountResponse.getUserName()).isEqualTo(accountRequest.getUserName());
        assertThat(createAccountResponse.getPassword()).isEqualTo(accountRequest.getPassword());
        assertThat(createAccountResponse.getId()).isNotNull();

        AccountResponse updateAccountResponse = accountController.updateAccount(accountRequest, createAccountResponse.getId());
        assertThat(updateAccountResponse.getUserName()).isEqualTo(accountRequest.getUserName());
        assertThat(updateAccountResponse.getPassword()).isEqualTo(accountRequest.getPassword());
        assertThat(updateAccountResponse.getId()).isNotNull();
        assertThat(updateAccountResponse.getId()).isEqualTo(createAccountResponse.getId());
    }
}

