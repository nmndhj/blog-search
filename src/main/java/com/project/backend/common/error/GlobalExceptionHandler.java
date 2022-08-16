package com.project.backend.common.error;

import com.project.backend.common.dto.ErrorResponseDTO;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    public ResponseEntity<Object> handleExceptionInternal(HttpStatus httpStatus, String errorCode, String msg){
        return new ResponseEntity<>(new ErrorResponseDTO(httpStatus, new ErrorResponse(errorCode,msg)),httpStatus);
    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handle(Exception ex,
//                                         HttpServletRequest request, HttpServletResponse response) {
//        System.out.println("ex = " + ex + ", request = " + request + ", response = " + response.getStatus());
//        return new ResponseEntity<>(new ErrorResponse("tt",ex.getMessage()),HttpStatus.OK);
////        return handleExceptionInternal(new ErrorResponse("00",ex.getMessage()),response.getStatus());
//    }

    /**
     * Biz Exception 처리
     * @param e
     * @return
     */
    @ExceptionHandler(value= BizException.class)
    public ResponseEntity<Object> handleBizEx(final BizException e){
        return new ResponseEntity<>(new ErrorResponse(e.errorCode),HttpStatus.OK);
    }

    /**
     * JWT Exception 처리
     * @param e
     * @return
     */
    @ExceptionHandler(value= JwtException.class)
    public ResponseEntity<Object> handleJwtBizEx(final BizException e){
        return new ResponseEntity<>(new ErrorResponse(e.errorCode),HttpStatus.UNAUTHORIZED);
    }

}
