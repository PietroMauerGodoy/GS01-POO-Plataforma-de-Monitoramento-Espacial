package plataforma;

/**
 * INTERFACE - Sensor
 * Define o "contrato" que todo sensor DEVE seguir.
 * Qualquer classe que implementar essa interface é OBRIGADA
 * a ter todos esses métodos.
 */
public interface Sensor {

    // Realiza a leitura do sensor
    double lerValor();

    // Verifica se o sensor está funcionando
    boolean verificarFuncionamento();

    // Retorna o tipo do sensor
    String retornarTipo();

    // Define o limite máximo
    void setLimiteAlerta(double limite);

    // Verifica se o valor atual ultrapassou o limite de alerta
    boolean verificarAlerta();
}