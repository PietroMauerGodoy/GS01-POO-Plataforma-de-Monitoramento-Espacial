package plataforma;

import java.util.Random;

/**
 * CLASSE CONCRETA - SensorRadiacao
 * Implementa a interface Sensor E herda de ComponenteEspacial
 * Mede o nível de radiação da estação espacial (em mSv/h)
 */
public class SensorRadiacao extends ComponenteEspacial implements Sensor {

    private double valorAtual;
    private double limiteAlerta;
    private Random random;

    public SensorRadiacao(String id, String nome) {
        super(id, nome); // chama o construtor da classe mãe
        this.limiteAlerta = 2.0; // limite padrão: 2 mSv/h
        this.random = new Random();
    }

    // ---- Implementação dos métodos da Interface Sensor ----

    @Override
    public double lerValor() {
        // Simula leitura com valor aleatório entre 0 e 5 mSv/h
        this.valorAtual = random.nextDouble() * 5;
        return Math.round(valorAtual * 100.0) / 100.0;
    }

    @Override
    public boolean verificarFuncionamento() {
        return isStatus();
    }

    @Override
    public String retornarTipo() {
        return "Radiação";
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
        return "Sensor de Radiação  | ID: " + id +
                " | Valor: " + valorAtual + " mSv/h" +
                " | Limite: " + limiteAlerta + " mSv/h" +
                " | Status: " + (status ? "LIGADO" : "DESLIGADO");
    }
}