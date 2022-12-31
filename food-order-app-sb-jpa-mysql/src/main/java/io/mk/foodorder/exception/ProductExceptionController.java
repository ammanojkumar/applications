package io.mk.foodorder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionController {

	@ExceptionHandler(value = PaymentFailedException.class)
	public ResponseEntity<ErrorResponse> handlePaymentFailedException(Exception ex) {
		ErrorResponse er = new ErrorResponse(ex.getMessage());
		ResponseEntity<ErrorResponse> resp = new ResponseEntity<ErrorResponse>(er, HttpStatus.EXPECTATION_FAILED);
		return resp;
	}
}
