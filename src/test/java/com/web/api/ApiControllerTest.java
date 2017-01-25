package com.web.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by xiaoning.sun on 2016/12/1.
 * Do unit test by MockMvc, please remember to disable to spring security when you do the unit test. Otherwise,
 * you maybe get the 302 error
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApiControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void selectBookByIdTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/query.do").param("id","16")).andExpect(status().isOk()).andDo(print());
    }
}
