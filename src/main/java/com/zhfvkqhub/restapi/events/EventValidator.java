package com.zhfvkqhub.restapi.events;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;

@Component
public class EventValidator {

    public void validate(EventDto eventDto, Errors errors){
        if(eventDto.getBasePrice() > eventDto.getMaxPrice() && eventDto.getMaxPrice() != 0){
//            errors.rejectValue("basePrice", "wrongValue", "BasePrice is wrong.");
//            errors.rejectValue("maxPrice", "wrongValue", "maxPrice is wrong.");
            errors.reject("wrongPrices", "Values fo prices are wrong"); // 글로벌 오류
        }

        LocalDateTime endEventDateTime = eventDto.getEndEventDateTime();
        if(endEventDateTime.isBefore(eventDto.getBeginEventDateTime()) ||
            endEventDateTime.isBefore(eventDto.getCloseEnrollmentDateTime()) ||
            endEventDateTime.isBefore(eventDto.getBeginEnrollmentDateTime())){
            errors.rejectValue("endEventDateTime","wrongValue", "endEventDateTime is wrong"); // 필드 오류
        }
    }
}
