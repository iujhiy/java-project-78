package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected boolean isRequiredSchema = false;
    protected final Map<String, Predicate<T>> checks = new LinkedHashMap<>();

    protected final void addCheck(String name, Predicate<T> validate) {
        checks.put(name, validate);
    }

    /**
     * Marks the schema as required. When called, the schema will validate
     * that the value is not null (or not empty for collections/strings).
     * Subclasses should override this method to add type-specific required validation
     * while preserving the fluent interface by returning the appropriate subtype.
     *
     * @return the current schema instance for method chaining
     */
    public BaseSchema<T> required() {
        isRequiredSchema = true;
        return this;
    }

    public final boolean isValid(T object) {
        if (isRequiredSchema && object == null) {
            return false;
        }
        if (object == null) {
            return true;
        }
        for (var schema: checks.values()) {
            if (!schema.test(object)) {
                return false;
            }
        }
        return true;
    }
}
