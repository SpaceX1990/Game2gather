package configuration;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

@Configuration
@Profile("testdata")
@RequiredArgsConstructor
public class TestDataConfiguration {

    private final JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void generateTestData() {
        generateDatabaseData("db/testdata/clearTables.sql");
        generateDatabaseData("db/testData/gamesData.sql");
    }


    private void generateDatabaseData(String filename) {
        Resource resource = new ClassPathResource(filename);

        try {
            ScriptUtils.executeSqlScript(Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection(), new EncodedResource(resource, UTF_8));
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute SQL script: " + filename, e);
        }
    }

}

