package at.spengergasse.haas.pos.planner;

import at.spengergasse.haas.pos.planner.persistence.PersistenceConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@Import(PersistenceConfiguration.class)
public class PersistenceTestConfiguration {

    @Bean
    public DataSource dataSource() {
        var dataSource = new DriverManagerDataSource(
                "jdbc:h2:mem:hif4b-database;DB_CLOSE_DELAY=-1",
                "sa",
                ""
        );
        dataSource.setDriverClassName("org.h2.Driver");
        return dataSource;
    }

}
