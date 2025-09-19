package dec.sitconsulting.metrics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    public LoginService() {
    }

    public String checkLogin(String user) {
        if ("user_accept".equals(user)) {
            return "OK";
        } else {
            return "NOK";
        }
    }
}
