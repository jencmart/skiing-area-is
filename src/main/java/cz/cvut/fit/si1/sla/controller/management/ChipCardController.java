package cz.cvut.fit.si1.sla.controller.management;


import cz.cvut.fit.si1.sla.domain.SlaChipCard;
import cz.cvut.fit.si1.sla.domain.SlaOrderSkipassArticle;
import cz.cvut.fit.si1.sla.domain.SlaRent;
import cz.cvut.fit.si1.sla.service.SlaChipCardService;
import cz.cvut.fit.si1.sla.service.SlaOrderService;
import cz.cvut.fit.si1.sla.validator.ChipCardFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.util.HashMap;

/**
 * Controller for managment of chip cards
 */
@Controller
@RequestMapping("/management/chipcard")
public class ChipCardController {

    // private final Logger logger = LoggerFactory.getLogger(ChipCardController.class);

    private static final String flashMsgSuccessDelete = "Cipova karta smazana!";
    private static final String flashMsgNotFound = "Cipova karta nenalezena!";
    private static final String flashMsgSuccessAdd = "Cipova karta uspesne pridana!";
    private static final String flashMsgSuccessEdit = "Cipova karta uspesne upravena!";

    private static final String viewList = "jsp/management/chipcard/list";
    private static final String viewShow = "jsp/management/chipcard/show";
    private static final String viewForm = "jsp/management/chipcard/form";

    private static final String formAttribute = "slaChipCardForm";
    private static final String allObjectsAttribute = "slaChipCards";
    private static final String oneObjectAttribute = "slaChipCard";

    private static final String contextRedirect = "/management/chipcard/";

    @Autowired
    SlaChipCardService chipCardService;

    @Autowired
    ChipCardFormValidator chipCardFormValidator;

    @Autowired
    SlaOrderService orderService;

    /**
     * Form validator
     *
     * @param binder binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(chipCardFormValidator);
    }


    /**
     * List all chip cards
     *
     * @param model model
     * @return view
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showAllChipCards(Model model) {
        //     logger.debug("showAllChipCards()");
        model.addAttribute(allObjectsAttribute, chipCardService.getAllList());

        return viewList;
    }

    // SAVE or UPDATE slaChipCard
    // 1. @ModelAttribute bind form value
    // 2. @Validated form validator
    // 3. RedirectAttributes for flash value

    /**
     * Save or update chip card
     *
     * @param slaChipCard        chip card (bind form value, form validator)
     * @param result             result
     * @param model              model
     * @param redirectAttributes redirect for flash value
     * @return view
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String saveOrUpdateChipCard(@ModelAttribute(formAttribute) @Validated SlaChipCard slaChipCard,
                                       BindingResult result, Model model,
                                       final RedirectAttributes redirectAttributes) {
        // todo verification unique rfid !!!

        if (result.hasErrors()) {
            //populateDefaultModel(model);
            return viewForm;

        } else {

            // Add message to flash scope
            redirectAttributes.addFlashAttribute("css", "success");
            if (slaChipCard.isNew()) {
                slaChipCard.setRegisteredTimestamp(new Timestamp(System.currentTimeMillis())); //todo  ! not everywhere
                chipCardService.saveOrUpdate(slaChipCard);
                redirectAttributes.addFlashAttribute("msg", flashMsgSuccessAdd);
            } else {
                SlaChipCard existing = chipCardService.findById(slaChipCard.getIdChipCard());
                existing.setRfidId(slaChipCard.getRfidId());
                existing.setDepositPrice(slaChipCard.getDepositPrice());
                chipCardService.saveOrUpdate(existing);
                redirectAttributes.addFlashAttribute("msg", flashMsgSuccessEdit);
            }


            // POST/REDIRECT/GET
            return "redirect:" + contextRedirect + slaChipCard.getIdChipCard();
        }
    }


    /**
     * Add chip card show form
     *
     * @param model model
     * @return view
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddSlaChipCardForm(Model model) {

        model.addAttribute(formAttribute, new SlaChipCard());
        //populateDefaultModel(model);

        return viewForm;
    }

    /**
     * Return chip card form
     *
     * @param rfid               rfid
     * @param model              model
     * @param redirectAttributes redirect
     * @return redirect
     */
    @RequestMapping(value = "/return", method = RequestMethod.POST)
    public String returnChipCardForm(@RequestParam(value = "rfid") String rfid, Model model,
                                     final RedirectAttributes redirectAttributes) {

        SlaChipCard chipCard = chipCardService.findByRfidId(rfid);
        System.out.println("RFID: " + rfid);
        if (chipCard != null) {

            if (returnCard(rfid)) {
                redirectAttributes.addFlashAttribute("css", "success");
                redirectAttributes.addFlashAttribute("msg", "Karta uspesne vracena. Cena zalohy cini: " + chipCard.getDepositPrice() + " Kc.");
            } else {
                redirectAttributes.addFlashAttribute("css", "danger");
                redirectAttributes.addFlashAttribute("msg", "Chyba! Tato karta je registrovana jako vracena.");
            }

        } else {
            redirectAttributes.addFlashAttribute("css", "danger");
            redirectAttributes.addFlashAttribute("msg", "Chyba! Karta neni v systemu registrovana.");
        }

        return "redirect:" + contextRedirect;
    }

