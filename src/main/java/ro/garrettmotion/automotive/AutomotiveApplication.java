package ro.garrettmotion.automotive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"ro.garrettmotion.automotive"})
public class AutomotiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomotiveApplication.class, args);
	}

}
