package com.my.application.black.jack.server.service;

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
