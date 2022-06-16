package com.epam.conference.entity.user;


import com.epam.conference.anotation.FieldsValueMatch;

import javax.validation.constraints.NotEmpty;


@FieldsValueMatch(field = "password",fieldMatch = "confirmPassword", message = "{password_error}")
public class UserDto {
    @NotEmpty
    private String username;
    @NotEmpty
    private String email;
    private Role role;
    @NotEmpty
    private String password;
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
