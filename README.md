# API Reservas Pádel

Backend en Spring Boot para la gestión de reservas de pistas de pádel.

## 📌 Descripción
Aplicación que permite gestionar reservas de pistas de pádel con control de horarios, usuarios y pistas disponibles.

## ⚙️ Tecnologías
- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Spring Security
- JWT (JSON Web Tokens)
- Lombok

## 🎾 Funcionalidades actuales
- Gestión de usuarios
- Gestión de pistas (A, B, C, D)
- Crear reservas
- Consultar reservas por usuario
- Cancelar reservas
- Control de disponibilidad por horario

## 🕒 Horarios disponibles
- 17:30 - 19:00
- 19:00 - 20:30
- 20:30 - 22:00
- 22:00 - 23:30

## 🚧 Estado del proyecto
Backend funcional con autenticación JWT implementada.

## 🔐 Autenticación JWT
La API utiliza autenticación basada en tokens JWT para proteger los endpoints.

### Endpoints públicos
- `POST /api/auth/register` - Registro de nuevos usuarios
- `POST /api/auth/login` - Inicio de sesión

### Uso de tokens
Para acceder a endpoints protegidos, incluye el token en el header:
```
Authorization: Bearer <token>
```

### Roles
- **USER**: Usuario estándar (por defecto)
- **ADMIN**: Administrador (permisos extendidos)
