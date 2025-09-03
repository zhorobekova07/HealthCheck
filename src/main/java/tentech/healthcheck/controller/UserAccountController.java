package tentech.healthcheck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tentech.healthcheck.model.dto.UserAccountResponse;
import tentech.healthcheck.service.UserAccountService;

import java.util.List;

@RestController
@RequestMapping("/api/userAccount")
public class UserAccountController {
    private final UserAccountService userAccountService;

    @Autowired
    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/findById/{id}")
    public UserAccountResponse findById(@PathVariable("id") Long id) {
        return userAccountService.findById(id);
    }

    @GetMapping("/findAll")
    public List<UserAccountResponse> findAll(){
        return userAccountService.findAll();
    }


}
