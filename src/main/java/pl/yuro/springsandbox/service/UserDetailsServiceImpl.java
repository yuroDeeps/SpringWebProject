package pl.yuro.springsandbox.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import pl.yuro.springsandbox.dto.PasswdChangeDTO;
import pl.yuro.springsandbox.dto.UserDTO;
import pl.yuro.springsandbox.entity.Role;
import pl.yuro.springsandbox.entity.User;
import pl.yuro.springsandbox.repository.RoleRepository;
import pl.yuro.springsandbox.repository.UserRepository;
import pl.yuro.springsandbox.utilities.KeyGenerator;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private VerificationTokenService verificationTokenService;
    @Autowired
    private EmailService emailService;
	
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
         
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new MyUserDetails(user);
    }
    
    @Transactional
    public <Optional>User loadUserByEmail(String email)
            throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email);      
         
        return user;
    }
    
    public boolean userExists(String email,String username) {
    	User user = loadUserByEmail(email);
    	User usernameCheck = loadUserByUsernameEs(username);
    	if (user!=null||usernameCheck!=null) {
    		return true;
    	}
    	return false;
    }
    
    @Transactional
    public User save(User user) {
    	return userRepository.save(user);
    }
    
    public User register(UserDTO userDTO) {
    	User user = new User();
    	
    	modelMapper.map(userDTO, user);
    	
    	user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    	
    	KeyGenerator keyGen = new KeyGenerator();
		String pubKey = keyGen.keyGeneratorPswdBase(userDTO.getPassword());
    	
		user.setPubkey(pubKey);
		
		Set<Role> roles = new HashSet<Role>();
		Role role = roleRepository.getRoleByRoleName("UNVERIFIED");
		roles.add(role);
		
		user.setRoles(roles);
		user.setEnabled(1);
    	
		Optional<User> saved = Optional.of(save(user));
		
		saved.ifPresent(u -> {
			try {
				String token = UUID.randomUUID().toString();
				verificationTokenService.save(token,saved.get());
				
				emailService.sendHTMLEmail(u);
			}catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		
		
		
		
		return saved.get();
    }
    @Transactional
    public <Optional>User loadUserByUsernameEs(String username) {
        User user = userRepository.getUserByUsername(username);
         

         
        return user; 
    }

	public BindingResult changePasswordSave(User user, PasswdChangeDTO userDTO, BindingResult bindingResult) {
		if(userDTO.getPassword() ==null && userDTO.getRpassword()==null) {
			bindingResult.addError(new FieldError("userDTO", "rpassword", "Hasła muszą zostać uzupełione!"));
		}
		
		if(userDTO.getPassword() !=null && userDTO.getRpassword()!=null) {
			System.out.println("Kontrolnie 2");
			if(!(userDTO.comparePassAndRPass(userDTO.getPassword(), userDTO.getRpassword()))) {
				System.out.println("Kontrolnie 3");
				bindingResult.addError(new FieldError("userDTO", "rpassword", "Wpsiane hasła muszą być takie same!"));
			}
		}
		
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		
		save(user);
		
		return bindingResult;
		
	}
    

    

}
