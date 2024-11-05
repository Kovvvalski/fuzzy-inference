package by.kovalski.fuzzyinference.entity;

import java.util.Map;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuzzySet fuzzySet = (FuzzySet) o;
        return Objects.equals(name, fuzzySet.name) && Objects.equals(elements, fuzzySet.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, elements);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("=");
        elements.forEach((key, value) -> sb.append("<").append(key).append(",").append(value).append(">").append(" "));
        return sb.toString();
    }
}
