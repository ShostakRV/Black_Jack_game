package com.my.application.black.jack.server.service;

import java.util.Collection;

/**
 * Developer: Roman Shostak
 * Date: 27-Oct-15.
 */
public interface CardGeneratorFactory {

    CardGenerator createGenerator(Collection<Integer> existingCards);
}
