package com.sparta.morningworkout.exceptionHandler;

import com.sparta.morningworkout.dto.StatusResponseDto;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SecurityException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.charset.Charset;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {
    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<StatusResponseDto> illegalArgumentExceptionHandler(IllegalArgumentException e) {
        StatusResponseDto statusResponseDto = new StatusResponseDto(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return ResponseEntity.badRequest().headers(httpHeaders).body(statusResponseDto);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StatusResponseDto> methodValidException(MethodArgumentNotValidException e){
        StatusResponseDto statusResponseDto = new StatusResponseDto(HttpStatus.BAD_REQUEST.value(),e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        return ResponseEntity.badRequest().headers(httpHeaders).body(statusResponseDto);
    }
    @ExceptionHandler(JwtException.class)
    private ResponseEntity<StatusResponseDto> JwtExceptionHandler(JwtException e){
        StatusResponseDto statusResponseDto = new StatusResponseDto(HttpStatus.UNAUTHORIZED.value(),e.getMessage());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(statusResponseDto,httpHeaders,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SecurityException.class)
    private ResponseEntity<StatusResponseDto> SecurityExceptionHandler(SecurityException e){
        StatusResponseDto statusResponseDto = new StatusResponseDto(HttpStatus.UNAUTHORIZED.value(),e.getMessage());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(statusResponseDto,httpHeaders,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MalformedJwtException.class)
    private ResponseEntity<StatusResponseDto> MalformedJwtExceptionHandler(MalformedJwtException e){
        StatusResponseDto statusResponseDto = new StatusResponseDto(HttpStatus.UNAUTHORIZED.value(),e.getMessage());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(statusResponseDto,httpHeaders,HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(ConstraintViolationException.class)//max min 유효성 검증시 나오는 예외
    private ResponseEntity<StatusResponseDto> ConstraintViolationExceptionHandler(ConstraintViolationException e){
        String message = e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList()
                .get(0);
        StatusResponseDto statusResponseDto = new StatusResponseDto(HttpStatus.BAD_REQUEST.value(), message);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        return ResponseEntity.badRequest().headers(httpHeaders).body(statusResponseDto);
    }

}