package de.juplo.boot.data.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import java.time.LocalDateTime;

@RestController
@Transactional
@RequestMapping("/users")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);


    private final UserRepository repository;


    public UserController(UserRepository repository) {
        this.repository = repository;
    }


    @PostMapping
    public ResponseEntity<Void> createUser(
            ServletUriComponentsBuilder builder,
            @RequestBody String username) {
        String sanitizedUsername = UserController.sanitize(username);
        User user = new User(sanitizedUsername, LocalDateTime.now(), false);
        repository.save(user);
        UriComponents uri =
            builder
                .fromCurrentRequest()
                .pathSegment("{username}")
                .buildAndExpand(sanitizedUsername);
        return ResponseEntity.created(uri.toUri()).build();
    }

    @GetMapping("{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        User user = repository.findByUsername(UserController.sanitize(username));

        if (user == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("{username}")
    public ResponseEntity<User> removeUser(@PathVariable String username) {
        User user = repository.findByUsername(UserController.sanitize(username));

        if (user == null)
            return ResponseEntity.notFound().build();

        repository.delete(user);

        return ResponseEntity.ok(user);
    }

    @GetMapping()
    public ResponseEntity<Iterable<User>> getUsers() {
        return ResponseEntity.ok(repository.findAll());
    }


    private static String sanitize(String string) {
        if (string == null)
            return "";

        return string.trim().toLowerCase();
    }
}
