package by.kovalski.fuzzyinference.validator;

public interface KnowledgeBaseValidator {

    boolean validateKb();

    boolean validateFuzzySet(String fuzzySet);

    boolean validateFuzzyImplication(String fuzzyImplication);
}
