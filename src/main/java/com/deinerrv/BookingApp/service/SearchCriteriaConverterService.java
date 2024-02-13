package com.deinerrv.BookingApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.deinerrv.BookingApp.specification.SearchCriteria;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchCriteriaConverterService {
    public List<SearchCriteria> convert(String searchParam){
        return this.convert(searchParam, null);
    }

    public List<SearchCriteria> convert(String searchParam, List<String> bannedParams) {
        if(bannedParams == null)
            bannedParams = new ArrayList<>();

        List<SearchCriteria> criteriaList = new ArrayList<>();
        Pattern pattern = Pattern.compile("(\\w+(?:\\.[\\w]+)*)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(searchParam + ",");
        while (matcher.find()) {
            System.out.println(matcher.group(1)+" "+matcher.group(2)+" "+matcher.group(3));
            if(!bannedParams.contains(matcher.group(1)))
                criteriaList.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
        }
        
        return criteriaList;
    }
}
