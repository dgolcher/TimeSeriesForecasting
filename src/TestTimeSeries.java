import geneticProgramming.GPConfiguration;
import geneticProgramming.functions.Node;
import geneticProgramming.functions.function.Simplification;
import geneticProgramming.geneticOperators.*;
import model.TimeNode;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.*;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.termination.GenerationCount;
import org.uncommons.watchmaker.framework.termination.Stagnation;
import org.uncommons.watchmaker.framework.termination.TargetFitness;
import postProcessors.Forecast;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 29/07/13
 * Time: 21:13
 */
public class TestTimeSeries
{


    public static void main(String[] args)
    {
        GPConfiguration configuration      = getConfigurations();

        ArrayList<TimeNode> data           = TestTimeSeries.getData(configuration);
        Node program  = TestTimeSeries.evolveProgram(data, configuration);
        System.out.println("Best solution found");
        System.out.println(program.print());

        Forecast forecast = new Forecast (
            data, program, configuration.getForecastHorizon(), configuration.getWindowSize()
        );
        ArrayList<TimeNode> timeSeriesForecasted = forecast.forecastForNPeriods();
        for (TimeNode aTimeSeriesForecasted : timeSeriesForecasted) {
            System.out.print(aTimeSeriesForecasted.getValue() + ", ");
        }
    }

