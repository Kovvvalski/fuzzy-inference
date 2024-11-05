package by.kovalski.fuzzyinference.entity;

public class FuzzyImplication {
    final FuzzySet fuzzySet1;
    final FuzzySet fuzzySet2;
    final ImplicationMatrix implicationMatrix;

    public FuzzyImplication(FuzzySet fuzzySet1, FuzzySet fuzzySet2, ImplicationMatrix implicationMatrix) {
        this.fuzzySet1 = fuzzySet1;
        this.fuzzySet2 = fuzzySet2;
        this.implicationMatrix = implicationMatrix;
    }

    public FuzzySet getFuzzySet1() {
        return fuzzySet1;
    }

    public FuzzySet getFuzzySet2() {
        return fuzzySet2;
    }

    public ImplicationMatrix getImplicationMatrix() {
        return implicationMatrix;
    }
}
