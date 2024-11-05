package by.kovalski.fuzzyinference.entity;

import java.util.*;
import java.util.function.BiFunction;

public class FuzzyImplication {

    final FuzzySet set1;
    final FuzzySet set2;
    final Map<String, List<Pair<String, Double>>> implicationMatrix;

    FuzzyImplication(FuzzySet set1, FuzzySet set2, Map<String, List<Pair<String, Double>>> implicationMatrix) {
        this.set1 = set1;
        this.set2 = set2;
        this.implicationMatrix = implicationMatrix;
    }

    public Map<String, List<Pair<String, Double>>> getImplicationMatrix() {
        return implicationMatrix;
    }

    public static FuzzyImplication compute(BiFunction<Double, Double, Double> implFunction,
                                           FuzzySet set1, FuzzySet set2) {

        FuzzyImplication result = new FuzzyImplication(set1, set2, new HashMap<>());
        for (Map.Entry<String, Double> set1Element : set1.elements.entrySet()) {
            List<Pair<String, Double>> matrixRow = new ArrayList<>();

            for (Map.Entry<String, Double> set2Element : set2.elements.entrySet()) {
                matrixRow.add(new Pair<>(set2Element.getKey(), implFunction.apply(set1Element.getValue(),
                        set2Element.getValue())));
            }
            result.implicationMatrix.put(set1Element.getKey(), matrixRow);
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        implicationMatrix.forEach((key, value) -> {
            implicationMatrix.get(key).forEach(implValue -> stringBuilder.append(implValue.getValue()).append(" "));
            stringBuilder.append("\n");
        });

        return stringBuilder.toString();
    }
}
