import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream

fun main() {
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .build()

    FirebaseApp.initializeApp(options)

    val messageLike = Message.builder()
        .putData("action", "LIKE")
        .putData("content", """{
          "userId": 1,
          "userName": "Ioann",
          "postId": 4,
          "postAuthor": "Netology"
        }""".trimIndent())
        .setToken(token)
        .build()

    val messageNew = Message.builder()
        .putData("action", "NEWPOST")
        .putData("content", """{
          "userName": "Curator",
          "postContent": "Привет. Поскольку вы учитесь профессиональной разработке, то и вопросы вы должны привыкать задавать профессионально. То же самое относится к сообщениям о любых проблемах, неточностях и ошибках. Шаблон сообщения о любых дефектах, вопросах и проблемах - весь ваш код должен быть выложен в открытом репозитории на GitHub."
          }""".trimIndent())
        .setToken(token)
        .build()

    FirebaseMessaging.getInstance().send(messageLike)
    FirebaseMessaging.getInstance().send(messageNew)
}