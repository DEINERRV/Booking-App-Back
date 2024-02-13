package com.deinerrv.BookingApp.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import com.deinerrv.BookingApp.dto.CreateLodging;
import com.deinerrv.BookingApp.dto.LodgingDto;
import com.deinerrv.BookingApp.dto.UpdateLodging;
import com.deinerrv.BookingApp.entity.Lodging;
import com.deinerrv.BookingApp.entity.LodgingStatus;
import com.deinerrv.BookingApp.entity.Service;
import com.deinerrv.BookingApp.entity.User;
import com.deinerrv.BookingApp.mapper.LodgingMapper;
import com.deinerrv.BookingApp.repository.LodgingRepository;
import com.deinerrv.BookingApp.specification.SpecificationsBuilder;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class LodgingService {
    
    private final LodgingRepository lodgingRepository;
    private final LodgingMapper lodgingMapper;
    private final AuthService authService;
    private final SearchCriteriaConverterService searchCriteriaConverterService;
    private final SpecificationsBuilder<Lodging> specificationsBuilder;

    @Transactional
    public void createLodging(CreateLodging dto, HttpServletRequest request){
        User owner = authService.getUserFromRequest(request);
        Lodging lodging = lodgingMapper.toToLodging(dto, owner, LodgingStatus.ACTIVE);
        lodgingRepository.save(lodging);
    }

    @Transactional
    public void updateLodging(UpdateLodging dto, Long lodgingId, HttpServletRequest request){
        User owner = authService.getUserFromRequest(request);
        Lodging lodging = lodgingRepository.findByIdAndOwnerEmail(lodgingId, owner.getEmail()).orElseThrow(()-> new RuntimeException("Lodging Not Found"));

        if(dto.getName() != null)
            lodging.setName(dto.getName());
        if(dto.getLocation() != null)
            lodging.setLocation(dto.getLocation());
        if(dto.getLatitude() != null)
            lodging.setLatitude(dto.getLatitude());
        if(dto.getLongitude() != null)
            lodging.setLongitude(dto.getLongitude());
        if(dto.getDescription() != null)
            lodging.setDescription(dto.getDescription());
        if(dto.getRules() != null)
            lodging.setRules(dto.getRules());
        if(dto.getCapacity() != null)
            lodging.setCapacity(dto.getCapacity()); 
        if(dto.getBedrooms() != null)
            lodging.setBedrooms(dto.getBedrooms()); 
        if(dto.getBathrooms() != null)
            lodging.setBathrooms(dto.getBathrooms()); 
        if(dto.getPrice() != null)
            lodging.setPrice(dto.getPrice()); 
        if(dto.getStatus() != null)
            lodging.setStatus(dto.getStatus()); 

        lodgingRepository.save(lodging);
    }

    @Transactional
    public void deleteLodging(Long lodgingId, HttpServletRequest request){
        User owner = authService.getUserFromRequest(request);
        Lodging lodging = lodgingRepository.findByIdAndOwnerEmail(lodgingId, owner.getEmail()).orElseThrow(()-> new RuntimeException("Lodging Not Found"));
        lodging.setStatus(LodgingStatus.DELETED);
        lodgingRepository.save(lodging);
    }

    @Transactional
    public Page<LodgingDto> getAllLodgings(Pageable pageable, String searchParam){
        List<String> bannedParams = List.of("bathrooms");
        Specification<Lodging> specs = specificationsBuilder.genSpec(searchCriteriaConverterService.convert(searchParam,bannedParams));
        return lodgingMapper.toResponseDtoPage(lodgingRepository.findAll(specs, pageable));
    }

    @Transactional 
    public Set<Service> getServices(Long id){
        Lodging lodging = lodgingRepository.findById(id).orElseThrow(()-> new RuntimeException("Lodging Not Found"));
        return lodging.getServices();
    }
}
