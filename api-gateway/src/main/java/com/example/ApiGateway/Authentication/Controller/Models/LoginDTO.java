package com.example.ApiGateway.Authentication.Controller.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    String email;
    String password;
}
