package com.sinanmutlu.vendingmachine.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinanmutlu.vendingmachine.dto.UserDto;
import com.sinanmutlu.vendingmachine.entity.UserEnt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import static com.sinanmutlu.vendingmachine.utils.TestUtils.user;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testUserShouldBeRegistered() throws Exception {
        UserDto userDto = user();
        this.mockMvc.perform(post("/user/add")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(userDto.getUsername())))
                .andExpect(jsonPath("$.password", notNullValue()))
                .andExpect(jsonPath("$.deposit", notNullValue()))
                .andExpect(jsonPath("$.roles", is(userDto.getRoles())))
                .andExpect(jsonPath("$.id").doesNotExist());
    }

}