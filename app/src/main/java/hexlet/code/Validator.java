package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public class Validator {

    public Validator() {
    }

    public NumberSchema number() {
        return new NumberSchema();
    }

    public StringSchema string() {
        return new StringSchema();
    }

    public MapSchema<String, String> map() {
        return new MapSchema<>();
    }
}
