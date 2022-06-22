package com.epam.conference.entity.dto;
import com.epam.conference.anotation.FieldsValueMatch;
import com.epam.conference.entity.user.Role;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
@Setter
@Getter
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
}
