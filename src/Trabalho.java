import processor.TimeSeriesProcessor;

/**
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 22/09/13
 * Time: 14:46
 */
public class Trabalho
{

    public static void main(String[] args) throws Exception
    {
//        caotica();
//        constanteSimulada();
//        crescente1();
        crescente2();
//        crescenteSimulada();
//        decrescente1();
//        decrescente2();
//        decrescenteSimulada();
//        parabola1();
//        parabola2();
//        parabola3();
//        parabolaSimulada();
//        terceiroGrauSimulado();
    }

    private static void caotica() throws Exception
    {
        String gpConfigurationPath     = "data/dados bolsas/trabalho/caotica/gp_configuration.arff";
        String islandConfigurationPath = "data/dados bolsas/trabalho/island_configuration.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(1);
    }

    private static void constanteSimulada() throws Exception
    {
        String gpConfigurationPath     = "data/dados bolsas/trabalho/constante_simulada/gp_configuration.arff";
        String islandConfigurationPath = "data/dados bolsas/trabalho/island_configuration.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(1);
    }

    private static void crescente1() throws Exception
    {
        System.out.println("CRESCENTE 1 NAO TEM CURVA - PROVIDENCIAR");
        String gpConfigurationPath     = "data/dados bolsas/trabalho/crescente_1/gp_configuration.arff";
        String islandConfigurationPath = "data/dados bolsas/trabalho/island_configuration.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(1);
    }

    private static void crescente2() throws Exception
    {
        String gpConfigurationPath     = "data/dados bolsas/trabalho/crescente_2/gp_configuration.arff";
        String islandConfigurationPath = "data/dados bolsas/trabalho/island_configuration.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(1);
    }

    private static void crescenteSimulada() throws Exception
    {
        String gpConfigurationPath     = "data/dados bolsa/trabalho/crescente_simulada/gp_configuration.arff";
        String islandConfigurationPath = "data/dados bolsa/trabalho/island_configuration.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(1);
    }

    private static void decrescente1() throws Exception
    {
        String gpConfigurationPath     = "data/dados bolsa/trabalho/decrescente_1/gp_configuration.arff";
        String islandConfigurationPath = "data/dados bolsa/trabalho/island_configuration.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(1);
    }

    private static void decrescente2() throws Exception
    {
        String gpConfigurationPath     = "data/dados bolsa/trabalho/decrescente_2/gp_configuration.arff";
        String islandConfigurationPath = "data/dados bolsa/trabalho/island_configuration.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(1);
    }

    private static void decrescenteSimulada() throws Exception
    {
        String gpConfigurationPath     = "data/dados bolsa/trabalho/decrescente_simulada/gp_configuration.arff";
        String islandConfigurationPath = "data/dados bolsa/trabalho/island_configuration.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(1);
    }

    private static void parabola1() throws Exception
    {
        String gpConfigurationPath     = "data/dados bolsa/trabalho/parabola_1/gp_configuration.arff";
        String islandConfigurationPath = "data/dados bolsa/trabalho/island_configuration.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(1);
    }

    private static void parabola2() throws Exception
    {
        String gpConfigurationPath     = "data/dados bolsa/trabalho/parabola_2/gp_configuration.arff";
        String islandConfigurationPath = "data/dados bolsa/trabalho/island_configuration.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(1);
    }

    private static void parabola3() throws Exception
    {
        String gpConfigurationPath     = "data/dados bolsa/trabalho/parabola_3/gp_configuration.arff";
        String islandConfigurationPath = "data/dados bolsa/trabalho/island_configuration.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(1);
    }

    private static void parabolaSimulada() throws Exception
    {
        String gpConfigurationPath     = "data/dados bolsa/trabalho/parabola_simulada/gp_configuration.arff";
        String islandConfigurationPath = "data/dados bolsa/trabalho/island_configuration.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(1);
    }

    private static void terceiroGrauSimulado() throws Exception
    {
        String gpConfigurationPath     = "data/dados bolsa/trabalho/terceiro_grau_simulado/gp_configuration.arff";
        String islandConfigurationPath = "data/dados bolsa/trabalho/island_configuration.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(1);
    }

}
