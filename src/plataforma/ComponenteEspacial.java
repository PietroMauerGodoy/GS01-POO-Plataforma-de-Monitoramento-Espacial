package plataforma;

/**
 * CLASSE ABSTRATA - ComponenteEspacial
 * Serve como "molde" para todos os componentes da estação espacial.
 * Não pode ser instanciada diretamente (não dá pra fazer new ComponenteEspacial())
 */
public abstract class ComponenteEspacial {

    // Atributos comuns a todo componente espacial
    protected String id;
    protected String nome;
    protected boolean status;
    protected double temperatura;

    // Construtor
    public ComponenteEspacial(String id, String nome) {
        this.id = id;
        this.nome = nome;
        this.status = false;
        this.temperatura = 20.0;
    }

    // ----Métodos CONCRETOS ----

    public void ligar() {
        this.status = true;
        System.out.println("✔ [" + nome + "] Componente LIGADO.");
    }

    public void desligar() {
        this.status = false;
        System.out.println("✘ [" + nome + "] Componente DESLIGADO.");
    }

    // ---- Getters e Setters ----

    public String getId() { return id; }
    public String getNome() { return nome; }
    public boolean isStatus() { return status; }
    public double getTemperatura() { return temperatura; }

    public void setTemperatura(double temperatura) {
        if (temperatura < -273.15) {
            System.out.println("⚠ Temperatura inválida! Abaixo do zero absoluto.");
        } else {
            this.temperatura = temperatura;
        }
    }

    // ---- Método ABSTRATO ----
    public abstract String exibirDiagnostico();
}