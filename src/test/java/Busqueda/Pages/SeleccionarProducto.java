package Busqueda.Pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author sentrauser3260
 */
public class SeleccionarProducto {
    
    private List<WebElement> listaProductos;
    private By elementosProductos;
    private WebDriver driver;
    
    JavascriptExecutor js = (JavascriptExecutor) driver;
    
    public SeleccionarProducto(WebDriver driver){
        this.driver = driver;
        this.elementosProductos = By.xpath("//img[@class='img-prod image-primary' or @class='img-prod image-primary loaded']");

    }
    
     public void seleccionarProducto(int i) throws InterruptedException{
         JavascriptExecutor javascript = (JavascriptExecutor) this.driver;
         WebElement producto = driver.findElement(this.elementosProductos);
         
         Thread.sleep(4000);
         
         try{
             javascript.executeScript("arguments[0].scrollIntoView();",producto);
         }catch(org.openqa.selenium.JavascriptException ex){
             javascript.executeScript("arguments[0].scrollIntoView();",producto);
         }
         
         Thread.sleep(1000);
         
         this.listaProductos = driver.findElements(this.elementosProductos);
         
         if(!this.listaProductos.isEmpty() && this.listaProductos.get(i).isEnabled()){
           //hacer click en el elemento indicado de la lista
           this.listaProductos.get(i).click();
         }else{
             System.out.println("El producto que intentó seleccionar no está disponible o no existe");
         }
         
     }
    
}
