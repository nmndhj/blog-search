package com.project.backend.common.util;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class DataUtils {

    private ModelMapper modelMapper;

    //entity List -> Dto List 변환
    public <D, T> List<D> mapList(List<T> entityList, Class<D> outClass) {
        return entityList.stream().map(o -> modelMapper.map(o, outClass)).collect(Collectors.toList());
    }
}
