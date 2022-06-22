package com.epam.conference.entity.event;
import org.springframework.security.core.GrantedAuthority;
public enum RequestType implements GrantedAuthority {
    New_Speaker,New_Report;
    @Override
    public String getAuthority() {
        return name();
    }
}
