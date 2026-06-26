package com.utp.DemoOratorIA.domain.model.repositories;


import com.utp.DemoOratorIA.domain.model.aggregate.User;

public interface IUserRepository extends ICRUD<User> {

    User findByEmail(String email);

    long countUsuarios();

    long countPremiunUsers();

}
