package com.zhfvkqhub.restapi.events;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;

@Component
public class EventValidator {

    public void validate(EventDTO eventDTO, Errors errors){
        if(eventDTO.getBasePrice() > eventDTO.getMaxPrice() && eventDTO.getMaxPrice() != 0){
            errors.rejectValue("basePrice", "wrongValue", "BasePrice is wrong.");
            errors.rejectValue("maxPrice", "wrongValue", "maxPrice is wrong.");
        }

        LocalDateTime endEventDateTime = eventDTO.getEndEventDateTime();
        if(endEventDateTime.isBefore(eventDTO.getBeginEventDateTime()) ||
            endEventDateTime.isBefore(eventDTO.getCloseEnrollmentDateTime()) ||
            endEventDateTime.isBefore(eventDTO.getBeginEnrollmentDateTime())){
            errors.rejectValue("endEventDateTime","wrongValue", "endEventDateTime is wrong");
        }
    }
}
