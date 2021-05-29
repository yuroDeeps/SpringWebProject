package pl.yuro.crudandloginexercisepage.entity;

import java.util.HashMap;
import java.util.List;

import javax.persistence.Entity;

import org.springframework.stereotype.Service;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@EqualsAndHashCode
public class USDAndEURToPLN {
	
	
	private boolean success;
	private String timestamp;
	private String base;
	private String date;
	
	private HashMap<String, Float> rates;

	public boolean getSuccess() {
		return success;
	}
	public void setSucces(boolean succes) {
		this.success = succes;
	}
	public HashMap<String, Float> getRates() {
		return rates;
	}
	public void setRates(HashMap<String, Float> rates) {
		this.rates = rates;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	

	
}
