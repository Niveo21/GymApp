# 🏋️ GymApp

Backend de una aplicación de gimnasio construido con **arquitectura de microservicios** en Spring Boot. Permite registrar usuarios, llevar un historial de sesiones de entrenamiento y ejercicios, controlar la alimentación diaria (calorías y macronutrientes), chatear entre usuarios en tiempo real y consultar a un asistente con IA.

---

## 📦 Microservicios

| Servicio | Puerto | Base de datos | Responsabilidad |
|---|---|---|---|
| **Usuario** | `8080` | `gymapp-usuario-db` | Registro/login, sesiones de entrenamiento, ejercicios, chat en tiempo real y chatbot IA |
| **Nutricion** | `8081` | `gymapp-nutricion-db` | Perfil físico del usuario y registro diario de alimentos |

Cada microservicio tiene su propia base de datos MySQL. Ambos se relacionan a través del **email** del usuario.

---

## 🛠️ Tecnologías

- Java 17
- Spring Boot 3.5
- Spring Web · Spring Data JPA · Spring WebSocket (STOMP + SockJS)
- MySQL (`mysql-connector-j`)
- Maven (incluye Maven Wrapper)
- [OpenRouter](https://openrouter.ai/) para el chatbot (modelo `openai/gpt-oss-20b:free`)

---

## 📁 Estructura del proyecto

```
GymApp/
├── Usuario/
│   └── src/main/java/com/example/Usuario/
│       ├── config/         # Configuración WebSocket
│       ├── controller/     # Registro, Sesiones, Ejercicios, Mensajes, Chatbot
│       ├── model/          # Registro, SesionEntrenamiento, Ejercicio, Mensaje
│       ├── repository/
│       └── service/
└── Nutricion/
    └── src/main/java/com/example/Nutricion/
        ├── controller/     # Perfil, Alimentacion
        ├── model/          # Perfil, Alimentacion
        ├── repository/
        └── service/
```

---

## ✅ Requisitos previos

- JDK 17 o superior
- MySQL 8 corriendo en `localhost:3307` (por ejemplo con XAMPP o Docker)
- Una API key de [OpenRouter](https://openrouter.ai/keys) (solo para el chatbot)

---

## 🚀 Instalación y ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/Niveo21/GymApp.git
cd GymApp
```

### 2. Crear las bases de datos

```sql
CREATE DATABASE `gymapp-usuario-db`;
CREATE DATABASE `gymapp-nutricion-db`;
```

### 3. Configurar la API key del chatbot

En `Usuario/src/main/resources/application.properties`:

```properties
openrouter.api.key=${OPENROUTER_API_KEY}
```

Y define la variable de entorno antes de levantar el servicio:

```bash
# Linux / macOS
export OPENROUTER_API_KEY=tu_api_key

# Windows (PowerShell)
$env:OPENROUTER_API_KEY="tu_api_key"
```

> Si tu MySQL usa otro puerto, usuario o contraseña, ajusta `spring.datasource.*` en el `application.properties` de cada servicio.

### 4. Levantar los microservicios

En dos terminales distintas:

```bash
# Terminal 1
cd Usuario
./mvnw spring-boot:run        # Windows: mvnw.cmd spring-boot:run
```

```bash
# Terminal 2
cd Nutricion
./mvnw spring-boot:run        # Windows: mvnw.cmd spring-boot:run
```

---

## 🔌 Endpoints

### Servicio Usuario (`http://localhost:8080`)

#### Registro y autenticación
| Método | Endpoint | Descripción |
|---|---|---|
| POST | `/registro` | Registra un usuario (`name`, `email`, `password` ≥ 6 caracteres) |
| POST | `/registro/login` | Inicia sesión con `email` y `password` |
| GET | `/registro/existe/{email}` | Verifica si un usuario existe |
| GET | `/registro/nombre/{email}` | Obtiene el nombre del usuario |

#### Sesiones de entrenamiento
| Método | Endpoint | Descripción |
|---|---|---|
| POST |
