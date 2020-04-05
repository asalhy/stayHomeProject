package com.stayhome.service.mapper;

import com.stayhome.domain.Authority;
import com.stayhome.domain.User;
import com.stayhome.service.dto.LocalityDTO;
import com.stayhome.service.dto.ServiceTypeDTO;
import com.stayhome.service.dto.UserDTO;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Mapper for the entity {@link User} and its DTO called {@link UserDTO}.
 *
 * Normal mappers are generated using MapStruct, this one is hand-coded as MapStruct
 * support is still in beta, and requires a manual step with an IDE.
 */
@Service
// FIXME - Use mapstruct
public class UserMapper {

    private final OrganizationMapper organizationMapper;
    private final LocalityMapper localityMapper;
    private final ServiceTypeMapper serviceTypeMapper;

    public UserMapper(OrganizationMapper organizationMapper, LocalityMapper localityMapper, ServiceTypeMapper serviceTypeMapper) {
        this.organizationMapper = organizationMapper;
        this.localityMapper = localityMapper;
        this.serviceTypeMapper = serviceTypeMapper;
    }

    public List<UserDTO> usersToUserDTOs(List<User> users) {
        return users.stream()
            .filter(Objects::nonNull)
            .map(this::userToUserDTO)
            .collect(Collectors.toList());
    }

    public UserDTO userToUserDTO(User user) {
        return new UserDTO(user);
    }

    public List<User> userDTOsToUsers(List<UserDTO> userDTOs) {
        return userDTOs.stream()
            .filter(Objects::nonNull)
            .map(this::userDTOToUser)
            .collect(Collectors.toList());
    }

    public User userDTOToUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        } else {
            User user = new User();
            user.setId(userDTO.getId());
            user.setLogin(userDTO.getLogin());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setImageUrl(userDTO.getImageUrl());
            user.setActivated(userDTO.isActivated());
            user.setLangKey(userDTO.getLangKey());
            user.setCin(userDTO.getCin());
            user.setPhone(userDTO.getPhone());
            user.setGroupName(userDTO.getGroupName());
            user.setMembershipId(userDTO.getMembershipId());
            user.setAuthorities(this.authoritiesFromStrings(userDTO.getAuthorities()));
            user.setOrganization(this.organizationMapper.findById(userDTO.getOrganization().getId()));
            user.setLocalities((userDTO.getLocalities() != null)
                ? userDTO.getLocalities().stream().map(LocalityDTO::getId).map(this.localityMapper::findById).collect(Collectors.toSet())
                : Collections.emptySet()
            );
            user.setServiceTypes((userDTO.getServiceTypes() != null)
                ? userDTO.getServiceTypes().stream().map(ServiceTypeDTO::getId).map(this.serviceTypeMapper::findById).collect(Collectors.toSet())
                : Collections.emptySet()
            );
            return user;
        }
    }

    private Set<Authority> authoritiesFromStrings(Set<String> authoritiesAsString) {
        Set<Authority> authorities = new HashSet<>();

        if (authoritiesAsString != null) {
            authorities = authoritiesAsString.stream().map(string -> {
                Authority auth = new Authority();
                auth.setName(string);
                return auth;
            }).collect(Collectors.toSet());
        }

        return authorities;
    }

    public User userFromId(Long id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }
}
