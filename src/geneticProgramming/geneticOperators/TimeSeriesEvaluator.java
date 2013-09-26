package geneticProgramming.geneticOperators;

import geneticProgramming.functions.Node;
import model.TimeNode;
import org.uncommons.watchmaker.framework.FitnessEvaluator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 29/07/13
 * Time: 20:57
 */
public class TimeSeriesEvaluator implements FitnessEvaluator<Node>
{

    public static final int SQUARED_ERROR                      = 0;
    public static final int MEAN_ABSOLUTE_ERROR                = 1;
    public static final int MEAN_ABSOLUTE_PERCENT_ERROR        = 2;
    public static final int MEAN_ABSOLUTE_DEVIATION            = 3;
    public static final int PERCENT_MEAN_ABSOLUTE_DEVIATION    = 4;
    public static final int MEAN_SQUARED_ERROR                 = 5;
    public static final int ROOT_MEAN_SQUARED_ERROR            = 6;
    public static final int FORECAST_SKILL                     = 7;
    public static final int AVERAGE_OF_ERROR                   = 8;

    private int fitnessType;
    private ArrayList<TimeNode> data;
    private int windowSize;
    private int numberOfTestCases;
    private boolean natural;

    /**
     * Class constructor.
     *
     * This constructor sets the list of data (a time series), the window size and the fitness type.
     *
     * @param data        Set of data.
     * @param windowSize  The size used in which case of learning.
     * @param fitnessType It's used to identify which type of fitness this object uses. It can be natural (the higher is
     *                    the fitness value higher is the quality of the individual) or not natural (the lower is
     *                    the fitness value higher is the quality of the individual).
     */
    public TimeSeriesEvaluator(ArrayList<TimeNode> data, int windowSize, int fitnessType, boolean natural)
    {
        this.fitnessType       = fitnessType;
        this.windowSize        = windowSize;
        this.data              = data;
        this.natural           = natural;
        // @todo verificar se este valor está correto (este corresponde ao "N", utilizado, por exmplo, na formula do mean absolute error).
        this.numberOfTestCases = this.data.size() - this.windowSize;
    }

    /**
     * public interface of this class. This method is used to return the individual 's fitness value.
     *
     * @param candidate  An individual in this population.
     * @param population A group of individuals.
     * @return The candidate's fitness value.
     */
    @Override
    public double getFitness(Node candidate, List<? extends Node> population)
    {
        double error = this.choseFitnessMethod(candidate);
        if (error != 0) {
            error += candidate.countNodes();
        }

        error = (Double.isNaN(error) || Double.isInfinite(error)) ? Double.MAX_VALUE : error;
        candidate.setFitnessValue(error);

        return error;
    }

    /**
     * Verify if the fitness measuring is made as natural fitness value (the higher is the fitness value, the best is
     * the candidate), or it is not natural (the lower is the fitness value, the best is the candidate).
     *
     * @return TRUE, if its fitness is natural, FALSE, otherwise.
     */
    @Override
    public boolean isNatural()
    {
        return this.natural;
    }

    /**
     * This method is used to choice the option that will be used as the fitness function method.
     *
     * @param candidate Candidate.
     * @return the value of fitness of this candidate.
     */
    private double choseFitnessMethod(Node candidate)
    {
        switch (this.fitnessType) {
            case TimeSeriesEvaluator.SQUARED_ERROR:
                return this.getSquaredErrorFitness(candidate);
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
            default: return Node.BAD_FITNESS_VALUE;
        }
    }

    /**
     *
     * @param candidate An individual of the population.
     * @return return its fitness value.
     */
    private double getSquaredErrorFitness(Node candidate)
    {
        double error = 0;

        for (int i = this.windowSize; i < this.data.size(); i++) {
            double[] params = new double[this.windowSize];
            int x = 0;
            for (int j = i - windowSize; j < i; j++) {
                params[x] = this.data.get(j).getValue();
                x++;
            }

            double actualValue = this.data.get(i).getValue();
            double forecast  = candidate.evaluate(params);
            forecast = (Double.isNaN(forecast) || Double.isInfinite(forecast)) ? Node.BAD_FITNESS_VALUE : forecast;
            double diff      = actualValue - forecast;

            error += Math.pow(diff, 2);
        }

        return error;
    }

    /**
     *
     * @param candidate An individual of the population.
     * @return return its fitness value.
     */
    private double getMeanAbsoluteErrorFitness(Node candidate)
    {
        double error = 0;

        for (int i = this.windowSize; i < this.data.size(); i++) {
            double[] params = new double[this.windowSize];
            int x = 0;
            for (int j = i - windowSize; j < i; j++) {
                params[x] = this.data.get(j).getValue();
                x++;
            }

            double actualValue = this.data.get(i).getValue();
            double forecast  = candidate.evaluate(params);
            double diff      = actualValue - forecast;

            error += Math.abs(diff);
        }

        return error/this.numberOfTestCases;
    }

