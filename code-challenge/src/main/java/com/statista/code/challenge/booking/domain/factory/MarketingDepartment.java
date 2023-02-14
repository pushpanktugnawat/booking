package com.statista.code.challenge.booking.domain.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MarketingDepartment implements DepartmentDoBusiness<String> {

    @Override
    public String getDepartment() {
        return "marketing-department";
    }

    @Override
    public String doBusiness() {
        log.info("execute marketing department doBusiness method");
        return "Marketing";
    }
}
