package org.openapitools.deprecated.deprecatedcorsfixes;

import org.junit.jupiter.api.Disabled;
import org.openapitools.OpenAPI2SpringBoot;
//import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;

//@SpringBootTest(classes = {OpenAPI2SpringBoot.class})
//@Disabled
public class OpenApiContractValidationT {
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
//        RestAssured.baseURI = "http://localhost:" + getAppPort();
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
