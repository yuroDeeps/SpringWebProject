package pl.yuro.springsandbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import pl.yuro.springsandbox.service.CurrencyService;

@Controller
@RequestMapping("/currency")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyAndCryptoCurrency {
	

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CurrencyService currencyService;
	
	@GetMapping("/test")
	public String test(Model model) throws JsonMappingException, JsonProcessingException {

		currencyService.currency(model);
		
		return "currency.html";
	}

}
