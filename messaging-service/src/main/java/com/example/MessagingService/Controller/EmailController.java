package com.example.MessagingService.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class EmailController {

    @GetMapping(value = "/email", params = "id")
    public ResponseEntity<String> getMedia(@RequestParam(name = "id", required = false) Long id) {

        try {
            return new ResponseEntity<>("Email enviado", HttpStatus.OK);
        } catch (RuntimeException e) {

            e.printStackTrace();

            return new ResponseEntity<>("Falha ao enviar email", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
