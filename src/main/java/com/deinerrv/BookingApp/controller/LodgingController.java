package com.deinerrv.BookingApp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deinerrv.BookingApp.dto.CreateLodging;
import com.deinerrv.BookingApp.dto.LodgingDto;
import com.deinerrv.BookingApp.dto.UpdateLodging;
import com.deinerrv.BookingApp.entity.LodgingType;
import com.deinerrv.BookingApp.entity.Service;
import com.deinerrv.BookingApp.service.LodgingService;
import com.deinerrv.BookingApp.service.LodgingTypeService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/lodgings")
@RequiredArgsConstructor
public class LodgingController {
    
    private final LodgingService lodgingService;
    private final LodgingTypeService lodgingTypeService;

    @PostMapping  
    public ResponseEntity<Void> createLodging(@RequestBody CreateLodging createDto, HttpServletRequest request) {
        lodgingService.createLodging(createDto, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateLodging(@PathVariable Long id, @RequestBody UpdateLodging updateDto, HttpServletRequest request){
        lodgingService.updateLodging(updateDto, id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLodging(@PathVariable Long id, HttpServletRequest request){
        lodgingService.deleteLodging(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public Page<LodgingDto> getMethodName(
    @RequestParam(defaultValue = "0") int page, 
    @RequestParam(defaultValue = "5") int size,
    @RequestParam(defaultValue = "") String search) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        return this.lodgingService.getAllLodgings(pageable, search);
    }
    
    //--------------------------------
    @GetMapping("/types")
    public List<LodgingType> getLodgingTypes() {
        return lodgingTypeService.getAllLodgingType();
    }

    //--------------------------------
    @GetMapping("/{id}/services")
    public Set<Service> getLodgingServices(@PathVariable Long id) {
        return lodgingService.getServices(id);
    }
}
