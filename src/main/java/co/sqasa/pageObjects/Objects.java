package co.sqasa.pageObjects;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("https://jqueryui.com/datepicker/")
public class Objects extends PageObject {

    @FindBy(id = "datepicker")
    WebElementFacade campoFecha;

    public void abrirCalendario() {
        campoFecha.click();
    }

    public void seleccionarDia(int dia) {

        // Selecciona el día dentro del calendario
        findBy("//table[@class='ui-datepicker-calendar']//a[text()='" + dia + "']").click();
    }

    public String obtenerFechaSeleccionada() {

        // Obtener valor del campo de texto
        return campoFecha.getValue();
    }

    public void CambiarMes() {

        // Permite hacer un clic para cambiar el mes
        findBy("//a[@class='ui-datepicker-next ui-corner-all']//span[text()='Next']").click();
    }
}