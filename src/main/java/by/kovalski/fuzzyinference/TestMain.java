package by.kovalski.fuzzyinference;

import by.kovalski.fuzzyinference.entity.FuzzyImplication;
import by.kovalski.fuzzyinference.entity.FuzzySet;
import by.kovalski.fuzzyinference.service.DirectFuzzyInferenceService;
import by.kovalski.fuzzyinference.service.impl.DirectFuzzyInferenceServiceImpl;
import by.kovalski.fuzzyinference.util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMain {
    static DirectFuzzyInferenceService inferenceService = new DirectFuzzyInferenceServiceImpl();

    public static void main(String[] args) {
        Map<String, Double> set1 = new HashMap<>();
        set1.put("x1", 0.0);
        set1.put("x2", 0.1);
        set1.put("x3", 0.3);
        set1.put("x4", 1.0);

        Map<String, Double> set2 = new HashMap<>();
        set2.put("y1", 1.0);
        set2.put("y2", 0.8);
        set2.put("y3", 0.2);
        set2.put("y4", 0.0);
        set2.put("y5", 0.0);

        Map<String, Double> set3 = new HashMap<>();
        set3.put("x1", 0.1);
        set3.put("x2", 1.0);
        set3.put("x3", 0.3);
        set3.put("x4", 0.0);

        FuzzySet fuzzySet1 = new FuzzySet("A", set1);
        FuzzySet fuzzySet2 = new FuzzySet("B", set2);
        FuzzySet fuzzySet3 = new FuzzySet("C", set3);

        FuzzyImplication fuzzyImplication1 = FuzzyImplication.compute(Util::godelImplication,
                fuzzySet1, fuzzySet2);

        FuzzyImplication fuzzyImplication2 = FuzzyImplication.compute(Util::godelImplication,
                fuzzySet3, fuzzySet1);

        System.out.println(fuzzySet1);
        System.out.println(fuzzySet2);
        System.out.println(fuzzySet3);
        System.out.println(fuzzyImplication1);
        System.out.println(fuzzyImplication2);

        inferenceService.makeInferenceFromKB(List.of(fuzzySet1, fuzzySet2, fuzzySet3),
                List.of(fuzzyImplication1, fuzzyImplication2),
                Util::minTNorm);

    }
}