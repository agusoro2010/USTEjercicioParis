package Busqueda.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author sentrauser3260
 */
public class Inicio {
    
    private WebDriver driver;
    private By inp_barraBusqueda;
    private By but_botonBusqueda;
    
    private String productoABuscar = "zapatillas";
    
    public Inicio(WebDriver driver){
        this.driver = driver;
        this.inp_barraBusqueda = By.xpath("//input[@id='desktop-search__form-input']");
        this.but_botonBusqueda = By.xpath("//header/div[2]/div[1]/nav[1]/div[2]/div[1]/div[1]/form[1]/button[1]/*[1]");
        
    }
    
    public void buscarProducto(){
        try{
            this.driver.findElement(inp_barraBusqueda).sendKeys(productoABuscar);
            this.driver.findElement(but_botonBusqueda).click();
            Thread.sleep(3000);
        }catch(Exception e){
            System.out.println("Los elementos de busqueda del producto han cambiado!");
            System.out.println(e);
        }
        
    }
    

   
    
}
