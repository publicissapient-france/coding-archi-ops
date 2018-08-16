package fr.xebia.ca.ops.ingestion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class IngestionApplication {

	@Value("${TARGET:NOT SPECIFIED}")
	String message;

	@RestController
	class ReferentielController {
		@PostMapping
		String add(@RequestBody Anomalie input) {

			String typeTrain = IngestionMethods.getTypeTrain(input);
			AnomalieCompletee anomalieCompletee = IngestionMethods.completerAnomalie(input, typeTrain);

			IngestionMethods.sendToPersistence(anomalieCompletee);

			return "OK";
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(IngestionApplication.class, args);
	}
}
