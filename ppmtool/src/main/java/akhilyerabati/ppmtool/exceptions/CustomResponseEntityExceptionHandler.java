package akhilyerabati.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice //mechanism that helps you break away from having exception handlers that are controller specific. Gives you  global expection handling controllers
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
@ExceptionHandler
    public final ResponseEntity<Object> handleProjectIdException(ProjectIdException ex, WebRequest request){
    ProjectIdExceptionResponse exceptionResponse= new ProjectIdExceptionResponse(ex.getMessage());
    return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);

}
}
