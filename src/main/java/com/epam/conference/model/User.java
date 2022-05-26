package com.epam.conference.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
public class User {
    @Getter @Setter private String email;
    @Getter @Setter private String password;
    @Getter @Setter private String role;
}
