package by.kovalski.fuzzyinference.service.impl;

import by.kovalski.fuzzyinference.entity.ImplicationMatrix;
import by.kovalski.fuzzyinference.entity.FuzzySet;
import by.kovalski.fuzzyinference.entity.Pair;
import by.kovalski.fuzzyinference.service.DirectFuzzyInferenceService;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class DirectFuzzyInferenceServiceImpl implements DirectFuzzyInferenceService {
    @Override
    public FuzzySet makeInference(FuzzySet set, ImplicationMatrix implication, BiFunction<Double, Double, Double> tNorm, String resName) {
        Map<String, Double> maxValues = new HashMap<>();
        for (Map.Entry<String, Double> setElement : set.getElements().entrySet()) {
            for (Pair<String, Double> implElement :
                    implication.getImplicationMatrix().get(setElement.getKey())) {

                Double newVarValue = tNorm.apply(setElement.getValue(), implElement.getValue());
                String varName = implElement.getKey();
                maxValues.put(varName,
                        maxValues.containsKey(varName) ?
                                Math.max(maxValues.get(varName), newVarValue) :
                                newVarValue
                );
            }
        }
        return new FuzzySet(resName, maxValues);
    }
}
