package com.switchfully.vaadin.ordergui.webapp.components;

import com.vaadin.data.util.converter.StringToFloatConverter;
import com.vaadin.data.util.converter.StringToIntegerConverter;

import java.text.NumberFormat;
import java.util.Locale;

public class Converters {

    public static StringToFloatConverter getFloatConverter() {
        return new StringToFloatConverter(){
            @Override
            protected NumberFormat getFormat(Locale locale) {
                NumberFormat format = super.getFormat(locale);
                format.setGroupingUsed(false);
                return format;
            };
        };
    }

    public static StringToIntegerConverter getIntegerConverter() {
        return new StringToIntegerConverter(){
            @Override
            protected NumberFormat getFormat(Locale locale) {
                NumberFormat format = super.getFormat(locale);
                format.setGroupingUsed(false);
                return format;
            };
        };
    }
}
