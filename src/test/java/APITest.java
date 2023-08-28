import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class APITest {

    private static ExtentReports extent;
    private static ExtentSparkReporter spark;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://viacep.com.br/ws";

        spark = new ExtentSparkReporter("RelatorioDeExecucao.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @AfterAll
    public static void tearDown() {
        extent.flush();
    }

    @ParameterizedTest
    @ValueSource(strings = {"90619900", "20211901", "96050500"})
    public void testeCenarioCepValido(String cep) {
        ExtentTest test = extent.createTest("testeCenarioCepValido");
        test.info("Iniciando teste com CEP válido: " + cep);

        given()
                .pathParam("cep", cep)
                .when()
                .get("/{cep}/json")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("response-schema.json"))
                .body("logradouro", is(notNullValue()))
                .extract().response();

            test.log(Status.PASS, "Teste concluído com sucesso");
    }

    @ParameterizedTest
    @ValueSource(strings = {"12345678", "98765432", "00067000"})
    public void testeCenarioCepInvalido(String cep) {
        ExtentTest test = extent.createTest("testeCenarioCepInvalido");
        test.info("Iniciando teste com CEP inválido: " + cep);

        given()
                .pathParam("cep", cep)
                .when()
                .get("/{cep}/json")
                .then()
                .statusCode(200)
                .body("erro", is(true));

        test.log(Status.PASS, "Teste concluído com sucesso");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1234678", "987654320", "0"})
    public void testeCenarioQuantidadeInvalidaDigitosCep() {
        String cep = "0100100"; // CEP com formato incorreto

        ExtentTest test = extent.createTest("testeCenarioCepFormatoIncorreto");
        test.info("Iniciando teste com CEP com quantidade invalida de digitos: " + cep);

        given()
                .pathParam("cep", cep)
                .when()
                .get("/{cep}/json")
                .then()
                .statusCode(400)
                .body(containsString("<title>ViaCEP 400</title>"));

        test.log(Status.PASS, "Teste concluído com sucesso");
    }
}
