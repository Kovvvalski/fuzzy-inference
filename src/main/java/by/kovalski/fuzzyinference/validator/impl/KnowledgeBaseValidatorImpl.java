package by.kovalski.fuzzyinference.validator.impl;

import by.kovalski.fuzzyinference.validator.KnowledgeBaseValidator;

public class KnowledgeBaseValidatorImpl implements KnowledgeBaseValidator {


    @Override
    public boolean validateKb() {
        return true;
    }

    @Override
    public boolean validateFuzzySet(String fuzzySet) {
        return true;
    }

    @Override
    public boolean validateFuzzyImplication(String fuzzyImplication) {
        return true;
    }
}
