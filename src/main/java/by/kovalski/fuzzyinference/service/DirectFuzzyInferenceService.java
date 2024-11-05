package by.kovalski.fuzzyinference.service;

import by.kovalski.fuzzyinference.entity.ImplicationMatrix;
import by.kovalski.fuzzyinference.entity.FuzzySet;

import java.util.List;
import java.util.function.BiFunction;

public interface DirectFuzzyInferenceService {
    FuzzySet makeInference(FuzzySet set, ImplicationMatrix implication, BiFunction<Double, Double, Double> tNorm, String resName);

//    default List<FuzzySet> inferenceFuzzySets (List<FuzzySet> sets, ) {
//
//    }
}
