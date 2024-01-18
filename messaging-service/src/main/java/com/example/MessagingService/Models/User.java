package com.example.MessagingService.Models;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private UUID id;
    private Boolean enabled;
    private Boolean deleted;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return "{" +
                "  id: " + this.id + "\n" +
                "  enabled: " + this.enabled + "\n" +
                "  deleted: " + this.deleted + "\n" +
                "  email: " + this.email + "\n" +
                "  createdAt: " + this.createdAt.toString() + "\n" +
                "  updatedAt: " + this.updatedAt.toString() + "\n" +
                "}";
    }
}
