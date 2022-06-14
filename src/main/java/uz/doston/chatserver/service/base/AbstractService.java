package uz.doston.chatserver.service.base;

import lombok.RequiredArgsConstructor;
import uz.doston.chatserver.mapper.base.BaseMapper;
import uz.doston.chatserver.repository.base.BaseRepository;

/**
 * @param <M> -> Mapper
 * @param <R> -> Repository
 */

@RequiredArgsConstructor
public class AbstractService<M extends BaseMapper, R extends BaseRepository> implements BaseService {

    protected final M mapper;
    protected final R repository;

}
