package hexlet.code;

public abstract class BaseSchema<T> {
    protected boolean isRequiredSchema = false;

    public BaseSchema<T> required() {
        isRequiredSchema = true;
        return this;
    }

    public boolean isValid(T object) {
        if (isRequiredSchema && object == null) {
            return false;
        }
        if (object == null) {
            return true;
        }
        return customValidate(object);
    }

    protected abstract boolean customValidate(T object);
}
