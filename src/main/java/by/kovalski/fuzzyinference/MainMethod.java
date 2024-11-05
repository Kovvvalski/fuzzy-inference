package by.kovalski.fuzzyinference;

import by.kovalski.fuzzyinference.entity.ImplicationMatrix;
import by.kovalski.fuzzyinference.entity.FuzzySet;
import by.kovalski.fuzzyinference.service.DirectFuzzyInferenceService;
import by.kovalski.fuzzyinference.service.impl.DirectFuzzyInferenceServiceImpl;
import by.kovalski.fuzzyinference.util.Util;

import java.util.*;

public class MainMethod {

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

        Map<String, Double> set3 = new HashMap<>();
        set3.put("x1", 0.1);
        set3.put("x2", 1.0);
        set3.put("x3", 0.3);
        set3.put("x4", 0.0);


        FuzzySet fuzzySet1 = new FuzzySet("A", set1);
        FuzzySet fuzzySet2 = new FuzzySet("B", set2);
        FuzzySet fuzzySet3 = new FuzzySet("C", set3);


        ImplicationMatrix implicationMatrix = ImplicationMatrix.compute(Util::godelImplication,
                fuzzySet1, fuzzySet2);

        System.out.println(fuzzySet1);
        System.out.println(fuzzySet2);
        System.out.println(implicationMatrix);

        FuzzySet inference = inferenceService.makeInference(fuzzySet3, implicationMatrix, Util::minTNorm, "B'");
        System.out.println(inference);
    }
}