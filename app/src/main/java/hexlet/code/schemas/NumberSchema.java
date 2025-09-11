package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {

    public final NumberSchema range(int minNumber, int maxNumber) {
        checks.put("Range", v -> (
                v != null && v >= minNumber && v <= maxNumber));
        return this;
    }

    public final NumberSchema positive() {
        checks.put("Positive", v -> v > 0);
        return this;
    }

    @Override
    public final NumberSchema required() {
        super.required();
        return this;
    }
}
