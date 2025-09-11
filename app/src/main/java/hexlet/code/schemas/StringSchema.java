package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    public final StringSchema minLength(int minLengthSchema) {
        addCheck("MinLength", v -> (!isNullOrEmpty(v) && v.length() >=  minLengthSchema));
        return this;
    }

    public final StringSchema contains(String containsStringSchema) {
        addCheck("Contains", v -> (!isNullOrEmpty(containsStringSchema)
                && v.contains(containsStringSchema)));
        return this;
    }

    @Override
    public final StringSchema required() {
        super.required();
        addCheck("NullOrEmpty", v -> (!(isRequiredSchema && isNullOrEmpty(v))));
        return this;
    }

    private boolean isNullOrEmpty(String object) {
        return (object == null || object.isEmpty());
    }
}
