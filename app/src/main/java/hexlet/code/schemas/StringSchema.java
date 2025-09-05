package hexlet.code.schemas;

import hexlet.code.BaseSchema;

public class StringSchema extends BaseSchema<String> {
    private int minLengthSchemaField = 0;
    private String containsStringSchemaField;

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

    @Override
    public StringSchema required() {
        super.required();
        return this;
    }


    @Override
    protected void addCustomValidate(String object) {
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
        addCheck("NullOrEmpty", v -> (!(isRequiredSchema && isNullOrEmpty(v))));
        addCheck("MinLength", v -> (isNullOrEmpty(v) && v.length() >=  minLengthSchemaField))
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
