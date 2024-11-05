package by.kovalski.fuzzyinference.entity;

import java.util.Map;

public class FuzzySet {
    final String name;
    final Map<String, Double> elements;

    public FuzzySet(String name, Map<String, Double> elements) {
        this.name = name;
        this.elements = elements;
    }

    public String getName() {
        return name;
    }

    public Map<String, Double> getElements() {
        return elements;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("=");
        elements.forEach((key, value) -> sb.append("<").append(key).append(",").append(value).append(">").append(" "));
        return sb.toString();
    }
}
