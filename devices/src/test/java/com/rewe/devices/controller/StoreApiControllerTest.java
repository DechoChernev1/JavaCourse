package com.rewe.devices.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rewe.devices.config.ModelMapperConfig;
import com.rewe.devices.data.entity.Device;
import com.rewe.devices.data.entity.Spec;
import com.rewe.devices.data.entity.Store;
import com.rewe.devices.dto.StoreDTO;
import com.rewe.devices.service.StoreService;
import org.apache.catalina.security.SecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StoreApiController.class)
@Import({ModelMapperConfig.class, SecurityConfig.class})
@WithMockUser(authorities = "admin")
public class StoreApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StoreService storeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper modelMapper;

    private Store store;
    private StoreDTO storeDTO;

    @BeforeEach
    void setup() {
        Spec spec = new Spec();
        spec.setId(1L);
        spec.setHeight(1);
        spec.setWidth(1);
        spec.setDepth(1);

        Device device = new Device();
        device.setId(1L);
        device.setName("Device A");
        Set<Spec> mySpecs = new HashSet<>();
        mySpecs.add(spec);
        device.setSpecs(mySpecs);

        store = new Store();
        store.setId(1L);
        store.setName("Store A");
        store.setLocation("Location A");
        Set<Device> myDevices = new HashSet<>();
        myDevices.add(device);
        store.setDevices(myDevices);

        storeDTO = modelMapper.map(store, StoreDTO.class);
    }

    @Test
    void testGetAllStores() throws Exception {
        when(storeService.getAllStores()).thenReturn(Collections.singletonList(store));

        mockMvc.perform(get("/api/stores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(store.getName()))
                .andExpect(jsonPath("$[0].devices[0].name").value("Device A"))
                .andExpect(jsonPath("$[0].devices[0].spec.specification").value("Spec A"));
    }

    @Test
    void testGetStoreById() throws Exception {
        when(storeService.getStoreById(1L)).thenReturn(store);

        mockMvc.perform(get("/api/stores/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(store.getName()))
                .andExpect(jsonPath("$.devices[0].name").value("Device A"))
                .andExpect(jsonPath("$.devices[0].spec.specification").value("Spec A"));
    }

    @Test
    void testCreateStore() throws Exception {
        when(storeService.createStore(any(Store.class))).thenReturn(store);

        mockMvc.perform(post("/api/stores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(storeDTO))
                        .with(csrf()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(storeDTO.getName()))
                .andExpect(jsonPath("$.devices[0].name").value("Device A"))
                .andExpect(jsonPath("$.devices[0].spec.specification").value("Spec A"));
    }

    @Test
    void testValidationOnCreateStore() throws Exception {
        StoreDTO invalidDTO = new StoreDTO();
        invalidDTO.setName(""); // Invalid name

        mockMvc.perform(post("/api/stores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").exists());
    }

    @Test
    void testUpdateStore() throws Exception {
        when(storeService.updateStore(eq(1L), any(Store.class))).thenAnswer(invocation -> {
            Store arg = invocation.getArgument(1);
            arg.setId(1L);
            return arg;
        });

        mockMvc.perform(put("/api/stores/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(storeDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Store A"))
                .andExpect(jsonPath("$.devices[0].name").value("Device A"))
                .andExpect(jsonPath("$.devices[0].spec.specification").value("Spec A"));
    }

    @Test
    void testDeleteStore() throws Exception {
        doNothing().when(storeService).deleteStore(1L);

        mockMvc.perform(delete("/api/stores/1"))
                .andExpect(status().isOk());
    }
}