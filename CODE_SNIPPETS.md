# Code Snippets & Examples

Quick reference for common patterns used in the Play Store Clone.

## 🎯 ViewModel Pattern

### Creating a ViewModel with Hilt

```kotlin
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel() {

    private val _allApps = MutableStateFlow<List<AppModel>>(emptyList())
    val allApps: StateFlow<List<AppModel>> = _allApps.asStateFlow()

    init {
        loadApps()
    }

    private fun loadApps() {
        viewModelScope.launch {
            try {
                repository.getAllApps().collect { apps ->
                    _allApps.value = apps
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
```

## 🗄️ Room Database

### Define Entity

```kotlin
@Entity(tableName = "apps")
data class AppModel(
    @PrimaryKey
    val id: String,
    val name: String,
    val rating: Float,
    // ... other fields
)
```

### Create DAO

```kotlin
@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApp(app: AppModel)

    @Query("SELECT * FROM apps")
    fun getAllApps(): Flow<List<AppModel>>

    @Query("SELECT * FROM apps WHERE name LIKE '%' || :query || '%'")
    fun searchApps(query: String): Flow<List<AppModel>>
}
```

### Setup Database

```kotlin
@Database(
    entities = [AppModel::class, ReviewModel::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
    
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "playstore_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
```

## 💉 Dependency Injection

### Create Hilt Module

```kotlin
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideAppDao(database: AppDatabase) = database.appDao()
}
```

### Use in Activity

```kotlin
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlaystoreCloneTheme {
                NavigationHost()
            }
        }
    }
}
```

### Application Class

```kotlin
@HiltAndroidApp
class PlaystoreCloneApplication : Application()
```

## 🧭 Navigation

### Define Routes

```kotlin
sealed class Route(val route: String) {
    object Home : Route("home")
    object AppDetail : Route("app_detail/{appId}") {
        fun createRoute(appId: String) = "app_detail/$appId"
    }
}
```

### Setup Navigation

```kotlin
@Composable
fun NavigationHost(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Route.Home.route
    ) {
        composable(Route.Home.route) {
            HomeScreen(
                onAppClick = { appId ->
                    navController.navigate(Route.AppDetail.createRoute(appId))
                }
            )
        }

        composable(Route.AppDetail.route) { backStackEntry ->
            val appId = backStackEntry.arguments?.getString("appId") ?: return@composable
            AppDetailScreen(
                appId = appId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
```

## 🎨 Jetpack Compose

### Basic Screen Structure

```kotlin
@Composable
fun MyScreen(
    viewModel: MyViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        when {
            state.isLoading -> CircularProgressIndicator()
            state.error != null -> ErrorMessage(state.error)
            else -> Content(state.data)
        }
    }
}
```

### LazyColumn with List

```kotlin
@Composable
fun AppList(apps: List<AppModel>, onClick: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(apps) { app ->
            AppCard(
                app = app,
                onClick = { onClick(app.id) }
            )
        }
    }
}
```

### Custom Composable Component

```kotlin
@Composable
fun MyComponent(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface)
            .clickable(onClick = onClick)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
```

## 📱 State Management

### Using StateFlow

```kotlin
// In ViewModel
private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
val uiState: StateFlow<UiState> = _uiState.asStateFlow()

// Update state
_uiState.value = UiState.Success(data)

// In Composable
val state by viewModel.uiState.collectAsStateWithLifecycle()
```

### Using Live Coroutines

```kotlin
viewModelScope.launch {
    try {
        val data = repository.getData()
        _uiState.value = UiState.Success(data)
    } catch (e: Exception) {
        _uiState.value = UiState.Error(e.message ?: "Unknown error")
    }
}
```

## 🔍 Search Implementation

### ViewModel Search

```kotlin
fun searchApps(query: String) {
    if (query.isEmpty()) {
        _searchResults.value = emptyList()
        return
    }
    
    viewModelScope.launch {
        repository.searchApps(query).collect { results ->
            _searchResults.value = results
        }
    }
}
```

### SearchBar Component

