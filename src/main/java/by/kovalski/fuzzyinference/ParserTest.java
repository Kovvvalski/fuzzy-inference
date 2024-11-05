package by.kovalski.fuzzyinference;

import by.kovalski.fuzzyinference.entity.FuzzyImplication;
import by.kovalski.fuzzyinference.parser.KnowledgeBaseParser;
import by.kovalski.fuzzyinference.parser.impl.KnowledgeBaseParserImpl;
import by.kovalski.fuzzyinference.util.Util;
import by.kovalski.fuzzyinference.validator.impl.KnowledgeBaseValidatorImpl;

import java.util.List;

public class ParserTest {

    public static void main(String[] args) {
        KnowledgeBaseParser testParser = new KnowledgeBaseParserImpl("src/main/resources/test.kb",
                new KnowledgeBaseValidatorImpl());
        var parsedSets = testParser.parseFuzzySet();
        System.out.println(parsedSets);
        List<FuzzyImplication> parsedImplList = testParser.parseFuzzyImplication(parsedSets);
        parsedImplList.forEach(System.out::println);
    }
}
