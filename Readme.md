# 🔌 Projeto Arduino - Controle de LED via Web

Sistema web desenvolvido em Java com Spring Boot para controlar LEDs conectados ao Arduino através de comunicação serial.

## 📋 Descrição

Este projeto permite controlar LEDs conectados ao Arduino através de uma interface web intuitiva. A aplicação utiliza comunicação serial para enviar comandos ao Arduino, proporcionando uma forma simples e eficaz de controlar dispositivos físicos via browser.

## 🛠️ Tecnologias Utilizadas

- **Java 17** - Linguagem de programação principal
- **Spring Boot 3.5.0** - Framework para desenvolvimento web
- **Spring Web** - Para criação de APIs REST
- **Thymeleaf** - Motor de templates para páginas web
- **Lombok** - Redução de código boilerplate
- **GNU IO (RXTX)** - Biblioteca para comunicação serial
- **Maven** - Gerenciamento de dependências
- **HTML/CSS/JavaScript** - Interface web responsiva

## 🏗️ Arquitetura do Projeto

```
src/main/java/com/thiago/arduino/
├── ArduinoApplication.java          # Classe principal Spring Boot
├── ControlePorta.java              # Classe para comunicação serial
├── controller/
│   └── ArduinoController.java      # Controller REST para endpoints
├── model/
│   └── Arduino.java                # Modelo de dados do Arduino
└── service/
    └── ArduinoService.java         # Serviço para lógica de negócio
```

## 📡 Funcionalidades

### 🌐 Interface Web
- **Página Principal**: Interface clean com botões para ligar/desligar LED
- **Controle em Tempo Real**: Feedback visual do estado atual do LED
- **Design Responsivo**: Interface adaptada para diferentes dispositivos

### 🔧 API REST
- `GET /led` - Página principal de controle
- `GET /led/ligar` - Liga o LED conectado ao Arduino
- `GET /led/desligar` - Desliga o LED conectado ao Arduino

### 🔌 Comunicação Serial
- **Configuração Automática**: Detecção e configuração da porta COM
- **Taxa de Transferência**: 9600 baud rate (padrão Arduino)
- **Controle de Erros**: Tratamento de exceções para portas não encontradas
- **Gerenciamento de Recursos**: Fechamento automático das conexões

## 🚀 Como Executar

### Pré-requisitos

1. **Java 17** ou superior instalado
2. **Maven** para gerenciamento de dependências
3. **Arduino** conectado via USB
4. **Driver da porta serial** instalado no sistema

### Configuração do Arduino

```cpp
// Código Arduino básico (exemplo)
int ledPin = 13;

void setup() {
  Serial.begin(9600);
  pinMode(ledPin, OUTPUT);
}

void loop() {
  if (Serial.available()) {
    char comando = Serial.read();
    
    if (comando == '1') {
      digitalWrite(ledPin, HIGH); // Liga LED
    } else if (comando == '2') {
      digitalWrite(ledPin, LOW);  // Desliga LED
    }
  }
}
```

### Executando a Aplicação

1. **Clone o repositório**:
   ```bash
   git clone <url-do-repositorio>
   cd arduino-projeto-avaliacao
   ```

2. **Configure a porta COM**:
   - Identifique a porta COM do seu Arduino (ex: COM3, COM4, COM5)
   - Edite o arquivo `ArduinoController.java` e altere "COM5" para sua porta

3. **Execute a aplicação**:
   ```bash
   cd arduino
   ./mvnw spring-boot:run
   ```
   
   Ou no Windows:
   ```cmd
   cd arduino
   mvnw.cmd spring-boot:run
   ```

4. **Acesse a aplicação**:
   - Abra o browser em: `http://localhost:8080/led`

## 🔧 Configuração da Porta Serial

Para configurar a porta COM correta, edite os arquivos:

**ArduinoController.java**:
```java
controlePorta = new ControlePorta("COM5", 9600); // Altere COM5 para sua porta
```

**ArduinoService.java**:
```java
private final String portaCOM = "COM2"; // Altere COM2 para sua porta
```

## 📁 Estrutura de Arquivos

```
arduino-projeto-avaliacao/
├── Readme.md
└── arduino/
    ├── pom.xml                     # Configurações Maven
    ├── mvnw / mvnw.cmd            # Maven Wrapper
    ├── src/
    │   ├── main/
    │   │   ├── java/               # Código Java
    │   │   └── resources/
    │   │       ├── application.properties
    │   │       └── templates/
    │   │           └── index.html  # Interface web
    │   └── test/                   # Testes unitários
    └── target/                     # Arquivos compilados
```

## 🎯 Comandos de Controle

| Comando | Ação | Endpoint |
|---------|------|----------|
| `1` | Liga o LED | `/led/ligar` |
| `2` | Desliga o LED | `/led/desligar` |

## 🔍 Solução de Problemas

### Porta COM não encontrada
- Verifique se o Arduino está conectado
- Confirme a porta COM no Gerenciador de Dispositivos (Windows)
- Instale os drivers do Arduino se necessário

### Erro de porta em uso
- Feche outras aplicações que possam estar usando a porta serial
- Desconecte e reconecte o Arduino

## 👥 Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

---

⭐ **Dica**: Certifique-se de que o Arduino esteja programado para receber os comandos '1' (ligar) e '2' (desligar) via porta serial!