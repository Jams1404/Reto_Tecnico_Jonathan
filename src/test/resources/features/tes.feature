Feature: Selección de fecha en el calendario

  @Scenario1
  Scenario: Reserva de una cita seleccionando una fecha en el calendario
    Given que estoy en la página principal de JQuery Datepicker
    When cambio al iframe del calendario
    And hago clic en el campo de selección de fecha
    And selecciono el día 15 del mes actual
    Then la fecha seleccionada debe aparecer en el campo de texto

  @Scenario2
  Scenario: Selección de una fecha específica en un mes diferente
    Given que estoy en la página principal de JQuery Datepicker
    When cambio al iframe del calendario
    And hago clic en el campo de selección de fecha
    And hago clic en el campo de cambio de mes
    And selecciono el día 10 del mes actual
    Then la fecha seleccionada debe aparecer en el campo de texto


  @Scenario3
  Scenario: Validación de campo bloqueado
    Given que estoy en la página principal de JQuery Datepicker
    When cambio al iframe del calendario
    And intento ingresar una fecha manualmente en el campo de texto
    Then la edición manual no debe ser permitida