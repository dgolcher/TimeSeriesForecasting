package geneticProgramming.geneticOperators;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import geneticProgramming.functions.Node;

import java.util.*;

/**
 * Genetic operator used to avoid stagnation of a population.
 *
 * If in many generations the population is stagnated (the average of fitness of the entire population is for a long
 * time the same, or not growing like expected), this operator will spread a plague in this population, making many
 * individuals die. The survivor ones (about 2% of this population) will be chosen randomly, although individuals with
 * better fitness values will have more chances to survive.
 *
 * After all this killing, this operator must create new individuals, to complete this population that was devastated.
 *
 * @author Paulo Fernandes
 *
 * Created with IntelliJ IDEA.
 * User: Paulo
 * Date: 24/06/13
 * Time: 13:59
 */
public class Plague implements EvolutionaryOperator<Node>
{
    /**
     * Factory used after the killing of the population. It will create the new population in the same size as the old
     * one was.
     */
    private TreeFactory treeFactory;
    /**
     * It will be used to decide if an individual will survive or not to the plague. It must have to guarantee that just
     * the most prepared slice of the population (that has the better fitness value) will survive.
     */
    private double survivalProbability;
    /**
     * Represents the amount of plagues that can be spread in this population.
     */
    private int amountOfPlagueSpreads;
    /**
     * All the plagues that was spread until now.
     */
    private int plaguesSpreadUntilNow;
    /**
     * Amount of generations that it have to pass before the plague is spread.
     */
    private int generationsBeforePlague;
    /**
     * True, if the fitness evaluation is natural (greater values to greater fitness), false, otherwise.
     */
    private boolean isFitnessNatural;

    /**
     * Counts the amount of generations without evolution in fitness.
     */
    public static int generationCounter = 0;
    /**
     * Saves the last better fitness value.
     */
    public static double lastBetterFitness = Double.MAX_VALUE;

    public Plague(TreeFactory treeFactory, double survivalProbability,
                  int amountOfPlagueSpreads, int generationsBeforePlague, boolean isFitnessNatural)
    {
        if (survivalProbability < 0 || survivalProbability > 1) {
            throw new IllegalArgumentException("The value of the survival probability must be between 0 and 1.");
        }

        this.treeFactory             = treeFactory;
        this.survivalProbability     = survivalProbability;
        this.amountOfPlagueSpreads   = amountOfPlagueSpreads;
        this.plaguesSpreadUntilNow   = 0;
        this.generationsBeforePlague = generationsBeforePlague;
        this.isFitnessNatural        = isFitnessNatural;
    }

    @Override
    public List<Node> apply(List<Node> population, Random random)
    {
        Collections.sort(population, new NodeComparator());
        boolean spreadThePlague  = this.spreadThePlague(population);

        if (spreadThePlague) {
            System.out.println("\t\tTHE PLAGUE WAS SPREAD IN THE POPULATION");
            int originalPopulationSize    = population.size();
            List<Node> survivorPopulation = new ArrayList<Node>();

            this.chooseSurvivors(population, survivorPopulation);
            this.fillPopulation(random, originalPopulationSize, survivorPopulation);

            // Updating the data about spreading the plague.
            Plague.generationCounter = 0;
            this.plaguesSpreadUntilNow++;

            return survivorPopulation;
        }

        Plague.generationCounter++;

        return population;
    }

    /**
     * Check if all the conditions to spread the plague are satisfied. It also updates the values of lastBetterFitness
     * and generationCounter if the fitness of the first better individual is better then the historical one.
     *
     * @param population Original population.
     * @return The conditions to spread the plague. In case of True, the plague will occur. In case of false, the plague
     *         will not be spread.
     */
    private boolean spreadThePlague(List<Node> population)
    {
        int bestCandidateIndex   = this.isFitnessNatural ? population.size() - 1 : 0;
        if (population.get(bestCandidateIndex).getFitnessValue() < Plague.lastBetterFitness) {
            Plague.lastBetterFitness = population.get(bestCandidateIndex).getFitnessValue();
            Plague.generationCounter = 0;
            return false;
        }

        return Plague.generationCounter    == this.generationsBeforePlague &&
               this.plaguesSpreadUntilNow   < this.amountOfPlagueSpreads;
    }

    /**
     * Choose the individuals in a population that will survive the plague. They will be put in a new population, where
     * the survivors will compose a new population.
     *
     * @param population         The old population, that will be devastated by this Plague.
     * @param survivorPopulation The new population. Here, it will be used to save all the survivors.
     */
    private void chooseSurvivors(List<Node> population, List<Node> survivorPopulation)
    {
        // Calculating the percentage of survivors in the population, based on the property "survivalProbability"
        double totalOfSurvivors = Math.ceil(population.size() * this.survivalProbability);
        // Choosing all the survivor individuals in this population. This choice is made using the orderly population
        // and catching the first individuals in this list (the better fit ones).
        if (this.isFitnessNatural) {
            for (int i = 0; i < totalOfSurvivors; i++) {
                survivorPopulation.add(population.get(i));
            }
        } else {
            for (int i = 0; i < totalOfSurvivors; i++) {
                survivorPopulation.add(population.get(population.size() - i - 1));
            }
        }
    }

    /**
     * This method completes the population devastated with new individuals, generated by this method.
     *
     * @param random                 Random value generator.
     * @param originalPopulationSize Size of the original population.
     * @param newPopulation          New population that will be created with the survivor individuals and the new
     *                               ones generated here.
     */
    private void fillPopulation(Random random, int originalPopulationSize, List<Node> newPopulation)
    {
        for (int i = newPopulation.size(); i < originalPopulationSize; i++) {
            newPopulation.add(this.treeFactory.generateRandomCandidate(random));
        }
    }

}

/**
* Class Auxiliary, used to compare the individuals of this population.
*/
class NodeComparator implements Comparator<Node>
{
    @Override
    public int compare(Node o1, Node o2) {
        return o1.getFitnessValue().compareTo(o2.getFitnessValue());
    }
}