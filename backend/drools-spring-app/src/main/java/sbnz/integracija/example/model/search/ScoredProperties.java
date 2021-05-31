package sbnz.integracija.example.model.search;

import java.util.List;

public class ScoredProperties {

    private List<ScoredProperty> scoredProperties;

    public ScoredProperties(List<ScoredProperty> scoredProperties) {
        this.scoredProperties = scoredProperties;
    }

    public List<ScoredProperty> getScoredProperties() {
        return scoredProperties;
    }

    public void setScoredProperties(List<ScoredProperty> scoredProperties) {
        this.scoredProperties = scoredProperties;
    }
}
