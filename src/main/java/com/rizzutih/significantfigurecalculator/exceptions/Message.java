package com.rizzutih.significantfigurecalculator.exceptions;

/**
 * Created by h.rizzuti on 20/06/2018.
 */
public interface Message<T> {

    T getCode();

    String getMessage();

}
