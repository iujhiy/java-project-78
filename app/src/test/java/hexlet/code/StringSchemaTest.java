package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringSchemaTest {
    private StringSchema schema;

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        Validator validator = new Validator();
        schema = validator.string();
        if (testInfo.getTags().contains("required")) {
            schema.required();
        }
    }

    @Test
    public void isValidTestShouldReturnTrueForAllStringBeforeRequired() {
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
    }

    @Test
    @Tag("required")
    public void isValidTestShouldReturnFalseIfStringIsEmpty() {
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
    }

    @Test
    @Tag("required")
    public void isValidTestShouldReturnTrue() {
        assertTrue(schema.isValid("Something valid"));
        assertTrue(schema.isValid("Pineapple"));
    }

    @Test
    @Tag("required")
    public void containsTestShouldReturnTrue() {
        var schema1 = schema.contains("wh").isValid("what does the fox say");
        var schema2 = schema.contains("what").isValid("what does the fox say");
        assertTrue(schema1);
        assertTrue(schema2);
    }

    @Test
    @Tag("required")
    public void containsTestShouldReturnFalse() {
        var schema1 = schema.contains("whatthe").isValid("what does the fox say");
        var schema2 = schema.isValid("what does the fox say");
        assertFalse(schema1);
        assertFalse(schema2);
    }

    @Test
    @Tag("required")
    public void minLengthTestShouldReturnTrue() {
        var schema1 = schema.minLength(10).minLength(4).isValid("Hexlet");
        assertTrue(schema1);
    }

    @Test
    @Tag("required")
    public void minLengthTestShouldReturnFalse() {
        var schema1 = schema.minLength(10).isValid("Hexlet");
        assertFalse(schema1);
    }
}
