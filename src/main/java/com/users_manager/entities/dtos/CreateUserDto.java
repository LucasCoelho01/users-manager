package com.users_manager.entities.dtos;

public record CreateUserDto(
        String login,
        String password
) {
}
