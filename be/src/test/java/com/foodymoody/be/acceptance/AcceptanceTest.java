package com.foodymoody.be.acceptance;

import static com.foodymoody.be.acceptance.auth.AuthSteps.로그인_한다;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

import com.foodymoody.be.acceptance.util.DatabaseCleanup;
import com.foodymoody.be.acceptance.util.TableCleanup;
import com.foodymoody.be.acceptance.util.SqlFileExecutor;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.restassured3.RestAssuredRestDocumentation;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ExtendWith(RestDocumentationExtension.class)
public abstract class AcceptanceTest {

    @Autowired
    protected DatabaseCleanup databaseCleanup;
    @Autowired
    protected TableCleanup tableCleanup;
    @Autowired
    protected SqlFileExecutor sqlFileExecutor;

    public static final DockerImageName MYSQL_IMAGE = DockerImageName.parse("mysql:8.0");
    public static String 회원아티_액세스토큰;
    public static String 회원푸반_액세스토큰;

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
        var 회원아티_로그인응답 = 로그인_한다("ati@ati.com", "ati123!", new RequestSpecBuilder().build());
        회원아티_액세스토큰 = 회원아티_로그인응답.jsonPath().getString("accessToken");
        var 회원푸반_로그인응답 = 로그인_한다("puban@puban.com", "puban123!", new RequestSpecBuilder().build());
        회원푸반_액세스토큰 = 회원푸반_로그인응답.jsonPath().getString("accessToken");
    }

    @AfterEach
    void cleanup() {
        databaseCleanup.execute();
    }

    @BeforeAll
    static void startContainer() {
        MYSQL.start();
    }

    protected void 데이터베이스를_비운다(List<String> excludeTableNames) {
        databaseCleanup.setExcludeTables(excludeTableNames);
        databaseCleanup.execute();
    }

    protected void 데이터베이스를_초기화한다() {
        databaseCleanup.setExcludeTables(List.of());
        databaseCleanup.execute();
        sqlFileExecutor.execute("data.sql");
    }

    protected void sql파일을_실행한다(String sqlFilePath) {
        sqlFileExecutor.execute(sqlFilePath);
    }

    protected void 테이블을_비운다(String tableName) {
        tableCleanup.setTableName(tableName);
        tableCleanup.execute();
    }
}
