import processor.TimeSeriesProcessor;

/**
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 08/10/13
 * Time: 22:22
 */
public class Trabalho
{

    public static void main(String[] args) throws Exception
    {
        bandaLargaBrasil();
        expectativaDeVidaBrasil();
        matrizEnergeticaBrasil();
        PIBPerCaptaBrasil();
        populacaoBrasil();
        taxaDeFertilidadeBrasil();
        temperaturaMediaBeloHorizonte();
    }

    private static void bandaLargaBrasil() throws Exception
    {
        String gpConfigurationPath     = "data/dados_apresentacao/configuration/gpconfigurations/banda_larga_brasil.arff";
        String islandConfigurationPath = "data/dados_apresentacao/configuration/island_configuration.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(1);
    }

    private static void expectativaDeVidaBrasil() throws Exception
    {
        String gpConfigurationPath     = "data/dados_apresentacao/configuration/gpconfigurations/expectativa_de_vida_brasil.arff";
        String islandConfigurationPath = "data/dados_apresentacao/configuration/island_configuration.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(1);
    }

    private static void matrizEnergeticaBrasil() throws Exception
    {
        String gpConfigurationPath     = "data/dados_apresentacao/configuration/gpconfigurations/matriz_energetica_brasil.arff";
        String islandConfigurationPath = "data/dados_apresentacao/configuration/island_configuration.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(1);
    }

    private static void PIBPerCaptaBrasil() throws Exception
    {
        String gpConfigurationPath     = "data/dados_apresentacao/configuration/gpconfigurations/PIB_per_capta_brasil.arff";
        String islandConfigurationPath = "data/dados_apresentacao/configuration/island_configuration.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(1);
    }

    private static void populacaoBrasil() throws Exception
    {
        String gpConfigurationPath     = "data/dados_apresentacao/configuration/gpconfigurations/populacao_brasil.arff";
        String islandConfigurationPath = "data/dados_apresentacao/configuration/island_configuration.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(1);
    }

    private static void taxaDeFertilidadeBrasil() throws Exception
    {
        String gpConfigurationPath     = "data/dados_apresentacao/configuration/gpconfigurations/taxa_de_fertilidade_brasil.arff";
        String islandConfigurationPath = "data/dados_apresentacao/configuration/island_configuration.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(1);
    }

    private static void temperaturaMediaBeloHorizonte() throws Exception
    {
        String gpConfigurationPath     = "data/dados_apresentacao/configuration/gpconfigurations/temperatura_media_belo_horizonte.arff";
        String islandConfigurationPath = "data/dados_apresentacao/configuration/island_configuration.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(1);
    }

}
