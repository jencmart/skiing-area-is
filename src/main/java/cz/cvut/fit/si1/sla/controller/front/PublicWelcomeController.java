package cz.cvut.fit.si1.sla.controller.front;

import cz.cvut.fit.si1.sla.service.SlaCustomerService;
import cz.cvut.fit.si1.sla.service.SlaSkipassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Homepage controller
 */
@Controller
@RequestMapping()
public class PublicWelcomeController {

    @Autowired
    SlaCustomerService customerService;

    @Autowired
    private SlaSkipassService skipassService;

    /**
     * Handles homepage
     *
     * @return index view
     */
    @RequestMapping("")
    public ModelAndView welcome() {

        ModelAndView modelAndView = new ModelAndView("jsp/front/index");

        modelAndView.addObject("skipassess", skipassService.getAllList());
        return modelAndView;
    }
}
