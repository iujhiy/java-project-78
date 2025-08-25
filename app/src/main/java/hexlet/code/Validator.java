package hexlet.code;

public class Validator {

    public Validator() {
    }

    public NumberSchema number() {
        return new NumberSchema();
    }

    public StringSchema string() {
        return new StringSchema();
    }
}
