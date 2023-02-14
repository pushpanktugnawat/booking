package com.statista.code.challenge.booking.domain.factory;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SalesDepartment implements DepartmentDoBusiness<String> {

    @Override
    public String getDepartment() {
        return "sales-department";
    }

    @Override
    public String doBusiness() {
        log.info("execute sales department doBusiness method");
        return "Sales";
    }
}
