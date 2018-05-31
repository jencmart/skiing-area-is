package cz.cvut.fit.si1.sla.controller.front;

import cz.cvut.fit.si1.sla.serviceImpl.SlaCustomerService;
import cz.cvut.fit.si1.sla.serviceImpl.SlaSkipassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

@RequestMapping()

public class PublicWelcomeController {

    @Autowired
    SlaCustomerService customerService;

    @Autowired
    private SlaSkipassService skipassService;

    @RequestMapping("")
    public ModelAndView welcome() {

        ModelAndView modelAndView = new ModelAndView("jsp/front/index");

        modelAndView.addObject("skipassess", skipassService.getAllList());
        return modelAndView;
    }


}
