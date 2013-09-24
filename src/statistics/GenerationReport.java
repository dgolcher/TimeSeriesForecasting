package statistics;

import geneticProgramming.functions.Node;

/**
 * This class is used to report the evolution of the Genetic programming system. It will log the number of the current
 * generation, the best solution found on it and its fitness value.
 *
 *
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 18/09/13
 * Time: 00:09
 */
public class GenerationReport
{
    private int generation;
    private Node bestSolution;
    private double fitness;

    public GenerationReport()
    {
        this.generation   = 0;
        this.bestSolution = null;
        this.fitness      = 0;
    }

    public int getGeneration()
    {
        return generation;
    }

    public void setGeneration(int generation)
    {
        this.generation = generation;
    }

    public Node getBestSolution()
    {
        return bestSolution;
    }

    public void setBestSolution(Node bestSolution)
    {
        this.bestSolution = bestSolution;
    }

    public double getFitness()
    {
        return fitness;
    }

    public void setFitness(double fitness)
    {
        this.fitness = fitness;
    }
}
