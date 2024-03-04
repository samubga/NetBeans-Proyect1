/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectounidad1;


import javax.swing.text.*;

/**
 *
 * @author samuel
 */
public class DateDocumentFilter extends DocumentFilter {
 @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (isValid(string)) {
            super.insertString(fb, offset, string, attr);
            updateText(fb);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (isValid(text)) {
            super.replace(fb, offset, length, text, attrs);
            updateText(fb);
        }
    }

    private void updateText(FilterBypass fb) throws BadLocationException {
        Document doc = fb.getDocument();
        String text = doc.getText(0, doc.getLength());
        String formattedText = formatText(text);
        fb.replace(0, doc.getLength(), formattedText, null);
    }

    private boolean isValid(String text) {
    return text.matches("\\d{0,4}(-\\d{0,2})?(-\\d{0,2})?");
}

    private String formatText(String text) {
        StringBuilder formattedText = new StringBuilder();
        int length = text.length();
        int maxLength = 10; // Longitud máxima del formato YYYY-MM-DD

        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);
            if (Character.isDigit(c)) {
                formattedText.append(c);
                if (formattedText.length() == 4 || formattedText.length() == 7) {
                    formattedText.append('-');
                }
            }
        }

        // Asegurar que no exceda la longitud máxima
        if (formattedText.length() > maxLength) {
            formattedText.setLength(maxLength);
        }

        return formattedText.toString();
    }

}
