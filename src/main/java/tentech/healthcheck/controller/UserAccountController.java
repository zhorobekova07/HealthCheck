package tentech.healthcheck.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tentech.healthcheck.model.dto.UserAccountResponse;
import tentech.healthcheck.service.UserAccountService;

import java.util.List;
@Tag(name = "User-Account Controller")
@RestController
@RequestMapping("/api/userAccount")
public class UserAccountController {
    private final UserAccountService userAccountService;

    @Autowired
    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @Operation(summary = "Ищет  пользователя по id" ,description = "Возвращает пользователя по id")
    @GetMapping("/findById/{id}")
    public UserAccountResponse findById(@PathVariable("id") Long id) {
        return userAccountService.findById(id);
    }

    @Operation(summary = "Возращает список пользователей" ,description = "Возвращает список пользователей")
    @GetMapping("/findAll")
    public List<UserAccountResponse> findAll() {
        return userAccountService.findAll();
    }

    @Operation(summary = "Удаляет пользователя по id" ,description = "Удаляет пользователя по id")
    @DeleteMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        userAccountService.deleteById(id);
        return "User by id " + id + " successfully deleted";
    }
}
