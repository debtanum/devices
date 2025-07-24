package com.example.schoolbus;

import com.example.schoolbus.controller.BusController;
import com.example.schoolbus.model.Bus;
import com.example.schoolbus.service.BusService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.anyString;

@WebMvcTest(BusController.class)
public class BusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BusService busService;

    @Test
    void createBus() throws Exception {
        Mockito.when(busService.createBus(anyString())).thenReturn(new Bus("Bus 1"));
        mockMvc.perform(post("/api/buses").param("name", "Bus 1"))
                .andExpect(status().isOk());
    }
}
