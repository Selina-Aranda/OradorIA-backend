    package com.utp.DemoOratorIA.infraestructure.adapters;

    import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

    import org.springframework.stereotype.Repository;

    import com.utp.DemoOratorIA.domain.model.aggregate.User;
    import com.utp.DemoOratorIA.domain.model.repositories.IUserRepository;
    import com.utp.DemoOratorIA.infraestructure.entities.UserEntity;
    import com.utp.DemoOratorIA.infraestructure.mappers.UserMapper;
    import com.utp.DemoOratorIA.infraestructure.repositories.JPAUserRepository;

    @Repository
    public class UserRepositoryAdapter implements IUserRepository {
        
        private final JPAUserRepository jpa;
        private final UserMapper mapper;

        public UserRepositoryAdapter(JPAUserRepository jpa, UserMapper mapper) {
            this.jpa = jpa;
            this.mapper = mapper;
        }

        @Override
        public User findByEmail(String email) {
            UserEntity userEntity = jpa.findByEmail(email);
            return userEntity != null ? mapper.toDomain(userEntity) : null;
        }

        @Override
        public User save(User user) {
                UserEntity userEntity = mapper.toEntity(user);
                UserEntity savedEntity = jpa.save(userEntity);
                return mapper.toDomain(savedEntity);
        }

        @Override
        public Optional<User> findById(Integer id) {
            return jpa.findById(id)
                    .map(mapper::toDomain);
        }

        @Override
        public User update(User t) {
            UserEntity userEntity = mapper.toEntity(t);
            UserEntity updatedEntity = jpa.save(userEntity);
            return mapper.toDomain(updatedEntity);
        }

        @Override
        public List<User> list() {
            return jpa.findAll().stream()
                    .map(mapper::toDomain)
                    .collect(Collectors.toList());
        }

        @Override
        public void delete(Integer id) {
            jpa.deleteById(id);
        }
        
    }
