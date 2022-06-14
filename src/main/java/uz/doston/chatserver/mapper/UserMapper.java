package uz.doston.chatserver.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import uz.doston.chatserver.dto.user.UserCreateDTO;
import uz.doston.chatserver.dto.user.UserDTO;
import uz.doston.chatserver.entity.User;
import uz.doston.chatserver.mapper.base.GenericMapper;

import java.util.List;

@Component
@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL
)
public interface UserMapper extends GenericMapper<User, UserDTO, UserCreateDTO> {

    @Override
    User fromDTO(UserDTO dto);

    @Override
    List<User> fromDTO(List<UserDTO> dtoList);

    @Override
    UserDTO toDTO(User entity);

    @Override
    List<UserDTO> toDTO(List<User> entities);

    @Override
    User fromCreateDTO(UserCreateDTO dto);

}
