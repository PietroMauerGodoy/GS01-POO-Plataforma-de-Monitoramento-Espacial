package plataforma;

import java.util.Scanner;

/**
 * CLASSE PRINCIPAL - SistemaMonitoramento
 * Menu interativo que integra todos os sistemas da estação espacial.
 */
public class SistemaMonitoramento {

    // Instâncias de todos os sistemas
    private static SensorTemperatura sensorTemp = new SensorTemperatura("S001", "Sensor Temperatura");
    private static SensorPressao sensorPressao = new SensorPressao("S002", "Sensor Pressão");
    private static SensorRadiacao sensorRadiacao = new SensorRadiacao("S003", "Sensor Radiação");
    private static PropulsaoQuimica propQuimica = new PropulsaoQuimica("P001", "Propulsão Química");
    private static PropulsaoEletrica propEletrica = new PropulsaoEletrica("P002", "Propulsão Elétrica");
    private static DadosMissao missao = new DadosMissao("Missão Apollo-X", 7, "nasa2026");
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // Liga todos os sensores ao iniciar
        sensorTemp.ligar();
        sensorPressao.ligar();
        sensorRadiacao.ligar();

        System.out.println("\n ======================================");
        System.out.println("   BEM-VINDO À PLATAFORMA ESPACIAL GS01");
        System.out.println("   ======================================\n");

