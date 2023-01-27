package Busqueda.Pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 *
 * @author sentrauser3260
 */
public class AgregarACarroCompras {
    
    private WebDriver driver;
    private List<WebElement> tallasDisponibles;
    private By opcionesTalla;
    private By region;
    private By comuna;
    private By btn_agregar;
    private By elementoEnCarro;
    private List<WebElement> elementoEnCarroCant;
    private int elementosEnCarroAgregados;
    
    private By link_eliminarProducto;
    
    public AgregarACarroCompras(WebDriver driver){
        this.driver = driver;
        this.opcionesTalla = By.xpath("//*[@class='size-selector__item']/a");
        this.region = By.name("location-region-select");
        this.comuna = By.name("location-comuna-select");
        this.btn_agregar = By.xpath("//*[@class='add-to-cart__label']");
        this.elementoEnCarro = By.xpath("//*[@class=\"box-item-mc box-item-os js-remove-from-minicart-data\"]");
        this.elementosEnCarroAgregados = 0;
        this.link_eliminarProducto = By.xpath("//*[@class=\'js-remove-product-line-item\']");
    }
    
    public void seleccionTalla(int talla) throws InterruptedException{
        JavascriptExecutor javascript = (JavascriptExecutor) this.driver;
        WebElement tallas = driver.findElement(this.opcionesTalla);
        WebElement tallaASeleccionar;
        this.tallasDisponibles = driver.findElements(this.opcionesTalla);
        
        try{
           javascript.executeScript("arguments[0].scrollIntoView();",tallas);
           Thread.sleep(1000);
           System.out.println("La cantidad de tallas: " + this.tallasDisponibles.size());
         }catch(org.openqa.selenium.JavascriptException ex){
            javascript.executeScript("arguments[0].scrollIntoView();",tallas);
            Thread.sleep(1000);
            System.out.println("La cantidad de tallas 2: " + this.tallasDisponibles.size());
        }
        tallaASeleccionar = driver.findElement(By.xpath("//*[@class='size-selector__item']/a[contains(text(),'"+ talla +"')]"));
        if(tallaASeleccionar.isDisplayed()){
           //hacer click en el elemento indicado de la lista
           javascript.executeScript("arguments[0].scrollIntoView();",tallas);
           
           tallaASeleccionar.click();
           Thread.sleep(5000);
         }else{
             System.out.println("La talla que intentó seleccionar no está disponible o no existe");
         }
        
    }
    
    public void agregarACarro() throws InterruptedException{
        
        JavascriptExecutor javascript = (JavascriptExecutor) this.driver;   
        WebElement botonDeCompra = driver.findElement(this.btn_agregar); 
        javascript.executeScript("arguments[0].scrollIntoView();",botonDeCompra); 
        Thread.sleep(500);
        System.out.println("Hago click en boton de compra");
        botonDeCompra.click();
        Thread.sleep(2000);
        this.elementosEnCarroAgregados++;
        this.confirmoProductoAgregado();
    }
    
    private void confirmoProductoAgregado(){
        List<WebElement> elementoEnCarroCant = driver.findElements(this.elementoEnCarro);
        int aux = elementoEnCarroCant.size();
        Assert.assertEquals(aux, this.elementosEnCarroAgregados);
    }
    
    public void eliminarProductoAgregado(){
        WebElement link_eliminar = driver.findElement(this.link_eliminarProducto);
        int produc_iniciales = this.elementosEnCarroAgregados;
        if(this.elementosEnCarroAgregados>0 && link_eliminar.isEnabled()){
            link_eliminar.click();
            this.elementosEnCarroAgregados--;
        }
        Assert.assertEquals(produc_iniciales-1, elementosEnCarroAgregados);
    }
    
}
