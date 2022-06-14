package uz.doston.chatserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.doston.chatserver.entity.Chat;
import uz.doston.chatserver.entity.Message;
import uz.doston.chatserver.repository.base.BaseRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long>, BaseRepository {

    List<Message> findAllByChat(Chat chat);

}
