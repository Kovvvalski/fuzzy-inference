package by.kovalski.fuzzyinference;

import by.kovalski.fuzzyinference.entity.FuzzyImplication;
import by.kovalski.fuzzyinference.entity.FuzzySet;
import by.kovalski.fuzzyinference.parser.KnowledgeBaseParser;
import by.kovalski.fuzzyinference.parser.impl.KnowledgeBaseParserImpl;
import by.kovalski.fuzzyinference.service.DirectFuzzyInferenceService;
import by.kovalski.fuzzyinference.service.impl.DirectFuzzyInferenceServiceImpl;
import by.kovalski.fuzzyinference.util.Util;
import by.kovalski.fuzzyinference.validator.impl.KnowledgeBaseValidatorImpl;

import java.util.*;

public class Main {

    static DirectFuzzyInferenceService inferenceService = new DirectFuzzyInferenceServiceImpl();

    static String FILE_PATH = "src/main/resources/test.kb";

    static KnowledgeBaseParser knowledgeBaseParser = new KnowledgeBaseParserImpl(FILE_PATH, new KnowledgeBaseValidatorImpl());

    public static void main(String[] args) {
        List<FuzzySet> sets = knowledgeBaseParser.parseFuzzySet();
        List<FuzzyImplication> fuzzyImplications = knowledgeBaseParser.parseFuzzyImplication(sets);
        for (FuzzySet fuzzySet : sets) {
            System.out.println(fuzzySet);
        }
        for (FuzzyImplication fuzzyImplication : fuzzyImplications) {
            System.out.println(fuzzyImplication);
        }
        System.out.println("-------------------------------------------");
        inferenceService.makeInferenceFromKB(sets, fuzzyImplications, Util::lukasiewiczTNorm);
    }
}