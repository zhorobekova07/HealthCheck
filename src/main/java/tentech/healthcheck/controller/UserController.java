//package tentech.healthcheck.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import tentech.healthcheck.service.UserService;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("api/user")
//public class UserController {
//
//    private final UserService userService;
//
//    @PostMapping("/save")
//    public UserResponse save(@RequestBody UserRequest request) {
//        return userService.save(request);
//    }
//}
