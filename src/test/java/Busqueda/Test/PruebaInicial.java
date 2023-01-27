package Busqueda.Test;

import Busqueda.Auxiliar.CapturarPantalla;
import Busqueda.Pages.AgregarACarroCompras;
import Busqueda.Pages.Filtros;
import Busqueda.Pages.Inicio;
import Busqueda.Pages.SeleccionarProducto;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 *
 * @author sentrauser3260
 */
public class PruebaInicial {
    
    WebDriver driver;
    
    @BeforeClass
    public void setup() throws InterruptedException{
        try{
        System.setProperty("webdriver.chrome.driver", "src/test/recursos/driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.paris.cl");
        Thread.sleep(2000);
        Assert.assertTrue(driver.getTitle().equals("Paris.cl | Tu experiencia de compra más confiable y segura"));
        }catch(WebDriverException e){
            System.out.println("Atención a la excepción " + e);
        }
    }
    
    @Test(priority=1)
    public void realizaBusqueda(){
        Inicio inicio = new Inicio(driver);
        inicio.buscarProducto();
    }
    
    @Test(priority=2)
    public void resultadoFiltro() throws InterruptedException{
        Filtros filtros = new Filtros(driver,"Adidas","Gris");
        SeleccionarProducto seleccionarP = new SeleccionarProducto(driver);
        filtros.aplicarFiltroMarca();
        filtros.aplicarFiltroColor();
        seleccionarP.seleccionarProducto(0);
    }
    
    @Test(priority=3)
    public void SeleccionProducto() throws InterruptedException{
        SeleccionarProducto seleccionarP = new SeleccionarProducto(driver);
        seleccionarP.seleccionarProducto(0);
    }
    
    @Test(priority=4)
    public void agregandoACarroZapatilla() throws InterruptedException{
        AgregarACarroCompras agregarACarro = new AgregarACarroCompras(driver);
        agregarACarro.seleccionTalla(40);
        agregarACarro.agregarACarro();
        CapturarPantalla.hacerCapturaPantalla(driver, "intento de de compra");
        agregarACarro.eliminarProductoAgregado();
    }
    
    @AfterClass
    public void cerrarDriver(){
        driver.quit();
    }
    
    
}
