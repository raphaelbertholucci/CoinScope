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
- **Unit Testing & CI/CD** – Ensuring code quality with **JUnit, MockK, and GitHub Actions**.

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


📜 License

This project is licensed under the Apache-2.0 license – see the LICENSE file for details.



