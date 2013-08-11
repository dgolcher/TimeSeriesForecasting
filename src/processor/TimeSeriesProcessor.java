package processor;

import geneticProgramming.configuration.GPConfiguration;
import geneticProgramming.configuration.IslandConfiguration;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Macro algoritmo para o processamento de series temporais:
 * 1.  Leitura dos arquivos de configuracao de GP e das ilhas;
 * 2.  Carregamento das diversas configurações;
 * 3.  Criação do arquivo de log do processamento da série (Como definir o nome do arquivo, de forma que este caracterize a execução da série?);
 * 4.  Carregamento das séries de aprendizado (treinamento) e avaliação do modelo obtido para a série;
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
// @todo at the end of the implemention of this class, remove this line and the other below.
@SuppressWarnings("UnusedDeclaration")
public class TimeSeriesProcessor
{

    private GPConfiguration                gpConfiguration;
    private ArrayList<IslandConfiguration> islandConfiguration;
    private String                         gpConfigurationFilePath;
    private String                         islandConfigurationFilePath;

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

}
