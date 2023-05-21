package com.application.Entity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Data
@Document
public class User {

    @Id
    private int userId;

    private String userName;

    @NotNull
    @NotEmpty
    @NotBlank
    private String userAddress;

    private int userAge;

    @Indexed ( unique = true )
    private String userContactNumber;

}
