package by.kovalski.fuzzyinference.parser;

import by.kovalski.fuzzyinference.entity.FuzzyImplication;
import by.kovalski.fuzzyinference.entity.FuzzySet;

import java.util.List;

public class KnowledgeBaseParserImpl implements KnowledgeBaseParser{

    private final String filePath;

    public KnowledgeBaseParserImpl(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<FuzzySet> parseFuzzySet() {
        return null;
    }

    @Override
    public List<FuzzyImplication> parseFuzzyImplication() {
        return null;
    }
}
