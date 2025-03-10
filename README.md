# CoinScope 🪙📊
A powerful cryptocurrency price tracking app built with **Jetpack Compose**, **Kotlin**, and **MVVM Clean Architecture**.

## 📌 Features
- **Real-time Cryptocurrency Prices** – Fetch live market prices using the [CoinGecko API](https://www.coingecko.com/en/api).
- **Search Functionality** – Quickly find coins with a powerful search feature.
- **Favorites & Portfolio Tracking** – Save your favorite coins and track their price changes.
- **Beautiful UI with Jetpack Compose** – Modern, declarative UI designed for performance.
- **Multi-Module Architecture** – Separates concerns for scalability and maintainability.
- **Paging 3 Support** – Efficient, smooth scrolling for large datasets.
- **Dark Mode Support** – Automatically adjusts between **light** and **dark themes**.
- **Unit Testing** – Ensuring code quality with **JUnit and MockK**.
- **CI Integration** – Automated builds, testing and code quality checks with GitHub Actions.
- **Code Quality Enforcement** – Integrated **Detekt** for static analysis and **KtLint** for Kotlin style enforcement.
- **Splash Screen** – Using the modern **Splash Screen API** provided by Android.

## 🏗️ Project Structure

CoinScope follows MVVM with Clean Architecture and modular structure to ensure scalability and maintainability.

    📂 app/              # UI Screens and ViewModels (Jetpack Compose)
    📂 data/             # API calls and repositories
    📂 domain/           # Use cases and domain models
    📂 design/           # UI Widgets that can be reused in the project
    📂 core/             # Common features, classes, extensions, etc.

### Why Modular Architecture?

- Scalability – Features can be developed separately.
- Code Reusability – Separates logic into data, domain, and presentation layers.
- Easier Testing – Clear boundaries make it easy to unit test individual components.

## 🔧 Tech Stack & Libraries

| Feature                   | Library                           |
|---------------------------|-----------------------------------|
| UI Framework              | Jetpack Compose                   |
| Navigation                | Jetpack Navigation for Compose    |
| API Requests              | Retrofit + OkHttp                 |
| State Management          | Kotlin Flow + StateFlow           |
| Dependency Injection      | Koin                              |
| Image Loading             | Coil                              |
| Paging                    | Paging 3                          |
| Asynchronous Programming  | Kotlin Coroutines                 |
| Testing                   | JUnit, MockK, Coroutine Test      |
| Linting & Code Quality    | Detekt, KtLint                    |
| Splash Screen             | AndroidX Core Splashscreen        |

## 🧪 Testing & CI
CoinScope follows a **test-driven approach** using **JUnit + MockK** for unit tests, with automated integration via GitHub Actions:

✅ **Repository Tests**  
✅ **Extensions Tests**  
✅ **CI Automation** (GitHub Actions)

Run tests locally:
```sh
./gradlew test
```

## 📸 Screenshots

### Home (Light & Dark)

<div style="display:flex; justify-content:center; gap:20px;">
  <img src="https://raw.githubusercontent.com/raphaelbertholucci/CoinScope/main/pictures/home_light.webp" alt="Home Screen Light" width="250"/>
  <img src="https://raw.githubusercontent.com/raphaelbertholucci/CoinScope/main/pictures/home_dark.webp" alt="Home Screen Dark" width="250"/>
</div>

### Coin Details (Light & Dark)

<div style="display: flex; justify-content: center; gap: 10px;">
  <img src="https://raw.githubusercontent.com/raphaelbertholucci/CoinScope/main/pictures/details_light.webp" alt="Coin Details Light" width="250"/>
  <img src="https://raw.githubusercontent.com/raphaelbertholucci/CoinScope/main/pictures/details_dark.webp" alt="Coin Details Dark" width="250"/>
</div>

<br/>

📜 License

This project is licensed under the Apache-2.0 license – see the LICENSE file for details.



