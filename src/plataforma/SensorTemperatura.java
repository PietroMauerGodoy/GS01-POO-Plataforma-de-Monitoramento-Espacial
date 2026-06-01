package plataforma;

import java.util.Random;

/**
 * CLASSE CONCRETA - SensorTemperatura
 * Implementa a interface Sensor E herda de ComponenteEspacial
 * Mede a temperatura da estação espacial
 */
public class SensorTemperatura extends ComponenteEspacial implements Sensor {

    private double valorAtual;
    private double limiteAlerta;
    private Random random;

    public SensorTemperatura(String id, String nome) {
        super(id, nome); // chama o construtor da classe mãe
        this.limiteAlerta = 80.0; // limite padrão: 80°C
        this.random = new Random();
    }

    // ---- Implementação dos métodos da Interface Sensor ----

    @Override
    public double lerValor() {
        // Simula leitura com valor aleatório entre -20 e 120
        this.valorAtual = -20 + (random.nextDouble() * 140);
        return Math.round(valorAtual * 10.0) / 10.0;
    }

    @Override
    public boolean verificarFuncionamento() {
        return isStatus();
    }

    @Override
    public String retornarTipo() {
        return "Temperatura";
    }

    @Override
    public void setLimiteAlerta(double limite) {
        this.limiteAlerta = limite;
    }

    @Override
    public boolean verificarAlerta() {
        return valorAtual > limiteAlerta;
    }

    // ---- Implementação do método abstrato de ComponenteEspacial ----

    @Override
    public String exibirDiagnostico() {
        return "Sensor de Temperatura | ID: " + id +
                " | Valor: " + valorAtual + "°C" +
                " | Limite: " + limiteAlerta + "°C" +
                " | Status: " + (status ? "LIGADO" : "DESLIGADO");
    }
}