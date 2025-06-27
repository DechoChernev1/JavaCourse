package com.rewe.devices.service.impl;

import com.rewe.devices.data.entity.Store;
import com.rewe.devices.data.repository.StoreRepository;
import com.rewe.devices.service.StoreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
//@Scope(scopeName = SCOPE_PROTOTYPE)
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public Store getStoreById(Long id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found with id " + id));
    }

    @Override
    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    @Transactional
    public Store updateStore(Long id, Store storeDetails) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found with id " + id));

        store.setName(storeDetails.getName());
        store.setLocation(storeDetails.getLocation());
        return storeRepository.save(store);
    }

    @Override
    public void deleteStore(Long id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found with id " + id));

        storeRepository.delete(store);
    }
}