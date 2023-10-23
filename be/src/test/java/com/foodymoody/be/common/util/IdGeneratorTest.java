package com.foodymoody.be.common.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ID 생성기 테스트")
class IdGeneratorTest {

    @DisplayName("생성된 ID가 unique한지 확인한다.")
    @Test
    void test_id_is_unique() {
        // given
        Set<String> generatedIds = new HashSet<>();
        int count = 1000000;

        // when
        for (int i = 0; i < count; i++) {
            String id = IdGenerator.generate();
            generatedIds.add(id);
        }

        // then
        assertThat(generatedIds).size().isEqualTo(count);
    }

    @DisplayName("생성된 ID가 16진수로 이루어져있는지 확인한다.")
    @Test
    void test_id_is_hexadecimal() {
        // given
        String id = IdGenerator.generate();

        // when
        boolean isHexadecimal = id.matches("[0-9a-f]+");

        // then
        assertThat(isHexadecimal).isTrue();
    }

    @DisplayName("생성된 ID가 길이가 24인지 확인한다.")
    @Test
    void test_id_is_24_length() {
        // given
        String id = IdGenerator.generate();

        // when
        int length = id.length();

        // then
        assertThat(length).isEqualTo(24);
    }

    @DisplayName("멀티 스테드 환경에서 생성된 ID가 unique한지 확인한다.")
    @Test
    void test_id_is_unique_in_multi_thread() {
        // given
        Set<String> generatedIds = new ConcurrentSkipListSet<>();
        int count = 1000000;

        // when
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < count / 2; i++) {
                String id = IdGenerator.generate();
                generatedIds.add(id);
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < count / 2; i++) {
                String id = IdGenerator.generate();
                generatedIds.add(id);
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException ignored) {
        }

        // then
        assertThat(generatedIds).size().isEqualTo(count);
    }
}
