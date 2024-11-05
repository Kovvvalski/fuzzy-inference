package by.kovalski.fuzzyinference.parser.impl;

import by.kovalski.fuzzyinference.entity.FuzzyImplication;
import by.kovalski.fuzzyinference.entity.FuzzySet;
import by.kovalski.fuzzyinference.parser.KnowledgeBaseParser;
import by.kovalski.fuzzyinference.validator.KnowledgeBaseValidator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KnowledgeBaseParserImpl implements KnowledgeBaseParser {

    private final String filePath;
    private final KnowledgeBaseValidator kbValidator;

    public KnowledgeBaseParserImpl(String filePath, KnowledgeBaseValidator kbValidator) {
        this.filePath = filePath;
        this.kbValidator = kbValidator;
    }

    @Override
    public List<FuzzySet> parseFuzzySet() {
        List<FuzzySet> resultList = new ArrayList<>();
        try (var in = this.fileOpen()){
            String fuzzySetStr;
            Map<String, Double> fuzzySetElements = new HashMap<>();
            StringBuilder sb = new StringBuilder();
            while ((fuzzySetStr = in.readLine()) != null) {
                if (kbValidator.validateFuzzySet(fuzzySetStr)) {
                    boolean readActive = false;
                    for (char letter : fuzzySetStr.toCharArray()) {
                        switch(letter) {
                            case '<':
                                readActive = true;
                                break;
                            case '>':
                                String[] strElements = sb.toString()
                                        .replaceAll(" ", "")
                                        .split(",");
                                fuzzySetElements.put(strElements[0], Double.valueOf(strElements[1]));
                                readActive = false;
                                sb.setLength(0);
                                break;
                            default:
                                if (readActive) {
                                    sb.append(letter);
                                }
                                break;
                        }
                    }
                    resultList.add(new FuzzySet(fuzzySetStr.substring(0, fuzzySetStr.indexOf(" ")), fuzzySetElements));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public List<FuzzyImplication> parseFuzzyImplication() {
        List<FuzzyImplication> resultList = new ArrayList<>();
        try (var in = this.fileOpen()) {
            String fuzzyImplicationStr;
            StringBuilder sb = new StringBuilder();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private BufferedReader fileOpen() throws FileNotFoundException {
        return new BufferedReader(new FileReader(this.filePath));
    }
}
