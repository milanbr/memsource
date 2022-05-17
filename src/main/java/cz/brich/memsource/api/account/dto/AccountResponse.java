package cz.brich.memsource.api.account.dto;

import lombok.Data;

@Data
public class AccountResponse {

    private String userName;

    private String password;

    private Long id;
}
