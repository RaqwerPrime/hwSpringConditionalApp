package ru.netology.hwconditionalapp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class HwConditionalAppApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    @Container
    private static final GenericContainer<?> devapp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);
    ;

    @Container
    private static final GenericContainer<?> prodapp = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        devapp.start();
        prodapp.start();
    }

    @Test
    void testDevApp() {
        Integer devAppPort = devapp.getMappedPort(8080);

        ResponseEntity<String> entityFromDevApp = restTemplate.getForEntity(
                "http://localhost:" + devAppPort + "/profile",
                String.class);
        System.out.println("DevApp response: " + entityFromDevApp.getBody());

        assertEquals("Current profile is dev", entityFromDevApp.getBody());

    }

    @Test
    void testProdApp() {
        Integer prodAppPort = prodapp.getMappedPort(8081);

        ResponseEntity<String> entityFromProdApp = restTemplate.getForEntity(
                "http://localhost:" + prodAppPort + "/profile",
                String.class);
        System.out.println("ProdApp response: " + entityFromProdApp.getBody());

        assertEquals("Current profile is production", entityFromProdApp.getBody());
    }

}
