package hexlet.code.schemas;

import hexlet.code.BaseSchema;

import java.util.HashMap;
import java.util.Map;

public class MapSchema<K, V> extends BaseSchema<Map<K, V>> {
    private Integer mapSizeField;
    private final Map<K, BaseSchema<V>> schemasField = new HashMap<>();

    public MapSchema<K, V> shape(Map<K, BaseSchema<V>> schemas) {
        schemasField.putAll(schemas);
        return this;
    }

    public MapSchema<K, V> sizeof(int mapSize) {
        mapSizeField = mapSize;
        return this;
    }

    @Override
    public MapSchema<K, V> required() {
        super.required();
        return this;
    }

    @Override
    protected boolean customValidate(Map<K, V> object) {
        if (mapSizeField != null && mapSizeField != object.size()) {
            return false;
        }
        if (!schemasField.isEmpty()) {
            if (!isValidSchema(object)) {
                return false;
            }
        }
        return true;
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
