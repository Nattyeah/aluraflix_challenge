package aluraflixbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"aluraflixbackend.*"})
public class BackendAluraChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendAluraChallengeApplication.class, args);
	}

}
