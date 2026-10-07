# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Fundamentos de las pruebas de seguridad en proyectos de automatización**.

| | |
|---|---|
| Tema | Fundamentos de las pruebas de seguridad |
| Nivel | advanced-l2 |
| Chapter | Calidad de Software |
| Especialidad | Automatizador |
| Stack | Java / Serenity BDD con Cucumber |
| Patron arquitectonico | Page Object Model con capas de seguridad y pruebas |
| Tiempo estimado | 3 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz con el runner y el plugin de reportes`
- `src/test/resources/features con los .feature en Gherkin`
- `src/test/java/.../runners con el runner`
- `src/test/java/.../pages o /tasks con Page Objects o Screenplay`
- `src/test/java/.../steps con los step definitions`
- `serenity.conf o config del entorno`

Trampas conocidas:

- Sin parent POM que gestione versiones, TODA dependencia lleva su `<version>` completa de tres segmentos.
- El groupId de Serenity es `net.serenity-bdd`, NO `org.serenity-bdd`. Con el groupId equivocado el artefacto no existe y el build muere resolviendo dependencias.
- Coordenadas exactas de lo mas usado: Selenium `org.seleniumhq.selenium:selenium-java`, Rest Assured `io.rest-assured:rest-assured`, Karate `com.intuit.karate:karate-junit5`, Cucumber `io.cucumber:cucumber-java`.
- JUnit 5 se declara con `junit-jupiter` (agregador) y necesita `maven-surefire-plugin` reciente para ejecutarse.
- Serenity y Cucumber tienen que ser de lineas compatibles entre si; mezclarlas rompe el runner.

Dependencias:

- net.serenity-bdd:serenity-core 4.0.48
- net.serenity-bdd:serenity-cucumber 4.0.48
- io.cucumber:cucumber-java 7.14.0
- org.seleniumhq.selenium:selenium-java 4.18.1
- io.rest-assured:rest-assured 5.3.2
- org.owasp:zap-clientapi 1.11.0
- io.jsonwebtoken:jjwt-api 0.11.5
- io.jsonwebtoken:jjwt-impl 0.11.5
- io.jsonwebtoken:jjwt-jackson 0.11.5
- org.junit.jupiter:junit-jupiter 5.10.0
- org.apache.maven.plugins:maven-surefire-plugin 3.2.5
- net.serenity-bdd:serenity-maven-plugin 4.0.48

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean test-compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean test-compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Evaluación de riesgos y gestión**: Documento que describe las áreas de riesgo identificadas y las estrategias de gestión propuestas.
- **Fase 2 — Identificación de vulnerabilidades**: Lista de vulnerabilidades comunes y descripción de cómo podrían ser explotadas.
- **Fase 3 — Técnicas de inyección SQL**: Documento que describe las técnicas de inyección SQL, ejemplos de su aplicación y estrategias de prevención.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `src/test/java/com/pragma/payments/security/SecurityConfig.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- [ ] `src/test/java/com/pragma/payments/security/JwtUtils.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- [ ] `src/test/java/com/pragma/payments/security/SecuritySteps.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- [ ] `src/test/resources/features/security/sql_injection.feature` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- [ ] `src/test/resources/features/security/authentication.feature` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- [ ] `src/test/java/com/pragma/payments/runners/RunSecurityTests.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.

## Lo que falta y tenes que completar

### 1. Archivos que la arquitectura declara (3 de 12)

La propuesta arquitectonica del reto los lista y no llegaron al repo. Crealos con implementacion real, respetando la capa en la que viven:

- [ ] `RunCucumberTest.java`
- [ ] `src/test/resources/features/security/authentication.feature`
- [ ] `src/test/java/com/pragma/payments/runners/RunSecurityTests.java`

### 2. Referencias colgando (6)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/test/java/com/pragma/payments/security/SecurityConfig.java` — `org.springframework.stereotype`
      El import org.springframework.stereotype.Component pertenece a org.springframework.stereotype, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/pragma/payments/security/JwtUtils.java` — `org.springframework.stereotype`
      El import org.springframework.stereotype.Component pertenece a org.springframework.stereotype, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/pragma/payments/security/SecuritySteps.java` — `org.springframework.beans`
      El import org.springframework.beans.factory.annotation.Autowired pertenece a org.springframework.beans, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/pragma/payments/security/SecuritySteps.java` — `org.hamcrest.Matchers`
      El import org.hamcrest.Matchers pertenece a org.hamcrest.Matchers, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `pom.xml` — `org.owasp:zap-clientapi@1.11.0`
      org.owasp:zap-clientapi declara la version 1.11.0, pero Maven Central respondio que esa version no existe. Es una version inventada: reemplazala por una version publicada real, o si no se conoce con certeza, usa el mecanismo centralizado del ecosistema (BOM/parent/platform/version catalog) y no declares una version individual.
- [ ] `pom.xml` — `net.serenity-bdd:serenity-bom@4.0.48`
      net.serenity-bdd:serenity-bom declara la version 4.0.48, pero Maven Central respondio que esa version no existe. Es una version inventada: reemplazala por una version publicada real, o si no se conoce con certeza, usa el mecanismo centralizado del ecosistema (BOM/parent/platform/version catalog) y no declares una version individual.

### Presentes (10)

- `pom.xml`
- `src/test/java/com/pragma/payments/runners/RunCucumberTest.java`
- `README.md`
- `src/test/java/com/pragma/payments/security/SecurityConfig.java`
- `src/test/java/com/pragma/payments/security/JwtUtils.java`
- `src/test/java/com/pragma/payments/security/SecuritySteps.java`
- `src/test/resources/features/security/sql_injection.feature`
- `src/test/resources/config/serenity.conf`
- `src/test/java/com/pragma/payments/pages/PaymentPage.java`
- `src/test/java/com/pragma/payments/steps/PaymentSteps.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/test/java/com/pragma/payments/security`
- `src/test/java/com/pragma/payments/pages`
- `src/test/java/com/pragma/payments/steps`
- `src/test/java/com/pragma/payments/runners`
- `src/test/resources/features`
- `src/test/resources/config`
- `src/test/resources/data`

## Verificacion

```bash
mvn clean test-compile
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **Page Object Model con capas de seguridad y pruebas**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Calidad de Software, Especialidad Automatizador, Advanced
- Brecha que el reto ataca: Comprende conceptos relacionados con las pruebas de seguridad, como: evaluación de riesgos y su gestión, la identificación de vulnerabilidades, las técnicas de inyección SQL, la autenticación, la autorización y la criptografía aplicados en proyectos de manera efectiva
- Mision: Candidato con experiencia en automatización de calidad

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
