package com.swe266group12.bankapp.repository;

import com.swe266group12.bankapp.model.BankUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<BankUser, Long> {
    List<BankUser> findByUsername(String username);

    List<BankUser> findByUsernameAndPassword(String username, String password);
}
