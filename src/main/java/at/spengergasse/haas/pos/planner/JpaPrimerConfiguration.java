package at.spengergasse.haas.pos.planner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@Configuration
@ComponentScan(basePackageClasses = JpaPrimerConfiguration.class)
public class JpaPrimerConfiguration {

    @Bean
    public EntityManager entityManager() {
        return Persistence.
                createEntityManagerFactory("hif4b").
                createEntityManager();
    }
}

