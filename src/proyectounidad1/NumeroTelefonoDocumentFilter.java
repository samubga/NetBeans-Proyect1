/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectounidad1;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;


/**
 *
 * @author samuel
 */
public class NumeroTelefonoDocumentFilter extends DocumentFilter {
      @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            // Asegúrate de que solo se permitan números y caracteres especiales
            if (string.matches("[\\d\\-().\\s]+")) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            // Asegúrate de que solo se permitan números y caracteres especiales
            if (text.matches("[\\d\\-().\\s]+")) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    
    }
