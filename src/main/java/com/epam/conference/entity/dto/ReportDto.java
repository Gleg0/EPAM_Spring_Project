package com.epam.conference.entity.dto;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
@Getter
@Setter
public class ReportDto {
    private Long speakerId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
}
