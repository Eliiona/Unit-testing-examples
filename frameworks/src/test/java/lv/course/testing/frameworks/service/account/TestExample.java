package lv.course.testing.frameworks.service.account;

import org.junit.jupiter.api.*;

public class TestExample {

    @BeforeAll
    static void beforeAll() {
        // This is executed once, before anything
    }

    @BeforeEach
    void setUp() {
        // This is executed before each test method
    }

    @Test
    void testMethod() {
        // this is a test method
    }

    @AfterEach
    void tearDown() {
        // This is executed after each test method
    }

    @AfterAll
    static void afterAll() {
        // This is executed once, after all tests are done
    }
}
