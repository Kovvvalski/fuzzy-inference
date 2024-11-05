package by.kovalski.fuzzyinference.service;

import by.kovalski.fuzzyinference.entity.FuzzyImplication;
import by.kovalski.fuzzyinference.entity.FuzzySet;

import java.util.function.BiFunction;

public interface DirectFuzzyInferenceService {
    FuzzySet makeInference(FuzzySet set, FuzzyImplication implication, BiFunction<Double, Double, Double> tNorm, String resName);

//    default List<FuzzySet> inferenceFuzzySets (List<FuzzySet> sets, ) {
//
//    }
}
