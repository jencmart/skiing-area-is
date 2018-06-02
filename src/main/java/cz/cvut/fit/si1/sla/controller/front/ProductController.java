package cz.cvut.fit.si1.sla.controller.front;


import cz.cvut.fit.si1.sla.service.SlaCustomerService;
import cz.cvut.fit.si1.sla.service.SlaSkipassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for products
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    SlaCustomerService customerService;

    @Autowired
    private SlaSkipassService skipassService;

    /**
     * Handles product page
     *
     * @param model model
     * @return products view
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showProfile(Model model) {

        model.addAttribute("skipassess", skipassService.getAllList());
        return "jsp/front/products";
    }
}
