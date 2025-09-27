package co.sqasa.StepDef;

import io.cucumber.java.en.*;
import co.sqasa.Steps.Steps;
import net.thucydides.core.annotations.Step;

public class TestStepDefinition {

    @net.thucydides.core.annotations.Steps
     Steps user;

    private int diaSeleccionado; // guardamos el día que selecciona el feature

    @Given("que estoy en la página principal de JQuery Datepicker")
    public void queEstoyEnLaPaginaPrincipal() {
        user.abrirPaginaPrincipal();
    }

    @When("cambio al iframe del calendario")
    public void cambioAlIframeDelCalendario() {
        user.cambiarAlIframe();
    }

    @When("hago clic en el campo de selección de fecha")
    public void hagoClicEnElCampoDeSeleccionDeFecha() {
        user.hacerClicCampoFecha();
    }

    @When("selecciono el día {int} del mes actual")
    public void seleccionoElDiaDelMesActual(int dia) {
        diaSeleccionado = dia;      // guardamos el día enviado desde el feature
        user.seleccionarDia(dia);   // hacemos clic en el calendario
    }

    @When("hago clic en el campo de cambio de mes")
    public void CambiarMes() {
        user.CambiarMes();
    }

    @Then("la fecha seleccionada debe aparecer en el campo de texto")
    public void laFechaSeleccionadaDebeAparecerEnElCampoDeTexto() {

        // obtenemos lo que quedó en el campo de la fecha
        String fechaEnCampo = user.obtenerFechaDelCampo();

        // extraemos el día de la fecha
        int diaEnCampo = Integer.parseInt(fechaEnCampo.split("/")[1]); // si formato es mm/dd/yyyy
        if (diaEnCampo != diaSeleccionado) {
            throw new AssertionError(
                    "El día seleccionado no coincide. Esperado: " + diaSeleccionado + " pero fue: " + diaEnCampo
            );
        }
    }

    @When("intento ingresar una fecha manualmente en el campo de texto")
    public void intentoIngresarFechaManual() {
        user.intentarEditarCampoFecha("01/01/2025"); // ejemplo de fecha
    }

    @Then("la edición manual no debe ser permitida")
    public void validacionCampoBloqueado() {
        user.validarCampoBloqueado();
    }

}