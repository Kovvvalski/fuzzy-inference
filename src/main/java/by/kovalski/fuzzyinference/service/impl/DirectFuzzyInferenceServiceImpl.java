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

    @Override
    public List<FuzzySet> makeInferenceFromKB(List<FuzzySet> sets, List<FuzzyImplication> implications,
                                              BiFunction<Double, Double, Double> tNorm) {
        Queue<FuzzySet> setsQueue = new LinkedList<>(sets);
        List<FuzzySet> out = new ArrayList<>();
        int setCounter = 0;
        while (!setsQueue.isEmpty()) {
            FuzzySet set = setsQueue.poll();
            for (FuzzyImplication implication : implications) {
                if (implication.getSet1().getElements().keySet().equals(set.getElements().keySet())) {
                    FuzzySet calculated = makeInference(set, implication, tNorm, "S" + setCounter);
                    if (out.stream().anyMatch(s -> s.getElements().equals(calculated.getElements()))) {
                        System.out.println(set.getName() + "/~\\" + "(" +
                                implication.getSet1().getName() + "~>" + implication.getSet2().getName() + ")" +
                                "=" + calculated + "=" + implication.getSet2().getName());
                    } else {
                        System.out.println(set.getName() + "/~\\" + "(" +
                                implication.getSet1().getName() + "~>" + implication.getSet2().getName() + ")" +
                                "=" + calculated);
                        setsQueue.add(calculated);
                        out.add(calculated);
                        setCounter++;
                    }
                }
            }
        }
        return out;
    }
}
