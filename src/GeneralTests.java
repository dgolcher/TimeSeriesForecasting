import geneticProgramming.functions.Node;
import geneticProgramming.functions.function.Simplification;
import geneticProgramming.geneticOperators.*;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.*;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.termination.GenerationCount;
import org.uncommons.watchmaker.framework.termination.Stagnation;
import org.uncommons.watchmaker.framework.termination.TargetFitness;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 30/07/13
 * Time: 23:33
 */
public class GeneralTests
{

    /**
     * Constantes que sao candidatos a parametros.
     *
     * Incluir como candidatos a parametros:
     *   Grupo de operadores a ser incluidos para a classe TreeFactory.
     *   Grupo de operadores geneticos que serão incluidos na lista de operadores geneticos.
     *   Grupo de condições de parada.
     *
     */
    private static final int POPULATION_SIZE         = 10000;
    private static final int ELITISM_COUNT           = 5;
    private static final int WINDOW_SIZE             = 1;
    private static final int MAXIMUM_TREE_DEPTH      = 4;
    private static final double LEAF_NODE_PROBABILITY = 0.6d;
    private static final double MUTATION_PROBABILITY  = 0.5d;
    private static final int MAX_DEPTH                = 10;

    private static double SURVIVAL_PROBABILITY       = 0.01d;
    private static int AMOUNT_OF_PLAGUE_SPREADS      = 3;
    private static int GENERATIONS_BEFORE_PLAGUE     = 1000;
    private static final boolean IS_FITNESS_NATURAL = false;
    private static final int TARGET_FITNESS         = 0;
    private static final int MAX_GENERATION_COUNT   = 10000;
    private static final int STAGNATION_LIMIT       = 5000;
    private static final boolean VERBOSE_EVOLVE     = true;
    private static final int PRINT_LOG_INTERVAL     = 100;
    private static final int FITNESS_TYPE           = TimeSeriesEvaluator.PLAIN_ERROR;


    public static void main(String[] args)
    {
        Map<double[], Double> data = getData();
        Node program  = evolveProgram(data);
        System.out.println("Best solution found");
        System.out.println(program.print());
    }

    private static Map<double[], Double> getData()
    {
        Map<double[], Double> data = new HashMap<double[], Double>();
        int size = 20;

        //region Testing Math functions.
//        double[]  x1 = new double[size];
//        double[]  x2 = new double[size];
//        double[]  x3 = new double[size];
//        double[]  x4 = new double[size];
//        double[]  x5 = new double[size];
//        double[]  x6 = new double[size];
//        double[]  y = new double[size];
//
//        Random random = new Random();
//        for (int i = 0; i < size; i++) {
//            float f = 13.0f * (random.nextFloat() - 0.3f);
//            x1[i] = f;
//            f = 13.0f * (random.nextFloat() - 0.3f);
//            x2[i] = f;
//            f = 13.0f * (random.nextFloat() - 0.3f);
//            x3[i] = f;
//            f = 13.0f * (random.nextFloat() - 0.3f);
//            x4[i] = f;
//            f = 13.0f * (random.nextFloat() - 0.3f);
//            x5[i] = f;
//            f = 13.0f * (random.nextFloat() - 0.3f);
//            x6[i] = f;
//            y[i] = x1[i] + x2[i] - x3[i] + x4[i] - x5[i] + x6[i];
//
//            System.out.println(x1[i] + "+" + x2[i] + "-" + x3[i] + "+" + x4[i] +
//                               "-" + x5[i] + "+" + x6[i] + " = " + y[i]);
//            data.put(new double[]{x1[i], x2[i], x3[i], x4[i], x5[i], x6[i]}, y[i]);
//        }
        //endregion


        //region Testing if.
//        double[] x       = new double[size];
//        double[] x1      = new double[size];
//        double[] y       = new double[size];
//        Random random    = new Random();
//
//        System.out.println("Proposed problem: if (x < x1) then x else x1");
//        for (int i = 0; i < size; i++) {
//            x[i]  =  Math.round(13.0f * (random.nextFloat() - 0.3f));
//            x1[i] =  Math.round(13.0f * (random.nextFloat() - 0.3f));
//            y[i]  = (x[i] < x1[i]) ? x[i] : x1[i];
//            data.put(new double[]{x[i], x1[i]}, y[i]);
//            System.out.println("x = " + x[i] + "; x1 = " + x1[i] + "; y = " + y[i]);
//        }
        //endregion


        //region Complex Expressions.
        double[] x = new double[size];
        double[] y = new double[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            x[i]   =  Math.round(13.0f * (random.nextFloat() - 0.3f));
            x[i]   = Math.abs(x[i]) + 1;
            //--------------------------------------------------------
            y[i]     = Math.log(x[i]) + 1;
//            y[i]   = Math.abs(x[i]);
//            y[i]   = Math.acos(x[i]);
//            y[i]   = Math.asin(x[i]);
//            y[i]   = Math.atan(x[i]);
//            y[i]   = Math.sin(x[i]);
//            y[i]   = Math.tan(x[i]);
//            y[i]   = Math.cos(x[i]);
//            y[i]   = Math.log(x[i]);
//            y[i]   = Math.log10(x[i]);
            //--------------------------------------------------------
            data.put(new double[]{x[i]}, y[i]);
            System.out.println("x = " + x[i] + "; y = " + y[i]);
        }
        //endregion

        return data;
    }

