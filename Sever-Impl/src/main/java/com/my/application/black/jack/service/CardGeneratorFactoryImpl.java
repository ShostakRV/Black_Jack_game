package com.my.application.black.jack.service;

import com.my.application.black.jack.service.CardGenerator;
import com.my.application.black.jack.service.CardGeneratorFactory;

import java.util.Collection;

/**
 * Developer: Roman Shostak
 * Date: 27-Oct-15.
 */
public class CardGeneratorFactoryImpl implements CardGeneratorFactory {
    public CardGenerator createGenerator(Collection<Integer> existingCards){
        CardGenerator generator = new  CardGeneratorImpl();

        return generator;
    }
}