        int opcao = -1;
        while (opcao != 0) {
            exibirMenuPrincipal();
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> menuSensores();
                case 2 -> menuPropulsao();
                case 3 -> menuDadosMissao();
                case 4 -> simularAlertas();
                case 5 -> exibirStatusCompleto();
                case 0 -> System.out.println("\n Encerrando sistema. Boa viagem!\n");
                default -> System.out.println(" Opção inválida!");
            }
        }
        scanner.close();
    }

    // ---- MENU PRINCIPAL ----

    private static void exibirMenuPrincipal() {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║       MENU PRINCIPAL         ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║  1. Verificar Sensores       ║");
        System.out.println("║  2. Controlar Propulsão      ║");
        System.out.println("║  3. Dados da Missão          ║");
        System.out.println("║  4. Simular Alertas          ║");
        System.out.println("║  5. Status Completo          ║");
        System.out.println("║  0. Sair                     ║");
        System.out.println("╚══════════════════════════════╝");
    }

    // ---- MENU SENSORES ----

    private static void menuSensores() {
        System.out.println("\n===== SISTEMA DE SENSORES =====");
        System.out.println("1. Ler todos os sensores");
        System.out.println("2. Verificar funcionamento");
        System.out.println("3. Definir limite de alerta");
        System.out.println("0. Voltar");

        int opcao = lerInteiro("Escolha: ");

        switch (opcao) {
            case 1 -> lerTodosSensores();
            case 2 -> verificarFuncionamento();
            case 3 -> definirLimiteAlerta();
            case 0 -> {}
            default -> System.out.println("⚠ Opção inválida!");
        }
    }

    private static void lerTodosSensores() {
        System.out.println("\n Lendo sensores...");
        double temp = sensorTemp.lerValor();
        double pressao = sensorPressao.lerValor();
        double radiacao = sensorRadiacao.lerValor();

        System.out.println("  Temperatura : " + temp + " °C");
        System.out.println(" Pressão      : " + pressao + " kPa");
        System.out.println("  Radiação     : " + radiacao + " mSv/h");

        verificarAlertasSensores(temp, pressao, radiacao);
    }

    private static void verificarFuncionamento() {
        System.out.println("\n Verificando funcionamento...");
        System.out.println("Sensor Temperatura : " + (sensorTemp.verificarFuncionamento() ? "✔ OK" : "✘ FALHA"));
        System.out.println("Sensor Pressão     : " + (sensorPressao.verificarFuncionamento() ? "✔ OK" : "✘ FALHA"));
        System.out.println("Sensor Radiação    : " + (sensorRadiacao.verificarFuncionamento() ? "✔ OK" : "✘ FALHA"));
    }

    private static void definirLimiteAlerta() {
        System.out.println("\n1. Temperatura (atual: 80°C)");
        System.out.println("2. Pressão (atual: 120 kPa)");
        System.out.println("3. Radiação (atual: 2 mSv/h)");
        int opcao = lerInteiro("Qual sensor? ");
        double limite = lerDouble("Novo limite: ");

        switch (opcao) {
            case 1 -> { sensorTemp.setLimiteAlerta(limite); System.out.println("✔ Limite de temperatura atualizado!"); }
            case 2 -> { sensorPressao.setLimiteAlerta(limite); System.out.println("✔ Limite de pressão atualizado!"); }
            case 3 -> { sensorRadiacao.setLimiteAlerta(limite); System.out.println("✔ Limite de radiação atualizado!"); }
            default -> System.out.println("⚠ Opção inválida!");
        }
    }

    // ---- MENU PROPULSÃO ----

    private static void menuPropulsao() {
        System.out.println("\n===== SISTEMA DE PROPULSÃO =====");
        System.out.println("1. Ligar motor Químico");
        System.out.println("2. Ligar motor Elétrico");
        System.out.println("3. Acelerar motor Químico");
        System.out.println("4. Acelerar motor Elétrico");
        System.out.println("5. Desligar motores");
        System.out.println("0. Voltar");

        int opcao = lerInteiro("Escolha: ");

        switch (opcao) {
            case 1 -> propQuimica.ligarMotor();
            case 2 -> propEletrica.ligarMotor();
            case 3 -> {
                double pot = lerDouble("Potência (0-100): ");
                propQuimica.acelerar(pot);
            }
            case 4 -> {
                double pot = lerDouble("Potência (0-100): ");
                propEletrica.acelerar(pot);
            }
            case 5 -> {
                propQuimica.desligarMotor();
                propEletrica.desligarMotor();
            }
            case 0 -> {}
            default -> System.out.println("⚠ Opção inválida!");
        }
    }

    // ---- MENU DADOS DA MISSÃO ----

    private static void menuDadosMissao() {
        System.out.println("\n===== DADOS DA MISSÃO =====");
        System.out.println("1. Exibir resumo");
        System.out.println("2. Ver coordenadas (requer senha)");
        System.out.println("3. Atualizar combustível");
        System.out.println("4. Atualizar trajetória");
        System.out.println("0. Voltar");

        int opcao = lerInteiro("Escolha: ");

        switch (opcao) {
            case 1 -> missao.exibirResumo();
            case 2 -> {
                System.out.print("Digite a senha: ");
                String senha = scanner.nextLine();
                System.out.println(missao.getCoordenadas(senha));
            }
            case 3 -> {
                double nivel = lerDouble("Novo nível de combustível (0-100): ");
                missao.setNivelCombustivel(nivel);
            }
            case 4 -> {
                System.out.print("Nova trajetória: ");
                String traj = scanner.nextLine();
                missao.setTrajetoria(traj);
            }
            case 0 -> {}
            default -> System.out.println(" Opção inválida!");
        }
    }

    // ---- SIMULAR ALERTAS ----

    private static void simularAlertas() {
        System.out.println("\n Simulando alertas críticos...");
        sensorTemp.setLimiteAlerta(10.0);
        sensorPressao.setLimiteAlerta(10.0);
        sensorRadiacao.setLimiteAlerta(0.1);
        lerTodosSensores();
        // Restaura limites normais
        sensorTemp.setLimiteAlerta(80.0);
        sensorPressao.setLimiteAlerta(120.0);
        sensorRadiacao.setLimiteAlerta(2.0);
    }

    // ---- STATUS COMPLETO ----

    private static void exibirStatusCompleto() {
        System.out.println("\n========== STATUS COMPLETO ==========");
        System.out.println(sensorTemp.exibirDiagnostico());
        System.out.println(sensorPressao.exibirDiagnostico());
        System.out.println(sensorRadiacao.exibirDiagnostico());
        System.out.println(propQuimica.exibirDiagnostico());
        System.out.println(propEletrica.exibirDiagnostico());
        missao.exibirResumo();
        System.out.println("=====================================");
    }

    // ---- VERIFICAR ALERTAS DOS SENSORES ----

    private static void verificarAlertasSensores(double temp, double pressao, double radiacao) {
        System.out.println("\n Verificando alertas...");

        if (sensorTemp.verificarAlerta()) {
            System.out.println(" [CRÍTICO] Temperatura acima do limite! " + temp + "°C");
        }
        if (sensorPressao.verificarAlerta()) {
            System.out.println(" [CRÍTICO] Pressão acima do limite! " + pressao + " kPa");
        }
        if (sensorRadiacao.verificarAlerta()) {
            System.out.println(" [CRÍTICO] Radiação acima do limite! " + radiacao + " mSv/h");
        }
        if (!sensorTemp.verificarAlerta() && !sensorPressao.verificarAlerta() && !sensorRadiacao.verificarAlerta()) {
            System.out.println("✔ Todos os sensores dentro dos limites normais.");
        }
    }

    // ---- HELPERS ----

    private static int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextInt()) {
            System.out.print(" Digite um número válido: ");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    private static double lerDouble(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextDouble()) {
            System.out.print(" Digite um número válido: ");
            scanner.next();
        }
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }
}