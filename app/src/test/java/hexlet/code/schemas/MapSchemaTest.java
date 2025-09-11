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
    Map<String, BaseSchema<String>> schemas;

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        var validator = new Validator();
        schema = validator.map();
        if (testInfo.getTags().contains("required")) {
            schema.required();
        }
        if (testInfo.getTags().contains("initializedMap")) {
            schemas = new HashMap<>();
            schemas.put("firstName", validator.string().required());
            schemas.put("lastName", validator.string().required().minLength(2));
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

    @Test
    @Tag("required")
    @Tag("initializedMap")
    public void shapeTestShouldReturnTrue() {
        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        var schema1 = schema.shape(schemas).isValid(human1);
        assertTrue(schema1);
    }

    @Test
    @Tag("required")
    @Tag("initializedMap")
    public void shapeNullTestShouldReturnFalse() {
        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);

        var schema1 = schema.shape(schemas).isValid(human2);
        assertFalse(schema1);
    }

    @Test
    @Tag("required")
    @Tag("initializedMap")
    public void shapeShortStringTestShouldReturnFalse() {
        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        var schema1 = schema.shape(schemas).isValid(human3);
        assertFalse(schema1);
    }

}
