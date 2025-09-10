# QR Craft App

<p align="center">
  A modern, feature-rich QR code scanner and generator for Android, built with Kotlin and Jetpack Compose.
</p>

---

## ✨ Features

*   **Fast QR Code Scanning**: Quickly scan QR codes using the device's camera with a real-time overlay.
*   **QR Code Generation**: Create custom QR codes for various data types.
*   **Scan & Creation History**: Keep a persistent history of all scanned and created QR codes.
*   **Modern UI**: A clean and intuitive user interface built with Material Design 3 components.

## 🏗️ Architecture

This project follows a modular, multi-module **Clean Architecture** pattern that separates concerns and promotes scalability.

*   **:app**: The main application module. It handles dependency injection setup (Koin), navigation, and ties all the modules together.
*   **:core**: Contains shared code used across multiple modules, including the Room database, design system components, UI utilities, and base classes.
*   **:feature**: A module containing the core QR-related features, separated into `data`, `domain`, and `ui` layers.

## 🛠️ Tech Stack

*   **Language**: [Kotlin](https://kotlinlang.org/)
*   **UI**: [Jetpack Compose](https://developer.android.com/jetpack/compose) for declarative UI development.
*   **Architecture**: MVVM with Clean Architecture principles.
*   **Asynchronicity**: [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for managing background threads.
*   **Dependency Injection**: [Koin](https://insert-koin.io/) for managing dependencies.
*   **Database**: [Room](https://developer.android.com/training/data-storage/room) for local data persistence.
*   **Navigation**: [Compose Navigation](https://developer.android.com/jetpack/compose/navigation) for navigating between screens.
*   **Camera**: [CameraX](https://developer.android.com/training/camerax) for camera interactions.
*   **Build System**: [Gradle](https://gradle.org/) with [Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) and [Version Catalogs](https://docs.gradle.org/current/userguide/version_catalogues.html).

## 🚀 Getting Started

### Prerequisites

*   Android Studio Narwhal (or newer)
*   JDK 17

### Build and Run

1.  Clone the repository:
    ```sh
    git clone https://github.com/burakkarabekir/qr-craft-app.git
    ```
2.  Open the project in Android Studio.
3.  Let Gradle sync and download the required dependencies.
4.  Run the `app` configuration on an Android emulator or a physical device.

## 📜 License

This project is licensed under the MIT License - see the `LICENSE` file for details.