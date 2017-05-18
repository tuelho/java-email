

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tuelho 2009
 */
public class Configuracao {

   // private static final String LOGO = "/br/com/idealize/imagem/icone_sistema.png";
    private static final String CAMINHO_PROPERTIES = "configuracao/sistema.properties";
//    private static final String CAMINHO_PROPERTIES = "configuracao/sistema-web.properties";

    public Configuracao() {
       
    }

    public static String getProperties(String campo){
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(CAMINHO_PROPERTIES));
            return prop.getProperty(campo);
        } catch (IOException ex) {
            Logger.getLogger(Configuracao.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
}