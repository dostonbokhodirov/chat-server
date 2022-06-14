package uz.doston.chatserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.doston.chatserver.entity.User;
import uz.doston.chatserver.repository.base.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, BaseRepository {

    @Query(value = "select u.* from public.users u where u.id in :idList", nativeQuery = true)
    Optional<List<User>> findAllByIds(@Param(value = "idList") List<Long> idList);

}
