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