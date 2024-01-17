package com.example.UserAPI.User.Controller.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CreateUserDTO {
    private String email;
    private String password;
    private String birthDate;
}
