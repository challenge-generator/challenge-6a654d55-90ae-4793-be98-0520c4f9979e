# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/test/java/com/pragma/payments/security/SecurityConfig.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- `src/test/java/com/pragma/payments/security/JwtUtils.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- `src/test/java/com/pragma/payments/security/SecuritySteps.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- `src/test/resources/features/security/sql_injection.feature` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- `src/test/resources/features/security/authentication.feature` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- `src/test/java/com/pragma/payments/runners/RunSecurityTests.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Archivos que la arquitectura del reto declara y no estan

Creálos con implementacion real, en la capa que les corresponde:

- `RunCucumberTest.java`
- `src/test/resources/features/security/authentication.feature`
- `src/test/java/com/pragma/payments/runners/RunSecurityTests.java`

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/test/java/com/pragma/payments/security/SecurityConfig.java` — `org.springframework.stereotype`: El import org.springframework.stereotype.Component pertenece a org.springframework.stereotype, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/payments/security/JwtUtils.java` — `org.springframework.stereotype`: El import org.springframework.stereotype.Component pertenece a org.springframework.stereotype, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/payments/security/SecuritySteps.java` — `org.springframework.beans`: El import org.springframework.beans.factory.annotation.Autowired pertenece a org.springframework.beans, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/payments/security/SecuritySteps.java` — `org.hamcrest.Matchers`: El import org.hamcrest.Matchers pertenece a org.hamcrest.Matchers, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `pom.xml` — `org.owasp:zap-clientapi@1.11.0`: org.owasp:zap-clientapi declara la version 1.11.0, pero Maven Central respondio que esa version no existe. Es una version inventada: reemplazala por una version publicada real, o si no se conoce con certeza, usa el mecanismo centralizado del ecosistema (BOM/parent/platform/version catalog) y no declares una version individual.
- `pom.xml` — `net.serenity-bdd:serenity-bom@4.0.48`: net.serenity-bdd:serenity-bom declara la version 4.0.48, pero Maven Central respondio que esa version no existe. Es una version inventada: reemplazala por una version publicada real, o si no se conoce con certeza, usa el mecanismo centralizado del ecosistema (BOM/parent/platform/version catalog) y no declares una version individual.

## Como saber que terminaste

