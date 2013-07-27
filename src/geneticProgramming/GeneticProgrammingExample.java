package geneticProgramming;

import geneticProgramming.functions.Node;
import geneticProgramming.functions.function.basic.Addition;
import geneticProgramming.functions.function.basic.Subtraction;
import geneticProgramming.functions.function.logic.Equals;
import geneticProgramming.functions.function.logic.IfThenElse;
import geneticProgramming.functions.function.logic.Not;
import geneticProgramming.geneticOperators.*;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.*;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.termination.GenerationCount;
import org.uncommons.watchmaker.framework.termination.Stagnation;
import org.uncommons.watchmaker.framework.termination.TargetFitness;
import geneticProgramming.functions.function.*;
import geneticProgramming.functions.terminal.*;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Paulo Fernandes
 *
 * User: Paulo
 * Date: 17/06/13
 * Time: 13:21
 */
public class GeneticProgrammingExample
{

    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
        Map<double[], Double> data = GeneticProgrammingExample.getData();
        Node program               = GeneticProgrammingExample.evolveProgram(data);

        System.out.println(program.print());
        long finishTime = System.currentTimeMillis();
        System.out.println("Duration: " + (finishTime - startTime) + "ms");

//        for (int i = 0; i < 1000; i++) {
//            testPopulationGeneration();
//        }
//        testSimplifier();
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

        System.out.println("Proposed problem: x² -2x + 1");
        for (int i = 0; i < size; i++) {
            x[i]   =  Math.round(13.0f * (random.nextFloat() - 0.3f));
            y[i]   = (x[i] * x[i]) - (2*x[i]) + 1;
            data.put(new double[]{x[i]}, y[i]);
            System.out.println("x = " + x[i] + "; y = " + y[i]);
        }
        //endregion

        return data;
    }

    private static Node evolveProgram(Map<double[], Double> data)
    {
        TreeFactory factory = new TreeFactory (
            1,                    // Number of parameters passed into each program.
            4,                    // Maximum of depth generated trees.
            Probability.EVENS,    // Probability that a node is a function node.
            new Probability(0.6d) // Probability that other nodes are params rather than constants.
        );

        List<EvolutionaryOperator<Node>> operators = new ArrayList<EvolutionaryOperator<Node>>(4);
        operators.add(new TreeMutation(factory, new Probability(0.5d)));
        operators.add(new TreeCrossover());
        operators.add(new Simplification());
        // Testing the new operator created: The plague
        operators.add(new Plague(factory, 0.01d, 3, 1000, false));

        EvolutionaryOperator<Node> pipelineOperators = new EvolutionPipeline<Node>(operators);

        TreeEvaluator fitnessFunction                = new TreeEvaluator(data);

        MersenneTwisterRNG rng                       = new MersenneTwisterRNG();

        EvolutionEngine<Node> engine                 = new GenerationalEvolutionEngine<Node>(
                factory, pipelineOperators, fitnessFunction, new RouletteWheelSelection(), rng
        );

        engine.addEvolutionObserver(new EvolutionObserver<Node>() {
            @Override
            public void populationUpdate(PopulationData<? extends Node> populationData) {
                if (populationData.getGenerationNumber() % 50 == 0) {
                    System.out.println("Generation: " + populationData.getGenerationNumber());
                    System.out.println("\tBest Solution: " + populationData.getBestCandidate());
                    System.out.println("\tIts Fitness is: " + populationData.getBestCandidateFitness());
                    System.out.println("\tPopulation size: " + populationData.getPopulationSize());
                    System.out.println("-----------------------------------------------------------");
                }

                if (populationData.getBestCandidateFitness() == 0) {
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

        TerminationCondition[] terminationConditions = new TerminationCondition[3];
        terminationConditions[0] = new TargetFitness(0, fitnessFunction.isNatural());
        terminationConditions[1] = new GenerationCount(10000);
        terminationConditions[2] = new Stagnation(5000, fitnessFunction.isNatural());

        try {
            return engine.evolve(100, 5, terminationConditions);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Termination conditions satisfied: ");
            List<TerminationCondition> a = engine.getSatisfiedTerminationConditions();
            for (TerminationCondition condition : a) {
                System.out.println(condition.toString());
            }
        }

        return null;
    }

    public static void testPopulationGeneration()
    {
        TreeFactory factory   = new TreeFactory(2, 4, Probability.EVENS, new Probability(0.6d));
        List<Node> population = factory.generateInitialPopulation(1000000, new MersenneTwisterRNG());
        for(Node individual : population) {
            if (individual.countNodes() > 1) {
                System.out.println(individual.simplify());
            }
        }
    }

    public static void testSimplifier()
    {
        Node node = new IfThenElse(
            new Not(
                new Equals(
                    new False(),
                    new False()
                )
            ),
            new Subtraction(
                new Addition(
                    new Parameter(0),
                    new Parameter(1)
                ),
                new Parameter(1)
            ),
            new Addition(
                new Constant(9),
                new Parameter(0)
            )
        );
        // if (!((FALSE = FALSE))) {((arg0 + arg1) - arg1)} else {(9.0 + arg0)}
        System.out.println(node);
        System.out.println(node.simplify());
    }

}
