package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public class MapSchema<K, V> extends BaseSchema<Map<K, V>> {
    //private Integer mapSizeField;
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
        //mapSizeField = mapSize;
        return this;
    }

    @Override
    public final MapSchema<K, V> required() {
        super.required();
        return this;
    }

 //   protected void addCustomValidate(Map<K, V> object) {
//        if (mapSizeField != null && mapSizeField != object.size()) {
//        }
//        if (!schemasField.isEmpty()) {
//            if (!isValidSchema(object)) {
//            }
//        }
        //addCheck("SizeOf", v -> (mapSizeField != null && mapSizeField == object.size()));
        //addCheck("Shape", v -> (!schemasField.isEmpty()
         //   && isValidSchema(v)));
//    }

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
