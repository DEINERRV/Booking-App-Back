package com.deinerrv.BookingApp.service;

import java.util.List;

import com.deinerrv.BookingApp.entity.Service;

import com.deinerrv.BookingApp.repository.ServiceRepository;

import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceService {
    
    private final ServiceRepository serviceRepository;

    public List<Service> getAllServicesByGroupId(Long groupId){
        return serviceRepository.findAllByGroupId(groupId);
    }

    public List<Service> getAllServicesByLodgingId(Long lodgingId){
        return serviceRepository.findAllByLodgingsId(lodgingId);
    }
}
