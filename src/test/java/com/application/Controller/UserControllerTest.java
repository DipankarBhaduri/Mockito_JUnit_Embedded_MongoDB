package com.application.Controller;

import com.application.Entity.User;
import com.application.Services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import springfox.documentation.swagger2.mappers.ModelMapper;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.http.RequestEntity.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest( UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc ;

    @MockBean
    private UserService userService ;

    @Autowired
    private ModelMapper modelMapper ;

    @Test
    void createNewUser() throws Exception {
        User newUser = new User();
        newUser.setUserId(1);
        newUser.setUserName("dipu");
        newUser.setUserAddress("");
        newUser.setUserAge(200);
        newUser.setUserContactNumber("7001234567");

        Mockito.when(userService.createNewUser(any(User.class)))
                .thenReturn(newUser.getUserName() + " added in embedded MongoDB");

        String url = "/user/api/new";
        String requestBody = new ObjectMapper().writeValueAsString(newUser);

        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isCreated());
    }

    @Test
    void getExistingUser() {
        int userId = 1;
        User existingUser = new User();
        existingUser.setUserId(userId);
        existingUser.setUserName("John Doe");
        existingUser.setUserAddress("123 Main St");
        existingUser.setUserAge(30);
        existingUser.setUserContactNumber("1234567890");

        Mockito.when(userService.getExistingUser(userId))
                .thenReturn(existingUser);

        String url = "/user/api/getUser/" + userId;
/*
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userId))
                .andExpect(jsonPath("$.userName").value(existingUser.getUserName()))
                .andExpect(jsonPath("$.userAddress").value(existingUser.getUserAddress()))
                .andExpect(jsonPath("$.userAge").value(existingUser.getUserAge()))
                .andExpect(jsonPath("$.userContactNumber").value(existingUser.getUserContactNumber()));
 */
    }


    @Test
    void updateExistingUser() throws JsonProcessingException {
        String userContactNumber = "1234567890";
        User updatedUser = new User();
        updatedUser.setUserId(1);
        updatedUser.setUserName("John Doe");
        updatedUser.setUserAddress("123 Main St");
        updatedUser.setUserAge(30);

        Mockito.when(userService.updateExistingUser(userContactNumber, updatedUser))
                .thenReturn("updated the existing user");

        String url = "/user/api/updateUser/" + userContactNumber;
        String requestBody = new ObjectMapper().writeValueAsString(updatedUser);

/*
        mockMvc.perform(put(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(requestBody))
                        .andExpect(status().isCreated())
                        .andExpect(content().string("User updated successfully."));

 */
    }

    @Test
    void deleteExistingUser() throws Exception {
        String userContactNumber = "7001234567";
        String expectedResponse = "User deleted successfully.";

        Mockito.when(userService.deleteExistingUser(userContactNumber))
                .thenReturn(expectedResponse);

        String url = "/user/api/delete/" + userContactNumber;

        mockMvc.perform(delete(url))
                .andExpect(status().isCreated())
                .andExpect(content().string(expectedResponse));
    }
}