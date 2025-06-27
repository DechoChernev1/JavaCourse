package com.rewe.devices.data.repository;

import com.rewe.devices.data.entity.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StoreRepositoryTest {

    @Autowired
    private StoreRepository storeRepository;

    private Store store;

    @BeforeEach
    void setup() {
        store = new Store();
        store.setName("Test Store");
        store.setLocation("Test Location");
        //store.setId(1);
        store.setDevices(null);
        store = storeRepository.save(store);
    }

    @Test
    void testFindAllStores() {
        List<Store> stores = storeRepository.findAll();
        assertThat(stores).hasSize(1);

    }

    @Test
    void testFindStoreById() {
        Store foundStore = storeRepository.findById(store.getId()).orElse(null);
        assertThat(foundStore).isNotNull();
        assertThat(foundStore.getName()).isEqualTo(store.getName());
    }

    @Test
    void testCreateStore() {
        Store newStore = new Store();
        newStore.setName("New Store");
        newStore.setLocation("New Location");

        Store savedStore = storeRepository.save(newStore);
        assertThat(savedStore).isNotNull();
        assertThat(savedStore.getId()).isNotNull();
    }

    @Test
    void testUpdateStore() {
        store.setName("Updated Store");
        Store updatedStore = storeRepository.save(store);

        assertThat(updatedStore.getName()).isEqualTo("Updated Store");
    }

    @Test
    void testDeleteStore() {
        storeRepository.delete(store);

        Store deletedStore = storeRepository.findById(store.getId()).orElse(null);
        assertThat(deletedStore).isNull();
    }
}