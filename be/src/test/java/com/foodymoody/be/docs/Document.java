package com.foodymoody.be.docs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.restassured3.RestAssuredRestDocumentation;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ExtendWith(RestDocumentationExtension.class)
@Testcontainers
abstract class Document {

    protected RequestSpecification spec;

    public static final DockerImageName MYSQL_IMAGE = DockerImageName.parse("mysql:8.0.24");
    @Container
    public static final MySQLContainer<?> MYSQL = new MySQLContainer<>(MYSQL_IMAGE)
            .withDatabaseName("foodymoody")
            .withUsername("bono")
            .withPassword("1111")
            .withExposedPorts(3306)  // 컨테이너 내부에서 3306 포트를 노출
            .withReuse(true);

    static {
        MYSQL.setPortBindings(List.of("3306:3306"));  // 호스트의 3308 포트를 컨테이너의 3306 포트에 바인드
    }

    @BeforeEach
    void setSpec(RestDocumentationContextProvider provider) {
        this.spec = new RequestSpecBuilder()
                .addFilter(RestAssuredRestDocumentation.documentationConfiguration(provider))
                .build();
    }

    @BeforeAll
    static void startContainer(){
        MYSQL.start();
    }

//    @AfterEach
//    void cleanUp(){
//        MYSQL.stop();
//        System.out.println("1");
//    }


//    @DynamicPropertySource
//    public static void setMysqlProperties(DynamicPropertyRegistry registry){
//        registry.add("spring.datasource.url",() -> "jdbc:mysql://localhost:"+MYSQL.getFirstMappedPort()+"/foodymoody");
//    }

}
