package de.tjackiz.gatewayService.exception.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ApiValidationError extends ApiSubError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    public ApiValidationError(String object, String message, Object rejectedValue, String field) {
        this.object = object;
        this.message = message;
        this.rejectedValue = rejectedValue;
        this.field = field;
    }
}
