package com.statista.code.challenge.booking.domain.factory;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepartmentFactory {

    private final List<DepartmentDoBusiness> departments;
    private Map<String, DepartmentDoBusiness> departmentDoBusinessMap;


    @PostConstruct
    private void postConstruct() {
        this.departmentDoBusinessMap = this.departments.stream().
            collect(Collectors.toMap(DepartmentDoBusiness::getDepartment, Function.identity()));
    }

    public DepartmentDoBusiness execute(String departments)
        throws Exception {
        Optional<DepartmentDoBusiness> converterOptional = Optional.ofNullable(departmentDoBusinessMap.get(departments));
        return converterOptional.orElseThrow(Exception::new);
    }
}
