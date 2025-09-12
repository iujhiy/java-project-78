package hexlet.code.schemas;

import java.util.Map;

public class MapSchema<K, V> extends BaseSchema<Map<K, V>> {

    public final MapSchema<K, V> shape(Map<K, BaseSchema<V>> schemas) {
        addCheck("Shape", v -> (!schemas.isEmpty()
                && isValidSchema(v, schemas)));
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

    private boolean isValidSchema(Map<K, V> v, Map<K, BaseSchema<V>> schemas) {
        for (K key: v.keySet()) {
            var value = v.get(key);
            BaseSchema<V> schema = schemas.get(key);
            if (!schema.isValid(value)) {
                return false;
            }
        }
        return true;
    }
}
