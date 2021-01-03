package com.greenbox.test.demo;

import com.greenbox.test.demo.entity.User;
import com.greenbox.test.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

/*    @Bean
    public CommandLineRunner run(UserRepository userRepository) throws Exception {
        return (String[] args) -> {

            User user1 = new User();
            user1.setUsername("admin");
            user1.setPassword("$2a$10$9eOIaFtzFIHJ69WslgrjieXUyPQz6F6T6SEp5vR0ZdkUHhw0mFoHK");
            userRepository.save(user1);
            userRepository.findAll().forEach(user -> System.out.println(user));


        };
    }*/

}
