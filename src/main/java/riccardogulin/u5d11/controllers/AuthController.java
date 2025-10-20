package riccardogulin.u5d11.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import riccardogulin.u5d11.payload.LoginDTO;
import riccardogulin.u5d11.payload.LoginResponseDTO;
import riccardogulin.u5d11.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public LoginResponseDTO login(@RequestBody LoginDTO body) {
		return new LoginResponseDTO(authService.checkCredentialsAndGenerateToken(body));
	}
}
