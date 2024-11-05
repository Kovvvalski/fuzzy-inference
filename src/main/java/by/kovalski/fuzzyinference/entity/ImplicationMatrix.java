package by.kovalski.fuzzyinference.entity;

import java.util.*;
import java.util.function.BiFunction;

public class ImplicationMatrix {

    final Map<String, List<Pair<String, Double>>> implicationMatrix;

    ImplicationMatrix(Map<String, List<Pair<String, Double>>> implicationMatrix) {
        this.implicationMatrix = implicationMatrix;
    }

    public Map<String, List<Pair<String, Double>>> getImplicationMatrix() {
        return implicationMatrix;
    }

    public static ImplicationMatrix compute(BiFunction<Double, Double, Double> implFunction,
                                            FuzzySet set1, FuzzySet set2) {

        ImplicationMatrix result = new ImplicationMatrix(new HashMap<>());
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
