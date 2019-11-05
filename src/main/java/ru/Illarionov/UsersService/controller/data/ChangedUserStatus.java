package ru.Illarionov.UsersService.controller.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ChangedUserStatus {
    @JsonProperty(value = "user_status")
    private String userStatus;
}
