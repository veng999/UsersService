package ru.Illarionov.UsersService.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users", schema = "public")
@Data
@NoArgsConstructor
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String userName;
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Europe/Moscow")
    @Column(name = "last_online", columnDefinition= "TIMESTAMP WITH TIME ZONE", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastOnline;
    @Column(name = "bank_name")
    private String bankName;
    private String sex;
    @Column(name = "is_adult")
    private Boolean isAdult;
    @NotNull
    @Column(name = "user_status")
    private String userStatus;
}
