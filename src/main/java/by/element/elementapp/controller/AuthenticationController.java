package by.element.elementapp.controller;

import by.element.elementapp.exception.authentication.AuthenticationException;
import by.element.elementapp.models.user.AuthenticationSignInDto;
import by.element.elementapp.models.user.AuthenticationDto;
import by.element.elementapp.models.user.AuthenticationSignUpDto;
import by.element.elementapp.service.authentication.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

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
    public ResponseEntity<?> checkRegistry(@ModelAttribute AuthenticationSignInDto authorizationData, UriComponentsBuilder urlBuilder) throws AuthenticationException {

        AuthenticationDto userAuthenticated = authenticationService.isUserAuthenticated(authorizationData);

        URI uri = urlBuilder
                .path("/element/chatPage")
                .queryParam("AuthenticationDto", userAuthenticated)
                .build()
                .toUri();

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(uri)
                .build();
    }

    @GetMapping("/chatPage")
    public ModelAndView getChatPage(@RequestParam AuthenticationDto authenticationDto) {
        return new ModelAndView(
                "chatPage",
                Map.of("authenticationDto", authenticationDto)
        );
    }

/*    @PostMapping("/signUp")
    public ResponseEntity<?> userRegister(@ModelAttribute AuthenticationSignUpDto authenticationSignUpDto, UriComponentsBuilder urlBuilder) {
        URI uri = urlBuilder
                .path("/element/chatPage")
                .build()
                .toUri();

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(uri)
                .build();
    }*/
}
