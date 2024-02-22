package lv.course.testing.simple;

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
        assertEquals(5, service.doCalculations(10 , 2));
    }

    @Test
    void doCalculations_2() {
        assertEquals(-8, service.doCalculations(32 , -4));
    }

    @Test
    void doCalculations_3() {
        assertEquals(0.75, service.doCalculations(3 , 4), 0.001);
    }

    @Test
    void doCalculations_4() {
        assertEquals(0.333333, service.doCalculations(1 , 3));
    }

    @Test
    void doCalculations_5() {
        assertEquals(Double.MAX_VALUE, service.doCalculations(79 , 0));
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