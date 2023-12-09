package com.foodymoody.be.acceptance;

import static com.foodymoody.be.acceptance.auth.AuthSteps.로그인_한다;
import static com.foodymoody.be.acceptance.auth.AuthSteps.푸반이_로그인한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.*;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

import com.foodymoody.be.acceptance.util.DatabaseCleanup;
import com.foodymoody.be.acceptance.util.SqlFileExecutor;
import com.foodymoody.be.acceptance.util.TableCleanup;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import javax.annotation.PostConstruct;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.restassured3.RestAssuredRestDocumentation;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(RestDocumentationExtension.class)
public abstract class AcceptanceTest {

    public static final DockerImageName MYSQL_IMAGE = DockerImageName.parse("mysql:8.0");
    public static final DockerImageName REDIS_IMAGE = DockerImageName.parse("redis:7.2");
    public static final MySQLContainer<?> MYSQL = new MySQLContainer<>(MYSQL_IMAGE)
            .withDatabaseName("foodymoody").withUsername("bono").withPassword("1111").withReuse(true);
    public static final GenericContainer<?> REDIS = new GenericContainer<>(REDIS_IMAGE).withReuse(true);

    static {
        MYSQL.setPortBindings(List.of("3306:3306"));
        REDIS.setPortBindings(List.of("6379:6379"));
    }

    @LocalServerPort
    public int port;
    public String 회원아티_액세스토큰;
    public String 회원푸반_액세스토큰;
    @Autowired
    protected DatabaseCleanup databaseCleanup;
    @Autowired
    protected TableCleanup tableCleanup;
    @Autowired
    protected SqlFileExecutor sqlFileExecutor;
    protected RequestSpecification spec;

    public static void api_문서_타이틀(String documentName, RequestSpecification specification) {
        specification.filter(document(documentName, Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                Preprocessors.preprocessResponse(Preprocessors.prettyPrint())));
    }

    @PostConstruct
    public void setRestAssuredPort() {
        RestAssured.port = port;
    }

    protected void 데이터베이스를_초기화한다() {
        databaseCleanup.execute();
        sqlFileExecutor.execute("data.sql");
    }

    protected void 테이블을_비운다(String tableName) {
        tableCleanup.setTableName(tableName);
        tableCleanup.execute();
    }

    @BeforeEach
    void setSpec(RestDocumentationContextProvider provider) {
        데이터베이스를_초기화한다();
        initAccessToken();
        this.spec = new RequestSpecBuilder().addFilter(
                RestAssuredRestDocumentation.documentationConfiguration(provider)).build();
    }

    @BeforeAll
    static void startContainer() {
        MYSQL.start();
        REDIS.start();
    }

    private void initAccessToken() {
        회원아티_액세스토큰 = 아티_액세스토큰_요청();
        회원푸반_액세스토큰 = 푸반_엑세스토큰_요청();
    }

    private static String 푸반_엑세스토큰_요청() {
        푸반_회원_가입한다();
        return 푸반이_로그인한다(new RequestSpecBuilder().build()).jsonPath().getString("accessToken");
    }

    private static String 아티_액세스토큰_요청() {
        아티_회원_가입한다();
        return 로그인_한다("ati@ati.com", "ati123!", new RequestSpecBuilder().build()).jsonPath().getString("accessToken");
    }
}
