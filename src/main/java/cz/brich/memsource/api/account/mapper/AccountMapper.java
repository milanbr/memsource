package cz.brich.memsource.api.account.mapper;

import cz.brich.memsource.api.account.dto.AccountRequest;
import cz.brich.memsource.api.account.dto.AccountResponse;
import cz.brich.memsource.api.account.entity.Account;
import cz.brich.memsource.client.memsource.api.authentication.AuthenticationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class AccountMapper {

    @Mapping(target = "id", ignore = true)
    public abstract Account remapRequestToEntity(AccountRequest accountRequest);

    @Mapping(target = "id", ignore = true)
    public abstract void remapRequestWithEntity(AccountRequest accountRequest, @MappingTarget Account account);

    public abstract AuthenticationRequest remapEntityToAuthenticationRequest(Account account);

    public abstract AccountResponse remapEntityToResponse(Account account);
}
