package com.foodymoody.be.docs;

import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.restassured3.RestAssuredRestDocumentation;
import org.springframework.restdocs.restassured3.RestAssuredRestDocumentationConfigurer;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ExtendWith(RestDocumentationExtension.class)
class Document {

	protected RequestSpecification spec;

	@BeforeEach
	void setSpec(RestDocumentationContextProvider provider) {
		this.spec = new RequestSpecBuilder()
			.addFilter(RestAssuredRestDocumentation.documentationConfiguration(provider))
			.build();
	}

	@DisplayName("피드를 등록한다.")
	@Test
	void createFeed() {
		spec.filter(document("registerFeed", Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
			Preprocessors.preprocessResponse(Preprocessors.prettyPrint())));

		// given
		Map<String, Object> body = new HashMap<>();
		body.put("review", "맛있게 먹었습니다.");
		body.put("images", List.of("https://www.google.com/", "https://www.naver.com/"));
		body.put("menu", List.of(List.of(Map.of("마라탕", 4)), List.of(Map.of("떡볶이", 5))));

		// when
		var extract = RestAssured
			.given()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.spec(spec)
			.log().all()
			.body(body)
			.when()
			.post("/api/feeds")
			.then()
			.log().all()
			.extract();

		// then
	}

}
