package lv.course.testing.frameworks.service.account;

import org.junit.jupiter.api.Test;

public class AssertJExamples {

    @Test
    void testCreateSquareWithJunit() {
        Square result = createDefaultSquare();

        org.junit.jupiter.api.Assertions.assertNotNull(result);
        org.junit.jupiter.api.Assertions.assertEquals(100, result.getSide());
        org.junit.jupiter.api.Assertions.assertEquals("green", result.getColour());
    }

    @Test
    void testCreateSquareWithAssertJ() {
        Square expected = new Square();
        expected.setColour("green");
        expected.setSide(100);

        Square result = createDefaultSquare();

        org.assertj.core.api.Assertions.assertThat(result)
                .isNotNull()
                .isEqualToComparingFieldByField(expected);
    }

    private Square createDefaultSquare() {
        Square square = new Square();
        square.setColour("red");
        square.setSide(10);
        return square;
    }

    private static class Square {

        private int side;
        private String colour;

        public int getSide() {
            return side;
        }

        public void setSide(int side) {
            this.side = side;
        }

        public String getColour() {
            return colour;
        }

        public void setColour(String colour) {
            this.colour = colour;
        }
    }
}
