package com.maykealisson.webflux.domain.mapper;

import com.maykealisson.webflux.domain.entity.User;
import com.maykealisson.webflux.domain.request.UserRequest;
import com.maykealisson.webflux.domain.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = IGNORE,
        nullValueCheckStrategy = ALWAYS
)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toEntity(final UserRequest request);

    UserResponse toResponse(final User user);
}
