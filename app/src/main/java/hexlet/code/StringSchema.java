package hexlet.code;

public class StringSchema {
    private boolean isRequiredSchema = false;
    private int minLengthSchema = 0;
    private String containsStringSchema;

    public StringSchema required() {
        setRequiredSchema();
        return this;
    }

    public StringSchema minLength(int minLengthSchema) {
        if (minLengthSchema != this.minLengthSchema) {
            setMinLengthSchema(minLengthSchema);
        }
        return this;
    }

    public StringSchema contains(String containsStringSchema) {
        if (!(containsStringSchema.equals(this.containsStringSchema))) {
            setContainsStringSchema(containsStringSchema);
        }
        return this;
    }

    public boolean isValid(String object) {
        if (isRequiredSchema && isNullOrEmpty(object)) {
                return false;
        }

        if (object != null && object.length() <  minLengthSchema) {
            return false;
        }

        if (containsStringSchema != null
                && !(object.contains(containsStringSchema))) {
            return false;
        }

        return true;
    }

    private boolean isNullOrEmpty(String object) {
        return (object == null || object.isEmpty());
    }

    private void setMinLengthSchema(int minLengthSchema) {
        this.minLengthSchema = minLengthSchema;
    }

    private void setContainsStringSchema(String containsStringSchema) {
        this.containsStringSchema = containsStringSchema;
    }

    private void setRequiredSchema() {
        isRequiredSchema = true;
    }
}