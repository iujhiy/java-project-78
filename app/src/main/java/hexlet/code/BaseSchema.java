package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected boolean isRequiredSchema = false;
    protected final Map<String, Predicate<T>> checks= new LinkedHashMap<>();

    protected final void addCheck(String name, Predicate<T> validate) {
        checks.put(name, validate);
    }

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
        addCustomValidate(object);
        for (var schema: checks.values()) {
            if (!schema.test(object)) {
                return false;
            }
        }
        return true;
    }

    protected abstract void addCustomValidate(T object);
}
