package cz.cvut.fit.si1.sla.validator;


import cz.cvut.fit.si1.sla.domain.SlaChipCard;
import cz.cvut.fit.si1.sla.service.SlaChipCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Used to validate chip card form
 */
@Component
public class ChipCardFormValidator implements Validator {

    @Autowired
    SlaChipCardService chipCardService;


    @Override
    public boolean supports(Class<?> aClass) {
        return SlaChipCard.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SlaChipCard card = (SlaChipCard) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rfidId", "NotEmpty.slaChipCardForm.rfidId");

        if (card.getRfidId() == null) {
            errors.rejectValue("rfidId", "NotEmpty.slaChipCardForm.rfidId");
        }

        if (card.getDepositPrice() <= 0) {
            errors.rejectValue("depositPrice", "NotEmpty.slaChipCardForm.depositPrice");
        }

    }
}
