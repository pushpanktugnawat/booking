package com.statista.code.challenge.booking.domain.factory;

public interface DepartmentDoBusiness<R> {

    String getDepartment();

    R doBusiness();
}