    private static Node evolveProgram(Map<double[], Double> data)
    {
        Node node = null;
        TerminationCondition[] terminationConditions = getTerminationConditions();
        EvolutionEngine<Node> engine                 = getEvolutionEngine(data);
        try {
            addEvolutionObservers(engine);
            node = engine.evolve(POPULATION_SIZE, ELITISM_COUNT, terminationConditions);
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

    private static EvolutionEngine<Node> getEvolutionEngine(Map<double[], Double> data)
    {
        TreeFactory factory               = getTreeFactory();
        EvolutionaryOperator<Node> evolutionaryOperators = getEvolutionaryOperators(factory);
        FitnessEvaluator<Node> fitnessEvaluator      = getFitnessEvaluator(data);
        SelectionStrategy selectionStrategy     = getSelectionStrategy();
        return new GenerationalEvolutionEngine<Node>(
                factory, evolutionaryOperators, fitnessEvaluator, selectionStrategy, new MersenneTwisterRNG()
        );
    }

    private static TreeFactory getTreeFactory()
    {
        TreeFactory factory = new TreeFactory (
                WINDOW_SIZE,
                MAXIMUM_TREE_DEPTH,
                Probability.EVENS,
                new Probability(LEAF_NODE_PROBABILITY)
        );
        try {
            factory.enableFunctionSet(TreeFactory.BASIC_OPERATORS);
            factory.enableFunctionSet(TreeFactory.COMPLEX_OPERATORS);
            factory.enableFunctionSet(TreeFactory.LOGIC_OPERATORS);
            factory.enableFunctionSet(TreeFactory.TRIGONOMETRIC_OPERATORS);
            factory.enableFunctionSet(TreeFactory.TERMINALS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return factory;
    }

    private static EvolutionaryOperator<Node> getEvolutionaryOperators(TreeFactory treeFactory)
    {
        List<EvolutionaryOperator<Node>> operators = new ArrayList<EvolutionaryOperator<Node>>();
        operators.add(new TreeMutation(treeFactory, new Probability(MUTATION_PROBABILITY)));
        operators.add(new TreeCrossover(MAX_DEPTH));
        operators.add(new Simplification());
        operators.add(new Plague(
                treeFactory, SURVIVAL_PROBABILITY, AMOUNT_OF_PLAGUE_SPREADS,
                GENERATIONS_BEFORE_PLAGUE, IS_FITNESS_NATURAL
        ));

        return new EvolutionPipeline<Node>(operators);
    }

    private static FitnessEvaluator<Node> getFitnessEvaluator(Map<double[], Double> data)
    {
        return new TreeEvaluator(data);
    }

    private static TerminationCondition[] getTerminationConditions()
    {
        // Preparar uma forma de parametrizacao para a quantidade de condicoes de parada.
        TerminationCondition[] terminationConditions = new TerminationCondition[3];
        terminationConditions[0] = new TargetFitness(TARGET_FITNESS, IS_FITNESS_NATURAL);
        terminationConditions[1] = new GenerationCount(MAX_GENERATION_COUNT);
        terminationConditions[2] = new Stagnation(STAGNATION_LIMIT, IS_FITNESS_NATURAL);

        return terminationConditions;
    }

    private static SelectionStrategy getSelectionStrategy()
    {
        return new RouletteWheelSelection();
    }

    private static void addEvolutionObservers(EvolutionEngine<Node> engine) {
        engine.addEvolutionObserver(new EvolutionObserver<Node>() {
            @Override
            public void populationUpdate(PopulationData<? extends Node> populationData) {
                if (VERBOSE_EVOLVE) {
                    if (populationData.getGenerationNumber() % PRINT_LOG_INTERVAL == 0) {
                        System.out.println("Generation: " + populationData.getGenerationNumber());
                        System.out.println("\tBest Solution: " + populationData.getBestCandidate());
                        System.out.println("\tIts Fitness is: " + populationData.getBestCandidateFitness());
                        System.out.println("\tPopulation size: " + populationData.getPopulationSize());
                        System.out.println("-----------------------------------------------------------");
                    }
                }

                if (populationData.getBestCandidateFitness() == TARGET_FITNESS) {
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
