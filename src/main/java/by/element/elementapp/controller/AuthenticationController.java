package by.element.elementapp.controller;

import by.element.elementapp.exception.authentication.AuthenticationException;
import by.element.elementapp.models.AuthenticationDataShortDto;
import by.element.elementapp.service.authentication.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

/*    @PostMapping("/signIn")
    public ResponseEntity<String> checkRegistry(@ModelAttribute AuthenticationDataShortDto authorizationData) throws AuthenticationException {
        return null;
    }*/
}
