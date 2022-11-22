package mingeso.clockservice;

import mingeso.clockservice.services.UploadService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import javax.annotation.Resource;

@SpringBootApplication
@EnableEurekaClient
public class ClockServiceApplication implements CommandLineRunner {

	@Resource
	UploadService uploadService;

	public static void main(String[] args) {
		SpringApplication.run(ClockServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		uploadService.deleteAll();
		uploadService.init();
	}
}
