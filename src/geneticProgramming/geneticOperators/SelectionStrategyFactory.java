package geneticProgramming.geneticOperators;

import geneticProgramming.configuration.IslandConfiguration;
import geneticProgramming.functions.Node;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.SelectionStrategy;
import org.uncommons.watchmaker.framework.selection.*;

/**
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 11/08/13
 * Time: 02:04
 */
public class SelectionStrategyFactory
{
    public static final int ROULETTE_WHEEL_SELECTION      = 1;
    public static final int TOURNAMENT_SELECTION          = 2;
    public static final int STOCHASTIC_UNIVERSAL_SAMPLING = 3;
    public static final int RANK_SELECTION                = 4;
    public static final int SIGMA_SCALING                 = 5;
    public static final int TRUNCATION_SELECTION          = 6;

    public static SelectionStrategy functionFactory(IslandConfiguration configuration)
    {
        switch (configuration.getSelectionStrategy()) {
            case ROULETTE_WHEEL_SELECTION:
                return new RouletteWheelSelection();
            case TOURNAMENT_SELECTION:
                return new TournamentSelection(new Probability(configuration.getSelectionProbability()));
            case STOCHASTIC_UNIVERSAL_SAMPLING:
                return new StochasticUniversalSampling();
            case RANK_SELECTION:
                return new RankSelection();
            case SIGMA_SCALING:
                return new SigmaScaling();
            case TRUNCATION_SELECTION:
                return new TruncationSelection(configuration.getSelectionRatio());
            default:
                return null;
        }
    }

}
