# 📱 InvestiGato Android App

The InvestiGato Android App is a financial application designed to help users calculate investment returns, manage their profiles, and access various financial tools. The app is built using Kotlin and Java, and it leverages several modern Android libraries and frameworks.

## 📋 Prerequisites

Ensure the following tools are installed on your machine:

- [Android Studio](https://developer.android.com/studio) (latest stable version)
- Java 17 or higher
- Kotlin (built-in with Android Studio)
- Firebase account with proper configuration

## 🚀 Setting Up the Project

1. Clone the repository:

   ```bash
   git clone https://github.com/alvarofelipe12/InvestiGato.git
   cd InvestiGato
   ```

2. Open the project in **Android Studio**.

3. Create a `local.properties` file in the root folder:

    - Add the `encryption_key` field from the backend.
    - Add the `api_key` field from the backend.
  
      ```bash
      encryption_key=your_encryption_key
      api_key=your_api_key
      ```

## 📦 Building the APK

To generate an APK, follow these steps:

1. Open a terminal in the project directory.

2. Run the following command:

   ```bash
   ./gradlew assembleRelease
   ```

3. The APK will be located at:

   ```bash
   app/build/outputs/apk/release/app-release.apk
   ```

### 📱 Debug APK (for testing)

```bash
./gradlew assembleDebug
```

The debug APK will be at:

```bash
app/build/outputs/apk/debug/app-debug.apk
```

## 🔥 Features

- Investment Calculator: Allows users to calculate potential returns on their investments.
- Profile Management: Users can view and manage their profile information.
- Video Integration: Includes video tutorials and guides using ExoPlayer.
- Custom Views: Custom views for displaying investment responses and charts.
- Localization: Supports Spanish (Mexico) locale.

## 📧 Technologies Used

- Kotlin and Java: Primary programming languages.
- Gradle: Build automation tool.
- Android Jetpack: Including ViewModel, LiveData, Navigation, and more.
- Retrofit: For network requests.
- Moshi: For JSON serialization.
- ExoPlayer: For video playback.
- MPAndroidChart: For displaying charts.
- Material Components: For modern UI components.

## 🐞 Debugging and Logs

- Use `Log.d()` for logging in Android Studio's **Logcat**.
- Check network logs via **OkHttp** interceptor.

## 📚 Useful Commands

- **Clean project:**

  ```bash
  ./gradlew clean
  ```

- **Rebuild project:**

  ```bash
  ./gradlew build
  ```
