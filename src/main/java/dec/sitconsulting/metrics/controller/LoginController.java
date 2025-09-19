package dec.sitconsulting.metrics.controller;

import dec.sitconsulting.metrics.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/login")
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public String login(@RequestParam(value = "user", required = false) String user) {
        return loginService.checkLogin(user);
    }
}
