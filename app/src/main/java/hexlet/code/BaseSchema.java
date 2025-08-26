package hexlet.code;

public abstract class BaseSchema<T> {
    protected boolean isRequiredSchema = false;

    public void required() {
        isRequiredSchema = true;
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
