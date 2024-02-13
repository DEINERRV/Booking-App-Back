package com.deinerrv.BookingApp.service;

import java.util.List;
import java.util.Set;

import com.deinerrv.BookingApp.entity.Service;
import com.deinerrv.BookingApp.entity.ServiceGroup;
import com.deinerrv.BookingApp.repository.ServiceGroupRepository;
import com.deinerrv.BookingApp.repository.ServiceRepository;

import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceGroupService {
 
    private final ServiceGroupRepository serviceGroupRepository;
    private final ServiceRepository serviceRepository;

    public ServiceGroup findById(Long id){
        return serviceGroupRepository.findById(id).orElseThrow(()->new RuntimeException("Service Group not found"));
    }

    public List<ServiceGroup> findAll(){
        return serviceGroupRepository.findAll();
    }

    public Set<Service> getServices(Long id){
        return this.serviceRepository.findAllByGroupId(id);
    }

}
