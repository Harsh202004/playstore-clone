# Architecture Guide

## Overview

This Play Store Clone follows **Clean Architecture** with **MVVM** pattern, ensuring maintainability, testability, and scalability.

## Architecture Layers

### 1. **Presentation Layer (UI)**
Located in: `ui/`

Responsible for displaying data to the user and capturing user interactions.

- **Screens** (`ui/screen/`)
  - `HomeScreen.kt` - Main app listing and search
  - `AppDetailScreen.kt` - Detailed app view with reviews

- **Components** (`ui/components/`)
  - `AppCard.kt` - Reusable app card widget
  - `SearchBar.kt` - Search input component

- **ViewModels** (`ui/viewmodel/`)
  - `HomeViewModel.kt` - Home screen state management
  - `AppDetailViewModel.kt` - App detail screen state management

- **Navigation** (`ui/navigation/`)
  - `Navigation.kt` - Navigation graph setup

- **Theme** (`ui/theme/`)
  - `Theme.kt` - Material Design 3 theme configuration

### 2. **Domain Layer**
The domain layer will contain:
- Use cases (business logic)
- Domain models
- Repository interfaces

*Note: Currently simplified with repository directly in data layer*

### 3. **Data Layer**
Located in: `data/`

Responsible for data fetching, caching, and persistence.

- **Models** (`data/model/`)
  - `AppModel.kt` - App entity
  - `ReviewModel.kt` - Review entity

- **Database** (`data/database/`)
  - `AppDatabase.kt` - Room database instance
  - `AppDao.kt` - Data access object for apps
  - `ReviewDao.kt` - Data access object for reviews

- **Repository** (`data/repository/`)
  - `AppRepository.kt` - Centralized data access point

- **Mock Data** (`data/mock/`)
  - `MockData.kt` - Sample data for development

## Data Flow

```
UI (Composables)
    ↓ (User Interaction)
ViewModel (State Management)
    ↓ (Requests)
Repository (Data Operations)
    ↓
Database (Local Storage)
    ↓
Room DAO
    ↓ (Emits Flow)
ViewModel (Updates State)
    ↓ (StateFlow)
UI (Recomposes)
```

## State Management

### Using StateFlow

```kotlin
// ViewModel
private val _appList = MutableStateFlow<List<AppModel>>(emptyList())
val appList: StateFlow<List<AppModel>> = _appList.asStateFlow()

// UI
val apps by viewModel.appList.collectAsStateWithLifecycle()
```

### ViewModel Scope

All coroutines use `viewModelScope` for automatic cancellation:

```kotlin
viewModelScope.launch {
    repository.loadData().collect { data ->
        _state.value = data
    }
}
```

## Dependency Injection with Hilt

### Application Setup
```kotlin
@HiltAndroidApp
class PlaystoreCloneApplication : Application()
```

### Injecting Dependencies
```kotlin
@AndroidEntryPoint
class MainActivity : AppCompatActivity()

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel()
```

### Module Configuration
```kotlin
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase = 
        AppDatabase.getDatabase(context)
}
```

## Database Design

### Room Configuration

**AppDatabase**
- Entities: AppModel, ReviewModel
- Version: 1
- DAOs: AppDao, ReviewDao

### Queries

**AppDao**
```kotlin
@Query("SELECT * FROM apps")
fun getAllApps(): Flow<List<AppModel>>

@Query("SELECT * FROM apps WHERE category = :category")
fun getAppsByCategory(category: String): Flow<List<AppModel>>

@Query("SELECT * FROM apps WHERE name LIKE '%' || :query || '%'")
fun searchApps(query: String): Flow<List<AppModel>>
```

## Navigation Architecture

Using Jetpack Navigation Compose with sealed classes for type safety:

```kotlin
sealed class Route(val route: String) {
    object Home : Route("home")
    object AppDetail : Route("app_detail/{appId}") {
        fun createRoute(appId: String) = "app_detail/$appId"
    }
}
```

Benefits:
- Type-safe navigation
- Clear navigation structure
- Easy to test
- Prevents incorrect route passing

## Component Reusability

### AppCard Component
Used in both:
- Home screen (list of apps)
- Search results

Features:
- Accepts app data
- Handles click events
- Displays ratings and reviews
- Shows install/buy button

## Error Handling

Current approach:
- Try-catch in repositories
- Loading state in ViewModels
- UI displays loading indicators

Future improvements:
- Custom error models
- Error callbacks in ViewModel
- User-friendly error messages

## Performance Considerations

1. **Image Loading**
   - Coil handles caching automatically
   - AsyncImage only renders when needed

2. **Database Queries**
   - Returned as Flow for reactive updates
   - Indexed queries for fast searches

3. **List Rendering**
   - LazyColumn for efficient scrolling
   - Only visible items are composed

4. **Memory Management**
   - ViewModel scope handles cleanup
   - Room database uses connection pooling

## Testing Architecture

### Unit Tests
- Test ViewModels independently
- Mock Repository
- Test business logic

### Integration Tests
- Test Database operations
- Test Repository with Room

### UI Tests
- Compose UI testing framework
- Test user interactions
- Verify navigation

## Extensibility

### Adding a New Feature

1. **Create Data Model**
   ```kotlin
   @Entity
   data class MyModel(...)
   ```

2. **Create DAO**
   ```kotlin
   @Dao
   interface MyDao { ... }
   ```

3. **Extend Repository**
   ```kotlin
   suspend fun getMyData() { ... }
   ```

4. **Create ViewModel**
   ```kotlin
   @HiltViewModel
   class MyViewModel @Inject constructor(...) { ... }
   ```

5. **Build UI Screen**
   ```kotlin
   @Composable
   fun MyScreen(...) { ... }
   ```

6. **Add Route**
   ```kotlin
   sealed class Route {
       object MyScreen : Route("my_screen")
   }
   ```

## Best Practices

1. **Single Responsibility** - Each class has one reason to change
2. **Dependency Injection** - All dependencies injected via Hilt
3. **Immutability** - Use data classes and immutable collections
4. **Reactive** - Use Flow/StateFlow for data updates
5. **Type Safety** - Leverage Kotlin's type system
6. **Testing** - Design for testability

## Future Architecture Improvements

- [ ] Add Domain layer with UseCases
- [ ] Implement Result wrapper for error handling
- [ ] Add DataSource abstraction
- [ ] Implement Paging for large lists
- [ ] Add Remote data source with Retrofit
- [ ] Implement proper error handling
- [ ] Add analytics layer

