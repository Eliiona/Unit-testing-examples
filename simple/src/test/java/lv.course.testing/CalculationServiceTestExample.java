package lv.course.testing;

import lv.course.testing.simple.CalculationService;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CalculationServiceTestExample {
    
    private final CalculationService service = new CalculationService();

    @BeforeAll
    static void beforeAll() {
        // Executed once before all tests
    }

    @BeforeEach
    void setUp() {
        // Executed once before each test
    }

    @Test
    void doCalculations_1() {
        assertEquals(0.75, service.doCalculations(3 , 4));
    }

    @AfterEach
    void tearDown() {
        // Executed once after each test
    }

    @AfterAll
    static void afterAll() {
        // Executed once after all tests
    }
}