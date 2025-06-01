package com.posfiap.techfood.controllers.handlers;

import com.posfiap.techfood.exceptions.ResourceNotFoundException;
import com.posfiap.techfood.models.dto.errors.ArgumentsMissingDTO;
import com.posfiap.techfood.models.dto.errors.ResourceNotFoundDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceNotFoundDTO> handlerResourceNotFound(ResourceNotFoundException e) {
        var status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status.value()).body(new ResourceNotFoundDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ArgumentsMissingDTO> handlerArgumentsMissing(IllegalArgumentException e) {
        var status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status.value()).body(new ArgumentsMissingDTO(e.getMessage(), status.value()));
    }
}
