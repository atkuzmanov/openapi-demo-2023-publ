package org.openapitools.api;

import com.atlassian.oai.validator.restassured.OpenApiValidationFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openapitools.OpenAPI2SpringBoot;
import org.openapitools.dao.PetRepository;
import org.openapitools.dockersupport.DockerComposeSupportRule;
import org.openapitools.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static io.restassured.RestAssured.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {OpenAPI2SpringBoot.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@Slf4j
public class OpenApiContractValidationWithDocker2Tests {

    @RegisterExtension
    public static DockerComposeSupportRule dockerComposeSupportRule = new DockerComposeSupportRule();

    @LocalServerPort
    private int localServerPort;

    public int getLocalServerPort() {
        return localServerPort;
    }

    public static final String SWAGGER_JSON_URL = "../openapi_openapitools-github_3.0.0.json";

    private final OpenApiValidationFilter validationFilter = new OpenApiValidationFilter(SWAGGER_JSON_URL);

    @Autowired
    private PetRepository petRepo;

    public int getAppPort() {
        return 8080;
    }

    @BeforeEach
    public void before() throws IOException {
        RestAssured.port = getLocalServerPort();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.baseURI = "http://localhost:" + getLocalServerPort();
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        setUpTestData();
    }

    @AfterEach
    public void after() {
//        cleanUpTestData();
    }

    private void setUpTestData() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String testDogPetJSONstr1 = "{\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"id\": 0,\n" +
                "  \"name\": \"Test Backend Doggie\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"status\": \"available\",\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"test dog pet 1\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Pet testDogPet1 = mapper.readValue(testDogPetJSONstr1, Pet.class);

        petRepo.save(testDogPet1);
    }

    private void cleanUpTestData() {
        petRepo.deleteById(0L);
    }

    @Test
    public void testPetEndpoint() {
        given()
                .filter(validationFilter)
                .port(getAppPort())
                .header("api_key", "special-key")
                .header("accept", "application/json")
                .when().get("/pet/0")
                .then()
                .statusCode(200);
    }
}
