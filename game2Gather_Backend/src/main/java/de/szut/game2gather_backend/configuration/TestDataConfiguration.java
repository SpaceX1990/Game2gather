package de.szut.game2gather_backend.configuration;

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

//declares this class as a configuration that is used when building the project
@Configuration
@Profile("testdata")

//automatically creates a constructor for each field declared as final as parameter
//so that they can be injected from the springboot / bean context
@RequiredArgsConstructor

public class TestDataConfiguration {

    private final JdbcTemplate jdbcTemplate;

    // After Construction Of Database, use testData files to insert testdata into database
    @PostConstruct
    private void generateTestData() {
        generateDatabaseData("db/testdata/clearTables.sql");
        generateDatabaseData("db/testdata/createTestData.sql");
        generateDatabaseData("db/testdata/voteData.sql");
    }

    //insert testData from testData-files into database
    private void generateDatabaseData(String filename) {
        Resource resource = new ClassPathResource(filename);

        //try to connect to database and execute sql in testData-files on success
        try {
            ScriptUtils.executeSqlScript(Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection(), new EncodedResource(resource, UTF_8));
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute SQL script: " + filename, e);
        }
    }

}

