package com.application.Controller;
import com.application.Entity.User;
import com.application.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user/api")
public class UserController  {

    @Autowired
    private UserService userService ;


    @PostMapping("/new")
    public ResponseEntity<String> createNewUser (@RequestBody User user ) {
        try {
            String response = userService.createNewUser(user) ;
            return new ResponseEntity <> ( response , HttpStatus.CREATED ) ;
        } catch ( Exception e ){
            return new ResponseEntity<>( e.getMessage() , HttpStatus.BAD_REQUEST)  ;
        }
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<?> getExistingUser (@PathVariable ("id") int id ) {
        try {
            User response = userService.getExistingUser(id) ;
            return new ResponseEntity <> ( response , HttpStatus.CREATED ) ;
        } catch ( Exception e ){
            return new ResponseEntity<>( e.getMessage() , HttpStatus.BAD_REQUEST)  ;
        }
    }

    @PutMapping("/updateUser/{contact}")
    public ResponseEntity<?> updateExistingUser (@PathVariable ("contact") String userContactNumber , @RequestBody User user) {
        try {
            String response = userService.updateExistingUser(userContactNumber , user ) ;
            return new ResponseEntity <> ( response , HttpStatus.CREATED ) ;
        } catch ( Exception e ){
            return new ResponseEntity<>( e.getMessage() , HttpStatus.BAD_REQUEST)  ;
        }
    }

    @DeleteMapping("/delete/{contact}")
    public ResponseEntity<?> deleteExistingUser ( @PathVariable("contact") String userContactNumber ) {
        try {
            String response = userService.deleteExistingUser ( userContactNumber ) ;
            return new ResponseEntity<>( response , HttpStatus.CREATED ) ;
        } catch ( Exception e ){
            return new ResponseEntity<>( e.getMessage() , HttpStatus.BAD_REQUEST) ;
        }
    }
}
