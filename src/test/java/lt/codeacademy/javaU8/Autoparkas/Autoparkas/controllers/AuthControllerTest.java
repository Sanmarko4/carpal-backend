package lt.codeacademy.javaU8.Autoparkas.Autoparkas.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.controllers.AuthController;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.models.EnumRoles;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.models.Role;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.payload.request.LoginRequest;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.payload.request.SignupRequest;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.payload.response.JwtResponse;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.repositories.RoleRepository;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.repositories.UserRepository;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.security.jwt.JwtUtils;
import lt.codeacademy.javaU8.Autoparkas.Autoparkas.security.services.UserDetailsImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private JwtUtils jwtUtils;

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthenticationManager authenticationManager;

    @Test
    void testAuthenticateUser() {
        // Mock login request
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testUser");
        loginRequest.setPassword("testPassword");

        // Mock authentication
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        // Mock UserDetailsImpl
        UserDetailsImpl userDetails = new UserDetailsImpl(1L, "testUser", "test@test.com", "testPassword", Collections.emptyList());
        Mockito.when(authentication.getPrincipal()).thenReturn(userDetails);

        // Mock JWT token
        Mockito.when(jwtUtils.generateJwtToken(authentication)).thenReturn("mockToken");

        // Set up controller
        AuthController authController = new AuthController(authenticationManager, jwtUtils);

        // Call the authenticateUser method
        ResponseEntity<?> responseEntity = authController.authenticateUser(loginRequest);

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Retrieve the token from the response body
        String token = ((JwtResponse) responseEntity.getBody()).getAccessToken();

        // Verify the token
        assertEquals("mockToken", token);
    }

    @Test
    public void testRegisterUser() throws Exception {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("testuser");
        signupRequest.setEmail("test@example.com");
        signupRequest.setPassword("testpassword");
        Set<String> roles = new HashSet<>();
        roles.add("driver");
        signupRequest.setRole(roles);

        // Mock role repository to return a role when queried with a role name
        Role driverRole = new Role(EnumRoles.ROLE_DRIVER);
        when(roleRepository.findByName(any(EnumRoles.class))).thenReturn(Optional.of(driverRole));

        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(signupRequest)))
                .andExpect(status().isOk());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
