package cz.cvut.fit.si1.sla.controller.management;


import cz.cvut.fit.si1.sla.domain.SlaSkipass;
import cz.cvut.fit.si1.sla.serviceImpl.SlaSkipassService;
import cz.cvut.fit.si1.sla.validator.SkipassFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/management/skipass")
public class SkiPassController {

    private static final String flashMsgSuccessDelete = "Skipass smazan!";
    private static final String flashMsgNotFound = "Skipass nenalezen!";
    private static final String flashMsgSuccessAdd = "Skipass uspesne pridan!";
    private static final String flashMsgSuccessEdit = "Skipass uspesne upraven!";

    private static final String viewList = "jsp/management/skipass/list";
    private static final String viewShow = "jsp/management/skipass/show";
    private static final String viewForm = "jsp/management/skipass/form";

    private static final String formAttribute = "slaSkipassForm";
    private static final String allObjectsAttribute = "slaSkipass";
    private static final String oneObjectAttribute = "slaSkipass";

    private static final String contextRedirect = "/management/skipass/";

    @Autowired
    SlaSkipassService skipassService;

    @Autowired
    SkipassFormValidator skipassFormValidator;

    //form validator
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(skipassFormValidator);
    }

    // LIST SKIPASS
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showAllSkipass(Model model) {

        model.addAttribute(allObjectsAttribute, skipassService.getAllList());

        return viewList;
    }

    // SAVE or UPDATE slaSkipass
    // 1. @ModelAttribute bind form value
    // 2. @Validated form validator
    // 3. RedirectAttributes for flash value
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String saveOrUpdateChipCard(@ModelAttribute(formAttribute) @Validated SlaSkipass slaSkipass,
                                       BindingResult result, Model model,
                                       final RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            //populateDefaultModel(model);
            return viewForm;

        } else {

            // Add message to flash scope
            redirectAttributes.addFlashAttribute("css", "success");
            if (slaSkipass.isNew()) {
                skipassService.saveOrUpdate(slaSkipass);
                redirectAttributes.addFlashAttribute("msg", flashMsgSuccessAdd);
            } else {
                SlaSkipass existing = skipassService.findById(slaSkipass.getIdSkipass());
                existing.setNumberOfDays(slaSkipass.getNumberOfDays());
                existing.setPrice(slaSkipass.getPrice());
                skipassService.saveOrUpdate(existing);
                redirectAttributes.addFlashAttribute("msg", flashMsgSuccessEdit);
            }


            // POST/REDIRECT/GET
            return "redirect:" + contextRedirect + slaSkipass.getIdSkipass();
        }
    }

    // FORM ADD SKIPASS
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddSlaSkipassForm(Model model) {

        model.addAttribute(formAttribute, new SlaSkipass());
        //populateDefaultModel(model);

        return viewForm;
    }

    // FORM UPDATE SKIPASS
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateSlaSkipassForm(@PathVariable("id") int id, Model model) {

        SlaSkipass slaSkipass = skipassService.findById(id);
        model.addAttribute(formAttribute, slaSkipass);

        return viewForm;
    }

    // DELETE SKIPASS
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String deleteSlaSkipass(@PathVariable("id") long id,
                                   final RedirectAttributes redirectAttributes) {

        skipassService.deleteById(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", flashMsgSuccessDelete);

        return "redirect:" + contextRedirect;
    }

    // SHOW SKIPASS
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showSlaSkipass(@PathVariable("id") int id, Model model) {

        SlaSkipass slaSkipass = skipassService.findById(id);
        if (slaSkipass == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", flashMsgNotFound);
        }
        model.addAttribute(oneObjectAttribute, slaSkipass);

        return viewShow;
    }

}
