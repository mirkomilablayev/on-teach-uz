package uz.onteach.onteachuz.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import uz.onteach.onteachuz.domain.User;

public interface UserRepository extends R2dbcRepository<User, Long> {
}
