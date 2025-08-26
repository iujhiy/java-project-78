package hexlet.code;

import java.util.HashMap;
import java.util.Map;

public class NumberSchema {
    private boolean isRequiredSchema = false;
    private final Map<String, Integer> minMaxValuesField = new HashMap<>();
    private boolean isPositive = false;

    public void required() {
        isRequiredSchema = true;
    }

    public NumberSchema range(int minNumber, int maxNumber) {
        minMaxValuesField.put("minValue", minNumber);
        minMaxValuesField.put("maxValue", maxNumber);
        return this;
    }

    public NumberSchema positive() {
        isPositive = true;
        return this;
    }

    public boolean isValid(Integer object) {
        if (isRequiredSchema && object == null) {
            return false;
        }

        if (object != null) {

            if (!minMaxValuesField.isEmpty()) {
                var minNumber = minMaxValuesField.get("minValue");
                var maxNumber = minMaxValuesField.get("maxValue");
                if (object < minNumber || object > maxNumber) {
                    return false;
                }
            }

            if (isPositive && object <= 0) {
                return false;
            }
        }

        return true;
    }
}
