package com.application.Services;
import com.application.Entity.User;
import com.application.UserRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository ;

    public String createNewUser(User user) {
        userRepository.save(user) ;
        return user.getUserName()+" added in embedded mongodb";
    }


    public User getExistingUser(int id) {
        User user = userRepository.findById(id).get() ;
        return user ;
    }


    public String updateExistingUser(String userContactNumber , User user) {
        List< User > userList = userRepository.findAll() ;
        for ( User curUser : userList ){
            if ( curUser.getUserContactNumber().equals(userContactNumber)){
                curUser.setUserAddress(user.getUserAddress());
                curUser.setUserId(user.getUserId());
                curUser.setUserAge(user.getUserAge());
                curUser.setUserName(user.getUserName());
                userRepository.save(user) ;
                break;
            }
        }
        return "updated the existing user" ;
    }


    public String deleteExistingUser(String userContactNumber) {
        List< User > userList = userRepository.findAll() ;
        for ( User curUser : userList ){
            if ( curUser.getUserContactNumber().equals(userContactNumber)){
                userRepository.delete(curUser);
                break;
            }
        }
        return "deleted the user" ;
    }
}
