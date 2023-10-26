package com.foodymoody.be.common.util;

import static com.foodymoody.be.common.util.Constants.UTILITY_CLASS;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {

    private static final Random random = new Random();

    // 각 섹션별 길이 설정
    private static final int TIMESTAMP_LENGTH = 4;  // 타임스탬프 길이: 4바이트
    private static final int MACHINE_ID_LENGTH = 3;  // 머신 식별자 길이: 3바이트
    private static final int PROCESS_ID_LENGTH = 2;  // 프로세스 식별자 길이: 2바이트
    private static final int COUNTER_LENGTH = 3;     // 카운터 길이: 3바이트

    // 각 섹션별 시작 오프셋 설정
    private static final int TIMESTAMP_OFFSET = 0;
    private static final int MACHINE_ID_OFFSET = TIMESTAMP_OFFSET + TIMESTAMP_LENGTH;
    private static final int PROCESS_ID_OFFSET = MACHINE_ID_OFFSET + MACHINE_ID_LENGTH;
    private static final int COUNTER_OFFSET = PROCESS_ID_OFFSET + PROCESS_ID_LENGTH;
    private static final int TOTAL_LENGTH = TIMESTAMP_LENGTH + MACHINE_ID_LENGTH + PROCESS_ID_LENGTH + COUNTER_LENGTH;

    // 머신과 프로세스의 식별자를 생성하며, 카운터는 무작위 값으로 초기화
    private static final int MACHINE_IDENTIFIER = createMachineIdentifier();
    private static final int PROCESS_IDENTIFIER = createProcessIdentifier();
    private static final AtomicInteger COUNTER = new AtomicInteger(random.nextInt(Integer.MAX_VALUE));
    private static final int TIMESTAMP_MASK = 0xffffffff;  // 타임스탬프 마스크

    // 기본 생성자 - 이 유틸리티 클래스는 인스턴스화되어서는 안됨
    private IdGenerator() {
        throw new IllegalStateException(UTILITY_CLASS);
    }

    // 현재 시간을 기반으로 ID 생성
    public static String generate() {
        return generate(System.currentTimeMillis());
    }

    // 로컬 머신의 MAC 주소를 기반으로 머신 식별자를 생성
    private static int createMachineIdentifier() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac = network.getHardwareAddress();
            return java.util.Arrays.hashCode(mac) & 0xffffff;
        } catch (Exception e) {
            // 예외 발생 시 무작위 값을 반환
            return random.nextInt(0xffffff);
        }
    }

    // 현재 실행중인 JVM 프로세스의 ID를 기반으로 프로세스 식별자를 생성
    private static int createProcessIdentifier() {
        try {
            String processName = ManagementFactory.getRuntimeMXBean().getName();
            return Integer.parseInt(processName.split("@")[0]) & 0xffff;
        } catch (Exception e) {
            // 예외 발생 시 무작위 값을 반환

            return random.nextInt(0xffff);
        }
    }

    // 주어진 정보를 기반으로 ID 생성
    private static String generate(long timestamp) {
        return generate(timestamp, MACHINE_IDENTIFIER, PROCESS_IDENTIFIER, COUNTER.getAndIncrement());
    }

    // 타임스탬프, 머신 식별자, 프로세스 식별자, 카운터를 이용해 ID 생성
    private static String generate(long timestamp, int machineIdentifier, int processIdentifier, int counter) {
        byte[] bytes = new byte[TOTAL_LENGTH];

        // 각 섹션 인코딩
        int time = (int) (timestamp & TIMESTAMP_MASK);
        bytes[TIMESTAMP_OFFSET + 3] = (byte) (time);
        bytes[TIMESTAMP_OFFSET + 2] = (byte) (time >>> 8);
        bytes[TIMESTAMP_OFFSET + 1] = (byte) (time >>> 16);
        bytes[TIMESTAMP_OFFSET] = (byte) (time >>> 24);

        bytes[MACHINE_ID_OFFSET + 2] = (byte) machineIdentifier;
        bytes[MACHINE_ID_OFFSET + 1] = (byte) (machineIdentifier >>> 8);
        bytes[MACHINE_ID_OFFSET] = (byte) (machineIdentifier >>> 16);

        bytes[PROCESS_ID_OFFSET + 1] = (byte) processIdentifier;
        bytes[PROCESS_ID_OFFSET] = (byte) (processIdentifier >>> 8);

        bytes[COUNTER_OFFSET + 2] = (byte) counter;
        bytes[COUNTER_OFFSET + 1] = (byte) (counter >>> 8);
        bytes[COUNTER_OFFSET] = (byte) (counter >>> 16);

        // 바이트 배열을 16진수 문자열로 변환
        return hexString(bytes);
    }

    // 바이트 배열을 16진수 문자열로 변환하는 유틸리티
    private static String hexString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}
