package com.utp.DemoOratorIA.domain.model.repositories;


import com.utp.DemoOratorIA.domain.model.aggregate.User;
import com.utp.DemoOratorIA.infraestructure.entities.UserEntity;

public interface IUserRepository extends ICRUD<User> {

    User findByEmail(String email);

}
