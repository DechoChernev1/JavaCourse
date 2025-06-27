package com.rewe.devices.service.impl;

import com.rewe.devices.data.entity.Store;
import com.rewe.devices.data.repository.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StoreServiceImplTest {

    @Mock
    private StoreRepository storeRepository;

    @InjectMocks
    private StoreServiceImpl storeService;

    private Store store;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        store = new Store();
        store.setId(1L);
        store.setName("Store A");
        store.setLocation("Location A");
    }

    @Test
    void testGetAllStores() {
        when(storeRepository.findAll()).thenReturn(Collections.singletonList(store));

        List<Store> stores = storeService.getAllStores();

        assertThat(stores).hasSize(1);
        assertThat(stores.get(0).getName()).isEqualTo("Store A");
    }

    @Test
    void testGetStoreById() {
        when(storeRepository.findById(1L)).thenReturn(Optional.of(store));

        Store foundStore = storeService.getStoreById(1L);

        assertThat(foundStore).isNotNull();
        assertThat(foundStore.getName()).isEqualTo("Store A");
    }

    @Test
    void testCreateStore() {
        when(storeRepository.save(any(Store.class))).thenReturn(store);

        Store savedStore = storeService.createStore(store);

        assertThat(savedStore).isNotNull();
        assertThat(savedStore.getName()).isEqualTo("Store A");
        verify(storeRepository, times(1)).save(any(Store.class));
    }

    @Test
    void testUpdateStore() {
        Store updatedStoreDetails = new Store();
        updatedStoreDetails.setName("Updated Store A");
        updatedStoreDetails.setLocation("Updated Location A");

        when(storeRepository.findById(1L)).thenReturn(Optional.of(store));
        when(storeRepository.save(any(Store.class))).thenReturn(updatedStoreDetails);

        Store updatedStore = storeService.updateStore(1L, updatedStoreDetails);

        assertThat(updatedStore.getName()).isEqualTo("Updated Store A");
        assertThat(updatedStore.getLocation()).isEqualTo("Updated Location A");
    }

    @Test
    void testDeleteStore() {
        when(storeRepository.findById(1L)).thenReturn(Optional.of(store));

        storeService.deleteStore(1L);

        verify(storeRepository, times(1)).delete(store);
    }

    @Test
    void testGetStoreById_NotFound() {
        when(storeRepository.findById(2L)).thenReturn(Optional.empty());

        Throwable thrown = catchThrowable(() -> storeService.getStoreById(2L));

        assertThat(thrown).isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Store not found with id 2");
    }
}