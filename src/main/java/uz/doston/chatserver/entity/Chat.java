package uz.doston.chatserver.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.doston.chatserver.entity.base.Auditable;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Chat extends Auditable {

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "chat_users",
            joinColumns = {@JoinColumn(name = "chat_id")},
            inverseJoinColumns = {@JoinColumn(name = "users_id")})
    private List<User> users;
}
