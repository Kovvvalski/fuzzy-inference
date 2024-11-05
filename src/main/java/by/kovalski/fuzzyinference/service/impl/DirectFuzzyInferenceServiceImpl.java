package by.kovalski.fuzzyinference.service.impl;

import by.kovalski.fuzzyinference.entity.FuzzyImplication;
import by.kovalski.fuzzyinference.entity.FuzzySet;
import by.kovalski.fuzzyinference.entity.Pair;
import by.kovalski.fuzzyinference.service.DirectFuzzyInferenceService;

import java.util.*;
import java.util.function.BiFunction;

public class DirectFuzzyInferenceServiceImpl implements DirectFuzzyInferenceService {
    @Override
    public FuzzySet makeInference(FuzzySet set, FuzzyImplication implication, BiFunction<Double, Double, Double> tNorm, String resName) {
        Map<String, Double> maxValues = new HashMap<>();
        for (Map.Entry<String, Double> setElement : set.getElements().entrySet()) {
            for (Pair<String, Double> implElement : implication.getImplicationMatrix().get(setElement.getKey())) {
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

    @Override
    public List<FuzzySet> makeInferenceFromKB(List<FuzzySet> sets, List<FuzzyImplication> implications,
                                              BiFunction<Double, Double, Double> tNorm) {
        Queue<FuzzySet> setsQueue = new LinkedList<>(sets);
        List<FuzzySet> outputSets = new ArrayList<>(sets);
        int setCounter = 1;

        while (!setsQueue.isEmpty()) {
            FuzzySet currentSet = setsQueue.poll();

            for (FuzzyImplication implication : implications) {
                if (implication.getSet1().getElements().keySet().equals(currentSet.getElements().keySet())) {
                    FuzzySet inferredSet = makeInference(currentSet, implication, tNorm, String.valueOf(setCounter));

                    Optional<FuzzySet> duplicateSet = outputSets.stream()
                            .filter(s -> s.getElements().equals(inferredSet.getElements()))
                            .findFirst();

                    if (duplicateSet.isPresent()) {
                        logInference(currentSet, implication, inferredSet, duplicateSet.get());
                    } else {
                        logInference(currentSet, implication, inferredSet, null);
                        setsQueue.add(inferredSet);
                        outputSets.add(inferredSet);
                    }
                    setCounter++;
                }
            }
        }

        return outputSets;
    }

    private void logInference(FuzzySet currentSet, FuzzyImplication implication, FuzzySet inferredSet, FuzzySet duplicateSet) {
        String implicationStr = String.format("%s -> %s", implication.getSet1().getName(), implication.getSet2().getName());
        String resultStr = String.format("%s /~\\ (%s) = %s", currentSet.getName(), implicationStr, inferredSet);

        if (duplicateSet != null) {
            System.out.println(resultStr + " = " + duplicateSet.getName());
        } else {
            System.out.println(resultStr);
        }
    }
}
