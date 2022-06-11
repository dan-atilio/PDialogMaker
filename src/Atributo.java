import java.net.URL;
import javax.swing.ImageIcon;

/**
 * Versão atual e métodos gerais
 * @author Daniel Atilio
 * @version 1.01
 * @see https://atiliosistemas.com/
 * @obs Baseado no software Cafeteira do grande Fernando Anselmo - http://fernandoanselmo.orgfree.com/wordpress/?page_id=7
 */ 

public class Atributo {
    
    /** Versao atual */
    public final String CFVERSAO = "Versao 1.02";
    public final String REVISAO = "Junho de 2022";
    
    /** Devolve um objeto ImageIcon com determinada imagem
     * @param s String contendo o nome da imagem
     * @return Objeto ImageIcon
     */
    public ImageIcon getImage(String s) {
        URL url = getResource("images/" + s);
        if (url != null)
            return new ImageIcon(url);
        else
            return null;
    }
    
    private URL getResource(String s) {
        return ClassLoader.getSystemResource(s);
    }
}