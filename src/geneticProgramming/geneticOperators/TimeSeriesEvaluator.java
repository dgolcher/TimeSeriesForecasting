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
    public static final int MEAN_ABSOLUTE_PERCENT_ERROR        = 2;
    public static final int MEAN_ABSOLUTE_DEVIATION            = 3;
    public static final int PERCENT_MEAN_ABSOLUTE_DEVIATION    = 4;
    public static final int MEAN_SQUARED_ERROR                 = 5;
    public static final int ROOT_MEAN_SQUARED_ERROR            = 6;
    public static final int FORECAST_SKILL                     = 7;
    public static final int AVERAGE_OF_ERROR                   = 8;

    private int fitnessType;
    private double[] data;
    private int windowSize;
    private int numberOfTestCases;

    public TimeSeriesEvaluator(double[] data, int windowSize, int fitnessType)
    {
        this.fitnessType       = fitnessType;
        this.windowSize        = windowSize;
        this.data              = data;
        // @todo verificar se este valor está correto (este corresponde ao "N", utilizado, por exmplo, na formula do mean absolute error).
        this.numberOfTestCases = this.data.length - this.windowSize;
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
                return this.getMeanAbsoluteErrorFitness(candidate);
            case TimeSeriesEvaluator.MEAN_ABSOLUTE_PERCENT_ERROR:
                return this.getMeanAbsolutePercentErrorFitness(candidate);
            case TimeSeriesEvaluator.MEAN_ABSOLUTE_DEVIATION:
                return this.getMeanAbsoluteDeviationFitness(candidate);
            case TimeSeriesEvaluator.PERCENT_MEAN_ABSOLUTE_DEVIATION:
                return this.getPercentMeanAbsoluteDeviation(candidate);
            case TimeSeriesEvaluator.MEAN_SQUARED_ERROR:
                return this.getMeanSquaredErrorFitness(candidate);
            case TimeSeriesEvaluator.ROOT_MEAN_SQUARED_ERROR:
                return this.getRootMeanSquaredErrorFitness(candidate);
            case TimeSeriesEvaluator.FORECAST_SKILL:
                return this.getForecastSkillFitness(candidate);
            case TimeSeriesEvaluator.AVERAGE_OF_ERROR:
                return this.getAverageOfErrorFitness(candidate);
            default: return Double.MAX_VALUE;
        }
    }

    private double getPlainErrorFitness(Node candidate)
    {
        double error = 0;

        for (int i = this.windowSize; i < this.data.length; i++) {
            double[] params = new double[this.windowSize];
            int x = 0;
            for (int j = i - windowSize; j < i; j++) {
                params[x] = this.data[j];
                x++;
            }

            double actualValue = this.data[i];
            double forecast  = candidate.evaluate(params);
            double diff      = actualValue - forecast;

            error += Math.pow(diff, 2);
        }

        return error;
    }

    private double getMeanAbsoluteErrorFitness(Node candidate)
    {
        double error = 0;

        for (int i = this.windowSize; i < this.data.length; i++) {
            double[] params = new double[this.windowSize];
            int x = 0;
            for (int j = i - windowSize; j < i; j++) {
                params[x] = this.data[j];
                x++;
            }

            double actualValue = this.data[i];
            double forecast  = candidate.evaluate(params);
            double diff      = actualValue - forecast;

            error += Math.abs(diff);
        }

        return error/this.numberOfTestCases;
    }

    private double getMeanAbsolutePercentErrorFitness(Node candidate)
    {
        double error = 0;

        for (int i = this.windowSize; i < this.data.length; i++) {
            double[] params = new double[this.windowSize];
            int x = 0;
            for (int j = i - windowSize; j < i; j++) {
                params[x] = this.data[j];
                x++;
            }

            double actualValue = this.data[i];
            double forecast  = candidate.evaluate(params);
            double diff      = (actualValue - forecast) / actualValue;

            error += Math.abs(diff);
        }

        return error/this.numberOfTestCases;
    }

    /**
     * @todo Verificar se esta formaula está correta. Atualmente, esta e a "Mean Absolute Error" são idênticas.
     *
     * @param candidate
     * @return
     */
    private double getMeanAbsoluteDeviationFitness(Node candidate)
    {
        double error = 0;

        for (int i = this.windowSize; i < this.data.length; i++) {
            double[] params = new double[this.windowSize];
            int x = 0;
            for (int j = i - windowSize; j < i; j++) {
                params[x] = this.data[j];
                x++;
            }

            double actualValue = this.data[i];
            double forecast  = candidate.evaluate(params);
            double diff      = actualValue - forecast;

            error += Math.abs(diff);
        }

        return error/this.numberOfTestCases;
    }

    private double getPercentMeanAbsoluteDeviation(Node candidate)
    {
        double error               = 0;
        double sumOfExpectedValues = 0;

        for (int i = this.windowSize; i < this.data.length; i++) {
            double[] params = new double[this.windowSize];
            int x = 0;
            for (int j = i - windowSize; j < i; j++) {
                params[x] = this.data[j];
                x++;
            }

            double actualValue = this.data[i];
            double forecast  = candidate.evaluate(params);
            double diff      = actualValue - forecast;

            error += Math.abs(diff);
            sumOfExpectedValues += Math.abs(this.data[i]);
        }

        return error/sumOfExpectedValues;
    }

    private double getMeanSquaredErrorFitness(Node candidate)
    {
        double error = 0;

        for (int i = this.windowSize; i < this.data.length; i++) {
            double[] params = new double[this.windowSize];
            int x = 0;
            for (int j = i - windowSize; j < i; j++) {
                params[x] = this.data[j];
                x++;
            }

            double actualValue = this.data[i];
            double forecast  = candidate.evaluate(params);
            double diff      = actualValue - forecast;

            error += Math.pow(diff, 2);
        }

        return error/this.numberOfTestCases;
    }

    private double getRootMeanSquaredErrorFitness(Node candidate)
    {
        double error = 0;

        for (int i = this.windowSize; i < this.data.length; i++) {
            double[] params = new double[this.windowSize];
            int x = 0;
            for (int j = i - windowSize; j < i; j++) {
                params[x] = this.data[j];
                x++;
            }

            double actualValue = this.data[i];
            double forecast  = candidate.evaluate(params);
            double diff      = actualValue - forecast;

            error += Math.pow(diff, 2);
        }

        return Math.sqrt(error/this.numberOfTestCases);
    }

    /**
     * @todo verificar se esta formula esta correta.
     *
     * @param candidate
     * @return
     */
    private double getForecastSkillFitness(Node candidate)
    {
        double error    = 0;
        double forecast = 0;
        double expected = 0;

        for (int i = this.windowSize; i < this.data.length; i++) {
            double[] params = new double[this.windowSize];
            int x = 0;
            for (int j = i - windowSize; j < i; j++) {
                params[x] = this.data[j];
                x++;
            }

            forecast += Math.pow(candidate.evaluate(params), 2);
            expected += Math.pow(this.data[i], 2);
        }

        return 1 - (forecast/expected);
    }

    /**
     * @todo Verificar se a formula está correta. Esta está identica à formula de "Mean Squared Error."
     *
     * @param candidate
     * @return
     */
    private double getAverageOfErrorFitness(Node candidate)
    {
        double error = 0;

        for (int i = this.windowSize; i < this.data.length; i++) {
            double[] params = new double[this.windowSize];
            int x = 0;
            for (int j = i - windowSize; j < i; j++) {
                params[x] = this.data[j];
                x++;
            }

            double actualValue = this.data[i];
            double forecast  = candidate.evaluate(params);
            double diff      = actualValue - forecast;

            error += Math.pow(diff, 2);
        }

        return error/this.numberOfTestCases;
    }

}
