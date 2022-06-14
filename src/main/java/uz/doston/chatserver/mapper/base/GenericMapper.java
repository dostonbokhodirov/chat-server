package uz.doston.chatserver.mapper.base;

import java.util.List;

/**
 * @param <E>  -> Entity
 * @param <D>  -> Data Transfer Object (DTO)
 * @param <CD> -> Create DTO
 */
public interface GenericMapper<E, D, CD> extends BaseMapper {

    E fromDTO(D dto);

    List<E> fromDTO(List<D> dtoList);

    D toDTO(E entity);

    List<D> toDTO(List<E> entities);

    E fromCreateDTO(CD dto);

}
