package uz.doston.chatserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.doston.chatserver.entity.Chat;
import uz.doston.chatserver.repository.base.BaseRepository;

public interface ChatRepository extends JpaRepository<Chat, Long>, BaseRepository {

}
