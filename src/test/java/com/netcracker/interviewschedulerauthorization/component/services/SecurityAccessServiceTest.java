package com.netcracker.interviewschedulerauthorization.component.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.interviewschedulerauthorization.model.SecurityAccessContext;
import com.netcracker.interviewschedulerauthorization.services.SecurityAccessService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityAccessServiceTest {
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    SecurityAccessService securityAccessService;

    @Test
    public void candidateEditByAdminFromOneOffice() throws IOException {
        JsonNode subject = mapper.readTree(new File("src/test/json/subjects/adminFrom1Office.txt"));
        JsonNode object = mapper.readTree(new File("src/test/json/objects/candidates/from1office.txt"));
        SecurityAccessContext context = new SecurityAccessContext(subject, object, "EDIT");
        assertTrue(securityAccessService.checkAccess(context));
    }

    @Test
    public void candidateEditByUserFromOneOffice() throws IOException {
        JsonNode subject = mapper.readTree(new File("src/test/json/subjects/userFrom1Office.txt"));
        JsonNode object = mapper.readTree(new File("src/test/json/objects/candidates/from1office.txt"));
        SecurityAccessContext context = new SecurityAccessContext(subject, object, "EDIT");
        assertFalse(securityAccessService.checkAccess(context));
    }

    @Test
    public void candidateEditByAdminFromDifferentOffice() throws IOException {
        JsonNode subject = mapper.readTree(new File("src/test/json/subjects/adminFrom1Office.txt"));
        JsonNode object = mapper.readTree(new File("src/test/json/objects/candidates/from2office.txt"));
        SecurityAccessContext context = new SecurityAccessContext(subject, object, "EDIT");
        assertFalse(securityAccessService.checkAccess(context));
    }

    @Test
    public void candidateEditByUserFromDifferentOffice() throws IOException {
        JsonNode subject = mapper.readTree(new File("src/test/json/subjects/userFrom1Office.txt"));
        JsonNode object = mapper.readTree(new File("src/test/json/objects/candidates/from2office.txt"));
        SecurityAccessContext context = new SecurityAccessContext(subject, object, "EDIT");
        assertFalse(securityAccessService.checkAccess(context));
    }

}
