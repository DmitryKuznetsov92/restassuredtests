package reqresin

import BaseUrls
import ErrorNotifications
import dataclasses.*
import io.qameta.allure.Description
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.junit.Test
import org.junit.jupiter.api.Assertions

class ReqresTest {
    @Test
    @Description("Получение списка пользователей - 200")
    fun listUserPositiveTest() {
        Specifications.resetSpecification()
        val expectedUser = User.UserData(
            id = 1,
            email = "george.bluth@reqres.in",
            firstName = "George",
            lastName = "Bluth",
            avatar = "https://reqres.in/img/faces/1-image.jpg"
        )

        val userList = given()
            .`when`()
            .contentType(ContentType.JSON)
            .get("${BaseUrls.REQRES_IN_BASE_URL}/api/users")
            .then()
            .statusCode(200)
            .extract().body().`as`(UsersList::class.java)

        val userAvatars = userList.data.map { it.avatar }
        val userIds = userList.data.map { it.id }

        println(userAvatars)
        println(userIds)

        Assertions.assertEquals(userList.data[0], expectedUser)
    }

    @Test
    @Description("Проверка соответствия аватарок пользователю")
    fun avatarNamesTest() {
        Specifications.installSpecification(
            Specifications.requestSpec(BaseUrls.REQRES_IN_BASE_URL),
            Specifications.responseSpec(200)
        )

        val userList = given()
            .`when`()
            .get("/api/users")
            .then()
            .extract().body().`as`(UsersList::class.java)

        userList.data.forEach {
            Assertions.assertTrue(it.avatar.contains(it.id.toString()))
        }
    }

    @Test
    @Description("Проверка почты на корркетный домен")
    fun emailContainsRightDomainTest() {
        Specifications.installSpecification(
            Specifications.requestSpec(BaseUrls.REQRES_IN_BASE_URL),
            Specifications.responseSpec(200)
        )

        val userList = given()
            .`when`()
            .get("/api/users")
            .then()
            .extract().body().`as`(UsersList::class.java)

        Assertions.assertTrue(userList.data.stream().allMatch {
            it.email.endsWith("@reqres.in")
        })
    }

    @Test
    @Description("Проверка успешной регистрации пользователя - 200")
    fun successRegistrationTest() {
        Specifications.installSpecification(
            Specifications.requestSpec(BaseUrls.REQRES_IN_BASE_URL),
            Specifications.responseSpec(200)
        )

        val expectedResponse = RegResponse(
            id = 4,
            token = "QpwL5tke4Pnpja7X4"
        )

        val registrationData = LogRegRequest(
            email = "eve.holt@reqres.in",
            password = "pistol"
        )

        val registrationResponse = given()
            .body(registrationData)
            .`when`()
            .post("/api/register")
            .then()
            .extract().body().`as`(RegResponse::class.java)

        Assertions.assertNotNull(registrationResponse.id)
        Assertions.assertNotNull(registrationResponse.token)
        Assertions.assertEquals(registrationResponse, expectedResponse)
    }

    @Test
    @Description("Проверка регистрации пользователя без пароля - 400")
    fun unsuccessfulRegistrationTest() {
        Specifications.installSpecification(
            Specifications.requestSpec(BaseUrls.REQRES_IN_BASE_URL),
            Specifications.responseSpec(400)
        )

        val expectedResponse = LogRegErrorResponse(
            error = ErrorNotifications.REGISTER_MISSING_PASSWORD
        )

        val registrationData = LogRegRequest(
            email = "sydney@fife",
            password = null
        )

        val registrationResponse = given()
            .body(registrationData)
            .`when`()
            .post("/api/register")
            .then()
            .extract().body().`as`(LogRegErrorResponse::class.java)

        Assertions.assertEquals(registrationResponse, expectedResponse)
    }

    @Test
    @Description("Проверка сортировки списка реусрсов")
    fun resourceListSortTest() {
        Specifications.installSpecification(
            Specifications.requestSpec(BaseUrls.REQRES_IN_BASE_URL),
            Specifications.responseSpec(200)
        )

        val resourceList = given()
            .`when`()
            .get("/api/unknown")
            .then()
            .extract().body().`as`(ResourceList::class.java)

        val resourceYears = resourceList.data.map { it.year }
        Assertions.assertTrue(resourceYears.asSequence().zipWithNext { a, b -> a <= b }.all { it })
    }
}
