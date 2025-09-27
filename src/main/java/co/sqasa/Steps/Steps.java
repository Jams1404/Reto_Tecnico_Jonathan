package co.sqasa.Steps;

import co.sqasa.pageObjects.Objects;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;



public class Steps extends PageObject {

    Objects objects;

    @FindBy(id = "datepicker")
    private WebElement campoFecha;

    @Step("Abrir la página principal de JQuery Datepicker")
    public void abrirPaginaPrincipal() {
        objects.open();
    }

    @Step("Cambiar al iframe del calendario")
    public void cambiarAlIframe() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10); // 10 segundos de espera
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("iframe.demo-frame")));
        getDriver().switchTo().frame(iframe);
    }

    @Step("Hacer clic en el campo de selección de fecha")
    public void hacerClicCampoFecha() {
        objects.abrirCalendario();
    }

    @Step("Seleccionar el día {0} del mes actual")
    public void seleccionarDia(int dia) {
        objects.seleccionarDia(dia);
    }

    @Step("Validar que la fecha seleccionada aparece en el campo de texto")
    public void validarFechaSeleccionada(int dia) {
        LocalDate fechaActual = LocalDate.now();
        String fechaEsperada = fechaActual.withDayOfMonth(dia)
                .format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        String fechaReal = objects.obtenerFechaSeleccionada();
        assertThat(fechaReal).isEqualTo(fechaEsperada);
    }

    @Step("Hacer clic en el campo de selección de mes")
    public void CambiarMes() {
        objects.CambiarMes();
    }

    @Step("Intentar ingresar una fecha manualmente en el campo de texto")
    public void intentarEditarCampoFecha(String fecha) {
        WebElementFacade campo = $(By.id("datepicker"));
        String valorOriginal = campo.getValue();
        campo.sendKeys(fecha);
        assertThat(campo.getValue())
                .as("El campo debería estar bloqueado y no permitir edición")
                .isEqualTo(valorOriginal);
    }

    @Step("Validar que la edición manual no es permitida")
    public void validarCampoBloqueado() {
        // Localizar el campo de fecha
        WebElementFacade campo = $(By.id("datepicker"));

        // Intentar escribir manualmente
        campo.sendKeys("27/09/2025");

        campo.clear();
        campo.sendKeys("29/09/2025");
        // Validar que el valor del campo sigue vacío
        String valor = campo.getValue().trim();
        if(valor.isEmpty()) {
            System.out.println("Campo bloqueado para edición manual");
        } else {
            throw new AssertionError("El campo permite edición manual");
        }
    }


    private WebElementFacade campoFecha() {
        return objects.findBy("#datepicker");
    }

    public String obtenerFechaDelCampo() {
        return campoFecha.getAttribute("value"); // retorna el valor actual del input
    }
}
