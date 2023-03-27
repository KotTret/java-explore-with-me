package ru.practicum.ewm.base.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.ewm.base.exception.ConditionsNotMetException;
import ru.practicum.ewm.base.exception.ConflictException;
import ru.practicum.ewm.base.exception.NotFoundException;
import ru.practicum.ewm.base.exception.error.ApiError;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleForbiddenException(final ConflictException e) {
        log.error(e.getLocalizedMessage(), e.getMessage());
        return new ApiError(
                HttpStatus.CONFLICT.toString(),
                "Integrity constraint has been violated.",
                e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleForbiddenException(final ConditionsNotMetException e) {
        log.error(e.getLocalizedMessage(), e.getMessage());
        return new ApiError(
                HttpStatus.CONFLICT.toString(),
                "For the requested operation the conditions are not met.",
                e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFoundException(final NotFoundException e) {
        log.error(e.getLocalizedMessage(), e.getMessage());
        return new ApiError(
                HttpStatus.NOT_FOUND.toString(),
                "The required object was not found.",
                e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleBlankException(final MethodArgumentNotValidException e) {
        log.error(e.getLocalizedMessage(), e.getMessage());
        String field = Objects.requireNonNull(e.getFieldError()).getField();

        return new ApiError(
                HttpStatus.BAD_REQUEST.toString(),
                "Incorrectly made request.",
                String.format("Field: %s. Error: must not be blank. Value: %s", field, e.getFieldValue(field)));
    }

/*    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleBadRequestException(final BadRequestException e) {
        log.info("400 {}", e.getMessage());
        return new ErrorResponse(
                "BAD_REQUEST",
                "Invalid request parameter",
                e.getMessage());
    }*/

/*    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleRuntimeException(final Throwable e) {
        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        printWriter.flush();

        String stackTrace = writer.toString();

        log.warn("500 INTERNAL_SERVER_ERROR; message: {}; stackTrace: {}", e.getMessage(), stackTrace);
        return new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                stackTrace,
                e.getMessage()
        );
    }*/
}
