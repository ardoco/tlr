/* Licensed under MIT 2022-2024. */
package edu.kit.kastel.mcse.ardoco.tlr.tests.integration.tlrhelper.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;

import edu.kit.kastel.mcse.ardoco.core.tests.eval.GoldStandardProject;
import edu.kit.kastel.mcse.ardoco.tlr.tests.integration.tlrhelper.ModelElementSentenceLink;

public class TLGoldStandardFile {

    private TLGoldStandardFile() {
        // no instantiation
        throw new IllegalAccessError("No instantiation allowed");
    }

    public static MutableList<ModelElementSentenceLink> loadLinks(GoldStandardProject goldStandardProject) throws IOException {
        Path path = goldStandardProject.getTlrGoldStandardFile().toPath();
        List<String> lines = Files.readAllLines(path);

        return Lists.mutable.ofAll(lines.stream()
                .skip(1) // skip csv header
                .map(line -> line.split(",")) // modelElementId,sentenceNr
                .map(array -> new ModelElementSentenceLink(array[0], Integer.parseInt(array[1])))
                .map(link -> new ModelElementSentenceLink(link.modelElementId(), link.sentenceNr() - 1))
                // ^ goldstandard sentences start with 1 while ISentences are zero indexed
                .toList());
    }

}