```kotlin
@Composable
fun SearchBar(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onSearch: (String) -> Unit = {}
) {
    TextField(
        value = searchQuery,
        onValueChange = {
            onSearchQueryChanged(it)
            onSearch(it)
        },
        placeholder = { Text("Search apps") },
        leadingIcon = { Icon(Icons.Filled.Search, "Search") }
    )
}
```

## 🖼️ Image Loading with Coil

### Display Image

```kotlin
AsyncImage(
    model = app.iconUrl,
    contentDescription = app.name,
    modifier = Modifier
        .size(80.dp)
        .clip(RoundedCornerShape(12.dp)),
    contentScale = ContentScale.Crop,
    loading = {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    },
    error = {
        Icon(Icons.Default.BrokenImage, "Error")
    }
)
```

## 📡 Retrofit (Future API Integration)

### Service Definition

```kotlin
interface AppService {
    @GET("/apps")
    suspend fun getAllApps(): List<AppModel>
    
    @GET("/apps/{id}")
    suspend fun getAppById(@Path("id") id: String): AppModel
    
    @GET("/apps/search")
    suspend fun searchApps(@Query("q") query: String): List<AppModel>
}
```

### Create Retrofit Instance

```kotlin
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.example.com/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Singleton
    @Provides
    fun provideAppService(retrofit: Retrofit): AppService {
        return retrofit.create(AppService::class.java)
    }
}
```

## 🎨 Material Design 3

### Theme Setup

```kotlin
@Composable
fun PlaystoreCloneTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        content = content
    )
}
```

### Using Theme Colors

```kotlin
Box(
    modifier = Modifier
        .background(MaterialTheme.colorScheme.surface)
        .padding(16.dp)
) {
    Text(
        text = "Hello",
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.bodyLarge
    )
}
```

## ✅ Error Handling

### Try-Catch in Repository

```kotlin
suspend fun loadApps() {
    try {
        val apps = appDao.getAllApps().first()
        _apps.value = apps
    } catch (e: Exception) {
        _error.value = e.message ?: "Unknown error"
    }
}
```

### Error State in ViewModel

```kotlin
sealed class UiState {
    object Loading : UiState()
    data class Success(val data: List<AppModel>) : UiState()
    data class Error(val message: String) : UiState()
}
```

## 🧪 Testing Patterns

### ViewModel Test

```kotlin
@Test
fun testSearchApps() {
    val viewModel = HomeViewModel(mockRepository)
    
    // Act
    viewModel.searchApps("Instagram")
    
    // Assert
    assertEquals(1, viewModel.searchResults.value.size)
}
```

### Compose UI Test

```kotlin
@Test
fun testAppCardDisplay() {
    composeTestRule.setContent {
        AppCard(mockApp, onClick = {})
    }
    
    composeTestRule.onNodeWithText("Instagram").assertIsDisplayed()
}
```

### Repository Test

```kotlin
@Test
fun testGetAllApps() = runTest {
    // Arrange
    val testApps = listOf(mockApp1, mockApp2)
    coEvery { appDao.getAllApps() } returns flowOf(testApps)
    
    // Act
    val result = repository.getAllApps().first()
    
    // Assert
    assertEquals(testApps, result)
}
```

## 🔧 Gradle Configuration

### Add Dependency in libs.versions.toml

```toml
[versions]
myLibVersion = "1.0.0"

[libraries]
my-lib = { group = "com.example", name = "my-lib", version.ref = "myLibVersion" }
```

### Use in build.gradle.kts

```kotlin
dependencies {
    implementation(libs.my.lib)
}
```

## 🚀 Common Tasks

### Add New Screen

1. Create `NewScreen.kt` in `ui/screen/`
2. Add route in `Navigation.kt`
3. Create `NewViewModel.kt` if needed
4. Add composable to NavHost

### Add New Data Model

1. Create entity in `data/model/`
2. Create DAO in `data/database/`
3. Update AppDatabase
4. Update AppRepository

### Change App Colors

1. Edit `ui/theme/Theme.kt`
2. Modify `DarkColorScheme` or `LightColorScheme`
3. Recompose (Ctrl+Shift+R)

---

**These snippets follow the patterns used throughout the Play Store Clone project.**

For more details, check:
- ARCHITECTURE.md for architectural patterns
- FEATURES.md for feature implementations
- Individual source files for complete context

