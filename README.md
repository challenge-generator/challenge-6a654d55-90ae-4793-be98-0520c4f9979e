# Fundamentos de las pruebas de seguridad en proyectos de automatización

En el contexto de un proyecto de automatización de calidad para un sistema de gestión de pagos en línea, es crucial comprender y aplicar los conceptos de pruebas de seguridad. Los actores clave incluyen el originador de créditos, el motor antifraude, el buró de riesgos, y el sistema de liquidación. El sistema debe manejar un volumen de 1 500 solicitudes por segundo en hora pico, con un SLA de 99.9%.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Fundamentos de las pruebas de seguridad |
| **Nivel** | advanced-l2 |
| **Tipo** | theoretical |
| **Tiempo estimado** | 3 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Evaluación de riesgos y gestión

**Objetivo:** Comprender y aplicar la evaluación de riesgos en el contexto de un sistema de gestión de pagos.

**Tiempo estimado:** 1 hora

**Instrucciones:**

- Identifica las áreas de riesgo en el sistema de gestión de pagos.
- Proporciona ejemplos de cómo gestionar estos riesgos en el contexto del proyecto.

**Entregable:** Documento que describe las áreas de riesgo identificadas y las estrategias de gestión propuestas.

<details>
<summary>Pistas de conocimiento</summary>

- Considera los diferentes actores y sus interacciones en el sistema.
- Piensa en los posibles impactos de las vulnerabilidades en el negocio.

</details>

### Fase 2: Identificación de vulnerabilidades

**Objetivo:** Identificar y comprender las vulnerabilidades comunes en sistemas de gestión de pagos.

**Tiempo estimado:** 1 hora

**Instrucciones:**

- Enumera las vulnerabilidades comunes en sistemas similares.
- Describe cómo estas vulnerabilidades podrían ser explotadas en el contexto del proyecto.

**Entregable:** Lista de vulnerabilidades comunes y descripción de cómo podrían ser explotadas.

<details>
<summary>Pistas de conocimiento</summary>

- Investiga vulnerabilidades conocidas en sistemas de gestión de pagos.
- Considera cómo estas vulnerabilidades podrían ser aprovechadas por atacantes.

</details>

### Fase 3: Técnicas de inyección SQL

**Objetivo:** Comprender y aplicar técnicas para prevenir inyecciones SQL en el sistema de gestión de pagos.

**Tiempo estimado:** 1 hora

**Instrucciones:**

- Describe las técnicas comunes de inyección SQL.
- Proporciona ejemplos de cómo estas técnicas podrían ser aplicadas en el contexto del proyecto.
- Propone estrategias para prevenir inyecciones SQL en el sistema.

**Entregable:** Documento que describe las técnicas de inyección SQL, ejemplos de su aplicación y estrategias de prevención.

<details>
<summary>Pistas de conocimiento</summary>

- Investiga casos de inyección SQL en sistemas similares.
- Considera las mejores prácticas para prevenir inyecciones SQL.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué son las pruebas de seguridad y por qué son importantes en un proyecto de automatización de calidad?
- **paraQueSirve**: ¿Cómo se aplican las pruebas de seguridad para proteger un sistema de gestión de pagos?
- **erroresComunes**: ¿Cuáles son los errores comunes al implementar pruebas de seguridad y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones clave deben tomarse al diseñar un sistema seguro y cómo afectan al proyecto?

## Criterios de Evaluacion

- Identificación correcta de áreas de riesgo en el sistema de gestión de pagos.
- Descripción precisa de vulnerabilidades comunes y cómo podrían ser explotadas.
- Propuesta de estrategias efectivas para prevenir inyecciones SQL en el sistema.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean test-compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
