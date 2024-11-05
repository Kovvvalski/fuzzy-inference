package by.kovalski.fuzzyinference.parser;

import by.kovalski.fuzzyinference.entity.FuzzyImplication;
import by.kovalski.fuzzyinference.entity.FuzzySet;

import java.util.List;

public interface KnowledgeBaseParser {

    List<FuzzySet> parseFuzzySet();

    List<FuzzyImplication> parseFuzzyImplication(List<FuzzySet> parsedSets);
}
