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
public class Filtros {
    private WebDriver driver;
    private String opcionMarca;
    private String opcionColor;
    private By elementoColorPagina;
    private By chk_listaMarca;
    private By chk_listaColor;
    private By validoFiltro;
    private List<WebElement> filtros;
    private List<WebElement> colores;
    //Actions action = new Actions(driver);
    JavascriptExecutor js = (JavascriptExecutor) driver;
    
    
   public Filtros(WebDriver driver, String marca, String color){
       this.driver = driver;
       
       
       this.opcionMarca = marca;
       this.opcionColor = color;
       //this.opciones.add("Bubble Gummers");
       //this.opciones.add("Nike");
       
       this.chk_listaMarca = By.xpath("//ul[@id='brand']/li/a/span");
       this.chk_listaColor = By.className(".color-name-refinement");
       this.elementoColorPagina=By.xpath("//div[@id='refinement-refinementColor']");
       this.validoFiltro = By.xpath("//div[@class='clear-refinement']");

   }
   
   public void aplicarFiltroMarca() throws InterruptedException{
       JavascriptExecutor javascript = (JavascriptExecutor) this.driver;
       List<WebElement> marcas = driver.findElements(this.chk_listaMarca);
       System.out.println(marcas.size());
       
       //realizo recorrido de la lista de opciones en marca a aplicar en filtro

           WebElement elementoOpcion;

            /*Esta exepción corresponde a que el elemento se pierde después de la primera iteración,
                en caso de que se vaya a buscar por más de una marca*/
            try{
                elementoOpcion = driver.findElement(By.xpath("//*[@id='brand']/li/a/span[contains(text(),'"+this.opcionMarca+"')]")) ;
            }catch(org.openqa.selenium.StaleElementReferenceException ex){
                elementoOpcion = driver.findElement(By.xpath("//*[@id='brand']/li/a/span[contains(text(),'"+this.opcionMarca+"')]")) ;
            }
           
           if(marcas.contains(elementoOpcion)){
               Thread.sleep(3000);
               
               try{
                    javascript.executeScript("arguments[0].scrollIntoView();",elementoOpcion);
                }catch(org.openqa.selenium.JavascriptException ex){
                    javascript.executeScript("arguments[0].scrollIntoView();",elementoOpcion);
                }
                 
               if(elementoOpcion.isDisplayed()){
                   System.out.println("Se visualiza");
                   
                   elementoOpcion.click();
                   
                   Thread.sleep(5000);
                } 
            }

       //confirmo que se haya quedado marcada, en caso contrario lo indico
       this.confirmaFiltro(this.opcionMarca);
    }

   public void aplicarFiltroColor() throws InterruptedException{
      
       JavascriptExecutor javascript = (JavascriptExecutor) this.driver;
    
       //Despliego los colores si es que exite en la página
        try{
            WebElement elemetPaginaColor = driver.findElement(this.elementoColorPagina);
            javascript.executeScript("arguments[0].scrollIntoView();",elemetPaginaColor);
            Thread.sleep(1000);
            elemetPaginaColor.click();
            Thread.sleep(500);
            this.colores = driver.findElements(this.chk_listaColor);
            System.out.println("Cantidad de colores disponibles: " + colores.size());
        }catch(org.openqa.selenium.JavascriptException ex){
            System.out.println("No fue posible conseguir el elemento asociado al color");
        }

       //realizo recorrido de la lista de opciones de color a aplicar en filtro
       
           System.out.println("Color a buscar: " + this.opcionColor);
           WebElement elementoOpcion;

            /*Esta exepción corresponde a que el elemento se pierde después de la primera iteración,
                en caso de que se vaya a buscar por más de una marca*/
            try{
                elementoOpcion = driver.findElement(By.xpath("//*[@class='color-name-refinement' and contains(text(),'"+ this.opcionColor +"')]")) ;
            }catch(org.openqa.selenium.StaleElementReferenceException ex){
                elementoOpcion = driver.findElement(By.xpath("//*[@class='color-name-refinement' and contains(text(),'"+ this.opcionColor +"')]")) ;
            }
           System.out.println("texto del color " + elementoOpcion.getText());
           
            Thread.sleep(3000);

            if(elementoOpcion.isDisplayed()){
                System.out.println("Se visualiza");
                elementoOpcion.click();
                Thread.sleep(5000);
            }
                    
            //validar que la opción haya quedado aplicada
            this.confirmaFiltro(this.opcionColor); 
    }
   
   private void confirmaFiltro(String filtroAp){
       this.filtros = driver.findElements(validoFiltro);
       if(this.filtros.size()>1){
              WebElement textoFiltroAplicado = driver.findElement(By.xpath("//div[@class='clear-refinement']/span[contains(text(),'"+ filtroAp +"')]"));
              Assert.assertEquals(textoFiltroAplicado.getText(), filtroAp.toLowerCase());
        }
       
   }
      
}
