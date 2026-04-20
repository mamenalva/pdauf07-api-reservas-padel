# AGENTS.md - API Reservas Pádel

## 📋 Descripción del Proyecto

Este documento describe las especificaciones técnicas y reglas para el desarrollo de la API de Reservas de Pádel, un backend en Spring Boot para la gestión de reservas de pistas de pádel. Está diseñado para guiar a agentes de IA en la generación de código correcto y consistente con la arquitectura del proyecto.

## ⚙️ Tecnologías del Proyecto

- **Java 21**: Lenguaje de programación principal
- **Spring Boot 4.0.5**: Framework para desarrollo de aplicaciones web
- **Spring Data JPA**: Para persistencia de datos y mapeo objeto-relacional
- **MySQL**: Base de datos relacional
- **Spring Security**: Para autenticación y autorización
- **JWT (JSON Web Tokens)**: Para autenticación stateless
- **JJWT**: Librería para manejo de tokens JWT (versión 0.12.5)
- **Lombok**: Para reducción de código boilerplate
- **Maven**: Gestión de dependencias y construcción

## 🏗️ Arquitectura por Capas

El proyecto sigue una arquitectura por capas clara y modular:

### 1. **Capa de Controladores (Controllers)**
- Ubicación: `src/main/java/mcan/reservaspadel/controllers/`
- Responsabilidad: Manejo de requests HTTP, validación de entrada, respuesta HTTP
- Anotaciones: `@RestController`, `@RequestMapping`, `@CrossOrigin`
- Seguridad: `@PreAuthorize` para endpoints protegidos

### 2. **Capa de Servicios (Services)**
- Ubicación: `src/main/java/mcan/reservaspadel/services/`
- Responsabilidad: Lógica de negocio, validaciones, transformación de datos
- Patrón: Interface + Implementación (ej: `AuthService` + `AuthServiceImpl`)
- Inyección: `@Service`, `@Autowired` o constructor injection

### 3. **Capa de Repositorios (Repositories)**
- Ubicación: `src/main/java/mcan/reservaspadel/repositories/`
- Responsabilidad: Acceso a datos, consultas JPA
- Extiende: `JpaRepository<Entity, ID>`
- Métodos: Queries derivadas, `@Query` para consultas complejas

### 4. **Capa de DTOs (Data Transfer Objects)**
- Ubicación: `src/main/java/mcan/reservaspadel/dto/`
- Responsabilidad: Transferencia de datos entre capas
- Uso: Request/Response objects, nunca exponer entidades directamente
- Anotaciones: `@Getter`, `@Setter` (Lombok)

