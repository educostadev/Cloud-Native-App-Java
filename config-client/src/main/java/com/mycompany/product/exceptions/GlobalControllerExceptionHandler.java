package com.mycompany.product.exceptions;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	void handleBadRequest(BadRequestException ex, HttpServletResponse response) throws IOException {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		if (BadRequestException.ID_NOT_FOUND == ex.errCode) {
			status = HttpStatus.NOT_FOUND;
		}
		response.sendError(
				status.value(),
				MessageFormat.format("Error code {0} - {1}", ex.errCode, ex.getMessage())
		);
	}
}