    /**
     *
     * @param candidate An individual of the population.
     * @return return its fitness value.
     */
    private double getMeanAbsolutePercentErrorFitness(Node candidate)
    {
        double error = 0;

        for (int i = this.windowSize; i < this.data.size(); i++) {
            double[] params = new double[this.windowSize];
            int x = 0;
            for (int j = i - windowSize; j < i; j++) {
                params[x] = this.data.get(j).getValue();
                x++;
            }

            double actualValue = this.data.get(i).getValue();
            double forecast  = candidate.evaluate(params);
            double diff      = (actualValue - forecast) / actualValue;

            error += Math.abs(diff);
        }

        return error/this.numberOfTestCases;
    }

    /**
     * @todo Verificar se esta formaula está correta. Atualmente, esta e a "Mean Absolute Error" são idênticas.
     *
     * @param candidate An individual of the population.
     * @return return its fitness value.
     */
    private double getMeanAbsoluteDeviationFitness(Node candidate)
    {
        double error = 0;

        for (int i = this.windowSize; i < this.data.size(); i++) {
            double[] params = new double[this.windowSize];
            int x = 0;
            for (int j = i - windowSize; j < i; j++) {
                params[x] = this.data.get(j).getValue();
                x++;
            }

            double actualValue = this.data.get(i).getValue();
            double forecast  = candidate.evaluate(params);
            double diff      = actualValue - forecast;

            error += Math.abs(diff);
        }

        return error/this.numberOfTestCases;
    }

    /**
     *
     * @param candidate An individual of the population.
     * @return return its fitness value.
     */
    private double getPercentMeanAbsoluteDeviation(Node candidate)
    {
        double error               = 0;
        double sumOfExpectedValues = 0;

        for (int i = this.windowSize; i < this.data.size(); i++) {
            double[] params = new double[this.windowSize];
            int x = 0;
            for (int j = i - windowSize; j < i; j++) {
                params[x] = this.data.get(j).getValue();
                x++;
            }

            double actualValue = this.data.get(i).getValue();
            double forecast  = candidate.evaluate(params);
            double diff      = actualValue - forecast;

            error += Math.abs(diff);
            sumOfExpectedValues += Math.abs(this.data.get(i).getValue());
        }

        return error/sumOfExpectedValues;
    }

    /**
     *
     * @param candidate An individual of the population.
     * @return return its fitness value.
     */
    private double getMeanSquaredErrorFitness(Node candidate)
    {
        double error = 0;

        for (int i = this.windowSize; i < this.data.size(); i++) {
            double[] params = new double[this.windowSize];
            int x = 0;
            for (int j = i - windowSize; j < i; j++) {
                params[x] = this.data.get(j).getValue();
                x++;
            }

            double actualValue = this.data.get(i).getValue();
            double forecast  = candidate.evaluate(params);
            double diff      = actualValue - forecast;

            error += Math.pow(diff, 2);
        }

        return error/this.numberOfTestCases;
    }

    /**
     *
     * @param candidate An individual of the population.
     * @return return its fitness value.
     */
    private double getRootMeanSquaredErrorFitness(Node candidate)
    {
        double error = 0;

        for (int i = this.windowSize; i < this.data.size(); i++) {
            double[] params = new double[this.windowSize];
            int x = 0;
            for (int j = i - windowSize; j < i; j++) {
                params[x] = this.data.get(j).getValue();
                x++;
            }

            double actualValue = this.data.get(i).getValue();
            double forecast  = candidate.evaluate(params);
            double diff      = actualValue - forecast;

            error += Math.pow(diff, 2);
        }

        return Math.sqrt(error/this.numberOfTestCases);
    }

    /**
     * @todo verificar se esta formula esta correta.
     *
     * @param candidate An individual of the population.
     * @return return its fitness value.
     */
    private double getForecastSkillFitness(Node candidate)
    {
        double forecast = 0;
        double expected = 0;

        for (int i = this.windowSize; i < this.data.size(); i++) {
            double[] params = new double[this.windowSize];
            int x = 0;
            for (int j = i - windowSize; j < i; j++) {
                params[x] = this.data.get(j).getValue();
                x++;
            }

            forecast += Math.pow(candidate.evaluate(params), 2);
            expected += Math.pow(this.data.get(i).getValue(), 2);
        }

        if (1 - (forecast/expected) < 0) {
            try {
                throw new Exception("Invalido ae porra.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return 1 - (forecast/expected);
    }

    /**
     * @todo Verificar se a formula está correta. Esta está identica à formula de "Mean Squared Error."
     *
     * @param candidate An individual of the population.
     * @return return its fitness value.
     */
    private double getAverageOfErrorFitness(Node candidate)
    {
        double error = 0;

        for (int i = this.windowSize; i < this.data.size(); i++) {
            double[] params = new double[this.windowSize];
            int x = 0;
            for (int j = i - windowSize; j < i; j++) {
                params[x] = this.data.get(j).getValue();
                x++;
            }

            double actualValue = this.data.get(i).getValue();
            double forecast  = candidate.evaluate(params);
            double diff      = actualValue - forecast;

            error += Math.pow(diff, 2);
        }

        return error/this.numberOfTestCases;
    }

}