### 5. **Capa de Entidades (Entities)**
- Ubicación: `src/main/java/mcan/reservaspadel/entities/`
- Responsabilidad: Representación de tablas de base de datos
- Anotaciones: `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, etc.
- Relaciones: `@OneToMany`, `@ManyToOne` según sea necesario

### 6. **Capa de Seguridad (Security)**
- Ubicación: `src/main/java/mcan/reservaspadel/config/`
- Componentes: `SecurityConfig`, `JwtUtils`, `JwtAuthenticationFilter`
- Responsabilidad: Configuración de autenticación JWT, filtros, permisos

### 7. **Capa de Excepciones (Exception)**
- Ubicación: `src/main/java/mcan/reservaspadel/exception/`
- Responsabilidad: Manejo global de excepciones
- Anotaciones: `@ControllerAdvice`, `@ExceptionHandler`

## 📏 Reglas del Proyecto

### Principios Generales
- **Uso obligatorio de DTOs**: Nunca exponer entidades directamente en controladores
- **Autenticación JWT obligatoria**: Todos los endpoints (excepto login/register) requieren token válido
- **Usuario desde token**: Extraer información del usuario autenticado del contexto de seguridad
- **Validación de entrada**: Usar `@Valid` y anotaciones de validación en DTOs
- **Manejo de errores**: Lanzar `RuntimeException` con mensajes descriptivos, capturar en `GlobalExceptionHandler`

### Seguridad
- **Endpoints públicos**: Solo `/api/auth/register` y `/api/auth/login`
- **Roles**: USER (por defecto), ADMIN
- **Autorización**: `@PreAuthorize("isAuthenticated()")` mínimo, roles específicos si necesario
- **Password encoding**: BCrypt obligatorio en registro y validación

### Estructura de Código
- **Paquete base**: `mcan.reservaspadel`
- **Nombres de clases**: PascalCase, descriptivos
- **Nombres de métodos**: camelCase, verbos de acción
- **Inyección de dependencias**: Constructor injection preferido
- **Lombok**: Usar `@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor`

## 🗂️ Entidades Principales

### Usuario
- **Campos**: id (Long), nombre (String), apellidos (String), telefono (String), email (String, unique), password (String), role (Role enum)
- **Relaciones**: OneToMany con Reserva
- **Validaciones**: Email único, password encriptada

### Pista
- **Campos**: id (Long), nombre (String)
- **Relaciones**: OneToMany con Reserva
- **Notas**: Pistas fijas: A, B, C, D

### Reserva
- **Campos**: id (Long), fecha (LocalDate), horario (String), usuario (Usuario), pista (Pista)
- **Relaciones**: ManyToOne con Usuario y Pista
- **Validaciones**: No duplicar reserva en mismo horario/pista/fecha

## 📋 Reglas de Negocio

### Horarios
- **Horarios fijos**: 17:30-19:00, 19:00-20:30, 20:30-22:00, 22:00-23:30
- **Validación**: Solo permitir estos horarios exactos

### Reservas
- **No duplicación**: Una pista no puede tener dos reservas en el mismo horario y fecha
- **Propiedad**: Solo el usuario propietario puede cancelar su reserva
- **Disponibilidad**: Verificar antes de crear reserva

### Usuarios
- **Registro**: Email único, password encriptada, role por defecto USER
- **Login**: Validar credenciales, retornar JWT token
- **Roles**: USER para operaciones básicas, ADMIN para gestión avanzada

## 🤖 Buenas Prácticas para IA

### Generación de Código
- **Seguir estructura de paquetes**: Mantener consistencia con el proyecto existente
- **Usar dependencias correctas**: Verificar imports y versiones en `pom.xml`
- **Implementar interfaces**: Para servicios, crear tanto interface como implementación
- **Manejar excepciones**: Lanzar RuntimeException con mensajes claros
- **Validar entrada**: Usar anotaciones de validación en DTOs
- **Seguridad primero**: Aplicar `@PreAuthorize` en todos los endpoints no públicos

### Consultas JPA
- **Métodos derivados**: Usar nombres descriptivos (ej: `findByUsuarioId`)
- **@Query**: Para consultas complejas con JOIN o condiciones específicas
- **Parámetros**: Usar @Param para claridad

### JWT y Seguridad
- **Token extraction**: Usar `SecurityContextHolder` para obtener usuario actual
- **Validación**: Verificar token en filtro antes de procesar request
- **Roles**: Usar `hasRole('ROLE_NAME')` en @PreAuthorize

### Testing
- **Unit tests**: Para servicios y utilidades
- **Integration tests**: Para controladores con @SpringBootTest
- **Mocking**: Usar Mockito para dependencias externas

### Documentación
- **Comentarios**: En español o inglés, descriptivos
- **README**: Mantener actualizado con cambios
- **Commits**: Mensajes claros en español

### Errores Comunes a Evitar
- Exponer entidades en controladores
- Hardcodear valores (usar application.properties)
- Olvidar validaciones de negocio
- No manejar excepciones apropiadamente
- Usar autenticación básica en lugar de JWT

---

**Última actualización**: 2026-04-20
**Versión del proyecto**: 0.0.1-SNAPSHOT

# Personalizado
- Incluye en este archivo los cambios después de cada implementación que te solicite(nuevas entidades, nuevos endpoinst...)
- Implementado sistema completo de autenticación JWT:
  - Dependencias JJWT agregadas al pom.xml
  - JwtUtils implementado con generación, validación y extracción de tokens
  - Campo role agregado a Usuario entity
  - Campo role agregado a RegisterRequestDTO y UsuarioResponseDTO
  - AuthController creado con endpoints /api/auth/register y /api/auth/login
  - @PreAuthorize agregado a ReservaController y PistaController
  - PistaController actualizado para usar PistaResponseDTO
  - ReservaService actualizado para obtener usuario desde SecurityContextHolder
  - Propiedades JWT agregadas a application.properties
