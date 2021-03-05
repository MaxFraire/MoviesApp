## Setup
MoviesApp consumes TMDB REST API. You'll need to supply an API Key [TMDB](https://developers.themoviedb.org/3/getting-started/introduction)

You can set the key in your `~/.gradle/gradle.properties`:

`MOVIES_APP_TMDB_API_KEY=<insert>`

## Libraries used

 * Entirely written in [Kotlin](https://kotlinlang.org/)
 * Uses [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html)
 * Uses many of the [Architecture Components](https://developer.android.com/topic/libraries/architecture/): Room, Paging3, LiveData, ViewModel, Navigation, Data Binding
 * Uses [Dagger2](https://dagger.dev/) for dependency injection
 * [Glide](https://bumptech.github.io/glide/) for image loading
 * [Timber](https://github.com/JakeWharton/timber) logging Library
 * [Retrofit 2](https://github.com/square/retrofit)  Type-safe HTTP client for Android
 * [Gson](https://github.com/google/gson) serialization/deserialization


| Discover | Movie Details |  Favorites | Search |
|:-:|:-:|:-:|:-:|
| ![discover](https://user-images.githubusercontent.com/8796215/110074498-ed754380-7d46-11eb-8b94-c742e34e9ddb.png) | ![movie details](https://user-images.githubusercontent.com/8796215/110075394-5e692b00-7d48-11eb-9d8f-8d0286f3ef6c.png)| ![fav](https://user-images.githubusercontent.com/8796215/110076113-8f962b00-7d49-11eb-9d3a-52abb4621c63.png) | ![search](https://user-images.githubusercontent.com/8796215/110075774-f49d5100-7d48-11eb-97f8-0f931aa77b5c.png) |
