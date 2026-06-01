package plataforma;

/**
 * ENCAPSULAMENTO - DadosMissao
 * Todos os atributos são PRIVADOS.
 * Dados sensíveis são protegidos por senha.
 * Getters e Setters fazem validação antes de alterar qualquer valor.
 */
public class DadosMissao {

    // Atributos privados - ninguém acessa direto
    private String nomeMissao;
    private double coordenadaX;
    private double coordenadaY;
    private double coordenadaZ;
    private double nivelCombustivel;  // 0 a 100%
    private String trajetoria;
    private int numeroDeTripulantes;
    private String codigoAcesso;      // senha para dados restritos

    // Construtor
    public DadosMissao(String nomeMissao, int numeroDeTripulantes, String codigoAcesso) {
        this.nomeMissao = nomeMissao;
        this.numeroDeTripulantes = numeroDeTripulantes;
        this.codigoAcesso = codigoAcesso;
        this.nivelCombustivel = 100.0;
        this.trajetoria = "Não definida";
        this.coordenadaX = 0;
        this.coordenadaY = 0;
        this.coordenadaZ = 0;
    }

    // ---- Getters simples (dados públicos) ----

    public String getNomeMissao() { return nomeMissao; }
    public int getNumeroDeTripulantes() { return numeroDeTripulantes; }
    public String getTrajetoria() { return trajetoria; }
    public double getNivelCombustivel() { return nivelCombustivel; }

    // ---- Getters PROTEGIDOS por senha (dados sensíveis) ----

    public String getCoordenadas(String senha) {
        if (senha.equals(codigoAcesso)) {
            return "X: " + coordenadaX + " | Y: " + coordenadaY + " | Z: " + coordenadaZ;
        } else {
            return "⚠ Acesso negado! Senha incorreta.";
        }
    }

    // ---- Setters com validação ----

    public void setNivelCombustivel(double nivel) {
        if (nivel < 0 || nivel > 100) {
            System.out.println("⚠ Nível de combustível inválido! Use valores entre 0 e 100.");
        } else {
            this.nivelCombustivel = nivel;
            verificarCombustivel();
        }
    }

    public void setCoordenadas(double x, double y, double z, String senha) {
        if (senha.equals(codigoAcesso)) {
            this.coordenadaX = x;
            this.coordenadaY = y;
            this.coordenadaZ = z;
            System.out.println(" Coordenadas atualizadas com sucesso!");
        } else {
            System.out.println(" Acesso negado! Senha incorreta.");
        }
    }

    public void setTrajetoria(String trajetoria) {
        if (trajetoria == null || trajetoria.isEmpty()) {
            System.out.println(" Trajetória inválida!");
        } else {
            this.trajetoria = trajetoria;
            System.out.println(" Trajetória atualizada: " + trajetoria);
        }
    }

    public void setNumeroDeTripulantes(int numero) {
        if (numero < 0) {
            System.out.println(" Número de tripulantes não pode ser negativo!");
        } else {
            this.numeroDeTripulantes = numero;
        }
    }

    // ---- Alerta automático de combustível ----

    private void verificarCombustivel() {
        if (nivelCombustivel < 10) {
            System.out.println(" [CRÍTICO] Combustível em " + nivelCombustivel + "%! Retorno imediato necessário!");
        } else if (nivelCombustivel < 20) {
            System.out.println(" [ALERTA] Combustível baixo: " + nivelCombustivel + "%!");
        } else if (nivelCombustivel < 30) {
            System.out.println(" [ATENÇÃO] Combustível em " + nivelCombustivel + "%.");
        }
    }

    // ---- Exibir resumo da missão ----

    public void exibirResumo() {
        System.out.println("\n===== DADOS DA MISSÃO =====");
        System.out.println("Missão         : " + nomeMissao);
        System.out.println("Tripulantes    : " + numeroDeTripulantes);
        System.out.println("Trajetória     : " + trajetoria);
        System.out.println("Combustível    : " + nivelCombustivel + "%");
        System.out.println("Coordenadas    : [PROTEGIDAS - requer senha]");
        System.out.println("===========================\n");
    }
}