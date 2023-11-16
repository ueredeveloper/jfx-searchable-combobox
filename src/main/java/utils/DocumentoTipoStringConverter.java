package utils;

import javafx.util.StringConverter;
import models.DocumentoTipo;

public class DocumentoTipoStringConverter extends StringConverter<DocumentoTipo> {
    @Override
    public String toString(DocumentoTipo tipo) {
        if (tipo == null) {
            return "";
        } else {
            return tipo.getDt_descricao();
        }
    }

    @Override
    public DocumentoTipo fromString(String string) {
        // This method is not used for this example
        return null;
    }
}
