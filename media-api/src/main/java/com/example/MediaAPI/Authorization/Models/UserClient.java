package com.example.MediaAPI.Authorization.Models;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("http://localhost:5252")
public interface UserClient {
    @RequestMapping(method = RequestMethod.GET, value = "/checkEmail/{email}")
    CheckEmailResponse checkEmail(@PathVariable("email") String email);
}
