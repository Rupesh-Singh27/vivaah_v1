package com.cg.marriageceremony.exceptionhandler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.cg.marriageceremony.exceptions.CatererDoesNotExistException;
import com.cg.marriageceremony.exceptions.FieldCannotBeEmptyException;
import com.cg.marriageceremony.exceptions.FieldCannotBeEmptyExceptionForCart;
import com.cg.marriageceremony.exceptions.FieldCannotBeEmptyExceptionForMakeup;
import com.cg.marriageceremony.exceptions.FieldCannotBeEmptyExceptionForPayment;
import com.cg.marriageceremony.exceptions.FieldCannotBeEmptyExceptionForPhotography;
import com.cg.marriageceremony.exceptions.FieldCannotBeEmptyExceptionForVendor;
import com.cg.marriageceremony.exceptions.FieldCannotBeEmptyExceptionForVenue;
import com.cg.marriageceremony.exceptions.ItemDoesNotExistInCartException;
import com.cg.marriageceremony.exceptions.MakeupDoesNotExistException;
import com.cg.marriageceremony.exceptions.NoCatererPresentException;
import com.cg.marriageceremony.exceptions.NoItemsPresentInCartException;
import com.cg.marriageceremony.exceptions.NoMakeupPresentException;
import com.cg.marriageceremony.exceptions.NoPhotographyPresentException;
import com.cg.marriageceremony.exceptions.NoVendorPresentException;
import com.cg.marriageceremony.exceptions.NoVenuePresentException;
import com.cg.marriageceremony.exceptions.PaymentDoesNotExistException;
import com.cg.marriageceremony.exceptions.PhotographyDoesNotExistException;
import com.cg.marriageceremony.exceptions.VendorDoesNotExistException;
import com.cg.marriageceremony.exceptions.VenueDoesNotExistException;
import com.cg.marriageceremony.response.ApiError;
import com.cg.marriageceremony.response.ExceptionResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	/* Caterer specific exceptions */
	
	@ExceptionHandler(CatererDoesNotExistException.class)
	public ResponseEntity<?> handleVendorDoesNotExistException(CatererDoesNotExistException vendorDoesNotExistException, WebRequest webRequest){
		ExceptionResponse exceptionResponse = new ExceptionResponse(vendorDoesNotExistException.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	//for list
	@ExceptionHandler(FieldCannotBeEmptyException.class)
	public ResponseEntity<?> handleFieldCannotBeEmptyException(FieldCannotBeEmptyException fieldCannotBeEmptyException, WebRequest webRequest){
		ExceptionResponse exceptionResponse = new ExceptionResponse(fieldCannotBeEmptyException.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	//for search by ID
	@ExceptionHandler(NoCatererPresentException.class)
	public ResponseEntity<?> handleNoCatererPresentException(NoCatererPresentException noCatererPresentException, WebRequest webRequest){
		ExceptionResponse exceptionResponse = new ExceptionResponse(noCatererPresentException.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	/* Makeup specific exceptions */
	
	@ExceptionHandler(FieldCannotBeEmptyExceptionForMakeup.class)
	public ResponseEntity<?> handleFieldCannotBeEmptyExceptionForMakeup(FieldCannotBeEmptyExceptionForMakeup fieldCannotBeEmptyExceptionForMakeup, WebRequest webRequest){
		ExceptionResponse exceptionResponse = new ExceptionResponse(fieldCannotBeEmptyExceptionForMakeup.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	//for List
	@ExceptionHandler(NoMakeupPresentException.class)
	public ResponseEntity<?> handleNoMakeupPresentException(NoMakeupPresentException noMakeupPresentException, WebRequest webRequest){
		ExceptionResponse exceptionResponse = new ExceptionResponse(noMakeupPresentException.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	//for search by ID
	@ExceptionHandler(MakeupDoesNotExistException.class)
	public ResponseEntity<?> handleMakeupDoesNotExistException(MakeupDoesNotExistException makeupDoesNotExistException, WebRequest webRequest){
		ExceptionResponse exceptionResponse = new ExceptionResponse(makeupDoesNotExistException.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	/* Makeup specific exceptions */
	
	@ExceptionHandler(FieldCannotBeEmptyExceptionForPhotography.class)
	public ResponseEntity<?> handleFieldCannotBeEmptyExceptionForPhotography(FieldCannotBeEmptyExceptionForPhotography fieldCannotBeEmptyExceptionForPhotography, WebRequest webRequest){
		ExceptionResponse exceptionResponse = new ExceptionResponse(fieldCannotBeEmptyExceptionForPhotography.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	//For list
	@ExceptionHandler(NoPhotographyPresentException.class)
	public ResponseEntity<?> handleNoPhotographyPresentException(NoPhotographyPresentException noPhotographyPresentException, WebRequest webRequest){
		ExceptionResponse exceptionResponse = new ExceptionResponse(noPhotographyPresentException.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	//for search by ID
	@ExceptionHandler(PhotographyDoesNotExistException.class)
	public ResponseEntity<?> handlePhotographyDoesNotExistException(PhotographyDoesNotExistException photographyDoesNotExistException, WebRequest webRequest){
		ExceptionResponse exceptionResponse = new ExceptionResponse(photographyDoesNotExistException.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	/* Venue specific exceptions */
	
	@ExceptionHandler(FieldCannotBeEmptyExceptionForVenue.class)
	public ResponseEntity<?> handleFieldCannotBeEmptyExceptionForVenue(FieldCannotBeEmptyExceptionForVenue fieldCannotBeEmptyExceptionForVenue, WebRequest webRequest){
		ExceptionResponse exceptionResponse = new ExceptionResponse(fieldCannotBeEmptyExceptionForVenue.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	//For list
	@ExceptionHandler(NoVenuePresentException.class)
	public ResponseEntity<?> handleNoVenuePresentException(NoVenuePresentException noVenuePresentException, WebRequest webRequest){
		ExceptionResponse exceptionResponse = new ExceptionResponse(noVenuePresentException.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	/* Vendor specific exceptions */
	
	@ExceptionHandler(FieldCannotBeEmptyExceptionForVendor.class)
	public ResponseEntity<?> handleFieldCannotBeEmptyExceptionForVendor(FieldCannotBeEmptyExceptionForVendor fieldCannotBeEmptyExceptionForVendor, WebRequest webRequest){
		ExceptionResponse exceptionResponse = new ExceptionResponse(fieldCannotBeEmptyExceptionForVendor.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	//For list
	@ExceptionHandler(NoVendorPresentException.class)
	public ResponseEntity<?> handleNoVendorPresentException(NoVendorPresentException noVendorPresentException, WebRequest webRequest){
		ExceptionResponse exceptionResponse = new ExceptionResponse(noVendorPresentException.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	//for search by ID
	@ExceptionHandler(VendorDoesNotExistException.class)
	public ResponseEntity<?> handleVendorDoesNotExistException(VendorDoesNotExistException vendorDoesNotExistException, WebRequest webRequest){
		ExceptionResponse exceptionResponse = new ExceptionResponse(vendorDoesNotExistException.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	
/* Payment specific exceptions */
	
	@ExceptionHandler(FieldCannotBeEmptyExceptionForPayment.class)
	public ResponseEntity<?> handleFieldCannotBeEmptyExceptionForPayment(FieldCannotBeEmptyExceptionForPayment fieldCannotBeEmptyExceptionForPayment, WebRequest webRequest){
		ExceptionResponse exceptionResponse = new ExceptionResponse(fieldCannotBeEmptyExceptionForPayment.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	//for search by ID
	@ExceptionHandler(PaymentDoesNotExistException.class)
	public ResponseEntity<?> handlePaymentDoesNotExistException(PaymentDoesNotExistException paymentDoesNotExistException, WebRequest webRequest){
		ExceptionResponse exceptionResponse = new ExceptionResponse(paymentDoesNotExistException.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	
	/* Cart specific exceptions */
	
	@ExceptionHandler(FieldCannotBeEmptyExceptionForCart.class)
	public ResponseEntity<?> FieldCannotBeEmptyExceptionForCart(FieldCannotBeEmptyExceptionForCart fieldCannotBeEmptyExceptionForCart, WebRequest webRequest){
		ExceptionResponse exceptionResponse = new ExceptionResponse(fieldCannotBeEmptyExceptionForCart.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	//For list
	@ExceptionHandler(NoItemsPresentInCartException.class)
	public ResponseEntity<?> handleNoItemsPresentInCartException(NoItemsPresentInCartException noItemsPresentInCartException, WebRequest webRequest){
		ExceptionResponse exceptionResponse = new ExceptionResponse(noItemsPresentInCartException.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	
	//Exceptions other than above
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handle(Exception e){
		e.printStackTrace();
		ApiError error=new ApiError();
		error.setMsg(e.getMessage());
		ResponseEntity<ApiError> resEntity=new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
		return resEntity;
	}
}
