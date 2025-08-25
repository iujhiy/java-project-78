package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {
    private NumberSchema schema;

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        Validator validator = new Validator();
        schema = validator.number();
        if (testInfo.getTags().contains("required")) {
            schema.required();
        }
    }

    @Test
    public void isValidTestShouldReturnTrueForNullBeforeRequired() {
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(4));
    }

    @Test
    @Tag("required")
    public void isValidTestShouldReturnFalseIfNumberIsNull() {
        assertFalse(schema.isValid(null));
    }

    @Test
    @Tag("required")
    public void isValidTestShouldReturnTrue() {
        assertTrue(schema.isValid(3));
        assertTrue(schema.isValid(-10));
        assertTrue(schema.isValid(0));
    }

    @Test
    @Tag("required")
    public void positiveTestShouldReturnTrue() {
        var schema1 = schema.positive().isValid(1);
        var schema2 = schema.positive().isValid(12);
        assertTrue(schema1);
        assertTrue(schema2);
    }

    @Test
    @Tag("required")
    public void positiveTestShouldReturnFalse() {
        var schema1 = schema.positive().isValid(-5);
        var schema2 = schema.isValid(0);
        assertFalse(schema1);
        assertFalse(schema2);
    }

    @Test
    @Tag("required")
    public void rangeTestShouldReturnTrue() {
        var schema1 = schema.range(0, 10).isValid(5);
        assertTrue(schema1);
        var schema2 = schema.range(0, 10).isValid(0);
        assertTrue(schema2);
        var schema3 = schema.range(0, 10).isValid(10);
        assertTrue(schema3);
    }

    @Test
    @Tag("required")
    public void rangeTestShouldReturnFalse() {
        var schema1 = schema.range(0, 10).isValid(-1);
        assertFalse(schema1);
        var schema2 = schema.range(0, 10).isValid(11);
        assertFalse(schema2);
    }
}
