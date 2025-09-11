package hexlet.code.schemas;

import hexlet.code.BaseSchema;

public class StringSchema extends BaseSchema<String> {
    //private int minLengthSchemaField = 0;
    //private String containsStringSchemaField;

    public StringSchema minLength(int minLengthSchema) {
        addCheck("MinLength", v -> (!isNullOrEmpty(v) && v.length() >=  minLengthSchema));
        return this;
    }

    public StringSchema contains(String containsStringSchema) {
        addCheck("Contains", v -> (!isNullOrEmpty(containsStringSchema)
                && v.contains(containsStringSchema)));
        return this;
    }

    @Override
    public StringSchema required() {
        super.required();
        addCheck("NullOrEmpty", v -> (!(isRequiredSchema && isNullOrEmpty(v))));
        return this;
    }

    private boolean isNullOrEmpty(String object) {
        return (object == null || object.isEmpty());
    }


//    protected void addCustomValidate(String object) {
//        if (isRequiredSchema && isNullOrEmpty(object)) {
//            return false;
//        }
//
//        if (!isNullOrEmpty(object) && object.length() <  minLengthSchemaField) {
//            return false;
//        }
//
//        if (!isNullOrEmpty(containsStringSchemaField)
//                && !(object.contains(containsStringSchemaField))) {
//            return false;
//        }
        //addCheck("NullOrEmpty", v -> (!(isRequiredSchema && isNullOrEmpty(v))));
        //addCheck("MinLength", v -> (isNullOrEmpty(v) && v.length() >=  minLengthSchemaField));
//        addCheck("Contains", v -> (!isNullOrEmpty(containsStringSchemaField)
//                && v.contains(containsStringSchemaField)));
//    }



//    private void setMinLengthSchema(int minLengthSchema) {
//        this.minLengthSchemaField = minLengthSchema;
//    }

//    private void setContainsStringSchemaField(String containsStringSchemaField) {
//        this.containsStringSchemaField = containsStringSchemaField;
//    }
}
