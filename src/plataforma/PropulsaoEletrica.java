package plataforma;

/**
 * HERANÇA - PropulsaoEletrica
 * Herda de SistemaPropulsao e implementa o acelerar() do seu jeito.
 * Propulsão elétrica usa energia solar, é mais eficiente mas tem empuxo menor.
 */
public class PropulsaoEletrica extends SistemaPropulsao {

    // Atributos específicos da propulsão elétrica
    private double cargaBateria;        // 0 a 100%
    private double eficiencia;          // % de eficiência do motor elétrico
    private double taxaConsumoBateria;  // % de bateria consumida por aceleração

    // Construtor
    public PropulsaoEletrica(String id, String nome) {
        super(id, nome, 20000.0);
        this.cargaBateria = 100.0;
        this.eficiencia = 92.0;
        this.taxaConsumoBateria = 1.2;
    }

    // ---- Implementação do método abstrato acelerar() ----

    @Override
    public void acelerar(double potencia) {
        if (!motorLigado) {
            System.out.println(" Motor desligado! Ligue o motor antes de acelerar.");
            return;
        }
        if (potencia < 0 || potencia > 100) {
            System.out.println(" Potência inválida! Use valores entre 0 e 100.");
            return;
        }
        if (cargaBateria <= 0) {
            System.out.println(" [CRÍTICO] Bateria descarregada! Impossível acelerar.");
            return;
        }

        this.potenciaAtual = potencia;

        // Consome bateria proporcional à potência
        double consumo = (potencia / 100.0) * taxaConsumoBateria;
        cargaBateria = Math.max(0, cargaBateria - consumo);

        // Motor elétrico aquece bem menos que o químico
        this.temperatura = 40 + (potencia * 0.8);

        // Empuxo real considera a eficiência do motor elétrico
        double empuxoReal = calcularEmpuxo() * (eficiencia / 100.0);

        System.out.println(" [Propulsão Elétrica] Acelerando a " + potencia + "% de potência.");
        System.out.println("   Empuxo gerado : " + Math.round(empuxoReal * 10.0) / 10.0 + " N");
        System.out.println("   Eficiência    : " + eficiencia + "%");
        System.out.println("   Bateria       : " + Math.round(cargaBateria * 10.0) / 10.0 + "%");
        System.out.println("   Temperatura   : " + temperatura + "°C");

        verificarBateria();
    }

    // ---- Alerta de bateria ----

    private void verificarBateria() {
        if (cargaBateria < 10) {
            System.out.println(" [CRÍTICO] Bateria em " + cargaBateria + "%!");
        } else if (cargaBateria < 20) {
            System.out.println(" [ALERTA] Bateria baixa: " + cargaBateria + "%!");
        } else if (cargaBateria < 30) {
            System.out.println(" [ATENÇÃO] Bateria em " + cargaBateria + "%.");
        }
    }

    // ---- Getters específicos ----

    public double getCargaBateria() { return cargaBateria; }
    public double getEficiencia() { return eficiencia; }

    // ---- Diagnóstico específico ----

    @Override
    public String exibirDiagnostico() {
        return super.exibirDiagnostico() +
                " | Bateria: " + Math.round(cargaBateria * 10.0) / 10.0 + "%" +
                " | Eficiência: " + eficiencia + "%";
    }
}