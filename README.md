#  GS01 - Plataforma de Monitoramento Espacial

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![OOP](https://img.shields.io/badge/POO-Orientado%20a%20Objetos-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Concluído-green?style=for-the-badge)

##  Sobre o Projeto

Sistema de monitoramento de uma estação espacial desenvolvido em Java, aplicando os quatro pilares da **Programação Orientada a Objetos (POO)**. O sistema monitora sensores ambientais, controla sistemas de propulsão e gerencia dados sigilosos da missão — tudo via menu interativo no terminal.

---

##  Integrantes do Grupo

| Nome | RM |
|---|---|
| Patrick Mansour | RM 562970 |
| Pietro Mauer | RM 564345 |
| Samir Assad | RM 561562 |

**Turma:** 2CCPX

**Disciplina:** Programação Orientada a Objetos  
**Instituição:** FIAP  
**Ano:** 2026

---

##  Estrutura do Projeto

```
src/
└── plataforma/
    ├── ComponenteEspacial.java    ← Classe Abstrata base
    ├── Sensor.java                ← Interface dos sensores
    ├── SensorTemperatura.java     ← Implementa Sensor
    ├── SensorPressao.java         ← Implementa Sensor
    ├── SensorRadiacao.java        ← Implementa Sensor
    ├── DadosMissao.java           ← Encapsulamento
    ├── SistemaPropulsao.java      ← Classe Abstrata de propulsão
    ├── PropulsaoQuimica.java      ← Herda de SistemaPropulsao
    ├── PropulsaoEletrica.java     ← Herda de SistemaPropulsao
    └── SistemaMonitoramento.java  ← Classe principal com menu
```

---

##  Conceitos de POO Aplicados

### 1.  Classe Abstrata — `ComponenteEspacial`
Serve como "molde" para todos os componentes da estação. Não pode ser instanciada diretamente.
- Define atributos comuns: `id`, `nome`, `status`, `temperatura`
- Métodos concretos: `ligar()` e `desligar()`
- Método abstrato: `exibirDiagnostico()` — obriga cada subclasse a implementar do seu jeito

### 2.  Interface — `Sensor`
Define o "contrato" que todo sensor deve seguir.
- Métodos obrigatórios: `lerValor()`, `verificarFuncionamento()`, `retornarTipo()`, `setLimiteAlerta()`, `verificarAlerta()`
- Implementada por: `SensorTemperatura`, `SensorPressao` e `SensorRadiacao`

### 3.  Encapsulamento — `DadosMissao`
Protege os dados sensíveis da missão.
- Todos os atributos são **privados**
- Coordenadas protegidas por **senha**
- Getters e Setters com **validação** de dados
- Alerta automático quando combustível está abaixo de 20%

### 4.  Herança — `SistemaPropulsao`
Reutilização de código entre os tipos de propulsão.
- `PropulsaoQuimica` — empuxo de 50.000N, usa Hidrogênio Líquido, aquece muito
- `PropulsaoEletrica` — empuxo de 20.000N, usa bateria, alta eficiência (92%), aquece pouco
- Ambas usam `super()` para chamar métodos da classe mãe
- Cada uma implementa `acelerar()` de forma diferente

---

##  Funcionalidades do Sistema

-  **Leitura de sensores** com valores simulados aleatoriamente
-  **Sistema de alertas** com três níveis: `ATENÇÃO`, `ALERTA` e `CRÍTICO`
-  **Controle de propulsão** com validação de potência (0-100%)
-  **Dados protegidos** por senha de acesso
-  **Status completo** de todos os sistemas
-  **Menu interativo** no terminal

---

##  Como Executar

1. Clone o repositório:
```bash
git clone https://github.com/PietroMauerGodoy/GS01-POO-Plataforma-de-Monitoramento-Espacial.git
```

2. Abra o projeto no **IntelliJ IDEA**

3. Execute a classe `SistemaMonitoramento.java`

4. Navegue pelo menu interativo no terminal

> **Dica:** A senha padrão para acessar as coordenadas é `nasa2026`

---

##  Preview do Sistema

```
 ======================================
   BEM-VINDO À PLATAFORMA ESPACIAL GS01
   ======================================

╔══════════════════════════════╗
║       MENU PRINCIPAL         ║
╠══════════════════════════════╣
║  1. Verificar Sensores       ║
║  2. Controlar Propulsão      ║
║  3. Dados da Missão          ║
║  4. Simular Alertas          ║
║  5. Status Completo          ║
║  0. Sair                     ║
╚══════════════════════════════╝
```

---

##  Licença

Projeto acadêmico desenvolvido para a Global Solution 2026 — FIAP.
