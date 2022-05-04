package com.example.restservice;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tk.swapjob.dao.requests.LoginRequest;
import tk.swapjob.dao.requests.SignupRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthControllerTest extends BaseTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testSignupGivenNothingShouldGoError() throws Exception {
        String uri = "/auth/signup";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
    }

    @Test
    public void testSignupGivenEmptyShouldGoError() throws Exception {
        String uri = "/auth/signup";
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail(null);
        signupRequest.setPassword(null);
        signupRequest.setFirstName(null);
        signupRequest.setLastName(null);
        signupRequest.setPhone(null);
        signupRequest.setBirthDate(null);
        signupRequest.setDescription(null);
        signupRequest.setPostalCode(null);
        String inputJson = super.mapToJson(signupRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.contains("Invalid signup request"));
    }

    @Test
    public void testSignupGivenInvalidEmailShouldGoError() throws Exception {
        String uri = "/auth/signup";
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("invalidEmail");
        signupRequest.setPassword("");
        signupRequest.setFirstName("");
        signupRequest.setLastName("");
        signupRequest.setPhone("");
        signupRequest.setBirthDate("");
        signupRequest.setDescription("");
        signupRequest.setPostalCode(0);
        String inputJson = super.mapToJson(signupRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.contains("Error: Invalid email"));
    }

    @Test
    public void testSignupGivenInvalidTimeStampShouldGoError() throws Exception {
        String uri = "/auth/signup";
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("valid@email.com");
        signupRequest.setPassword("P@ssw0rd");
        signupRequest.setFirstName("Invalid");
        signupRequest.setLastName("BirthDate");
        signupRequest.setPhone("654321987");
        signupRequest.setBirthDate("123456789");
        signupRequest.setDescription("");
        signupRequest.setPostalCode(0);
        String inputJson = super.mapToJson(signupRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.contains("Error: Invalid birth date"));
    }

    @Test
    public void testSignupGivenValidUserShouldGoOK() throws Exception {
        String uri = "/auth/signup";
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("valid@email.com");
        signupRequest.setPassword("P@ssw0rd");
        signupRequest.setFirstName("Valid");
        signupRequest.setLastName("User");
        signupRequest.setPhone("654321987");
        signupRequest.setBirthDate("2019-01-01");
        signupRequest.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. ");
        signupRequest.setPostalCode(8495);
        String inputJson = super.mapToJson(signupRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.contains("User registered successfully!"));
        testSignupGivenRepeatedEmailShouldGoError();
    }

    @Ignore
    public void testSignupGivenRepeatedEmailShouldGoError() throws Exception {
        String uri = "/auth/signup";
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("valid@email.com");
        signupRequest.setPassword("P@ssw0rd");
        signupRequest.setFirstName("Valid");
        signupRequest.setLastName("User");
        signupRequest.setPhone("654321987");
        signupRequest.setBirthDate("2019-01-01");
        signupRequest.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. ");
        signupRequest.setPostalCode(8495);
        String inputJson = super.mapToJson(signupRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.contains("Error: Email is already in use!"));
    }

    @Test
    public void testLoginGivenNothingShouldGoError() throws Exception {
        String uri = "/auth/signin";
        String inputJson = super.mapToJson(null);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.contains("Invalid login request"));
    }

    @Test
    public void testLoginGivenInvalidEmailShouldGoError() throws Exception {
        String uri = "/auth/signin";
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("invalid@email.com");
        loginRequest.setPassword("P@ssw0rd");
        String inputJson = super.mapToJson(loginRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.contains("Invalid login request"));
    }

    @Test
    public void testLoginGivenInvalidPasswordShouldGoError() throws Exception {
        String uri = "/auth/signin";
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("valid@email.com");
        loginRequest.setPassword("InvalidP@ssw0rd");
        String inputJson = super.mapToJson(loginRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.contains("Invalid login request"));
    }

    @Test
    public void testLoginGivenValidEmailAndPasswordShouldGoOK() throws Exception {
        String uri = "/auth/signin";
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("valid@email.com");
        loginRequest.setPassword("P@ssw0rd");
        String inputJson = super.mapToJson(loginRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.contains("accessToken"));
    }
}
