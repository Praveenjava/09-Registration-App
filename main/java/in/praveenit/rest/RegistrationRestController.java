package in.praveenit.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import in.praveenit.bindings.User;
import in.praveenit.service.RegistrationService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController

public class RegistrationRestController {
	@Autowired
	private RegistrationService regService;
	
	@GetMapping("/emailcheck/{email}")
	public String checkEmail(@PathVariable String email) {
		boolean uniqueEmail = regService.uniqueEmail(email);
		if(uniqueEmail) {
			return "UNIQUE";
		}else {
			return "DUPLICATE";
		}
	}
	@GetMapping("/countries")
	public Map<Integer, String> getCountries(){
		Map<Integer, String> countries = regService.getCountries();
		return countries;
		
	}
	
	@GetMapping("/states/{countryId}")
	public Map<Integer, String> getStates(@PathVariable Integer countryId){
		return regService.getStates(countryId);
		
	}
	
	@GetMapping("/cities/{stateId}")
	public Map<Integer, String> getCities(@PathVariable Integer stateId){
		Map<Integer, String> cities = regService.getCities(stateId);
		return cities;
	}
	
	
	@PostMapping("/saveuser")
	public String saveUser(@RequestBody User user) {
		boolean registerUser = regService.registerUser(user);
		if(registerUser) {
			return "SUCCESS";
		}else {
			return "FAIL";
		}
		
	}

}
