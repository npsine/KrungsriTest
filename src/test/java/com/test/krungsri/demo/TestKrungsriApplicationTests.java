package com.test.krungsri.demo;

import com.test.controller.UserController;
import com.test.entity.User;
import com.test.entity.request.CreateUserRequest;
import com.test.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class TestKrungsriApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService userService;

    private User mockUser = new User(1, "test@gmail.com", "123456789", 15000.00, "Silver");

    private String userDataJson = "{\"email\":\"test@gmail.com\",\"password\":\"123456789\",\"salary\":15000.0}";

    @Test
    public void testGetUser() throws Exception {
        Mockito.when(userService.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(mockUser));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/user/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "{\"id\":1,\"email\":\"test@gmail.com\",\"salary\":15000.0,\"typeOfUser\":\"Silver\"}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void testCreateUser() throws Exception {
        Mockito.when(userService.createUser(any(CreateUserRequest.class))).thenReturn(mockUser);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/create/user")
                .accept(MediaType.APPLICATION_JSON).content(userDataJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }
}