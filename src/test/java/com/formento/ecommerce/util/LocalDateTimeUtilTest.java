package com.formento.ecommerce.util;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class LocalDateTimeUtilTest {

    @Test
    public void testFromText() {
        // given
        LocalDateTime localDateTime = LocalDateTime.of(2016, 5, 10, 22, 30, 40);

        // when
        String format = LocalDateTimeUtil.format(localDateTime);

        // then
        assertEquals("10-05-2016 22:30:40", format);
    }

    @Test
    public void testFormat() {
        // given
        String text = "10-05-2016 22:30:40";

        // when
        LocalDateTime localDateTime = LocalDateTimeUtil.fromText(text);

        // then
        assertEquals(LocalDateTime.of(2016, 5, 10, 22, 30, 40), localDateTime);
    }

}