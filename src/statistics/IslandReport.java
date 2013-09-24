package statistics;

import java.util.ArrayList;

/**
 * This class will be used to log the evolution of an Island. It will show the history of evolution of it.
 *
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 18/09/13
 * Time: 00:30
 */
public class IslandReport
{

    private int islandIdentifier;
    private ArrayList<GenerationReport> evolutionHistory;
    private int generationCounter;

    public IslandReport(int islandIdentifier)
    {
        this.islandIdentifier  = islandIdentifier;
        this.evolutionHistory  = new ArrayList<GenerationReport>();
        this.generationCounter = 0;
    }

    public int getGenerationCounter()
    {
        return generationCounter;
    }

    public void setGenerationCounter(int generationCounter)
    {
        this.generationCounter = generationCounter;
    }

    public int getIslandIdentifier()
    {
        return islandIdentifier;
    }

    public void setIslandIdentifier(int islandIdentifier)
    {
        this.islandIdentifier = islandIdentifier;
    }

    public ArrayList<GenerationReport> getEvolutionHistory()
    {
        return evolutionHistory;
    }

    public void setEvolutionHistory(ArrayList<GenerationReport> evolutionHistory)
    {
        this.evolutionHistory = evolutionHistory;
    }

    public void addEvolutionReport(GenerationReport report)
    {
        this.evolutionHistory.add(report);
    }
}
