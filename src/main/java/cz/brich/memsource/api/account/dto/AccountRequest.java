package cz.brich.memsource.api.account.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AccountRequest {

    @NotNull
    private String userName;

    @NotNull
    private String password;
}
