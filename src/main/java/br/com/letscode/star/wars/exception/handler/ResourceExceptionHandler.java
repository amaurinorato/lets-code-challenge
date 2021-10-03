package br.com.letscode.star.wars.exception.handler;

import br.com.letscode.star.wars.dto.response.StandardErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebInputException;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardErrorResponse> validateException(MethodArgumentNotValidException e) {
        StandardErrorResponse dto = new StandardErrorResponse();
        dto.setCodeDescription(HttpStatus.METHOD_NOT_ALLOWED.name());
        dto.setMessage(e.getMessage());

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(dto);
    }

    @ExceptionHandler(ServerWebInputException.class)
    public ResponseEntity<StandardErrorResponse> validateException(ServerWebInputException e) {
        StandardErrorResponse dto = new StandardErrorResponse();
        dto.setCodeDescription(HttpStatus.BAD_REQUEST.name());
        dto.setMessage(e.getReason());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(dto);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<StandardErrorResponse> validateException(ResponseStatusException e) {
        StandardErrorResponse dto = new StandardErrorResponse();
        dto.setCodeDescription(e.getStatus().name());
        dto.setMessage(e.getReason());

        return ResponseEntity
                .status(e.getStatus())
                .body(dto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardErrorResponse> validateException(Exception e) {
        StandardErrorResponse dto = new StandardErrorResponse();
        dto.setCodeDescription(HttpStatus.INTERNAL_SERVER_ERROR.name());
        dto.setMessage("Generic Error");

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(dto);
    }

}