package com.rewe.devices.controller;

import com.rewe.devices.data.entity.Store;
import com.rewe.devices.dto.StoreDTO;
import com.rewe.devices.service.StoreService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stores")
@Validated
public class StoreApiController {

    private final StoreService storeService;
    private final ModelMapper modelMapper;

    public StoreApiController(StoreService storeService, ModelMapper modelMapper) {
        this.storeService = storeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<StoreDTO>> getAllStores() {
        List<Store> stores = storeService.getAllStores();
        List<StoreDTO> storeDTOs = stores.stream()
                .map(store -> modelMapper.map(store, StoreDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(storeDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDTO> getStoreById(@PathVariable Long id) {
        Store store = storeService.getStoreById(id);
        return new ResponseEntity<>(modelMapper.map(store, StoreDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StoreDTO> createStore(@Valid @RequestBody StoreDTO storeDTO) {
        Store store = storeService.createStore(modelMapper.map(storeDTO, Store.class));
        return new ResponseEntity<>(modelMapper.map(store, StoreDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreDTO> updateStore(@PathVariable Long id, @Valid @RequestBody StoreDTO storeDTO) {
        Store store = storeService.updateStore(id, modelMapper.map(storeDTO, Store.class));
        return new ResponseEntity<>(modelMapper.map(store, StoreDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
        return ResponseEntity.ok().build();
    }
}