package by.element.elementapp.controller;

import by.element.elementapp.service.authorization.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/element")
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    @GetMapping("/startPage")
    public ModelAndView showStartPage() {
        return new ModelAndView(
            "startPage"
        );
    }

/*    @GetMapping("/signIn")
    public ModelAndView getSignInPage() {

    }*/
}
