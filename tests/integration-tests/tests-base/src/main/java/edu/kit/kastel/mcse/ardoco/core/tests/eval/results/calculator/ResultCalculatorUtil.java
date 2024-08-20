/* Licensed under MIT 2023-2024. */
package edu.kit.kastel.mcse.ardoco.core.tests.eval.results.calculator;

import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.list.ImmutableList;

import edu.kit.kastel.mcse.ardoco.core.tests.eval.results.EvaluationResults;
import edu.kit.kastel.mcse.ardoco.metrics.ClassificationMetricsCalculator;
import edu.kit.kastel.mcse.ardoco.metrics.result.AggregationType;
import edu.kit.kastel.mcse.ardoco.metrics.result.SingleClassificationResult;

/**
 * This utility class provides methods to form the average of several {@link EvaluationResults}
 */
public final class ResultCalculatorUtil {

    private ResultCalculatorUtil() {
        throw new IllegalAccessError();
    }

    public static <T> EvaluationResults<T> calculateAverageResults(ImmutableList<EvaluationResults<T>> results) {
        var calculator = ClassificationMetricsCalculator.getInstance();
        var classifications = results.stream().map(EvaluationResults::classificationResult).toList();

        var averages = calculator.calculateAverages(classifications, null);
        var macroAverage = averages.stream().filter(it -> it.getType() == AggregationType.MACRO_AVERAGE).findFirst().orElseThrow();

        var macroAverageAsSingle = new SingleClassificationResult<T>(Sets.mutable.empty(), Sets.mutable.empty(), Sets.mutable.empty(), null, macroAverage
                .getPrecision(), macroAverage.getRecall(), macroAverage.getF1(), macroAverage.getAccuracy(), macroAverage.getSpecificity(), macroAverage
                        .getPhiCoefficient(), macroAverage.getPhiCoefficientMax(), macroAverage.getPhiOverPhiMax());

        return new EvaluationResults<>(macroAverageAsSingle);
    }

    public static <T> EvaluationResults<T> calculateWeightedAverageResults(ImmutableList<EvaluationResults<T>> results) {
        var calculator = ClassificationMetricsCalculator.getInstance();
        var classifications = results.stream().map(EvaluationResults::classificationResult).toList();

        var averages = calculator.calculateAverages(classifications, null);
        var macroAverage = averages.stream().filter(it -> it.getType() == AggregationType.WEIGHTED_AVERAGE).findFirst().orElseThrow();

        var weightedAverageAsSingle = new SingleClassificationResult<T>(Sets.mutable.empty(), Sets.mutable.empty(), Sets.mutable.empty(), null, macroAverage
                .getPrecision(), macroAverage.getRecall(), macroAverage.getF1(), macroAverage.getAccuracy(), macroAverage.getSpecificity(), macroAverage
                        .getPhiCoefficient(), macroAverage.getPhiCoefficientMax(), macroAverage.getPhiOverPhiMax());

        return new EvaluationResults<>(weightedAverageAsSingle);

    }
}
