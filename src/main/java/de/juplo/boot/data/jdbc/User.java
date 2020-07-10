package de.juplo.boot.data.jdbc;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("users")
public class User {
    @Id
    Long id;
    @Getter
    @Setter
    String username;
    @Getter
    @Setter
    LocalDateTime created;
    @Getter
    @Setter
    boolean loggedIn;

    public User(String username, LocalDateTime created, boolean loggedIn) {
        this.username = username;
        this.created = created;
        this.loggedIn = loggedIn;
    }
}
