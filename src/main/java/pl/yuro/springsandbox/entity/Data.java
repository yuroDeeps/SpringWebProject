package pl.yuro.springsandbox.entity;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

private int id;
	
	private String name;
	
	private String symbol;
	private String slug;
	private long cmc_rank;
	private long num_market_pairs;


	private String last_updated;
	private String date_added;
	private List<String> tags;

	private HashMap<String, Quote> quote;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public long getCmc_rank() {
		return cmc_rank;
	}
	public void setCmc_rank(int cmc_rank) {
		this.cmc_rank = cmc_rank;
	}
	public long getNum_market_pairs() {
		return num_market_pairs;
	}
	public void setNum_market_pairs(int num_market_pairs) {
		this.num_market_pairs = num_market_pairs;
	}


	public String getLast_updated() {
		return last_updated;
	}
	public void setLast_updated(String last_updated) {
		this.last_updated = last_updated;
	}
	public String getDate_added() {
		return date_added;
	}
	public void setDate_added(String date_added) {
		this.date_added = date_added;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public HashMap<String, Quote> getQuote() {
		return quote;
	}
	public void setQuote(HashMap<String, Quote> quote) {
		this.quote = quote;
	}
	
}
