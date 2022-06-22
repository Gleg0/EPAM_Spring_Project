package com.epam.conference.entity.event;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
@Getter
public class EventDto {
    @NotEmpty
    String name;
    @NotEmpty
    String description;
    @NotNull
    @DateTimeFormat
    String date;
}
