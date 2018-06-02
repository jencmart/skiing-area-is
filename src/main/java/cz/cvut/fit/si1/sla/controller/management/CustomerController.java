package cz.cvut.fit.si1.sla.controller.management;


import cz.cvut.fit.si1.sla.domain.SlaCustomer;
import cz.cvut.fit.si1.sla.service.SlaCustomerService;
import cz.cvut.fit.si1.sla.service.SlaOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Controller for managing customers
 */
@Controller
@RequestMapping("/management/customer")
public class CustomerController {


    // private final Logger logger = LoggerFactory.getLogger(ChipCardController.class);
    private static final String flashMsgNotFound = "Zakaznik nenalezen!";

    private static final String viewList = "jsp/management/customer/list";
    private static final String viewShow = "jsp/management/customer/show";


    private static final String allObjectsAttribute = "slaCustomers";
    private static final String oneObjectAttribute = "slaCustomer";

    private static final String contextRedirect = "/management/customer/";

    @Autowired
    SlaCustomerService customerService;

    @Autowired
    SlaOrderService orderService;

    /**
     * List all customers
     *
     * @param model model
     * @return view
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showAllCustomers(Model model) {
        model.addAttribute(allObjectsAttribute, customerService.getAllList());

        return viewList;
    }


    /**
     * Show certain customer
     *
     * @param id    id of customer
     * @param model model
     * @return view
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showCustomer(@PathVariable("id") int id, Model model) {

        SlaCustomer slaCustomer = customerService.findById(id);
        if (slaCustomer == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", flashMsgNotFound);
        }
        model.addAttribute(oneObjectAttribute, slaCustomer);
        return viewShow;
    }

    /**
     * Find customer by customer
     *
     * @param customer customer (some values can be null)
     * @param model    model
     * @return view
     */
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String findCustomer(@ModelAttribute SlaCustomer customer, Model model) {


        List<SlaCustomer> customerList = customerService.findCustomer(customer);
        if (customerList.size() == 0) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Zakaznik nenalezen");
            model.addAttribute(allObjectsAttribute, customerService.getAllList());
        } else {
            model.addAttribute(allObjectsAttribute, customerList);
        }

        return viewList;
    }
}