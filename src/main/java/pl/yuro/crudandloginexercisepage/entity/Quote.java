package pl.yuro.crudandloginexercisepage.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {
	
	private float price;

	private float percent_change_1h;
	private float percent_change_24h;
	private float percent_change_7d;
	private long market_cap;
	private String last_updated;
	
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}

	public float getPercent_change_1h() {
		return percent_change_1h;
	}
	public void setPercent_change_1h(float percent_change_1h) {
		this.percent_change_1h = percent_change_1h;
	}
	public float getPercent_change_24h() {
		return percent_change_24h;
	}
	public void setPercent_change_24h(float percent_change_24h) {
		this.percent_change_24h = percent_change_24h;
	}
	public float getPercent_change_7d() {
		return percent_change_7d;
	}
	public void setPercent_change_7d(float percent_change_7d) {
		this.percent_change_7d = percent_change_7d;
	}
	public long getMarket_cap() {
		return market_cap;
	}
	public void setMarket_cap(long market_cap) {
		this.market_cap = market_cap;
	}
	public String getLast_updated() {
		return last_updated;
	}
	public void setLast_updated(String last_updated) {
		this.last_updated = last_updated;
	}
}
