package com.org.backend.dto;

import lombok.Getter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public enum CodeError {
    NAME_ALREADY_EXISTS("ERR001", "Le nom existe déjà"),
    LIBELLE_NOT_SPECIFIE("ERR039", "Le libellé n'est pas spécifié"),
    LIBELLE_ALREADY_EXISTS("ERR040", "Libellé déjà existant"),
    PRODUCT_NOT_FOUND("ERR002", "Produit non trouvé"),
    SYSTEM_ERROR("ERR007", "Une erreur est survenue, veuillez réessayer ultérieurement. "),
    PERSONNE_NOT_FOUND("ERR003","Personne non trouvé" );

    private final String code;
    private final String message;

    CodeError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorResponse toErrorResponse(Exception ex) {
        return new ErrorResponse(
            code,
            message,
            ex != null ? ex.getMessage() : null,
            LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
        );
    }

    @Getter
    public static class ErrorResponse {
        private final String code;
        private final String message;
        private final String detail;
        private final String timestamp;

        public ErrorResponse(String code, String message, String detail, String timestamp) {
            this.code = code;
            this.message = message;
            this.detail = detail;
            this.timestamp = timestamp;
        }
    }
}

