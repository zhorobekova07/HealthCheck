package tentech.healthcheck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/search")
    public List<UserAccountResponse> searchAndPagination(@RequestParam(name = "text", required = false) String text,
                                                         @RequestParam int page,
                                                         @RequestParam int size) {
        return userAccountService.searchAndPagination(text, page, size);
    }

    @GetMapping("/findById/{id}")
    public UserAccountResponse findById(@PathVariable("id") Long id) {
        return userAccountService.findById(id);
    }

    @GetMapping("/findAll")
    public List<UserAccountResponse> findAll() {
        return userAccountService.findAll();

    }

    @DeleteMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        userAccountService.deleteById(id);
        return "User by id " + id + " successfully deleted";
    }
}
