package hexlet.code;

public class StringSchema {
    private boolean isRequiredSchema = false;
    private int minLengthSchemaField = 0;
    private String containsStringSchemaField;

    public void required() {
        isRequiredSchema = true;
    }

    public StringSchema minLength(int minLengthSchema) {
        if (minLengthSchema != minLengthSchemaField) {
            setMinLengthSchema(minLengthSchema);
        }
        return this;
    }

    public StringSchema contains(String containsStringSchema) {
        if (!(containsStringSchema.equals(containsStringSchemaField))) {
            setContainsStringSchemaField(containsStringSchema);
        }
        return this;
    }

    public boolean isValid(String object) {
        if (isRequiredSchema && isNullOrEmpty(object)) {
            return false;
        }

        if (!isNullOrEmpty(object) && object.length() <  minLengthSchemaField) {
            return false;
        }

        if (!isNullOrEmpty(containsStringSchemaField)
                && !(object.contains(containsStringSchemaField))) {
            return false;
        }

        return true;
    }

    private boolean isNullOrEmpty(String object) {
        return (object == null || object.isEmpty());
    }

    private void setMinLengthSchema(int minLengthSchema) {
        this.minLengthSchemaField = minLengthSchema;
    }

    private void setContainsStringSchemaField(String containsStringSchemaField) {
        this.containsStringSchemaField = containsStringSchemaField;
    }
}
