package cz.cvut.fit.si1.sla.controller.management;


import cz.cvut.fit.si1.sla.domain.SlaChipCard;
import cz.cvut.fit.si1.sla.domain.SlaOrder;
import cz.cvut.fit.si1.sla.domain.SlaOrderSkipassArticle;
import cz.cvut.fit.si1.sla.domain.SlaRent;
import cz.cvut.fit.si1.sla.dto.RentDto;
import cz.cvut.fit.si1.sla.service.SlaChipCardService;
import cz.cvut.fit.si1.sla.service.SlaOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.util.List;

/**
 * Controller for managing orders
 */
@Controller
@RequestMapping("/management/order")
public class OrderController {

    // private final Logger logger = LoggerFactory.getLogger(ChipCardController.class);

    private static final String flashMsgSuccessDelete = "Objednavka smazana!";
    private static final String flashMsgNotFound = "Objednavka nenalezena!";
    private static final String flashMsgSuccessAdd = "Objednavka uspesne pridana!";
    private static final String flashMsgSuccessEdit = "Objednavka uspesne upravena!";

    private static final String viewList = "jsp/management/order/list";
    private static final String viewShow = "jsp/management/order/show";
    private static final String viewForm = "jsp/management/order/form";

    private static final String formAttribute = "slaOrderForm";
    private static final String allObjectsAttribute = "slaOrders";
    private static final String oneObjectAttribute = "slaOrder";

    private static final String contextRedirect = "/management/order/";


    @Autowired
    SlaOrderService orderService;

    @Autowired
    SlaChipCardService chipCardService;

    /**
     * Lists all orders
     *
     * @param model model
     * @return view
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showAllChipCards(Model model) {
        model.addAttribute(allObjectsAttribute, orderService.getAllList());
        return viewList;
    }

    /**
     * Rents card by id
     *
     * @param rent               rent dto
     * @param id                 id of card
     * @param redirectAttributes redirect
     * @return redirect to rented card
     */
    @RequestMapping(value = "/{id}/rent", method = RequestMethod.POST)
    public String rentRFID(RentDto rent,
                           @PathVariable("id") int id,
                           final RedirectAttributes redirectAttributes) {
        orderService.rent(rent);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Karta uspesne zapujcena.");

        return "redirect:/management/order/" + id;
    }

    /**
     * Return card by id
     *
     * @param idOrder            id of order
     * @param idArticle          id of article
     * @param redirectAttributes redirect
     * @return redirect to order
     */
    @RequestMapping(value = "/{id}/return/{id2}", method = RequestMethod.POST)
    public String returnRFID(@PathVariable("id") int idOrder,
                             @PathVariable("id2") int idArticle,
                             final RedirectAttributes redirectAttributes) {

        Long depositPrice = (long) 0;

        SlaOrderSkipassArticle article = orderService.getArticle(idArticle);
        List<SlaRent> rents = article.getRents();
        for (SlaRent rent : rents) {

            if (!rent.getDepositReturned()) {
                SlaChipCard card = rent.getChipCard();
                if (!card.isCardRented()) {

                    redirectAttributes.addFlashAttribute("css", "danger");
                    redirectAttributes.addFlashAttribute("msg", "Karta je evidovana jako vracena. Nezlze vratit!");
                    return "redirect:/management/order/";
                } else {
                    card.setCardRented(false);
                    depositPrice = card.getDepositPrice();
                    rent.setDepositReturned(true);
                    article.setCardRented(false);

                    chipCardService.saveOrUpdate(card);
                    orderService.updateRent(rent);
                    orderService.updateArticle(article);
                    break;
                }
            }
        }

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Karta uspesne vracena. Vrate zalohu " + depositPrice + " Kc.");
        return "redirect:" + "/management/order/" + idOrder;

    }

    /**
     * Pay certain order
     *
     * @param id                 id of order
     * @param redirectAttributes redirect
     * @return redirect to payed order
     */
    @RequestMapping(value = "/{id}/pay", method = RequestMethod.POST)
    public String pay(@PathVariable("id") int id,
                      final RedirectAttributes redirectAttributes) {

        SlaOrder order = orderService.findById(id);
        if (order == null) {
            redirectAttributes.addFlashAttribute("css", "danger");
            redirectAttributes.addFlashAttribute("msg", "Chyba! Objednavka nenalezena.");
            return "redirect:" + "/management/order/";
        }

        order.setPaid(true);
        order.setPaidDatetime(new Timestamp(System.currentTimeMillis()));
        orderService.updateOrder(order);


        int price = orderService.calculatePrice(order);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Objednavka uspesne zaplacena.\nCena " + price + " Kc.");

        return "redirect:/management/order/" + id;
    }


    /**
     * Lost card by id
     *
     * @param rent  rent dto
     * @param id    id of card
     * @param model model
     * @return redirect to order
     */
    @RequestMapping(value = "/{id}/lost", method = RequestMethod.POST)
    public String lostRFID(RentDto rent, @PathVariable("id") int id, Model model) {
        // todo

        // musim nastavit ze:
        // 1. karta je ztracena
        // 2. rent musim nastavit na uzavreny + musim nastavit datum ZTRACENI karty
        // 4. artikl objednakvy musim nastavit ze karta je vracena a je vse hotovo...

        return "redirect:/management/order/" + id;
    }


    /**
     * Find order by id
     *
     * @param number             id of order
     * @param redirectAttributes redirect
     * @return redirect to order
     */
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String findOrder(@RequestParam("number") int number,
                            final RedirectAttributes redirectAttributes) {


        SlaOrder order = orderService.findById(number);
        if (order == null) {
            redirectAttributes.addFlashAttribute("css", "danger");
            redirectAttributes.addFlashAttribute("msg", "Chyba! Objednavka nenalezena.");
            return "redirect:" + "/management/order/";
        }

        int price = orderService.calculatePrice(order);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Objednavka nalezena");
        redirectAttributes.addFlashAttribute("msg", "Cena objednavky" + price + " price");
        return "redirect:" + "/management/order/" + number;
    }

    /**
     * Show order by id
     *
     * @param id    id of order
     * @param model model
     * @return view
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showOrder(@PathVariable("id") int id, Model model) {

        SlaOrder slaOrder = orderService.findById(id);
        if (slaOrder == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", flashMsgNotFound);
            return viewShow;
        }


        int price = orderService.calculatePrice(slaOrder);

        List<SlaOrderSkipassArticle> articles = orderService.getArticles(slaOrder);
        model.addAttribute(oneObjectAttribute, slaOrder);
        model.addAttribute("articles", articles);
        model.addAttribute("price", price);
        return viewShow;
    }
}
