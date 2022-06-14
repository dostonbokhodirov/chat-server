package uz.doston.chatserver.entity;

import lombok.*;
import uz.doston.chatserver.entity.base.Auditable;
import uz.doston.chatserver.enums.MessageType;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Message extends Auditable {

    @OneToOne
    private Chat chat;

    @ManyToOne
    @JoinColumn
    private User author;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private MessageType contentType;

    @Column(nullable = false)
    private String content;

}
