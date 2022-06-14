package uz.doston.chatserver.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import uz.doston.chatserver.dto.user.UserCreateDTO;
import uz.doston.chatserver.dto.user.UserDTO;
import uz.doston.chatserver.entity.User;
import uz.doston.chatserver.exceptions.BadRequestException;
import uz.doston.chatserver.mapper.UserMapper;
import uz.doston.chatserver.repository.UserRepository;
import uz.doston.chatserver.response.DataDTO;
import uz.doston.chatserver.response.ResponseEntity;
import uz.doston.chatserver.service.base.AbstractService;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "userCache")
public class UserService extends AbstractService<UserMapper, UserRepository> {

    public UserService(UserMapper mapper, UserRepository repository) {
        super(mapper, repository);
    }

    @CacheEvict(cacheNames = "users", allEntries = true)
    public ResponseEntity<DataDTO<Long>> create(UserCreateDTO dto) {
        User user = mapper.fromCreateDTO(dto);
        repository.save(user);
        return new ResponseEntity<>(new DataDTO<>(user.getId()));
    }

    @Cacheable(cacheNames = "users")
    public ResponseEntity<DataDTO<List<UserDTO>>> getAll() {
        List<User> userList = repository.findAll();
        List<UserDTO> userDTOList = mapper.toDTO(userList);
        return new ResponseEntity<>(new DataDTO<>(userDTOList, (long) userDTOList.size()));
    }

    @Cacheable(cacheNames = "users")
    public List<UserDTO> getAllByIds(List<Long> idList) {
        Optional<List<User>> optional = repository.findAllByIds(idList);
        return optional.map(mapper::toDTO).orElseThrow(() -> new BadRequestException("Any user not found"));
    }

    public UserDTO getById(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new BadRequestException("User not found"));
        return mapper.toDTO(user);
    }
}
