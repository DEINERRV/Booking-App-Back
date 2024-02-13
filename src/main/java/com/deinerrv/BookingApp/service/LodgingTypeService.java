package com.deinerrv.BookingApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.deinerrv.BookingApp.entity.LodgingType;
import com.deinerrv.BookingApp.repository.LodgingTypeRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LodgingTypeService {
    
    private final LodgingTypeRepository lodgingTypeRepository;

    @Transactional
    public LodgingType getLodgingTypeById(long id){
        return lodgingTypeRepository.findById(id).orElseThrow(()->new RuntimeException("Lodging Type not found"));
    }

    @Transactional
    public List<LodgingType> getAllLodgingType(){
        return lodgingTypeRepository.findAll();
    }

}
