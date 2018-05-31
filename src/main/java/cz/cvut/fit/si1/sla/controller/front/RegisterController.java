package cz.cvut.fit.si1.sla.controller.front;

import cz.cvut.fit.si1.sla.domain.SlaUser;
import cz.cvut.fit.si1.sla.dto.CustomerUserDto;
import cz.cvut.fit.si1.sla.serviceImpl.SlaCustomerService;
import cz.cvut.fit.si1.sla.serviceImpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller

@RequestMapping()
public class RegisterController {

    @Autowired
    SlaCustomerService customerService;

    @Autowired
    UserService userService;


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        CustomerUserDto customerUserDto = new CustomerUserDto();
        model.addAttribute("customerUserFrom", customerUserDto);


        return "jsp/front/register";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("customerUserFrom") CustomerUserDto customerUserDto,
                               BindingResult result, Model model,
                               final RedirectAttributes redirectAttributes) {
        // todo verification unique email !!!

        SlaUser newUser = userService.registerNewCustomer(customerUserDto);

        model.addAttribute("name", newUser.getCustomer().getName());
        return "jsp/front/registerSuccess";
    }

}
