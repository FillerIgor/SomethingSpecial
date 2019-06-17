package com.example.anagram.util;

import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


public class UUIDUtilTest {

    @Test
    public void shouldConvertFromStringToUUID() {
        //given
        final UUID uuid = UUID.randomUUID();
        //when
        final UUID result = UUIDUtil.fromString(uuid.toString());
        //then
        assertThat(result).isEqualByComparingTo(uuid);
    }

    @Test
    public void shouldConvertFromUUIDToString() {
        //given
        final UUID uuid = UUID.randomUUID();
        //when
        final String result = UUIDUtil.fromUUID(uuid);
        //then
        assertThat(result).isEqualTo(uuid.toString());
    }
}