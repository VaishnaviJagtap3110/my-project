package com.wheelshare.driver.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.doNothing;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;


import com.wheelshare.driver.service.DriverService;

@WebMvcTest(DriverController.class)
class DriverControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DriverService driverService;

    @Test
    void updateStatus_success() throws Exception {
    	doNothing().when(driverService).updateAvailability(anyLong(), any());

    	mockMvc.perform(
    		    put("/drivers/1/status")
    		        .contentType("application/json")
    		        .content("\"ONLINE\"")
    		).andExpect(status().isOk());

    }


}
