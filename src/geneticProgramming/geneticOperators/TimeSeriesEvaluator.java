package geneticProgramming.geneticOperators;

import geneticProgramming.functions.Node;
import org.uncommons.watchmaker.framework.FitnessEvaluator;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 29/07/13
 * Time: 20:57
 */
public class TimeSeriesEvaluator implements FitnessEvaluator<Node>
{

    public static final int PLAIN_ERROR                        = 0;
    public static final int MEAN_ABSOLUTE_ERROR                = 1;
    public static final int MEAN_ABSOLUTE_PERCENTAGE_ERROR     = 2;
    public static final int MEAN_ABSOLUTE_DEVIATION            = 3;
    public static final int PERCENTAGE_MEAN_ABSOLUTE_DEVIATION = 4;
    public static final int MEAN_SQUARED_ERROR                 = 5;
    public static final int ROOT_MEAN_SQUARED_ERROR            = 6;
    public static final int FORECAST_SKILL                     = 7;
    public static final int AVERAGE_OF_ERROR                   = 8;

    private int fitnessType;
    private double[] data;
    private int windowSize;

    public TimeSeriesEvaluator(double[] data, int windowSize, int fitnessType)
    {
        this.fitnessType = fitnessType;
        this.windowSize  = windowSize;
        this.data        = data;
    }

    @Override
    public double getFitness(Node node, List<? extends Node> population)
    {
        double error = this.choseFitnessMethod(node);
        if (error != 0) {
            error += node.countNodes();
        }

        return error;
    }

    @Override
    public boolean isNatural()
    {
        return false;
    }

    private double choseFitnessMethod(Node candidate)
    {
        switch (this.fitnessType) {
            case TimeSeriesEvaluator.PLAIN_ERROR:
                return this.getPlainErrorFitness(candidate);
            case TimeSeriesEvaluator.MEAN_ABSOLUTE_ERROR:
                return this.getMeanAbsoluteErrorFitness();
            case TimeSeriesEvaluator.MEAN_ABSOLUTE_PERCENTAGE_ERROR:
                return this.getMeanAbsolutePercentageErrorFitness();
            case TimeSeriesEvaluator.MEAN_ABSOLUTE_DEVIATION:
                return this.getMeanAbsoluteDeviationFitness();
            case TimeSeriesEvaluator.PERCENTAGE_MEAN_ABSOLUTE_DEVIATION:
                return this.getMeanAbsoluteDeviationFitness();
            case TimeSeriesEvaluator.MEAN_SQUARED_ERROR:
                return this.getMeanSquaredErrorFitness();
            case TimeSeriesEvaluator.ROOT_MEAN_SQUARED_ERROR:
                return this.getRootMeanSquaredErrorFitness();
            case TimeSeriesEvaluator.FORECAST_SKILL:
                return this.getForecastSkillFitness();
            case TimeSeriesEvaluator.AVERAGE_OF_ERROR:
                return this.getAverageOfErrorFitness();
            default: return Double.MAX_VALUE;
        }
    }

    private double getPlainErrorFitness(Node candidate)
    {
        double error = 0;

        for (int i = this.windowSize; i <= this.data.length; i++) {
            double[] params = new double[this.windowSize];
            int x = 0;
            for (int j = i - windowSize; j < i; j++) {
                params[x] = this.data[j];
                x++;
            }

            double realValue = this.data[i];
            double forecast  = candidate.evaluate(params);
            double diff      = forecast - realValue;

            error += Math.pow(diff, 2);
        }

        return error;
    }

    private double getMeanAbsoluteErrorFitness()
    {
        return 0;
    }

    private double getMeanAbsolutePercentageErrorFitness()
    {
        return 0;
    }

    private double getMeanAbsoluteDeviationFitness()
    {
        return 0;
    }

    private double getMeanSquaredErrorFitness()
    {
        return 0;
    }

    private double getRootMeanSquaredErrorFitness()
    {
        return 0;
    }

    private double getForecastSkillFitness()
    {
        return 0;
    }

    private double getAverageOfErrorFitness()
    {
        return 0;
    }

}