    private static GPConfiguration getConfigurations()
    {
        try {
            String filePath                  = "data/config/gp_configuration.arff";

            BufferedReader reader            = new BufferedReader(new java.io.FileReader(filePath));
            ArffLoader.ArffReader arffReader = new ArffLoader.ArffReader(reader);
            Instances instances              = arffReader.getData();
            instances.setClassIndex(instances.numAttributes() - 1);

            return new GPConfiguration(instances.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    private static ArrayList<TimeNode> getData(GPConfiguration configuration)
    {
        ArrayList<TimeNode> series = new ArrayList<TimeNode>();
        int initial          = Math.abs(new Random(13).nextInt(100));
        int increase         = Math.abs(new Random(13).nextInt(100));
        TimeNode initialNode = new TimeNode();
        initialNode.setDate(null);
        initialNode.setValue(initial);
        series.add(initialNode);

        for (int i = 1; i < configuration.getTimeSeriesSize(); i++) {
            TimeNode node = new TimeNode();
            node.setDate(null);
            node.setValue(series.get(i-1).getValue() + increase);
            series.add(node);
            System.out.print(node.getValue() + ", ");
        }

        System.out.println();
        return series;
    }

    private static Node evolveProgram(ArrayList<TimeNode> data, GPConfiguration configuration)
    {
        Node node = null;
        TerminationCondition[] terminationConditions = getTerminationConditions(configuration);
        EvolutionEngine<Node> engine                 = getEvolutionEngine(data, configuration);
        try {
            addEvolutionObservers(engine, configuration);
            node = engine.evolve(
                configuration.getPopulationSize(), configuration.getElitismCount(), terminationConditions
            );
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Termination conditions satisfied: ");
            List<TerminationCondition> a = engine.getSatisfiedTerminationConditions();
            for (TerminationCondition condition : a) {
                System.out.println(condition.toString());
            }
        }

        return node;
    }

    private static EvolutionEngine<Node> getEvolutionEngine(ArrayList<TimeNode> data, GPConfiguration configuration)
    {
        TreeFactory                factory               = TestTimeSeries.getTreeFactory(configuration);
        EvolutionaryOperator<Node> evolutionaryOperators = TestTimeSeries.getEvolutionaryOperators(factory, configuration);
        FitnessEvaluator<Node>     fitnessEvaluator      = TestTimeSeries.getFitnessEvaluator(data, configuration);
        SelectionStrategy<Object>  selectionStrategy     = TestTimeSeries.getSelectionStrategy();
        return new GenerationalEvolutionEngine<Node> (
                factory, evolutionaryOperators, fitnessEvaluator, selectionStrategy, new MersenneTwisterRNG()
        );
    }

    private static TreeFactory getTreeFactory(GPConfiguration configuration)
    {
        TreeFactory factory = new TreeFactory (
            configuration.getWindowSize(),
            configuration.getMaximumTreeDepth(),
            Probability.EVENS,
            new Probability(configuration.getLeafNodeProbability())
        );
        try {
            factory.enableFunctionSet(TreeFactory.BASIC_OPERATORS);
            factory.enableFunctionSet(TreeFactory.COMPLEX_OPERATORS);
//            factory.enableFunctionSet(TreeFactory.LOGIC_OPERATORS);
            factory.enableFunctionSet(TreeFactory.TRIGONOMETRIC_OPERATORS);
            factory.enableFunctionSet(TreeFactory.TERMINALS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return factory;
    }

    private static EvolutionaryOperator<Node> getEvolutionaryOperators(TreeFactory treeFactory, GPConfiguration configuration)
    {
        List<EvolutionaryOperator<Node>> operators = new ArrayList<EvolutionaryOperator<Node>>();
        operators.add(new TreeMutation(treeFactory, new Probability(configuration.getMutationProbability())));
        operators.add(new TreeCrossover(configuration.getMaximumDepth()));
        operators.add(new Simplification());
        operators.add(new Plague(
            treeFactory, configuration.getSurvivalProbability(), configuration.getAmountOfPlagueSpreads(),
            configuration.getGenerationsBeforePlague(), configuration.isNaturalFitness()
        ));

        return new EvolutionPipeline<Node>(operators);
    }

    private static FitnessEvaluator<Node> getFitnessEvaluator(ArrayList<TimeNode> data, GPConfiguration configuration)
    {
        return new TimeSeriesEvaluator(data, configuration.getWindowSize(), configuration.getFitnessType());
    }

    private static TerminationCondition[] getTerminationConditions(GPConfiguration configuration)
    {
        // @todo Parametrizar a possibilidade de configuracao de diversas condicoes de parada.
        TerminationCondition[] terminationConditions = new TerminationCondition[3];
        terminationConditions[0] = new TargetFitness(configuration.getTargetFitness(), configuration.isNaturalFitness());
        terminationConditions[1] = new GenerationCount(configuration.getMaximumGenerationCount());
        terminationConditions[2] = new Stagnation(configuration.getStagnationLimit(), configuration.isNaturalFitness());

        return terminationConditions;
    }

    private static SelectionStrategy<Object> getSelectionStrategy()
    {
        return new RouletteWheelSelection();
    }

    private static void addEvolutionObservers(EvolutionEngine<Node> engine, final GPConfiguration configuration) {
        engine.addEvolutionObserver(new EvolutionObserver<Node>() {
            @Override
            public void populationUpdate(PopulationData<? extends Node> populationData) {
                if (configuration.isVerboseEvolve()) {
                    if (populationData.getGenerationNumber() % configuration.getPrintLogInterval() == 0) {
                        System.out.println("Generation: " + populationData.getGenerationNumber());
                        System.out.println("\tBest Solution: " + populationData.getBestCandidate());
                        System.out.println("\tIts Fitness is: " + populationData.getBestCandidateFitness());
                        System.out.println("\tPopulation size: " + populationData.getPopulationSize());
                        System.out.println("-----------------------------------------------------------");
                    }
                }

                if (populationData.getBestCandidateFitness() == configuration.getTargetFitness()) {
                    System.out.println("=============================================================");
                    System.out.println("======================== FINAL RESULT =======================");
                    System.out.println("=============================================================");
                    System.out.println("Generation: " + populationData.getGenerationNumber());
                    System.out.println("\tBest Solution: " + populationData.getBestCandidate());
                    System.out.println("\tIts Fitness is: " + populationData.getBestCandidateFitness());
                    System.out.println("\tPopulation size: " + populationData.getPopulationSize());
                    System.out.println("-----------------------------------------------------------");
                }
            }
        });
    }

}
