package hexlet.code.schemas;

import hexlet.code.BaseSchema;

import java.util.HashMap;
import java.util.Map;

public class NumberSchema extends BaseSchema<Integer> {
    private final Map<String, Integer> minMaxValuesField = new HashMap<>();
    private boolean isPositive = false;

    public NumberSchema range(int minNumber, int maxNumber) {
        minMaxValuesField.put("minValue", minNumber);
        minMaxValuesField.put("maxValue", maxNumber);
        return this;
    }

    public NumberSchema positive() {
        isPositive = true;
        return this;
    }

    @Override
    public NumberSchema required() {
        super.required();
        return this;
    }


    @Override
    protected void addCustomValidate(Integer object) {
//        if (object != null) {
//            if (!minMaxValuesField.isEmpty()) {
//                var minNumber = minMaxValuesField.get("minValue");
//                var maxNumber = minMaxValuesField.get("maxValue");
//                if (object < minNumber || object > maxNumber) {
//                    return false;
//                }
//            }
        var minNumber = minMaxValuesField.get("minValue");
        var maxNumber = minMaxValuesField.get("maxValue");
        checks.put("Range", v -> (
            v != null
            && !minMaxValuesField.isEmpty()
            && v > minNumber && v < maxNumber));
        checks.put("Positive", v -> (isPositive && v > 0));
        //}
    }
}
