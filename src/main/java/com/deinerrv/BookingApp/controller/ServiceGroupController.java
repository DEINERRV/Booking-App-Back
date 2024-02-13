package com.deinerrv.BookingApp.controller;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deinerrv.BookingApp.entity.Service;
import com.deinerrv.BookingApp.entity.ServiceGroup;
import com.deinerrv.BookingApp.service.ServiceGroupService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/service-groups")
@RequiredArgsConstructor
public class ServiceGroupController {

    private final ServiceGroupService servicegGroupService;

    @GetMapping
    public List<ServiceGroup> getLodgingServices() {
        return servicegGroupService.findAll();
    }

    @GetMapping("/{id}")
    public ServiceGroup getGroup(@PathVariable Long id) {
        return servicegGroupService.findById(id);
    }

    @GetMapping("/{id}/services")
    public Set<Service> getServices(@PathVariable Long id) {
        return servicegGroupService.getServices(id);
    }
}
