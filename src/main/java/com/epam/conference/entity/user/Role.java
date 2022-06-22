package com.epam.conference.entity.user;
import org.springframework.security.core.GrantedAuthority;
public enum Role implements GrantedAuthority {
    USER, SPEAKER, MODERATOR;
    @Override
    public String getAuthority() {
        return name();
    }
}
