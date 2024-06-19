package Controlador;

import java.awt.Toolkit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

//Clase para limitar el tamanio de los JTextField + beep.
public class LimitDocumentFilter extends DocumentFilter {

    private int limit;
    private int tipoFiltro; //0 es para permitir TODO, 1 es para permitir solo numeros.

    public LimitDocumentFilter(int limit, int tipoFiltro) {
        if (limit <= 0) {
            throw new IllegalArgumentException("El limit no puede ser <= 0");
        }
        this.limit = limit;
        this.tipoFiltro = tipoFiltro;
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        int currentLength = fb.getDocument().getLength();
        int overLimit = (currentLength + text.length()) - limit - length;
        if("".equals(text)){
            super.remove(fb, offset, length);
        }
        if (overLimit > 0) {
            Toolkit.getDefaultToolkit().beep();
            text = text.substring(0, text.length() - overLimit);
        } else 
            if(tipoFiltro==0){
                if (text.length() > 0) {
                    super.replace(fb, offset, length, text, attrs); 
                }
            } else
                if(tipoFiltro==1){
                    Pattern regEx = Pattern.compile("\\d*");
                    Matcher matcher = regEx.matcher(text);
                    if (text.length() > 0 && matcher.matches()) {
                        super.replace(fb, offset, length, text, attrs); 
                    } else
                        Toolkit.getDefaultToolkit().beep();
                }
    }
}
