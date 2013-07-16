package IslandModelExample;

import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.*;
import org.uncommons.watchmaker.framework.factories.StringFactory;
import org.uncommons.watchmaker.framework.islands.IslandEvolution;
import org.uncommons.watchmaker.framework.islands.IslandEvolutionObserver;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.operators.StringCrossover;
import org.uncommons.watchmaker.framework.operators.StringMutation;
//import watchmaker.StringEvolution.StringsExampleFitnessFunction;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Paulo
 * Date: 16/06/13
 * Time: 17:07
 */
public class IslandExample
{

    public static final String TARGET = "NOW YOU ALL KNOW THE BARDS AND THEIR SONGS";
    public static final char[] VALID_VALUES = ("ABCDEFGHIJKLMNOPQRSTUVWXYZ ").toCharArray();


    public static void main (String[] args)
    {
//        long startTime = System.currentTimeMillis();
//        System.out.println("initialing program...");
//
//        CandidateFactory<String> candidateFactory          = getStringCandidateFactory();
//        EvolutionaryOperator<String> evolutionaryOperators = getStringEvolutionaryOperator();
//        StringsExampleFitnessFunction fitnessFunction      = new StringsExampleFitnessFunction(IslandExample.TARGET);
//        MersenneTwisterRNG rng                             = new MersenneTwisterRNG();
//        RouletteWheelSelection selection                   = new RouletteWheelSelection();
//
//        IslandEvolution<String> engine                     = new IslandEvolution<String>(
//            5, new RingMigration(), candidateFactory, evolutionaryOperators, fitnessFunction, selection, rng
//        );
//
//        IslandExample.addEngineObserver(engine);
//
//        String result = engine.evolve(100, 5, 20, 3, new TargetFitness(fitnessFunction.targetValueOfFitness(), true));
//        System.out.println(result);
//        long finishTime = System.currentTimeMillis();
//        System.out.println("Duration: " + (finishTime - startTime) + "ms");
    }

    private static void addEngineObserver(IslandEvolution<String> engine)
    {
        engine.addEvolutionObserver(new IslandEvolutionObserver<String>() {
            @Override
            public void islandPopulationUpdate(int i, PopulationData<? extends String> populationData) {
                System.out.println (
                    "Population: " + i +
                    "\n\tGeneration: " + populationData.getGenerationNumber() +
                    "\n\tBest Fitness value: " + populationData.getBestCandidateFitness() +
                    "\n\tBest individual: " + populationData.getBestCandidate() +
                    "\n###################################################"
                );
            }

            @Override
            public void populationUpdate(PopulationData<? extends String> populationData) {
                System.out.println(
                    "World Data: "+
                    "\n\tGeneration: " + populationData.getGenerationNumber() +
                    "\n\tBest Fitness value: " + populationData.getBestCandidateFitness() +
                    "\n\tBest individual: " + populationData.getBestCandidate() +
                    "\n###################################################"
                );
            }
        });
    }

    private static EvolutionaryOperator<String> getStringEvolutionaryOperator()
    {
        List<EvolutionaryOperator<String>> operators = new LinkedList<EvolutionaryOperator<String>>();
        operators.add(new StringCrossover());
        operators.add(new StringMutation(IslandExample.VALID_VALUES, new Probability(0.02)));
        return new EvolutionPipeline<String>(operators);
    }

    private static CandidateFactory<String> getStringCandidateFactory()
    {
        return new StringFactory(
            IslandExample.VALID_VALUES, IslandExample.TARGET.length()
        );
    }

}
