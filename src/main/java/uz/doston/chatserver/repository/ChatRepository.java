package uz.doston.chatserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.doston.chatserver.entity.Chat;
import uz.doston.chatserver.repository.base.BaseRepository;

public interface ChatRepository extends JpaRepository<Chat, Long>, BaseRepository {

    @Query(
            value = "select cast((select array_to_json(array_agg(row_to_json(my_table)))" +
                    " from (select ch.name, ch.created_at, u.* as users from public.chat ch " +
                    "inner join public.chat_user chu on ch.id = chu.chat_id " +
                    "inner join users u on u.id = chu.users_id order by ch.name) as my_table) as text)",
            nativeQuery = true)
    String findAllDetails();
}
