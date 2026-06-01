package plataforma;

/**
 * HERANÇA - PropulsaoQuimica
 * Herda de SistemaPropulsao e implementa o acelerar() do seu jeito.
 * Propulsão química usa combustível líquido e tem aquecimento rápido.
 */
public class PropulsaoQuimica extends SistemaPropulsao {

    // Atributos específicos da propulsão química
    private String tipoCombustivel;
    private double nivelCombustivel;  // 0 a 100%
    private double taxaConsumo;       // % de combustível consumido por aceleração

    // Construtor
    public PropulsaoQuimica(String id, String nome) {
        super(id, nome, 50000.0); // empuxo máximo: 50.000 N
        this.tipoCombustivel = "Hidrogênio Líquido";
        this.nivelCombustivel = 100.0;
        this.taxaConsumo = 2.5;
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
        if (nivelCombustivel <= 0) {
            System.out.println(" [CRÍTICO] Sem combustível! Impossível acelerar.");
            return;
        }

        this.potenciaAtual = potencia;

        // Consome combustível proporcional à potência
        double consumo = (potencia / 100.0) * taxaConsumo;
        nivelCombustivel = Math.max(0, nivelCombustivel - consumo);

        // Aquecimento proporcional à potência (característica química)
        this.temperatura = 200 + (potencia * 3.5);

        System.out.println(" [Propulsão Química] Acelerando a " + potencia + "% de potência.");
        System.out.println("   Empuxo gerado : " + calcularEmpuxo() + " N");
        System.out.println("   Combustível   : " + Math.round(nivelCombustivel * 10.0) / 10.0 + "%");
        System.out.println("   Temperatura   : " + temperatura + "°C");

        verificarCombustivel();
    }

    // ---- Alerta de combustível ----

    private void verificarCombustivel() {
        if (nivelCombustivel < 10) {
            System.out.println(" [CRÍTICO] Combustível em " + nivelCombustivel + "%!");
        } else if (nivelCombustivel < 20) {
            System.out.println(" [ALERTA] Combustível baixo: " + nivelCombustivel + "%!");
        } else if (nivelCombustivel < 30) {
            System.out.println(" [ATENÇÃO] Combustível em " + nivelCombustivel + "%.");
        }
    }

    // ---- Getters específicos ----

    public String getTipoCombustivel() { return tipoCombustivel; }
    public double getNivelCombustivel() { return nivelCombustivel; }

    // ---- Diagnóstico específico ----

    @Override
    public String exibirDiagnostico() {
        return super.exibirDiagnostico() +
                " | Combustível: " + Math.round(nivelCombustivel * 10.0) / 10.0 + "%" +
                " | Tipo: " + tipoCombustivel;
    }
}