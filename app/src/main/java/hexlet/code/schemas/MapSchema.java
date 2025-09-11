package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public class MapSchema<K, V> extends BaseSchema<Map<K, V>> {
    private final Map<K, BaseSchema<V>> schemasField = new HashMap<>();

    public final MapSchema<K, V> shape(Map<K, BaseSchema<V>> schemas) {
        schemasField.putAll(schemas);
        addCheck("Shape", v -> (!schemas.isEmpty()
                && isValidSchema(v)));
        return this;
    }

    public final MapSchema<K, V> sizeof(Integer mapSize) {
        addCheck("SizeOf", v ->
                (mapSize != null && mapSize == v.size()));
        return this;
    }

    @Override
    public final MapSchema<K, V> required() {
        super.required();
        return this;
    }

    private boolean isValidSchema(Map<K, V> object) {
        for (K key: object.keySet()) {
            var value = object.get(key);
            BaseSchema<V> schema = schemasField.get(key);
            if (!schema.isValid(value)) {
                return false;
            }
        }
        return true;
    }
}
