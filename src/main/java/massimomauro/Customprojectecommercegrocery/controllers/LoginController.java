package massimomauro.Customprojectecommercegrocery.controllers;

import massimomauro.Customprojectecommercegrocery.payloads.EntrepreneurLoginDTO;
import massimomauro.Customprojectecommercegrocery.payloads.EntrepreneurLoginSuccessDTO;
import massimomauro.Customprojectecommercegrocery.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    AuthService authService;
    @PostMapping("")
    public EntrepreneurLoginSuccessDTO login(@RequestBody EntrepreneurLoginDTO body){

        return new EntrepreneurLoginSuccessDTO(authService.authenticateUser(body));
    }
}
