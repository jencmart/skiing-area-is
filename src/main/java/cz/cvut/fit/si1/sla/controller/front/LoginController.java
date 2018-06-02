package cz.cvut.fit.si1.sla.controller.front;

import cz.cvut.fit.si1.sla.dto.UserLoginDto;
import cz.cvut.fit.si1.sla.serviceImpl.SlaCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;


@Controller
@RequestMapping()

public class LoginController {

    @Autowired
    SlaCustomerService customerService;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        UserLoginDto userLoginDto = new UserLoginDto();
        model.addAttribute("customerUserLoginFrom", userLoginDto);
        return "jsp/front/login";
    }
}

