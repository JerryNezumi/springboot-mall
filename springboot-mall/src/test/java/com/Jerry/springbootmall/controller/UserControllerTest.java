package com.Jerry.springbootmall.controller;

import com.Jerry.springbootmall.dao.UserDao;
import com.Jerry.springbootmall.dto.UserRegisterRequest;
import com.Jerry.springbootmall.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserDao userDao;

    private ObjectMapper objectMapper = new ObjectMapper();

    //註冊新帳號
    @Test
    public void register_success() throws Exception {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setEmail("test@test.com");
        userRegisterRequest.setPassword("123456");
        String json = objectMapper.writeValueAsString(userRegisterRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/User/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(jsonPath("$.email", notNullValue()))
                .andExpect(jsonPath("$.userId", notNullValue()))
                .andExpect(status().is(201));

        //檢查資料庫的密碼不為明碼
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());
        assertNotEquals(user.getPassword(), userRegisterRequest.getPassword());


    }

    //檢查是否可阻擋無效的email
    @Test
    public void register_invalidEmailFormat() throws Exception {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setEmail("sadasdasd");
        userRegisterRequest.setPassword("123456");
        String Json = objectMapper.writeValueAsString(userRegisterRequest);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/User/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Json);
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));


    }

    //email不得重複註冊
    @Test
    public void register_emailAlreadyExist() throws Exception {

        //先註冊一個帳號
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setEmail("test1@test.com");
        userRegisterRequest.setPassword("123456");
        String json = objectMapper.writeValueAsString(userRegisterRequest);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/User/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201));

        //再次註冊同個帳號
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));


    }
    //登入
    @Test
    public void login_success() throws Exception {
        //先註冊一個帳號
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setEmail("test2@test.com");
        userRegisterRequest.setPassword("123456");
        register(userRegisterRequest);
        //登入該帳號
        UserRegisterRequest userLoginRequest = new UserRegisterRequest();
        userLoginRequest.setEmail(userRegisterRequest.getEmail());
        userLoginRequest.setPassword(userRegisterRequest.getPassword());
        String json = objectMapper.writeValueAsString(userLoginRequest);

        RequestBuilder requestBuilderLogin = MockMvcRequestBuilders
                .post("/User/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        mockMvc.perform(requestBuilderLogin)
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.email", equalTo(userRegisterRequest.getEmail())))
                .andExpect(jsonPath("$.userId", notNullValue()))
                .andExpect(jsonPath("$.createDate", notNullValue()))
                .andExpect(jsonPath("$.lastModifiedDate", notNullValue()));


    }

    //測試密碼錯誤
    @Test
    public void login_wrongPassword() throws Exception {
        //註冊新帳號
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setEmail("test3@test.com");
        userRegisterRequest.setPassword("123456");
        register(userRegisterRequest);

        //使用錯誤密碼嘗試登入
        UserRegisterRequest userLoginRequest = new UserRegisterRequest();
        userLoginRequest.setEmail(userRegisterRequest.getEmail());
        userLoginRequest.setPassword("654321");
        String json = objectMapper.writeValueAsString(userLoginRequest);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/User/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));



    }

    //電子郵件格式錯誤
    @Test
    public void login_invalidEmailFormat() throws Exception {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setEmail("unknown");
        userRegisterRequest.setPassword("123456");
        String json = objectMapper.writeValueAsString(userRegisterRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/User/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));

    }

    //電子郵件不存在
    @Test
    public void login_emailNotExist() throws Exception {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setEmail("unknown@test.com");
        userRegisterRequest.setPassword("123456");
        String json = objectMapper.writeValueAsString(userRegisterRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/User/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));


    }



    //註冊帳號方法
    private void register(UserRegisterRequest userRegisterRequest) throws Exception {
        String json = objectMapper.writeValueAsString(userRegisterRequest);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/User/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201));

    }

}