package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {
    private MapSchema<String, String> schema;

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        Validator validator = new Validator();
        schema = validator.map();
        if (testInfo.getTags().contains("required")) {
            schema.required();
        }
    }

    @Test
    public void isValidTestShouldReturnTrueForNullBeforeRequired() {
        assertTrue(schema.isValid(null));
    }

    @Test
    @Tag("required")
    public void isValidTestShouldReturnFalseIfNumberIsNull() {
        assertFalse(schema.isValid(null));
    }

    @Test
    @Tag("required")
    public void isValidTestShouldReturnTrue() {
        assertTrue(schema.isValid(new HashMap<>()));
        assertTrue(schema.isValid(new TreeMap<>()));
    }

    @Test
    @Tag("required")
    public void sizeofTestShouldReturnTrue() {
        var schema1 = schema.sizeof(2)
                .isValid(new HashMap<>(Map.of("Some", "thing", "Any", "thing")));
        var schema2 = schema.sizeof(0).isValid(new HashMap<>());
        assertTrue(schema1);
        assertTrue(schema2);
    }

    @Test
    @Tag("required")
    public void sizeofTestShouldReturnFalse() {
        var schema1 = schema.sizeof(3)
                .isValid(new HashMap<>(Map.of("Some", "thing", "Any", "thing")));
        var schema2 = schema.sizeof(0)
                .isValid(new HashMap<>(Map.of("Some", "thing")));
        assertFalse(schema1);
        assertFalse(schema2);
    }
}
