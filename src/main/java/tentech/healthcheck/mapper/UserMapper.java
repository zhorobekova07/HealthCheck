//package tentech.healthcheck.mapper;
//
//import org.springframework.stereotype.Component;
//import tentech.healthcheck.model.dto.UserRequest;
//import tentech.healthcheck.model.dto.UserResponse;
//import tentech.healthcheck.model.entity.User;
//
//@Component
//public class UserMapper {
//    public UserResponse mapToResponse(User user){
//        return UserResponse.builder()
//                .id(user.getId())
//                .firstName(user.getFirstName())
//                .lastName(user.getLastName())
//                .build();
//    }
//
//    public User mapToEntity(UserRequest userRequest){
//        return User.builder()
//                .firstName(userRequest.getFirstName())
//                .lastName(userRequest.getLastName())
//                .build();
//    }
//}
