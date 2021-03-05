package com.example.evoluum1.converters;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

public class CsvConverter<T> extends AbstractHttpMessageConverter<T> {

	private final Logger logger = Logger.getLogger(CsvConverter.class.getName());
    private final Class<T> supportedType;

    public CsvConverter(MediaType mediaType,Class<T> supportedType) {
      super(mediaType);
      this.supportedType = supportedType;
    }

    @Override
    protected boolean supports(Class<?> classType) {
      return supportedType.isAssignableFrom(classType);
    }

    @Override
    public boolean canRead(Class<?> classType, MediaType mediaType) {
    	// For this demo, just write is enough.
    	logger.info("CSV converter will not be used for: "+classType.getName());
    	return false;
    }

    @Override
    protected T readInternal(Class<? extends T> classType, HttpInputMessage inputMessage) throws HttpMessageNotReadableException {
    	// For this demo, just write is enough.
    	logger.info("Trying to read to OutputStream. This shouldn't be happening");
      	throw new UnsupportedOperationException("Write only!");
    }

    @Override
    protected void writeInternal(T o, HttpOutputMessage outputMessage) throws HttpMessageNotWritableException, IOException {
    	logger.info("Writing OutputStream message to OutputStream of response");
    	ByteArrayOutputStream baos = (ByteArrayOutputStream) o;
    	baos.writeTo(outputMessage.getBody());
    }
  }