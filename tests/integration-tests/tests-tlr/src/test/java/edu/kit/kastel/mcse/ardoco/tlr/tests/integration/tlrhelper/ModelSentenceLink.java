/* Licensed under MIT 2022-2024. */
package edu.kit.kastel.mcse.ardoco.tlr.tests.integration.tlrhelper;

import java.util.Comparator;

import edu.kit.kastel.mcse.ardoco.core.api.models.tracelinks.SadSamTraceLink;

/**
 * Represents a simple trace link by the id of the model and number of the sentence involved.
 */
public record ModelSentenceLink(String modelId, int sentenceNr) implements Comparable<ModelSentenceLink> {

    public ModelSentenceLink(SadSamTraceLink traceLink) {
        this(traceLink.getModelElementUid(), traceLink.getSentenceNumber());
    }

    @Override
    public int compareTo(ModelSentenceLink o) {
        return Comparator.comparing(ModelSentenceLink::modelId).thenComparing(ModelSentenceLink::sentenceNr).compare(this, o);
    }

}
