package Busqueda.Auxiliar;

import java.io.File;
import java.io.IOException;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author sentrauser3260
 */
public class CapturarPantalla {
    public static void hacerCapturaPantalla(WebDriver driver, String nombreCaptura){
        File myScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try{
                FileUtils.copyFile(myScreenshot, new File(nombreCaptura + '_' + System.currentTimeMillis()+".png"));
            }catch(IOException e){
                e.printStackTrace();
            }
    }
}
