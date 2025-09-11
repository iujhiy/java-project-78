package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {
    //private final Map<String, Integer> minMaxValuesField = new HashMap<>();
    //private boolean isPositive = false;

    public NumberSchema range(int minNumber, int maxNumber) {
        //minMaxValuesField.put("minValue", minNumber);
        //minMaxValuesField.put("maxValue", maxNumber);
        //var minNumber = minMaxValuesField.get("minValue");
        //var maxNumber = minMaxValuesField.get("maxValue");
        checks.put("Range", v -> (
                v != null && v >= minNumber && v <= maxNumber));
        return this;
    }

    public NumberSchema positive() {
        checks.put("Positive", v -> v > 0);
        return this;
    }

    @Override
    public NumberSchema required() {
        super.required();
        return this;
    }


//    protected void addCustomValidate(Integer object) {
//        if (object != null) {
//            if (!minMaxValuesField.isEmpty()) {
//                var minNumber = minMaxValuesField.get("minValue");
//                var maxNumber = minMaxValuesField.get("maxValue");
//                if (object < minNumber || object > maxNumber) {
//                    return false;
//                }
//            }
//        var minNumber = minMaxValuesField.get("minValue");
//        var maxNumber = minMaxValuesField.get("maxValue");
//        checks.put("Range", v -> (
//            v != null
//            && !minMaxValuesField.isEmpty()
//            && v > minNumber && v < maxNumber));
        //checks.put("Positive", v -> v > 0);
        //Проверки добавляются всегда, а не при определенных проверках
        //}
//    }
}
