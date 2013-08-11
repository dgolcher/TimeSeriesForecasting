package processor;

import geneticProgramming.configuration.GPConfiguration;
import geneticProgramming.configuration.IslandConfiguration;
import geneticProgramming.functions.Node;
import model.TimeNode;
import postProcessors.Forecast;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Macro algoritmo para o processamento de series temporais:
 * 1.  Leitura dos arquivos de configuracao de GP e das ilhas; ok
 * 4.  Carregamento das séries de aprendizado (treinamento) e avaliação do modelo obtido para a série;
 * 2.  Carregamento das diversas configurações;
 * 3.  Criação do arquivo de log do processamento da série (Como definir o nome do arquivo, de forma que este caracterize a execução da série?);
 * 5.  Inicialização dos componentes do GP, criação e configuração das ilhas;
 * 6.  Pré-Processamento da busca;
 * 7.  Processamento da busca;
 * 8.  Pós-Processamento do busca;
 * 9.  Processamento da previsão dos n períodos de horizonte de busca;
 * 10. Apresentação dos resultados;
 * 11. Comparação da série obtida com a série de avaliação.
 *
 *
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 10/08/13
 * Time: 20:28
 */
// @todo at the end of the implementation of this class, remove this line and the other below.
@SuppressWarnings("UnusedDeclaration")
public class TimeSeriesProcessor
{

    private GPConfiguration                gpConfiguration;
    private ArrayList<IslandConfiguration> islandConfiguration;
    private String                         gpConfigurationFilePath;
    private String                         islandConfigurationFilePath;

    private ArrayList<TimeNode>            originalTimeSeries;
    private ArrayList<TimeNode>            trainingData;
    private ArrayList<TimeNode>            testingData;
    private Node                           bestCandidate;

    public TimeSeriesProcessor(String gpConfigurationFilePath, String islandConfigurationFilePath)
    {
        this.gpConfigurationFilePath     = gpConfigurationFilePath;
        this.islandConfigurationFilePath = islandConfigurationFilePath;
        this.gpConfiguration             = new GPConfiguration();
        this.islandConfiguration         = new ArrayList<IslandConfiguration>();
    }

    public void loadConfigurations() throws IOException, InstantiationException, IllegalAccessException
    {
        ConfigurationLoader.loadConfigurations(this.gpConfigurationFilePath, this.gpConfiguration);
        ConfigurationLoader.loadConfigurations (
            this.islandConfigurationFilePath, this.islandConfiguration, IslandConfiguration.class
        );
    }

    public void getData() throws Exception
    {
        DataProvider dataProvider = new DataProvider(gpConfiguration);
        this.originalTimeSeries   = dataProvider.getFullTimeSeriesData();
        this.trainingData         = dataProvider.getTrainingData();
        this.testingData          = dataProvider.getTestingData();
    }

    public void getIslands() throws Exception
    {
        IslandBuilder.build(this.islandConfiguration, this.gpConfiguration, this.trainingData);
    }

    /**
     * @todo this method was not implemented yet.
     *
     * This method processes all the modifications required to produce a better forecasting. All modifications made
     * over the data must be reverted after by the method postProcessingData.
     */
    public void preProcessingData()
    {
        // This method must execute the pre-processing methods.
    }

    /**
     * @todo this method was not implemented yet.
     *
     * This method reverts all modifications made over the data in preProcessingData method. This method have also to
     * make the same modifications over the forecasted data (all values produced).
     */
    public void postProcessingData()
    {
        // This method must undo all modifications in data made by the preProcessingData method.
    }

    /**
     * This method executes the forecasting of values. It will use the best candidate found in the execution of GP
     * Program and then, return that data.
     *
     * @return Return the time series forecasted (this time series is composed by a portion of original data, generally
     * the data used to train the GP Machine + the data forecasted).
     */
    public ArrayList<TimeNode> getForecastedTimeSeries()
    {
        Forecast forecast = new Forecast(this.originalTimeSeries, this.gpConfiguration, this.bestCandidate);
        return forecast.processForecasting();
    }

}