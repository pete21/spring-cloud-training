package pl.training.cloud.payments.adapters.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.training.cloud.payments.application.PaymentNotFoundException;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice(basePackageClasses = {GetPaymentController.class, ProcessPaymentController.class})
@Log
@RequiredArgsConstructor
public class PaymentsExceptionHandler {

    private static final String SEPARATOR = ": ";
    private final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> onException(Exception exception, Locale locale) {
        log.warning("Exception: " + exception.getMessage());
        return createResponse(exception, INTERNAL_SERVER_ERROR, locale);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDto> onValidationException(MethodArgumentNotValidException exception, Locale locale) {
        var errors = getErrors(exception);
        var description = prepareDescription(mapExceptionToKey(exception), new Object[] { errors }, locale);
        return ResponseEntity.badRequest()
                .body(new ExceptionDto(description, LocalDateTime.now()));
    }

    private String getErrors(MethodArgumentNotValidException exception) {
        return exception.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + SEPARATOR + error.getDefaultMessage())
                .collect(joining());
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<ExceptionDto> onPaymentNotFound(PaymentNotFoundException exception, Locale locale) {
        return createResponse(exception, NOT_FOUND, locale);
    }

    private ResponseEntity<ExceptionDto> createResponse(Exception exception, HttpStatus status, Locale locale) {
        var description = prepareDescription(mapExceptionToKey(exception), new String[] {}, locale);

        return ResponseEntity.status(status)
                .body(new ExceptionDto(description, LocalDateTime.now()));
    }

    private String mapExceptionToKey(Exception exception) {
        return exception.getClass().getSimpleName();
    }

    private String prepareDescription(String key, Object[] parameters, Locale locale) {
        String description;
        try {
            description = messageSource.getMessage(key, parameters, locale);
        } catch (NoSuchMessageException exception) {
            description = key;
        }
        return description;
    }

}
