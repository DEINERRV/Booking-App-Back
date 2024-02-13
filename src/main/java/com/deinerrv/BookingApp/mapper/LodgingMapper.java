package com.deinerrv.BookingApp.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.deinerrv.BookingApp.dto.CreateLodging;
import com.deinerrv.BookingApp.dto.LodgingDto;
import com.deinerrv.BookingApp.entity.Lodging;
import com.deinerrv.BookingApp.entity.LodgingStatus;
import com.deinerrv.BookingApp.entity.LodgingType;
import com.deinerrv.BookingApp.entity.Service;
import com.deinerrv.BookingApp.entity.User;
import com.deinerrv.BookingApp.service.LodgingTypeService;

@Mapper(componentModel = "spring")
public abstract class LodgingMapper {

    @Autowired LodgingTypeService lodgingTypeService;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", source = "status")
    @Mapping(target = "owner", source = "owner")
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "type", source = "dto.typeId")
    @Mapping(target = "services", source = "dto.servicesIds")
    public abstract Lodging toToLodging(CreateLodging dto, User owner, LodgingStatus status);

    @Mapping(target = "ownerId", source = "lodging.owner.id")
    @Mapping(target = "type", source = "lodging.type.name")
    public abstract LodgingDto toResponseDto(Lodging lodging);

    public abstract List<LodgingDto> toResponseDto(List<Lodging> list);

    public Page<LodgingDto> toResponseDtoPage(Page<Lodging> entityPage) {
        return entityPage.map(this::toResponseDto);
    }



    LodgingType mapType(Long typeId) {
        return lodgingTypeService.getLodgingTypeById(typeId);
    }

    Set<Service> mapServices(int[] ids){
        if (ids == null)
            return null;

        Set<Service> services = new HashSet<>();
        for(int id : ids){
            Service aux = new Service();
            aux.setId(id);
            services.add(aux);
        }
        
        return services;
    }
}
