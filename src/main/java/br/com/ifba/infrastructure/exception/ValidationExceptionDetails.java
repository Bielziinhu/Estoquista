package br.com.ifba.infrastructure.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ValidationExceptionDetails {

    private LocalDateTime timestamp;
    private int status;
    private String title;
    private String details;
    private String developerMessage;
    private String fields;
    private String fieldsMessage;

    // Construtor privado, necessário
    private ValidationExceptionDetails(Builder builder) {
        this.timestamp = builder.timestamp;
        this.status = builder.status;
        this.title = builder.title;
        this.details = builder.details;
        this.developerMessage = builder.developerMessage;
        this.fields = builder.fields;
        this.fieldsMessage = builder.fieldsMessage;
    }

    // Padrão builder
    public static class Builder {
        private LocalDateTime timestamp;
        private int status;
        private String title;
        private String details;
        private String developerMessage;
        private String fields;
        private String fieldsMessage;

        public Builder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder details(String details) {
            this.details = details;
            return this;
        }

        public Builder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public Builder fields(String fields) {
            this.fields = fields;
            return this;
        }

        public Builder fieldsMessage(String fieldsMessage) {
            this.fieldsMessage = fieldsMessage;
            return this;
        }

        public ValidationExceptionDetails build() {
            return new ValidationExceptionDetails(this);
        }
    }

    // Inicialização
    public static Builder builder() {
        return new Builder();
    }
}
