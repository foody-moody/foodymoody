package com.foodymoody.be.docs;

import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.restassured3.RestAssuredRestDocumentation;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ExtendWith(RestDocumentationExtension.class)
public abstract class Document {

    public static final DockerImageName MYSQL_IMAGE = DockerImageName.parse("mysql:8.0");

    public static final MySQLContainer<?> MYSQL = new MySQLContainer<>(MYSQL_IMAGE)
            .withDatabaseName("foodymoody")
            .withUsername("bono")
            .withPassword("1111")
            .withReuse(true);

    static {
        MYSQL.setPortBindings(List.of("3306:3306"));
    }

    protected RequestSpecification spec;

    public static void api_문서_타이틀(String documentName, RequestSpecification specification) {
        specification.filter(
                document(documentName,
                        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()))
        );
    }

    @BeforeEach
    void setSpec(RestDocumentationContextProvider provider) {
        this.spec = new RequestSpecBuilder()
                .addFilter(RestAssuredRestDocumentation.documentationConfiguration(provider))
                .build();
    }

    @BeforeAll
    static void startContainer() {
        MYSQL.start();
    }
}
