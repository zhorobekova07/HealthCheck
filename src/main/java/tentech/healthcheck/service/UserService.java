//package tentech.healthcheck.service;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import tentech.healthcheck.mapper.UserMapper;
//import tentech.healthcheck.model.dto.UserRequest;
//import tentech.healthcheck.model.dto.UserResponse;
//import tentech.healthcheck.model.entity.User;
//import tentech.healthcheck.repository.UserRepository;
//
//@Service
//@RequiredArgsConstructor
//public class UserService {
//
//    private final UserMapper userMapper;
//    private final UserRepository userRepository;
//
//    public UserResponse save(UserRequest userRequest){
//        User user = userMapper.mapToEntity(userRequest);
//        userRepository.save(user);
//        return userMapper.mapToResponse(user);
//    }
//
//}
