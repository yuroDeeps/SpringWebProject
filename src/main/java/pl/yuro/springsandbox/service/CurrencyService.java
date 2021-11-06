package pl.yuro.springsandbox.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pl.yuro.springsandbox.entity.CryptoCurrency;
import pl.yuro.springsandbox.entity.Data;
import pl.yuro.springsandbox.entity.Quote;
import pl.yuro.springsandbox.entity.USDAndEURToPLN;

@Service
public class CurrencyService {

	private static String apiKey = "";
	
	@Autowired
	private RestTemplate restTemplate;
	
	public void currency(Model model) throws JsonMappingException, JsonProcessingException {

		
		ObjectMapper objectMapper = new ObjectMapper();

		
		//skakuj ten klucz jak wrzuczsz na gita
		final String uri1 = "http://data.fixer.io/api/latest?access_key=&symbols=USD,PLN";

		String result1 = restTemplate.getForObject(uri1, String.class);

		USDAndEURToPLN currency = objectMapper.readValue(result1, USDAndEURToPLN.class);

		HashMap<String, Float>rates = currency.getRates();

		Float usd = rates.get("USD");

		Float cenaUsd = rates.get("PLN")/usd;


		cenaUsd = (float) Math.round(cenaUsd*100)/100;

		model.addAttribute("USD",cenaUsd);

		Float cenaEuro = rates.get("PLN");
	
		cenaEuro = (float) Math.round(cenaEuro*100)/100;

		model.addAttribute("EUR",cenaEuro);
		
		
		// add to model cryptocurrences
		String uri = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
	    List<NameValuePair> paratmers = new ArrayList<NameValuePair>();

	    try {
	      String result = makeAPICall(uri, paratmers);
	      
	      
	      System.out.println(result);
	      
	      
	      CryptoCurrency cryptoCurrency = objectMapper.readValue(result, CryptoCurrency.class);
	   // add BTC currency
	      List<Data> datas = cryptoCurrency.getData();
	      
	      HashMap<String, Quote> quotes = datas.get(0).getQuote();
	      
	      Quote quoteBTCUSD = quotes.get("USD");
	      
	      model.addAttribute("BTC", quoteBTCUSD.getPrice()*cenaUsd);
	      
	      // add eth currency   	      
	      HashMap<String, Quote> quotes2 = datas.get(1).getQuote();
	      
	      Quote quoteETHUSD = quotes2.get("USD");
	      
	      model.addAttribute("ETH", quoteETHUSD.getPrice()*cenaUsd);
	      
	    } catch (IOException e) {
	      System.out.println("Error: cannont access content - " + e.toString());
	    } catch (URISyntaxException e) {
	      System.out.println("Error: Invalid URL " + e.toString());
	    }
	    
	    
	    
	  }
	
	
	public static String makeAPICall(String uri, List<NameValuePair> parameters)
		      throws URISyntaxException, IOException {
		    String response_content = "";

		    URIBuilder query = new URIBuilder(uri);
		    query.addParameters(parameters);

		    CloseableHttpClient client = HttpClients.createDefault();
		    HttpGet request = new HttpGet(query.build());

		    request.setHeader(HttpHeaders.ACCEPT, "application/json");
		    request.addHeader("X-CMC_PRO_API_KEY", apiKey);

		    CloseableHttpResponse response = client.execute(request);

		    try {
		      System.out.println(response.getStatusLine());
		      HttpEntity entity = response.getEntity();
		      response_content = EntityUtils.toString(entity);
		      EntityUtils.consume(entity);
		    } finally {
		      response.close();
		    }

		    return response_content;
		  }

}