    /**
     * Try to return rented chip card
     *
     * @param rfid rfid
     * @return is card returned
     */
    private boolean returnCard(String rfid) {

        SlaChipCard chipCard = chipCardService.findByRfidIdFetch(rfid);

        if (!chipCard.isCardRented())
            return false;

        for (SlaRent rent : chipCard.getRents()) {
            if (!rent.getDepositReturned()) {
                SlaOrderSkipassArticle article = rent.getArticle();

                chipCard.setCardRented(false);
                rent.setDepositReturned(true);
                article.setCardRented(false);

                chipCardService.saveOrUpdate(chipCard);
                orderService.updateRent(rent);
                orderService.updateArticle(article);

                return true;
            }
        }

        return false;
    }


    /**
     * Form update chip card (GET)
     *
     * @param id    id of card
     * @param model model
     * @return view
     */
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateSlaChipCardForm(@PathVariable("id") int id, Model model) {

        SlaChipCard slaChipCard = chipCardService.findById(id);
        model.addAttribute(formAttribute, slaChipCard);

        return viewForm;
    }

    /**
     * Delete chip card
     *
     * @param id                 id of card
     * @param redirectAttributes redirect
     * @return redirect
     */
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String deleteSlaChipCard(@PathVariable("id") long id,
                                    final RedirectAttributes redirectAttributes) {

        chipCardService.deleteById(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", flashMsgSuccessDelete);

        return "redirect:" + contextRedirect;
    }

    /**
     * Show chip card
     *
     * @param id    id of card
     * @param model model
     * @return view
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showSlaChipCard(@PathVariable("id") int id, Model model) {

        SlaChipCard slaChipCard = chipCardService.findById(id);
        if (slaChipCard == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", flashMsgNotFound);
        }
        model.addAttribute(oneObjectAttribute, slaChipCard);

        return viewShow;
    }

    /**
     * Ajax show chip card
     *
     * @param id    id of card
     * @param model model
     * @return chip card
     */
    @RequestMapping(value = "/ajaxFind/{id}", method = RequestMethod.GET)
    public @ResponseBody
    HashMap<String, String> ajaxFindChipCard(@PathVariable("id") String id, Model model) {
        SlaChipCard chipCard = chipCardService.findByRfidId(id);

        if (chipCard != null) {
            HashMap<String, String> map = new HashMap<>();
            map.put("idChipCard", chipCard.getIdChipCard().toString());
            map.put("rfidId", chipCard.getRfidId());
            map.put("depositPrice", chipCard.getDepositPrice().toString());

            if (chipCard.isCardRented())
                map.put("cardRented", "true");
            else
                map.put("cardRented", "false");
            return map;
        }
        return null;
    }
}
