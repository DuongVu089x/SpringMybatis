package com.dav.mybatis.common.custom;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.dav.mybatis.common.util.Constants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomDateDeserializer.
 */
public class CustomDateDeserializer extends JsonDeserializer<Date> {

	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext)
	 */
	@Override
	public Date deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext) throws IOException {
		SimpleDateFormat format = new SimpleDateFormat(Constants.STR_FORMAT_DATE);
		String date = jsonparser.getText();
		try {
			return format.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
