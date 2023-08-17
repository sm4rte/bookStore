package gpt.task.bookStore;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Log4j2
@SpringBootApplication
public class BookStoreApplication {

	public static void main(String[] args) {
		log.info("Trying to start bookStore...");
		SpringApplication.run(BookStoreApplication.class, args);
	}

}