```bash
mvn clean test-compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Calidad de Software, Especialidad Automatizador, Advanced

### Brecha de conocimiento
Comprende conceptos relacionados con las pruebas de seguridad, como: evaluación de riesgos y su gestión, la identificación de vulnerabilidades, las técnicas de inyección SQL, la autenticación, la autorización y la criptografía aplicados en proyectos de manera efectiva

### Misión / candidato
Candidato con experiencia en automatización de calidad

### Reto
- Tema: Fundamentos de las pruebas de seguridad
- Seniority: advanced-l2
- Tipo: theoretical
- Título: Fundamentos de las pruebas de seguridad en proyectos de automatización
- Tiempo estimado: 3 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Evaluación de riesgos y gestión — objetivo: Comprender y aplicar la evaluación de riesgos en el contexto de un sistema de gestión de pagos. — entregable (NO resolver): Documento que describe las áreas de riesgo identificadas y las estrategias de gestión propuestas.
- Fase 2: Identificación de vulnerabilidades — objetivo: Identificar y comprender las vulnerabilidades comunes en sistemas de gestión de pagos. — entregable (NO resolver): Lista de vulnerabilidades comunes y descripción de cómo podrían ser explotadas.
- Fase 3: Técnicas de inyección SQL — objetivo: Comprender y aplicar técnicas para prevenir inyecciones SQL en el sistema de gestión de pagos. — entregable (NO resolver): Documento que describe las técnicas de inyección SQL, ejemplos de su aplicación y estrategias de prevención.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.pragma</groupId>
    <artifactId>payments-security-tests</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <serenity.version>4.0.48</serenity.version>
        <cucumber.version>7.14.0</cucumber.version>
        <selenium.version>4.18.1</selenium.version>
        <rest-assured.version>5.3.2</rest-assured.version>
        <junit.version>5.10.0</junit.version>
        <zap.version>1.11.0</zap.version>
        <jjwt.version>0.11.5</jjwt.version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>net.serenity-bdd</groupId>
                <artifactId>serenity-bom</artifactId>
                <version>${serenity.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <!-- Serenity BDD -->
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-core</artifactId>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-cucumber</artifactId>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-screenplay</artifactId>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-rest-assured</artifactId>
            <scope>compile</scope>
        </dependency>

        <!-- Cucumber -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>${cucumber.version}</version>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit-platform-engine</artifactId>
            <version>${cucumber.version}</version>
            <scope>test</scope>
        </dependency>

        <!-- Selenium -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>${selenium.version}</version>
            <scope>compile</scope>
        </dependency>

        <!-- REST Assured -->
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>rest-assured</artifactId>
            <version>${rest-assured.version}</version>
            <scope>compile</scope>
        </dependency>

        <!-- OWASP ZAP -->
        <dependency>
            <groupId>org.owasp</groupId>
            <artifactId>zap-clientapi</artifactId>
            <version>${zap.version}</version>
            <scope>compile</scope>
        </dependency>

        <!-- JWT -->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>${jjwt.version}</version>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>${jjwt.version}</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>${jjwt.version}</version>
            <scope>runtime</scope>
        </dependency>

        <!-- JUnit -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.2.5</version>
                <configuration>
                    <includes>
                        <include>**/*Test.java</include>
                        <include>**/*Runner.java</include>
                    </includes>
                    <systemPropertyVariables>
                        <serenity.project.name>Payments Security Tests</serenity.project.name>
                    </systemPropertyVariables>
                </configuration>
            </plugin>
            <plugin>
                <groupId>net.serenity-bdd.maven.plugins</groupId>
                <artifactId>serenity-maven-plugin</artifactId>
                <version>${serenity.version}</version>
                <executions>
                    <execution>
                        <id>serenity-reports</id>
                        <phase>post-integration-test</phase>
                        <goals>
                            <goal>aggregate</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>exec-maven-plugin</artifactId>
                <version>3.1.0</version>
                <executions>
                    <execution>
                        <id>start-zap</id>
                        <phase>pre-integration-test</phase>
                        <goals>
                            <goal>exec</goal>
                        </goals>
                        <configuration>
                            <executable>docker</executable>
                            <arguments>
                                <argument>run</argument>
                                <argument>-d</argument>
                                <argument>-p</argument>
                                <argument>8080:8080</argument>
                                <argument>--name</argument>
                                <argument>zap</argument>
                                <argument>owasp/zap2docker-stable</argument>
                            </arguments>
                        </configuration>
                    </execution>
                    <execution>
                        <id>stop-zap</id>
                        <phase>post-integration-test</phase>
                        <goals>
                            <goal>exec</goal>
                        </goals>
                        <configuration>
                            <executable>docker</executable>
                            <arguments>
                                <argument>stop</argument>
                                <argument>zap</argument>
                            </arguments>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

    <profiles>
        <profile>
            <id>security-tests</id>
            <build>
                <plugins>
                    <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-failsafe-plugin</artifactId>
                        <version>3.2.5</version>
                        <executions>
                            <execution>
                                <goals>
                                    <goal>integration-test</goal>
                                    <goal>verify</goal>
                                </goals>
                            </execution>
                        </executions>
                        <configuration>
                            <includes>
                                <include>**/*Test.java</include>
                                <include>**/*Runner.java</include>
                            </includes>
                            <systemPropertyVariables>
                                <zap.api.url>http://localhost:8080</zap.api.url>
                                <zap.api.key>change-me</zap.api.key>
                            </systemPropertyVariables>
                        </configuration>
                    </plugin>
                </plugins>
            </build>
        </profile>
    </profiles>
</project>

// === ARCHIVO: src/test/java/com/pragma/payments/runners/RunCucumberTest.java ===
package com.pragma.payments.runners;

import io.cucumber.junit.platform.Cucumber;
import org.junit.platform.suite.annotation.ConfigurationParameter;
import org.junit.platform.suite.annotation.IncludeEngines;
import org.junit.platform.suite.engine.SuiteEngine;
import org.junit.platform.suite.engine.SuiteLauncherDescriptor;

import static io.cucumber.junit.platform.engine.Constants.*;

@Cucumber
@IncludeEngines("cucumber")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty,net.serenitybdd.cucumber.merging.SerenityReporter")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.pragma.payments.steps,com.pragma.payments.runners")
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "src/test/resources/features")
@ConfigurationParameter(key = TAGS_PROPERTY_NAME, value = "@security")
@ConfigurationParameter(key = SNIPPET_TYPE_PROPERTY_NAME, value = "CAMELCASE")
public class RunCucumberTest {
    
    private RunCucumberTest() {
    }
}

// === ARCHIVO: README.md ===
# Payments Security Tests

Proyecto de automatización de pruebas de seguridad para el sistema de gestión de pagos en línea de Pragma.

## Requisitos Previos

- Java 21 o superior
- Maven 3.8+ 
- Docker (para OWASP ZAP)
- Navegador Chrome/Firefox para pruebas UI

## Estructura del Proyecto

```
payments-security-tests/
├── pom.xml
├── src/
│   └── test/
│       ├── java/
│       │   └── com/pragma/payments/
│       │       ├── runners/
│       │       │   └── RunCucumberTest.java
│       │       ├── steps/
│       │       │   └── PaymentSteps.java
│       │       ├── pages/
│       │       │   └── PaymentPage.java
│       │       └── security/
│       │           ├── SecurityConfig.java
│       │           ├── JwtUtils.java
│       │           └── SecuritySteps.java
│       ├── resources/
│       │   ├── features/
│       │   │   └── security/
│       │   │       ├── sql_injection.feature
│       │   │       └── authentication.feature
│       │   └── config/
│       │       └── serenity.conf
│       └── docs/
└── README.md
```

## Configuración del Entorno

### 1. Variables de Sistema

Configurar las siguientes variables de entorno:

```bash
export JAVA_HOME=/path/to/jdk-21
export MAVEN_HOME=/path/to/maven-3.9+
```

### 2. Docker para OWASP ZAP

Las pruebas de seguridad requieren OWASP ZAP ejecutándose en Docker:

```bash
docker run -d -p 8080:8080 --name zap owasp/zap2docker-stable
```

### 3. Configuración de Serenity

El archivo `src/test/resources/config/serenity.conf` contiene la configuración base:
- URL del sistema bajo prueba
- Configuración del navegador
- Timeouts
- Configuración de reportes

## Ejecución de Pruebas

### Compilar el Proyecto

```bash
mvn clean compile
```

### Ejecutar Todas las Pruebas

```bash
mvn test
```

### Ejecutar Pruebas de Seguridad con ZAP

```bash
mvn verify -Psecurity-tests
```

Este comando:
1. Inicia el contenedor Docker de OWASP ZAP
2. Ejecuta las pruebas de seguridad
3. Genera reporte de vulnerabilidades
4. Detiene el contenedor ZAP

### Ejecutar Solo Pruebas de SQL Injection

```bash
mvn test -Dcucumber.filter.tags="@sql_injection"
```

### Ejecutar Solo Pruebas de Autenticación

```bash
mvn test -Dcucumber.filter.tags="@authentication"
```

### Generar Reportes de Serenity

```bash
mvn serenity:aggregate
```

Los reportes se generan en `target/site/serenity/index.html`

## Perfiles de Maven

| Perfil | Descripción |
|--------|-------------|
| `security-tests` | Ejecuta pruebas con escaneo de OWASP ZAP |
| default | Solo pruebas unitarias y de integración |

## Tecnologías Utilizadas

- **Serenity BDD 4.0.48**: Framework de automatización con reportes ricos
- **Cucumber 7.14.0**: BDD con Gherkin
- **Selenium WebDriver 4.18.1**: Automatización de navegador
- **REST Assured 5.3.2**: Pruebas de API REST
- **OWASP ZAP 1.11.0**: Escaneo de vulnerabilidades
- **JWT (jjwt) 0.11.5**: Manejo de tokens JWT
- **JUnit 5.10.0**: Framework de pruebas

##不走

Para ejecutar las pruebas de seguridad, el sistema debe cumplir con:

1. Todas las dependencias de Maven resueltas correctamente
2. Contenedor Docker de OWASP ZAP ejecutándose en puerto 8080
3. Sistema bajo prueba accesible en la URL configurada
4. Navegadores instalados (Chrome/Firefox) para pruebas UI

## Solución de Problemas

### Error: "zap-clientapi not found"
Verificar que el perfil `security-tests` está activo y Docker está ejecutándose.

### Error: "Feature not found"
Ejecutar desde la raíz del proyecto y verificar la ruta en `FEATURES_PROPERTY_NAME`.

### Error: "Timeout on payment page"
Ajustar los timeouts en `serenity.conf` según la velocidad de la red.

// === ARCHIVO: src/test/java/com/pragma/payments/security/SecurityConfig.java ===
package com.pragma.payments.security;

import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuración de seguridad para pruebas automatizadas.
 * Proporciona utilitários para manejar autenticación JWT y configuración de seguridad.
 */
@Component
public class SecurityConfig extends PageObject {

    private static final String DEFAULT_SECRET_KEY = "test-secret-key-for-security-tests-minimum-256-bits-required";
    private static final long DEFAULT_EXPIRATION = 3600000;
    private static Map<String, String> tokenStore = new HashMap<>();

    public SecurityConfig() {
        super();
        setDefaultBaseUrl("http://localhost:8080");
    }

    /**
     * Configura el actor de pruebas con credenciales de seguridad.
     */
    public static void configureSecurityActor(String actorName) {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled(actorName);
    }

    /**
     * Obtiene la clave secreta configurada para JWT.
     */
    public String getSecretKey() {
        return DEFAULT_SECRET_KEY;
    }

    /**
     * Obtiene el tiempo de expiración de tokens.
     */
    public long getTokenExpiration() {
        return DEFAULT_EXPIRATION;
    }

    /**
     * Almacena un token para uso posterior en las pruebas.
     */
    public static void storeToken(String key, String token) {
        tokenStore.put(key, token);
    }

    /**
     * Recupera un token almacenado previamente.
     */
    public static String getStoredToken(String key) {
        return tokenStore.get(key);
    }

    /**
     * Limpia todos los tokens almacenados.
     */
    public static void clearTokens() {
        tokenStore.clear();
    }

    /**
     * Valida si un token tiene el formato correcto.
     */
    public boolean isValidTokenFormat(String token) {
        if (token == null || token.isEmpty()) {
            return false;
        }
        String[] parts = token.split("\\.");
        return parts.length == 3;
    }

    /**
     * Obtiene los headers de autorización para requests.
     */
    public Map<String, String> getAuthHeaders(String token) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + token);
        headers.put("Content-Type", "application/json");
        return headers;
    }

    /**
     * Configura el contexto de seguridad para pruebas de inyección SQL.
     */
    public void configureSqlInjectionTestContext() {
        setDefaultBaseUrl("http://localhost:8080");
    }

    /**
     * Obtiene el usuario admin para pruebas.
     */
    public String getAdminUser() {
        return "admin";
    }

    /**
     * Obtiene la contraseña admin para pruebas.
     */
    public String getAdminPassword() {
        return "admin123";
    }
}

// === ARCHIVO: src/test/java/com/pragma/payments/security/JwtUtils.java ===
package com.pragma.payments.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import net.serenitybdd.core.Serenity;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Utilidades para generación y validación de tokens JWT en pruebas de seguridad.
 * Proporciona métodos para crear tokens de prueba y validar su integridad.
 */
@Component
public class JwtUtils {

    private static final String DEFAULT_SECRET = "test-secret-key-for-security-tests-minimum-256-bits-required";
    private static final long EXPIRATION_TIME = 3600000;
    private final SecretKey key;

    public JwtUtils() {
        this.key = Keys.hmacShaKeyFor(DEFAULT_SECRET.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Genera un token JWT básico para pruebas.
     */
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", username);
        claims.put("role", "USER");
        return createToken(claims, username);
    }

    /**
     * Genera un token JWT con roles específicos.
     */
    public String generateTokenWithRoles(String username, String... roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", username);
        claims.put("roles", roles);
        return createToken(claims, username);
    }

    /**
     * Genera un token JWT con permisos de administrador.
     */
    public String generateAdminToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", username);
        claims.put("role", "ADMIN");
        claims.put("permissions", new String[]{"READ", "WRITE", "DELETE"});
        return createToken(claims, username);
    }

    /**
     * Crea el token JWT con los claims proporcionados.
     */
    private String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Valida un token JWT y retorna los claims.
     */
    public Claims validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Extrae el nombre de usuario del token.
     */
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    /**
     * Extrae la fecha de expiración del token.
     */
    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    /**
     * Verifica si el token ha expirado.
     */
    public boolean isTokenExpired(String token) {
        try {
            return extractExpiration(token).before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * Valida el token y retorna true si es válido.
     */
    public boolean validateToken(String token, String username) {
        try {
            final String extractedUsername = extractUsername(token);
            return (extractedUsername.equals(username) && !isTokenExpired(token));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Extrae todos los claims del token.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Genera un token inválido para pruebas de seguridad.
     */
    public String generateInvalidToken() {
        return "invalid.token.here";
    }

    /**
     * Genera un token expirado para pruebas.
     */
    public String generateExpiredToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", username);
        Date now = new Date();
        Date pastDate = new Date(now.getTime() - 10000);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(pastDate)
                .setExpiration(pastDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Almacena el token en el contexto de Serenity para acceso global.
     */
    public void setAuthToken(String token) {
        Serenity.setSessionVariable("auth_token").to(token);
    }

    /**
     * Recupera el token del contexto de Serenity.
     */
    public String getAuthToken() {
        return Serenity.sessionVariableCalled("auth_token");
    }

    /**
     * Extrae un claim específico del token.
     */
    public Object extractClaim(String token, String claimKey) {
        return extractAllClaims(token).get(claimKey);
    }

    /**
     * Genera un token con claim personalizado.
     */
    public String generateTokenWithCustomClaim(String username, String claimKey, Object claimValue) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", username);
        claims.put(claimKey, claimValue);
        return createToken(claims, username);
    }
}

// === ARCHIVO: src/test/java/com/pragma/payments/security/SecuritySteps.java ===
package com.pragma.payments.security;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Definiciones de pasos para escenarios de pruebas de seguridad.
 * Incluye pasos para inyección SQL y autenticación JWT.
 */
public class SecuritySteps {

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private JwtUtils jwtUtils;

    private RequestSpecification requestSpec;
    private Response response;
    private String currentToken;
    private String currentEndpoint;

    @Dado("que el usuario tiene credenciales válidas de autenticación")
    @Step("Preparar credenciales de autenticación")
    public void queElUsuarioTieneCredencialesValidasDeAutenticación() {
        currentToken = jwtUtils.generateToken("testuser");
        jwtUtils.setAuthToken(currentToken);
    }

    @Dado("que el usuario tiene un token JWT válido")
    public void queElUsuarioTieneUnTokenJWTValido() {
        currentToken = jwtUtils.generateToken("testuser");
    }

    @Dado("que el usuario tiene un token JWT inválido")
    public void queElUsuarioTieneUnTokenJWTInválido() {
        currentToken = jwtUtils.generateInvalidToken();
    }

    @Dado("que el usuario tiene un token JWT expirado")
    public void queElUsuarioTieneUnTokenJWTExpirado() {
        currentToken = jwtUtils.generateExpiredToken("testuser");
    }

    @Cuando("el usuario envía una solicitud GET al endpoint de pagos")
    @Step("Enviar solicitud GET al endpoint")
    public void elUsuarioEnvíaUnaSolicitudGETAlEndpointDePagos() {
        currentEndpoint = "/api/payments";
        requestSpec = RestAssured.given()
                .baseUri("http://localhost:8080")
                .header("Authorization", "Bearer " + currentToken)
                .contentType("application/json");

        response = requestSpec.get(currentEndpoint);
    }

    @Cuando("el usuario intenta acceder al endpoint {string}")
    public void elUsuarioIntentaAccederAlEndpoint(String endpoint) {
        currentEndpoint = endpoint;
        requestSpec = RestAssured.given()
                .baseUri("http://localhost:8080")
                .header("Authorization", "Bearer " + currentToken)
                .contentType("application/json");

        response = requestSpec.get(endpoint);
    }

    @Cuando("el usuario envía una solicitud con payload malicioso de inyección SQL")
    @Step("Enviar payload de inyección SQL")
    public void elUsuarioEnvíaUnaSolicitudConPayloadMaliciosoDeInyecciónSQL() {
        String maliciousPayload = "' OR '1'='1";
        currentEndpoint = "/api/payments/search";

        Map<String, String> body = new HashMap<>();
        body.put("query", maliciousPayload);

        requestSpec = RestAssured.given()
                .baseUri("http://localhost:8080")
                .header("Authorization", "Bearer " + currentToken)
                .contentType("application/json")
                .body(body);

        response = requestSpec.post(currentEndpoint);
    }

    @Cuando("el usuario envía una solicitud con payload de inyección SQL en el campo {string}")
    public void elUsuarioEnvíaUnaSolicitudConPayloadDeInyecciónSQLEnElCampo(String campo) {
        Map<String, String> body = new HashMap<>();
        body.put(campo, "' OR '1'='1");

        requestSpec = RestAssured.given()
                .baseUri("http://localhost:8080")
                .header("Authorization", "Bearer " + currentToken)
                .contentType("application/json")
                .body(body);

        response = requestSpec.post(currentEndpoint);
    }

    @Cuando("el usuario envía credenciales de autenticación inválidas")
    @Step("Enviar credenciales inválidas")
    public void elUsuarioEnvíaCredencialesDeAutenticaciónInválidas() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "invaliduser");
        credentials.put("password", "wrongpassword");

        requestSpec = RestAssured.given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body(credentials);

        response = requestSpec.post("/api/auth/login");
    }

    @Cuando("el usuario envía credenciales válidas")
    public void elUsuarioEnvíaCredencialesVálidas() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", securityConfig.getAdminUser());
        credentials.put("password", securityConfig.getAdminPassword());

        requestSpec = RestAssured.given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body(credentials);

        response = requestSpec.post("/api/auth/login");
    }

    @Entonces("el sistema debe responder con código de estado {int}")
    @Step("Verificar código de estado")
    public void elSistemaDebeResponderConCódigoDeEstado(int expectedStatus) {
        assertEquals("El código de estado no coincide", expectedStatus, response.getStatusCode());
    }

    @Entonces("el sistema debe rechazar la solicitud por autenticación fallida")
    public void elSistemaDebeRechazarLaSolicitudPorAutenticaciónFallida() {
        assertTrue("Expected 401 or 403",
                response.getStatusCode() == 401 || response.getStatusCode() == 403);
    }

    @Entonces("el sistema debe rechazar la solicitud por token inválido")
    public void elSistemaDebeRechazarLaSolicitudPorTokenInválido() {
        assertEquals("Expected 401 for invalid token", 401, response.getStatusCode());
    }

    @Entonces("el sistema debe rechazar la solicitud por token expirado")
    public void elSistemaDebeRechazarLaSolicitudPorTokenExpirado() {
        assertEquals("Expected 401 for expired token", 401, response.getStatusCode());
    }

    @Entonces("el sistema debe prevenir la inyección SQL y retornar un error")
    public void elSistemaDebePrevenirLaInyecciónSQLYRetornarUnError() {
        int statusCode = response.getStatusCode();
        assertTrue("Expected error status code (4xx or 5xx) for SQL injection attempt",
                statusCode >= 400);

        String responseBody = response.getBody().asString();
        assertFalse("Response should not expose database errors",
                responseBody.toLowerCase().contains("sql") ||
                responseBody.toLowerCase().contains("database"));
    }

    @Entonces("el sistema debe retornar un token JWT válido")
    public void elSistemaDebeRetornarUnTokenJWTVálido() {
        assertEquals("Expected 200 for successful login", 200, response.getStatusCode());

        String responseBody = response.getBody().asString();
        assertTrue("Response should contain token",
                responseBody.contains("token") || responseBody.contains("jwt"));
    }

    @Entonces("el sistema debe retornar los datos del pago solicitado")
    public void elSistemaDebeRetornarLosDatosDelPagoSolicitado() {
        assertEquals("Expected 200 for successful request", 200, response.getStatusCode());
        assertNotNull("Response body should not be null", response.getBody());
    }

    @Entonces("el sistema debe registrar el intento de intrusión")
    public void elSistemaDebeRegistrarElIntentoDeIntrusión() {
        String responseBody = response.getBody().asString();
        assertTrue("Response should indicate security event logged",
                responseBody.toLowerCase().contains("logged") ||
                responseBody.toLowerCase().contains("security"));
    }

    @Dado("que el usuario tiene rol de administrador")
    public void queElUsuarioTieneRolDeAdministrador() {
        currentToken = jwtUtils.generateAdminToken("adminuser");
    }

    @Cuando("el usuario intenta acceder a un endpoint protegido sin token")
    public void elUsuarioIntentaAccederAUnEndpointProtegidoSinToken() {
        currentEndpoint = "/api/payments";
        requestSpec = RestAssured.given()
                .baseUri("http://localhost:8080")
                .contentType("application/json");

        response = requestSpec.get(currentEndpoint);
    }

    @Entonces("el sistema debe rechazar el acceso sin autenticación")
    public void elSistemaDebeRechazarElAccesoSinAutenticación() {
        assertEquals("Expected 401 for missing authentication", 401, response.getStatusCode());
    }

    @Cuando("el usuario envía datos de pago con caracteres especiales")
    public void elUsuarioEnvíaDatosDePagoConCaracteresEspeciales() {
        Map<String, Object> paymentData = new HashMap<>();
        paymentData.put("amount", 100.00);
        paymentData.put("description", "<script>alert('xss')</script>");
        paymentData.put("recipient", "test<script>");

        requestSpec = RestAssured.given()
                .baseUri("http://localhost:8080")
                .header("Authorization", "Bearer " + currentToken)
                .contentType("application/json")
                .body(paymentData);

        response = requestSpec.post("/api/payments");
    }

    @Entonces("el sistema debe sanitizar los datos y prevenir XSS")
    public void elSistemaDebeSanitizarLosDatosYPrevenirXSS() {
        assertTrue("Expected success or sanitized response",
                response.getStatusCode() == 200 ||
                !response.getBody().asString().contains("<script>"));
    }
}

// === ARCHIVO: src/test/resources/features/security/sql_injection.feature ===
Feature: Pruebas de Inyeccion SQL en el Sistema de Pagos

  Como analista de calidad de software
  Necesito verificar que el sistema de gestion de pagos es resistente a ataques de inyeccion SQL
  Para garantizar la seguridad de los datos financieros de los usuarios

  Background:
    Given el usuario tiene acceso al sistema de pagos
    And el sistema esta operativo con la base de datos conectada

  @sql-injection-basica
  Scenario: Attempt SQL injection in login form
    When el usuario ingresa "' OR '1'='1" en el campo de usuario
    And el usuario ingresa cualquier contrasena
    And el usuario envia el formulario de autenticacion
    Then el sistema debe rechazar la solicitud
    And el sistema debe mostrar un mensaje de error de autenticacion
    And no se debe ejecutar la consulta SQL maliciosa

  @sql-injection-payment-query
  Scenario: Attempt SQL injection in payment search parameter
    Given el usuario esta autenticado en el sistema
    When el usuario busca pagos con el parametro "'; DROP TABLE payments; --"
    Then el sistema debe sanitizar el parametro de busqueda
    And el sistema debe devolver resultados vacios o un mensaje de error
    And la tabla de pagos debe seguir existiendo

  @sql-injection-amount-field
  Scenario: Attempt SQL injection in payment amount field
    Given el usuario esta autenticado y tiene permisos para crear pagos
    When el usuario intenta crear un pago con monto "100; DELETE FROM accounts WHERE '1'='1'"
    Then el sistema debe rechazar el valor del monto
    And el sistema debe validar que solo se permiten valores numericos
    And no se debe modificar ninguna tabla de la base de datos

  @sql-injection-union-attack
  Scenario: Attempt UNION-based SQL injection
    Given el usuario esta autenticado en el sistema
    When el usuario busca transacciones con "' UNION SELECT username, password FROM users--"
    Then el sistema debe bloquear la consulta
    And el sistema debe registrar el intento de ataque
    And no se debe exponer informacion sensible

  @sql-injection-blind
  Scenario: Attempt blind SQL injection in transaction status
    Given el usuario esta autenticado en el sistema
    When el usuario consulta el estado de una transaccion con "' AND SLEEP(5)--"
    Then el sistema debe timeout la consulta
    And el sistema debe detectar el patron de ataque
    And debe generar una alerta de seguridad

  @sql-injection-preventivo
  Scenario: Verify parameterized queries prevent SQL injection
    Given el sistema utiliza consultas parametrizadas
    When un atacante intenta inyeccion SQL con cualquier vector
    Then el sistema debe tratar la entrada como dato, no como codigo
    And la consulta debe ejecutarse de forma segura
    And no debe haber vulnerabilidad de inyeccion

  @sql-injection-api-rest
  Scenario: Attempt SQL injection via REST API parameter
    Given el sistema expone endpoints REST para consulta de pagos
    When el usuario realiza una peticion GET a "/api/payments?id=1' OR '1'='1"
    Then el sistema debe validar y sanitizar los parametros
    And debe devolver un error 400 Bad Request
    And no debe exponer datos de otras transacciones

  @sql-injection-logging
  Scenario: Verify SQL injection attempts are logged
    Given el sistema tiene habilitado el logging de seguridad
    When se detecta un intento de inyeccion SQL
    Then el sistema debe registrar el intento con timestamp
    And debe incluir la IP de origen
    And debe incluir el payload malicioso
    And debe notificar al equipo de seguridad

// === ARCHIVO: src/test/resources/config/serenity.conf ===
webdriver {
  driver = chrome
  autodownload = true
  capabilities {
    "goog:chromeOptions" {
      args = ["--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu", "--headless"]
      prefs {
        download.prompt_for_download = false
        download.default_directory = "target/downloads"
      }
    }
  }
}

environments {
  default = "local"
  local {
    base.url = "http://localhost:8080"
    api.url = "http://localhost:8080/api"
    admin.url = "http://localhost:8080/admin"
  }
  development {
    base.url = "https://dev.payments.pragma.local"
    api.url = "https://dev.payments.pragma.local/api"
    admin.url = "https://dev.payments.pragma.local/admin"
  }
  staging {
    base.url = "https://staging.payments.pragma.local"
    api.url = "https://staging.payments.pragma.local/api"
    admin.url = "https://staging.payments.pragma.local/admin"
  }
  production {
    base.url = "https://payments.pragma.com"
    api.url = "https://payments.pragma.com/api"
    admin.url = "https://payments.pragma.com/admin"
  }
}

security {
  zap {
    enabled = true
    api.url = "${zap.api.url}"
    api.key = "${zap.api.key}"
    scan.delay.in.ms = 1000
  }
  jwt {
    secret = "test-secret-key-for-security-tests-minimum-256-bits-required"
    expiration.minutes = 30
  }
  sql.injection {
    test.payloads = ["' OR '1'='1", "'; DROP TABLE users; --", "1' AND '1'='1", "1 OR 1=1", "admin'--"]
    time.based.enabled = true
  }
  authentication {
    test.users {
      valid = "testuser@pragma.com"
      invalid = "nonexistent@pragma.com"
      locked = "lockeduser@pragma.com"
    }
    test.passwords {
      valid = "Test1234!"
      weak = "password"
      wrong = "WrongPass123!"
    }
  }
}

reporting {
  single.page.reports = true
  output.formats = ["html", "json"]
  history.dir = "target/site/serenity/history"
  thumbnail.width = 300
}

concurrency {
  max = 4
  thread.count = 2
}

// === ARCHIVO: src/test/java/com/pragma/payments/pages/PaymentPage.java ===
package com.pragma.payments.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;
import java.util.List;

public class PaymentPage extends PageObject {

    @FindBy(how = How.ID, using = "payment-form")
    private WebElementFacade paymentForm;

    @FindBy(how = How.ID, using = "amount")
    private WebElementFacade amountField;

    @FindBy(how = How.ID, using = "card-number")
    private WebElementFacade cardNumberField;

    @FindBy(how = How.ID, using = "card-holder")
    private WebElementFacade cardHolderField;

    @FindBy(how = How.ID, using = "expiry-date")
    private WebElementFacade expiryDateField;

    @FindBy(how = How.ID, using = "cvv")
    private WebElementFacade cvvField;

    @FindBy(how = How.ID, using = "recipient-account")
    private WebElementFacade recipientAccountField;

    @FindBy(how = How.ID, using = "recipient-bank")
    private WebElementFacade recipientBankField;

    @FindBy(how = How.ID, using = "reference-number")
    private WebElementFacade referenceNumberField;

    @FindBy(how = How.ID, using = "submit-payment")
    private WebElementFacade submitButton;

    @FindBy(how = How.ID, using = "payment-success-message")
    private WebElementFacade successMessage;

    @FindBy(how = How.ID, using = "payment-error-message")
    private WebElementFacade errorMessage;

    @FindBy(how = How.CSS, using = ".error-message")
    private List<WebElementFacade> validationErrors;

    @FindBy(how = How.ID, using = "login-username")
    private WebElementFacade loginUsernameField;

    @FindBy(how = How.ID, using = "login-password")
    private WebElementFacade loginPasswordField;

    @FindBy(how = How.ID, using = "login-submit")
    private WebElementFacade loginSubmitButton;

    @FindBy(how = How.ID, using = "user-dashboard")
    private WebElementFacade userDashboard;

    @FindBy(how = How.CSS, using = ".transaction-row")
    private List<WebElementFacade> transactionRows;

    @FindBy(how = How.ID, using = "search-transactions")
    private WebElementFacade searchField;

    @FindBy(how = How.ID, using = "filter-transactions")
    private WebElementFacade filterDropdown;

    private static final By SQL_INJECTION_VULNERABLE_FIELD = By.id("search-transactions");
    private static final By SQL_INJECTION_VULNERABLE_PARAM = By.name("query");
    private static final By DYNAMIC_CONTENT_AREA = By.cssSelector(".transaction-data");
    private static final By ERROR_CONTAINER = By.cssSelector(".alert-danger");

    public void openPaymentPage() {
        open();
        waitForFormToLoad();
    }

    public void waitForFormToLoad() {
        withTimeoutOf(Duration.ofSeconds(10)).waitFor(paymentForm);
    }

    public void enterAmount(String amount) {
        amountField.type(amount);
    }

    public void enterCardNumber(String cardNumber) {
        cardNumberField.type(cardNumber);
    }

    public void enterCardHolder(String holderName) {
        cardHolderField.type(holderName);
    }

    public void enterExpiryDate(String expiryDate) {
        expiryDateField.type(expiryDate);
    }

    public void enterCvv(String cvv) {
        cvvField.type(cvv);
    }

    public void enterRecipientAccount(String accountNumber) {
        recipientAccountField.type(accountNumber);
    }

    public void enterRecipientBank(String bankCode) {
        recipientBankField.type(bankCode);
    }

    public void enterReferenceNumber(String reference) {
        referenceNumberField.type(reference);
    }

    public void submitPayment() {
        submitButton.click();
        waitForPaymentProcessing();
    }

    public void waitForPaymentProcessing() {
        withTimeoutOf(Duration.ofSeconds(15)).waitForJavaScriptExecution(
            "return document.readyState === 'complete'"
        );
    }

    public boolean isPaymentSuccessful() {
        return successMessage.isVisible();
    }

    public boolean isPaymentErrorDisplayed() {
        return errorMessage.isVisible();
    }

    public String getErrorMessageText() {
        return errorMessage.getText();
    }

    public List<String> getValidationErrors() {
        return validationErrors.stream()
            .map(WebElementFacade::getText)
            .toList();
    }

    public void loginAsUser(String username, String password) {
        loginUsernameField.type(username);
        loginPasswordField.type(password);
        loginSubmitButton.click();
        withTimeoutOf(Duration.ofSeconds(10)).waitFor(userDashboard);
    }

    public boolean isUserDashboardVisible() {
        return userDashboard.isVisible();
    }

    public int getTransactionCount() {
        return transactionRows.size();
    }

    public void searchTransaction(String searchTerm) {
        searchField.type(searchTerm);
        searchField.pressEnter();
        withTimeoutOf(Duration.ofSeconds(5)).waitForAjaxCompleted();
    }

    public void filterTransactionsByStatus(String status) {
        filterDropdown.selectByVisibleText(status);
        withTimeoutOf(Duration.ofSeconds(5)).waitForAjaxCompleted();
    }

    public void performSqlInjectionTest(String payload) {
        WebElementFacade vulnerableField = find(SQL_INJECTION_VULNERABLE_FIELD);
        vulnerableField.clear();
        vulnerableField.type(payload);
        vulnerableField.pressEnter();
        withTimeoutOf(Duration.ofSeconds(5)).waitForAjaxCompleted();
    }

    public void performSqlInjectionOnParameter(String parameterName, String payload) {
        String urlWithInjection = String.format(
            "?%s=%s",
            parameterName,
            payload
        );
        openAt(urlWithInjection);
        withTimeoutOf(Duration.ofSeconds(5)).waitForPageToLoad();
    }

    public boolean isErrorMessageDisplayed() {
        return find(ERROR_CONTAINER).isVisible();
    }

    public String getPageContent() {
        return evaluateJavascript("return document.body.innerText");
    }

    public boolean containsDatabaseErrorPattern() {
        String content = getPageContent().toLowerCase();
        return content.contains("sql") || 
               content.contains("database") || 
               content.contains("syntax") ||
               content.contains("ora-") ||
               content.contains("mysql") ||
               content.contains("postgresql") ||
               content.contains("sqlite");
    }

    public boolean containsUnexpectedData(String unexpectedData) {
        return getPageContent().contains(unexpectedData);
    }

    public void clearSearchField() {
        searchField.clear();
    }

    public String getCurrentUrl() {
        return getCurrentUrl();
    }

    public boolean hasPagination() {
        return find(By.cssSelector(".pagination")).isPresent();
    }

    public void navigateToPage(int pageNumber) {
        find(By.cssSelector(".page-link[data-page='" + pageNumber + "']")).click();
        withTimeoutOf(Duration.ofSeconds(5)).waitForAjaxCompleted();
    }
}

// === ARCHIVO: src/test/java/com/pragma/payments/steps/PaymentSteps.java ===
package com.pragma.payments.steps;

import com.pragma.payments.pages.PaymentPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Step;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentSteps {

    private final PaymentPage paymentPage;

    public PaymentSteps() {
        this.paymentPage = new PaymentPage();
    }

    @Given("el usuario está en la página de pagos")
    @Step("El usuario navega a la página de pagos")
    public void elUsuarioEstaEnLaPaginaDePagos() {
        paymentPage.openPaymentPage();
    }

    @When("el usuario ingresa el monto de {string}")
    @Step("El usuario ingresa el monto: {0}")
    public void elUsuarioIngresaElMonto(String amount) {
        paymentPage.enterAmount(amount);
    }

    @And("el usuario ingresa el número de tarjeta {string}")
    @Step("El usuario ingresa el número de tarjeta: {0}")
    public void elUsuarioIngresaElNumeroDeTarjeta(String cardNumber) {
        paymentPage.enterCardNumber(cardNumber);
    }

    @And("el usuario ingresa el nombre del titular {string}")
    @Step("El usuario ingresa el nombre del titular: {0}")
    public void elUsuarioIngresaElNombreDelTitular(String holderName) {
        paymentPage.enterCardHolder(holderName);
    }

    @And("el usuario ingresa la fecha de expiración {string}")
    @Step("El usuario ingresa la fecha de expiración: {0}")
    public void elUsuarioIngresaLaFechaDeExpiracion(String expiryDate) {
        paymentPage.enterExpiryDate(expiryDate);
    }

    @And("el usuario ingresa el CVV {string}")
    @Step("El usuario ingresa el CVV: {0}")
    public void elUsuarioIngresaElCVV(String cvv) {
        paymentPage.enterCvv(cvv);
    }

    @And("el usuario ingresa la cuenta del beneficiario {string}")
    @Step("El usuario ingresa la cuenta del beneficiario: {0}")
    public void elUsuarioIngresaLaCuentaDelBeneficiario(String accountNumber) {
        paymentPage.enterRecipientAccount(accountNumber);
    }

    @And("el usuario ingresa el banco del beneficiario {string}")
    @Step("El usuario ingresa el banco del beneficiario: {0}")
    public void elUsuarioIngresaElBancoDelBeneficiario(String bankCode) {
        paymentPage.enterRecipientBank(bankCode);
    }

    @And("el usuario ingresa el número de referencia {string}")
    @Step("El usuario ingresa el número de referencia: {0}")
    public void elUsuarioIngresaElNumeroDeReferencia(String reference) {
        paymentPage.enterReferenceNumber(reference);
    }

    @When("el usuario envía el formulario de pago")
    @Step("El usuario envía el formulario de pago")
    public void elUsuarioEnviaElFormularioDePago() {
        paymentPage.submitPayment();
    }

    @Then("el sistema debe mostrar un mensaje de éxito")
    @Step("El sistema muestra mensaje de éxito")
    public void elSistemaDebeMostrarUnMensajeDeExito() {
        assertThat(paymentPage.isPaymentSuccessful())
            .as("El pago debería procesarse exitosamente")
            .isTrue();
    }

    @Then("el sistema debe mostrar un mensaje de error")
    @Step("El sistema muestra mensaje de error")
    public void elSistemaDebeMostrarUnMensajeDeError() {
        assertThat(paymentPage.isPaymentErrorDisplayed())
            .as("Debería mostrarse un mensaje de error")
            .isTrue();
    }

    @Then("el mensaje de error debe contener {string}")
    @Step("El mensaje de error contiene: {0}")
    public void elMensajeDeErrorDebeContener(String expectedError) {
        String actualError = paymentPage.getErrorMessageText();
        assertThat(actualError)
            .as("El mensaje de error debería contener: %s", expectedError)
            .containsIgnoringCase(expectedError);
    }

    @Then("el sistema debe mostrar errores de validación")
    @Step("El sistema muestra errores de validación")
    public void elSistemaDebeMostrarErroresDeValidacion() {
        List<String> errors = paymentPage.getValidationErrors();
        assertThat(errors)
            .as("Deberían mostrarse errores de validación")
            .isNotEmpty();
    }

    @Given("el usuario está autenticado en el sistema")
    @Step("El usuario se autentica en el sistema")
    public void elUsuarioEstaAutenticadoEnElSistema() {
        paymentPage.openPaymentPage();
        paymentPage.loginAsUser("testuser@pragma.com", "Test1234!");
    }

    @When("el usuario busca transacciones con el término {string}")
    @Step("El usuario busca transacciones con: {0}")
    public void elUsuarioBuscaTransaccionesConElTermino(String searchTerm) {
        paymentPage.searchTransaction(searchTerm);
    }

    @Then("el sistema debe mostrar resultados de la búsqueda")
    @Step("El sistema muestra resultados de búsqueda")
    public void elSistemaDebeMostrarResultadosDeLaBusqueda() {
        int transactionCount = paymentPage.getTransactionCount();
        assertThat(transactionCount)
            .as("Deberían mostrarse transacciones")
            .isGreaterThanOrEqualTo(0);
    }

    @When("el usuario intenta realizar una inyección SQL con el payload {string}")
    @Step("El usuario intenta inyección SQL con: {0}")
    public void elUsuarioIntentaRealizarUnaInyeccionSQL(String payload) {
        paymentPage.performSqlInjectionTest(payload);
    }

    @Then("el sistema no debe revelar información sensible de la base de datos")
    @Step("El sistema no revela información de base de datos")
    public void elSistemaNoDebeRevelarInformacionSensible() {
        assertThat(paymentPage.containsDatabaseErrorPattern())
            .as("No debería revelar información de la base de datos")
            .isFalse();
    }

    @Then("el sistema debe manejar correctamente la entrada maliciosa")
    @Step("El sistema maneja la entrada maliciosa")
    public void elSistemaDebeManejarCorrectamenteLaEntradaMaliciosa() {
        boolean hasError = paymentPage.isErrorMessageDisplayed();
        boolean hasDbError = paymentPage.containsDatabaseErrorPattern();
        
        assertThat(hasDbError)
            .as("No debería mostrar errores de base de datos")
            .isFalse();
        
        assertThat(hasError || !hasDbError)
            .as("El sistema debería manejar la entrada de forma segura")
            .isTrue();
    }

    @When("el usuario filtra las transacciones por estado {string}")
    @Step("El usuario filtra transacciones por estado: {0}")
    public void elUsuarioFiltraLasTransaccionesPorEstado(String status) {
        paymentPage.filterTransactionsByStatus(status);
    }

    @Then("el sistema debe mostrar las transacciones filtradas")
    @Step("El sistema muestra transacciones filtradas")
    public void elSistemaDebeMostrarLasTransaccionesFiltradas() {
        int transactionCount = paymentPage.getTransactionCount();
        assertThat(transactionCount)
            .as("Deberían mostrarse transacciones filtradas")
            .isGreaterThanOrEqualTo(0);
    }

    @When("el usuario limpia el campo de búsqueda")
    @Step("El usuario limpia el campo de búsqueda")
    public void elUsuarioLimpiaElCampoDeBusqueda() {
        paymentPage.clearSearchField();
    }

    @Then("el sistema debe mostrar todas las transacciones disponibles")
    @Step("El sistema muestra todas las transacciones")
    public void elSistemaDebeMostrarTodasLasTransacciones() {
        int transactionCount = paymentPage.getTransactionCount();
        assertThat(transactionCount)
            .as("Deberían mostrarse transacciones")
            .isGreaterThan(0);
    }
}
```
