package org.openapitools.deprecated.deprecatedcorsfixes;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openapitools.OpenAPI2SpringBoot;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;

//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = {OpenAPI2SpringBoot.class})
//@DirtiesContext
//@Disabled
public class OpenApiContractValidationTWithDockerT {
//
//    @RegisterExtension
//    static final DockerComposeExtension docker = DockerComposeExtension.builder()
//            .pullOnStartup(true)
//            .file("src/test/resources/docker/docker-compose-test.yml")
//            .waitingForService("mongodb", HealthChecks.toHaveAllPortsOpen())
//            .build();
//
//    @BeforeAll
//    static void updateEnv() {
//        System.setProperty("spring.data.mongodb.uri", docker.containers().container("mongodb").port(27017).inFormat("mongodb://$HOST:$EXTERNAL_PORT"));
//    }
//
//    public static final String SWAGGER_JSON_URL = "../openapi_openapitools-github_3.0.0.json";
//
//    private final OpenApiValidationFilter validationFilter = new OpenApiValidationFilter(SWAGGER_JSON_URL);
//
//    @Autowired
//    private PetRepository petRepo;
//
//    public int getAppPort() {
//        return 8080;
//    }
//
//    @BeforeEach
//    public void before() throws IOException {
//        RestAssured.port = getAppPort();
//        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
////        RestAssured.baseURI = InetAddress.getLocalHost().getHostName() + getAppPort();
////        RestAssured.baseURI = "http://localhost:" + getAppPort();
////        RestAssured.baseURI = "http://127.0.0.1:" + getAppPort();
////        RestAssured.basePath = "/";
//        RestAssured.defaultParser = Parser.JSON;
//
//        setUpTestData();
//    }
//
//    @AfterEach
//    public void after() {
////        cleanUpTestData();
//    }
//
//    private void setUpTestData() throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        String testDogPetJSONstr1 = "{\n" +
//                "  \"category\": {\n" +
//                "    \"id\": 0,\n" +
//                "    \"name\": \"string\"\n" +
//                "  },\n" +
//                "  \"id\": 0,\n" +
//                "  \"name\": \"Test Backend Doggie\",\n" +
//                "  \"photoUrls\": [\n" +
//                "    \"string\"\n" +
//                "  ],\n" +
//                "  \"status\": \"available\",\n" +
//                "  \"tags\": [\n" +
//                "    {\n" +
//                "      \"id\": 0,\n" +
//                "      \"name\": \"test dog pet 1\"\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}";
//
//        Pet testDogPet1 = mapper.readValue(testDogPetJSONstr1, Pet.class);
//
//        // Debug code:
//        //        System.out.println(">>>" + testDogPet1);
//        //        System.out.println(">>> inserted: " + petRepo.save(testDogPet1));
//
//        petRepo.save(testDogPet1);
//    }
//
//    private void cleanUpTestData() {
//        petRepo.deleteById(0L);
//    }
//
//    @Test
//    public void testPetEndpoint() {
//        given()
//                .filter(validationFilter)
//                .port(getAppPort())
//                .header("api_key", "special-key")
//                .header("accept", "application/json")
//                .when().get("/pet/0")
//                .then()
//                .statusCode(200);
//    }
}
