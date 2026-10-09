package in.praveenit.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.praveenit.bindings.User;
import in.praveenit.entity.CityEntity;
import in.praveenit.entity.CountryEntity;
import in.praveenit.entity.StateEntity;
import in.praveenit.entity.UserEntity;
import in.praveenit.repositories.CityRepository;
import in.praveenit.repositories.CountryRepository;
import in.praveenit.repositories.StateRepository;
import in.praveenit.repositories.UserRepository;
import in.praveenit.util.EmailUtils;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CountryRepository countryRepo;

	@Autowired
	private StateRepository stateRepo;

	@Autowired
	private CityRepository cityRepo;
	
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public boolean uniqueEmail(String email) {
		UserEntity userEntity = userRepo.findByUserEmail(email);
		return userEntity == null;
	}

	@Override
	public Map<Integer, String> getCountries() {
		List<CountryEntity> findAll = countryRepo.findAll();

		Map<Integer, String> countryMap = new HashMap<>();
		for (CountryEntity entity : findAll) {
			countryMap.put(entity.getCountryId(), entity.getCountryname());

		}
		return countryMap;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		List<StateEntity> statesLidt = stateRepo.findByCountryId(countryId);

		Map<Integer, String> statesMap = new HashMap<>();

		for (StateEntity state : statesLidt) {
			statesMap.put(state.getStateId(), state.getStatename());
		}
		return statesMap;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		List<CityEntity> citiesList = cityRepo.findByStateId(stateId);

		Map<Integer, String> cityMap = new HashMap<>();

		for (CityEntity city : citiesList) {
			cityMap.put(city.getCityId(), city.getCityName());
		}
		return cityMap;
	}

	@Override
	public boolean registerUser(User user) {
		
		user.setUserPwd(generateTempPwd());
		user.setUserAccStatus("LOCKED");
		
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(user, entity);
		UserEntity save = userRepo.save(entity);
		
		if(null != save.getClass()) {
			return sendRegEmail(user);
		}
		
		return false;
	}

	private String generateTempPwd() {

		String tempPwd = null;
		int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 6;
	    Random random = new Random();

	    tempPwd = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
		return tempPwd;

	}
	
	private boolean sendRegEmail(User user) {
		boolean emailSent = false;
		String subject = "User Registration Success";
		String body = readMailBody("UNLOCK-ACC-EMAIL-BODY-TEMPLATE.txt",user);
		emailUtils.sendEmail(subject, body, user.getUserEmail());
		return emailSent;
	}
	
	public String readMailBody(String fileName, User user) {
		String mailBody = null;
		
		StringBuffer buffer = new StringBuffer();
		
		 Path path = Paths.get(fileName);
		 
	        try (Stream<String> stream = Files.lines(path)) {
	            stream.forEach(line ->{
	            	buffer.append(line);
	            	
	            });
	            
	            mailBody = buffer.toString();
	            mailBody = mailBody.replace("{FNAME}", user.getUserFname());
	            mailBody = mailBody.replace("{EMAIL}", user.getUserEmail());
	            mailBody = mailBody.replace("{TEMP-PWD}", user.getUserPwd());
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
		
		return mailBody;
	}

}
