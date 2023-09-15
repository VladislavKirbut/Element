package by.element.app.controller;

import by.element.app.exception.authentication.AuthenticationException;
import by.element.app.models.security.AccessToken;
import by.element.app.models.user.AuthenticationSignInDto;
import by.element.app.models.user.AuthenticationSignUpDto;
import by.element.app.service.authentication.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Controller
@RequiredArgsConstructor
@RequestMapping("/element")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @GetMapping("/startPage")
    public ModelAndView showStartPage() {
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
    public ResponseEntity<?> signIn(@ModelAttribute AuthenticationSignInDto signInDto, UriComponentsBuilder urlBuilder) throws AuthenticationException {
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
    public ResponseEntity<?> signUp(@ModelAttribute AuthenticationSignUpDto authenticationSignUpDto, UriComponentsBuilder urlBuilder) {

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
