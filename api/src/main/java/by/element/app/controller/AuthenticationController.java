package by.element.app.controller;

import by.element.app.exception.authentication.AuthenticationException;
import by.element.app.exception.validation.ValidationException;
import by.element.app.models.security.AccessToken;
import by.element.app.models.user.dto.AuthenticationSignInDto;
import by.element.app.models.user.dto.AuthenticationSignUpDto;
import by.element.app.service.authentication.AuthenticationService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/element")
@Slf4j
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @GetMapping("/startPage")
    public ModelAndView showStartPage() {
        log.info("Opened general page");
        return new ModelAndView(
            "startPage"
        );
    }

    @GetMapping("/signIn")
    public ModelAndView getSignInPage() {
        return new ModelAndView(
            "signInPage"
        );
    }

    @GetMapping("/signUp")
    public ModelAndView getSignUpPage() {
        return new ModelAndView(
                "signUpPage"
        );
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@ModelAttribute @Valid @Parameter(description = "User info", required = true)
                                    AuthenticationSignInDto signInDto,
                                    BindingResult bindingResult,
                                    UriComponentsBuilder urlBuilder) throws AuthenticationException {

        if (bindingResult.hasErrors()) {
            String message = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            throw new ValidationException(message);
        }

        AccessToken token = authenticationService.signIn(signInDto);

        URI uri = urlBuilder
                .path("/element/chatPage")
                .queryParam("userId", token.getPrincipal().getId())
                .build()
                .toUri();

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(uri)
                .build();
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@ModelAttribute @Valid @Parameter(description = "User info", required = true)
                                    AuthenticationSignUpDto authenticationSignUpDto,
                                    BindingResult bindingResult,
                                    UriComponentsBuilder urlBuilder) {

        if (bindingResult.hasErrors()) {
            String message = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            throw new ValidationException(message);
        }

        AccessToken token = authenticationService.signUp(authenticationSignUpDto);

        URI uri = urlBuilder
                .path("/element/chatPage")
                .queryParam("userId", token.getPrincipal().getId())
                .build()
                .toUri();

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(uri)
                .header(HttpHeaders.AUTHORIZATION, token.toString())
                .build();
    }
}
