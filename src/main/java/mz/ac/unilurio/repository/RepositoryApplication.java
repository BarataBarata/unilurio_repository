package mz.ac.unilurio.repository;

import mz.ac.unilurio.repository.repository.UserRepositoryCustomImpl;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RepositoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(RepositoryApplication.class, args);
    }

}
