package com.netcracker.interviewschedulerauthorization.controllers.api;

import com.netcracker.interviewschedulerauthorization.exceptions.BadRequestException;
import com.netcracker.interviewschedulerauthorization.model.SecurityAccessContext;
import com.netcracker.interviewschedulerauthorization.services.SecurityAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/access")
public class SecurityAccessRESTController {

    private final SecurityAccessService securityAccessService;

    @Autowired
    public SecurityAccessRESTController(SecurityAccessService securityAccessService) {
        this.securityAccessService = securityAccessService;
    }

    @PostMapping
    public String checkAccess(@RequestBody SecurityAccessContext context) {
        try {
            return securityAccessService.checkAccess(context) ? "permit" : "deny";
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }
}
