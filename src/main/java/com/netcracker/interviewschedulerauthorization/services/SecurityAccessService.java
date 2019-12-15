package com.netcracker.interviewschedulerauthorization.services;

import com.netcracker.interviewschedulerauthorization.model.SecurityAccessContext;

import java.io.IOException;

public interface SecurityAccessService {
    boolean checkAccess(SecurityAccessContext context) throws IOException;
}

