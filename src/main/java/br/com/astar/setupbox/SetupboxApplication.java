package br.com.astar.setupbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@SpringBootApplication
public class SetupboxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SetupboxApplication.class, args);
	}
	
	@Bean(name = "fileExecutor")
	public ThreadPoolTaskExecutor taskExecutor() {
		final ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(1);
		pool.setMaxPoolSize(3);
		pool.initialize();
		return pool;
	}

}

