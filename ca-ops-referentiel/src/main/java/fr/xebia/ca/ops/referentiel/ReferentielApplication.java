package fr.xebia.ca.ops.referentiel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class ReferentielApplication {

	@Value("${TARGET:NOT SPECIFIED}")
	String message;

	@RestController
	class ReferentielController {
		@GetMapping("/trains")
		List<Train> listTrains() throws IOException {
			return ReferentielMethods.getTrains();
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(ReferentielApplication.class, args);
	}
}
