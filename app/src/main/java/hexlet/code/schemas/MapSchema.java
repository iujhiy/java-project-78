package hexlet.code.schemas;

import hexlet.code.BaseSchema;

import java.util.Map;

public class MapSchema<K, V> extends BaseSchema<Map<K, V>> {
    private Integer mapSizeField;

    public MapSchema<K, V> sizeof(int mapSize) {
        mapSizeField = mapSize;
        return this;
    }

    @Override
    protected boolean customValidate(Map<K, V> object) {
        if (mapSizeField != null && mapSizeField != object.size()) {
            return false;
        }
        return true;
    }
}
