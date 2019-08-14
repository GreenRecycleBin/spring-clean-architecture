package io.github.greenrecyclebin.astrology;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
	@GetMapping
	public List<User> getUsers() {
		return List.of(new User.Builder("Daniel", "Le").birthday(LocalDate.of(1990, 9, 29)).build(),
				new User.Builder("Eugine", "Dang").birthday(LocalDate.of(1992, 12, 30)).build());
	}

	@PostMapping
	public ResponseEntity<Void> createUser(@RequestBody User user) {
		return ResponseEntity.created(URI.create("http://localhost:8080/users/" + UUID.randomUUID())).build();
	}
}

@org.springframework.web.bind.annotation.ControllerAdvice(annotations = RestController.class)
class ControllerAdvice {
	@ExceptionHandler
	public ResponseEntity<Set<ConstraintViolation<?>>> handle(ConstraintViolationException exception) {
		return ResponseEntity.badRequest().body(exception.getConstraintViolations());
	}
}
