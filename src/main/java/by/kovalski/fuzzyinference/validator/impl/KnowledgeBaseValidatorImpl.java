package by.kovalski.fuzzyinference.validator.impl;

import by.kovalski.fuzzyinference.validator.KnowledgeBaseValidator;

public class KnowledgeBaseValidatorImpl implements KnowledgeBaseValidator {


    @Override
    public boolean validateKb() {
        return true;
    }

    @Override
    public boolean validateFuzzySet(String fuzzySet) {
        String FUZZY_SET_REGEX = "^[a-zA-Z][0-9]? = \\{(<[a-z][0-9]?, ?[0|1].[0-9]+>, ?)*<[a-z][0-9]?, ?[0|1].[0-9]+>\\}$";
        return fuzzySet.matches(FUZZY_SET_REGEX);
    }

    @Override
    public boolean validateFuzzyImplication(String fuzzyImplication) {
        String FUZZY_IMPLICATION_REGEX = "^[a-zA-Z][0-9]?\\([a-z]\\) ~> [a-zA-Z][0-9]?\\([a-z]\\)$";
        return fuzzyImplication.matches(FUZZY_IMPLICATION_REGEX);
    }
}
