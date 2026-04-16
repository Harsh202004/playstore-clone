# Play Store Clone - Android Application

A modern Android application that replicates the Google Play Store functionality with a beautiful Material Design 3 UI.

## 🎯 Features

- **App Listing** - Browse a collection of apps with ratings and reviews
- **Search** - Search apps by name or developer
- **App Details** - View comprehensive app information including descriptions, ratings, and user reviews
- **Top Rated** - Quick access to the most popular and highest-rated apps
- **Responsive Design** - Works seamlessly on all Android devices
- **Offline Support** - Local database caching with Room
- **Modern Architecture** - MVVM with Jetpack Compose

## 🏗️ Architecture

The project follows **Clean Architecture** principles with clear separation of concerns:

```
com/example/playstoreclone/
├── data/
│   ├── database/          # Room Database (AppDao, ReviewDao, AppDatabase)
│   ├── model/             # Data models (AppModel, ReviewModel)
│   ├── repository/        # Repository pattern for data management
│   └── mock/              # Mock data for development
├── di/                    # Dependency Injection (Hilt modules)
├── ui/
│   ├── screen/            # Composable screens (HomeScreen, AppDetailScreen)
│   ├── components/        # Reusable UI components (AppCard, SearchBar)
│   ├── viewmodel/         # ViewModels (HomeViewModel, AppDetailViewModel)
│   ├── navigation/        # Navigation graph
│   └── theme/             # Material Design 3 theme
└── PlaystoreCloneApplication.kt  # Hilt Application
```

## 📦 Technology Stack

### UI & Presentation
- **Jetpack Compose** - Modern declarative UI framework
- **Material Design 3** - Material Design components
- **Navigation Compose** - Type-safe navigation

### Architecture & State Management
- **MVVM** - Model-View-ViewModel pattern
- **StateFlow** - Reactive state management
- **ViewModel** - Lifecycle-aware view models

### Data & Storage
- **Room Database** - Local data persistence
- **Retrofit** - HTTP client for API calls
- **OkHttp** - HTTP interceptor and logging
- **Kotlin Serialization** - JSON serialization

### Dependency Injection
- **Hilt** - Dependency injection framework

### Image Loading
- **Coil** - Lightweight image loading library

## 🚀 Getting Started

### Prerequisites
- Android Studio Flamingo or later
- Java 11 or higher
- Android SDK 24+ (minimum API level)

### Installation

1. **Clone the repository**
```bash
git clone <repository-url>
cd playstore-clone
```

2. **Set up Java Home** (if needed)
```bash
# Windows
set JAVA_HOME=C:\Program Files\Java\jdk-21

# macOS/Linux
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home
```

3. **Build the project**
```bash
./gradlew build
```

4. **Run the app**
```bash
./gradlew installDebug
# Then run on a connected device or emulator
```

## 📱 Project Structure

### Models

**AppModel** - Represents a playable app
- id, name, developer, description
- icons and cover images
- ratings, reviews, and download count
- pricing information
- version and update info

**ReviewModel** - User reviews
- App reference
- User rating and comment
- Timestamp

### Database

**AppDao** - Data Access Object for apps
- Insert/Delete operations
- Query all apps, by ID, by category
- Search functionality
- Top-rated apps

**ReviewDao** - Data Access Object for reviews
- CRUD operations
- Query reviews by app ID

### ViewModels

**HomeViewModel**
- Manages home screen state
- Loads all apps and top-rated apps
- Handles search functionality
- Tracks loading state

**AppDetailViewModel**
- Manages app detail screen
- Loads specific app details
- Fetches associated reviews
- Handles adding new reviews

## 🎨 UI Screens

### Home Screen
- Search bar at the top
- "Top Rated" section featuring popular apps
- "Popular Apps" section with full app list
- Search results view
- App cards with install/buy buttons

### App Detail Screen
- Cover image with back navigation
- App icon, name, developer, and rating
- Full description
- Technical details (size, category, version, last update)
- Install/Buy button with pricing
- User reviews section

## 📝 Mock Data

The app includes 10 sample apps with realistic data:
- Instagram, TikTok, WhatsApp, Spotify, Netflix
- Gmail, Photoshop, Telegram, Google Maps, Clash Royale

Each app includes:
- Complete information (name, developer, description)
- Icons and cover images (placeholder URLs)
- Ratings and review counts
- Installation counts
- Pricing information

## 🔧 Configuration

### Colors & Theme
- Primary Color: `#6200EE` (Purple)
- Secondary Color: `#03DAC6` (Teal)
- Supports light and dark modes
- Dynamic color on Android 12+

### Dependencies

All dependencies are managed in `gradle/libs.versions.toml`:

```toml
# Jetpack Compose
androidx-compose-bom = "2024.03.00"

# Hilt
hilt = "2.48"

# Retrofit & OkHttp
retrofit = "2.10.0"
okhttp = "4.11.0"

# Room Database
room = "2.6.1"

# Image Loading
coil = "2.5.0"
```

## 🧪 Testing

The project includes testing infrastructure for:
- Unit tests with JUnit
- UI tests with Espresso/Compose
- Integration tests with Room

Run tests with:
```bash
./gradlew test
./gradlew connectedAndroidTest
```

## 🔐 Permissions

The app requires:
- `android.permission.INTERNET` - For fetching app data

## 🎓 Learning Outcomes

This project demonstrates:
- Modern Android development with Jetpack Compose
- Clean Architecture principles
- MVVM pattern implementation
- Room Database for local storage
- Hilt dependency injection
- Navigation Compose
- Flow and StateFlow for reactive programming
- Material Design 3

## 🚀 Future Enhancements

- [ ] Backend API integration (REST/Firebase)
- [ ] User authentication and profiles
- [ ] Wishlist functionality
- [ ] Download management and progress tracking
- [ ] Push notifications
- [ ] App categories and filtering
- [ ] User review submission
- [ ] Download history
- [ ] App update notifications
- [ ] Analytics and crash reporting

## 📄 License

This project is for educational purposes.

## 🤝 Contributing

Feel free to fork and submit pull requests!

## 📧 Support

For issues and questions, please open an issue in the repository.

---

**Built with ❤️ using Jetpack Compose and Modern Android Architecture**

