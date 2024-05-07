package org.example;

import java.util.Comparator;

public class SortElements implements Comparator<Element> {
    @Override
    public int compare(Element e1, Element e2) {
        int comparisonValue = e2.getFrequency() - e1.getFrequency();
        if (comparisonValue == 0) {
            return e1.getWord().compareTo(e2.getWord());
        }
        return comparisonValue;
    }
}
