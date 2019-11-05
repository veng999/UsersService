package ru.Illarionov.UsersService.controller.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.Date;


@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CreatedUser {
    @JsonProperty(value = "user_name")
    private String userName;
    private String email;
    @JsonProperty(value = "phone_number")
    private String phoneNumber;
    @JsonProperty(value = "last_online")
    private Date lastOnline;
    @JsonProperty(value = "bank_name")
    private String bankName;
    private String sex;
    @JsonProperty(value = "is_adult")
    private Boolean isAdult;
    @JsonProperty(value = "user_status")
    private String userStatus;

}
