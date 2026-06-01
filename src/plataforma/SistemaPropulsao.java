package plataforma;

/**
 * CLASSE ABSTRATA - SistemaPropulsao
 * Herança: define atributos e métodos comuns a todos os tipos de propulsão.
 * As subclasses (PropulsaoQuimica e PropulsaoEletrica) herdam dessa classe
 * e implementam o método acelerar() do seu próprio jeito.
 */
public abstract class SistemaPropulsao extends ComponenteEspacial {

    // Atributos comuns a todo sistema de propulsão
    protected double potenciaAtual;    // 0 a 100%
    protected double empuxoMaximo;     // em Newtons
    protected boolean motorLigado;

    // Construtor
    public SistemaPropulsao(String id, String nome, double empuxoMaximo) {
        super(id, nome); // chama o construtor de ComponenteEspacial
        this.potenciaAtual = 0;
        this.empuxoMaximo = empuxoMaximo;
        this.motorLigado = false;
    }

    // ---- Métodos concretos comuns ----

    public void ligarMotor() {
        this.motorLigado = true;
        super.ligar(); // chama o ligar() da classe mãe
        System.out.println("🚀 Motor de propulsão ativado!");
    }

    public void desligarMotor() {
        this.motorLigado = false;
        this.potenciaAtual = 0;
        super.desligar(); // chama o desligar() da classe mãe
        System.out.println("Motor de propulsão desativado.");
    }

    public double calcularEmpuxo() {
        return (potenciaAtual / 100.0) * empuxoMaximo;
    }

    // ---- Getters ----

    public double getPotenciaAtual() { return potenciaAtual; }
    public double getEmpuxoMaximo() { return empuxoMaximo; }
    public boolean isMotorLigado() { return motorLigado; }

    // ---- Método ABSTRATO ----
    // Cada tipo de propulsão acelera de um jeito diferente
    public abstract void acelerar(double potencia);

    // ---- Implementação do método abstrato de ComponenteEspacial ----

    @Override
    public String exibirDiagnostico() {
        return "Sistema de Propulsão | ID: " + id +
                " | Potência: " + potenciaAtual + "%" +
                " | Empuxo atual: " + calcularEmpuxo() + "N" +
                " | Motor: " + (motorLigado ? "LIGADO" : "DESLIGADO");
    }
}