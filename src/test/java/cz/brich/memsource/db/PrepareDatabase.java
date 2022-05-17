package cz.brich.memsource.db;

import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class PrepareDatabase {

    @Bean
    @Primary
    public DataSource inMemoryDS() throws Exception {
        DataSource embeddedPostgresDS = EmbeddedPostgres.builder()
                .setConnectConfig("gssEncMode", "disable")
                .setConnectConfig("sslmode", "disable")
                .start().getPostgresDatabase();

        return embeddedPostgresDS;
    }
}
