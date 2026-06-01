package plataforma;

import java.util.Random;

/**
 * CLASSE CONCRETA - SensorPressao
 * Implementa a interface Sensor E herda de ComponenteEspacial
 * Mede a pressão interna da estação espacial (em kPa)
 */
public class SensorPressao extends ComponenteEspacial implements Sensor {

    private double valorAtual;
    private double limiteAlerta;
    private Random random;

    public SensorPressao(String id, String nome) {
        super(id, nome); // chama o construtor da classe mãe
        this.limiteAlerta = 120.0; // limite padrão: 120 kPa
        this.random = new Random();
    }

    // ---- Implementação dos métodos da Interface Sensor ----

    @Override
    public double lerValor() {
        // Simula leitura com valor aleatório entre 80 e 140 kPa
        this.valorAtual = 80 + (random.nextDouble() * 60);
        return Math.round(valorAtual * 10.0) / 10.0;
    }

    @Override
    public boolean verificarFuncionamento() {
        return isStatus();
    }

    @Override
    public String retornarTipo() {
        return "Pressão";
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
        return "Sensor de Pressão   | ID: " + id +
                " | Valor: " + valorAtual + " kPa" +
                " | Limite: " + limiteAlerta + " kPa" +
                " | Status: " + (status ? "LIGADO" : "DESLIGADO");
    }
}