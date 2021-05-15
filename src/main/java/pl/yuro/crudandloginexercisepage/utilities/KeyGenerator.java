package pl.yuro.crudandloginexercisepage.utilities;

import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class KeyGenerator {

	
	
	
	public String keyGeneratorPswdBase(String password) {
		
		String forKeyGen = password + getCurrentTimeUsingDate();
		String pubKey = DigestUtils.sha256Hex(forKeyGen);
		return pubKey;
	}
	
	private String getCurrentTimeUsingDate() {
	    Date date = new Date();
	    String strDateFormat = "hh:mm:ss a";
	    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	    String formattedDate= dateFormat.format(date);
	    return formattedDate;
	}
}
