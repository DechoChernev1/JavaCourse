package com.rewe.devices.service;

import com.rewe.devices.data.entity.Store;

import java.util.List;

public interface StoreService {
    List<Store> getAllStores();

    Store getStoreById(Long id);

    Store createStore(Store store);

    Store updateStore(Long id, Store storeDetails);

    void deleteStore(Long id);
}