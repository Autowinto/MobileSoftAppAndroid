Skriver lige en kort guide til at bruge vores API på frontenden, fordi jeg har brugt flere timer på at finde ud af lortet og I skal ikke gå igennem samme smerte. Jeg bruger en User som eksempel, men det kan være hvad som helst.
# How to Retrofit

## Data Model
Indeholder information som mapper kolonner i databasen til properties som vi kan accesse i kode.
### Syntax
```kotlin
data class User(
  @JsonProperty("id") val id: Int,
  @JsonProperty("name") val name: String,
)
```
## API Interface
Indeholder funktioner, som er mappet til en bestemt rute på backenden.

Funktionerne er markeret som suspend, da det tillader os at kalde dem asynkront uden at frontenden staller. Vi fortæller funktionen, at api'et returnerer en Response som indeholder en List af User data klasser.
### Syntax
```kotlin
interface UserApi(
  @GET("users")
  suspend fun getUsers(): Response<List<User>>
)
```

## Definition i RetrofitClient
Api'et defineres i RetrofitClient filen, så den er tilgængelig for frontenden.

### Syntax
```kotlin
class RetrofitClient {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("127.0.0.1:8001/api")
.addConverterFactory(JacksonConverterFactory.create()).build();
    }
    val userApi: UserApi by lazy {
        retrofit.create(userApi::class.java)
    }
}
``````

### ViewModels
Håndterer state. Hver activity side bør have sin egen ViewModel

### UiState
UiState bruges internt i ViewModels for at holde styr på state. Brugbart for at have lists klar