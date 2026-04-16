# Setup and Build Guide

## Prerequisites

- **Android Studio** - Arctic Fox or later (Flamingo recommended)
- **Java Development Kit (JDK)** - Version 11 or higher
- **Android SDK** - API level 24 or higher
- **Git** - For version control

## Environment Setup

### Windows

#### 1. Install Java

1. Download JDK 11+ from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://adoptopenjdk.net/)
2. Install JDK
3. Set JAVA_HOME environment variable:
   ```powershell
   # Right-click This PC → Properties → Advanced system settings
   # Click Environment Variables
   # New System Variable:
   # Variable name: JAVA_HOME
   # Variable value: C:\Program Files\Java\jdk-21
   ```
4. Verify installation:
   ```powershell
   java -version
   javac -version
   ```

#### 2. Install Android Studio

1. Download from [developer.android.com](https://developer.android.com/studio)
2. Run installer and follow setup wizard
3. Install Android SDK:
   - File → Settings → Appearance & Behavior → System Settings → Android SDK
   - Install SDK Platform 24+ and Build Tools 36

#### 3. Set ANDROID_HOME

```powershell
# Add environment variable:
# Variable name: ANDROID_HOME
# Variable value: C:\Users\YourUsername\AppData\Local\Android\Sdk
```

#### 4. Add to PATH

Add to System PATH:
```
C:\Users\YourUsername\AppData\Local\Android\Sdk\tools
C:\Users\YourUsername\AppData\Local\Android\Sdk\platform-tools
```

### macOS

```bash
# Install Java (if using Homebrew)
brew install openjdk@21
export JAVA_HOME=$(/usr/libexec/java_home)

# Install Android Studio
brew install android-studio

# Set ANDROID_HOME
export ANDROID_HOME=$HOME/Library/Android/sdk
export PATH=$PATH:$ANDROID_HOME/tools
export PATH=$PATH:$ANDROID_HOME/platform-tools
```

### Linux (Ubuntu)

```bash
# Install Java
sudo apt-get update
sudo apt-get install openjdk-21-jdk

# Download Android Studio
# https://developer.android.com/studio

# Set ANDROID_HOME
export ANDROID_HOME=$HOME/Android/Sdk
export PATH=$PATH:$ANDROID_HOME/tools
export PATH=$PATH:$ANDROID_HOME/platform-tools
```

## Project Setup

### 1. Clone Repository

```bash
git clone https://github.com/your-repo/playstore-clone.git
cd playstore-clone
```

### 2. Open in Android Studio

1. File → Open
2. Select the `playstore-clone` directory
3. Wait for Gradle sync to complete

### 3. Build the Project

#### Command Line

```bash
# Clean build
./gradlew clean build

# Build without running tests
./gradlew build -x test

# Build and run on connected device
./gradlew installDebug
```

#### Android Studio

1. Build → Make Project (Ctrl+F9)
2. Or Build → Build Variant selector to choose debug/release

### 4. Run the App

#### Android Studio

1. Run → Run 'app'
2. Select emulator or connected device
3. Click OK

#### Command Line

```bash
# List connected devices
adb devices

# Install and run
./gradlew installDebug
adb shell am start -n com.example.playstoreclone/.MainActivity

# Or with automatic activity launch
./gradlew run
```

## Gradle Configuration

### Gradle Properties

The project uses Gradle Wrapper for version management. Important files:

- `gradle/wrapper/gradle-wrapper.properties` - Gradle version
- `gradle/libs.versions.toml` - All dependency versions
- `app/build.gradle.kts` - App-specific configuration

### Common Gradle Tasks

```bash
# Build variants
./gradlew assembleDebug        # Build debug APK
./gradlew assembleRelease      # Build release APK
./gradlew bundleRelease        # Build App Bundle

# Testing
./gradlew test                 # Run unit tests
./gradlew connectedAndroidTest # Run instrumented tests

# Development
./gradlew clean               # Clean build artifacts
./gradlew assemble            # Build all variants
./gradlew check               # Run all checks

# Publishing
./gradlew signingReport       # Show signing certificate info
```

## Development Workflow

### 1. Creating a New Feature

```bash
# Create feature branch
git checkout -b feature/my-feature

# Make changes
# Test locally
./gradlew build

# Commit and push
git add .
git commit -m "feat: add my feature"
git push origin feature/my-feature
```

### 2. Adding Dependencies

1. Open `gradle/libs.versions.toml`
2. Add version in `[versions]` section:
   ```toml
   myLibraryVersion = "1.0.0"
   ```
3. Add library in `[libraries]` section:
   ```toml
   my-library = { group = "com.example", name = "my-library", version.ref = "myLibraryVersion" }
   ```
4. Use in `app/build.gradle.kts`:
   ```kotlin
   implementation(libs.my.library)
   ```
5. Sync Gradle

### 3. Hot Reload During Development

- Edit Compose UI code
- Click "Recompose" button in Android Studio (Ctrl+Shift+R)
- Changes appear instantly on connected device

## Debugging

### Android Studio Debugger

1. Set breakpoint (click line number)
2. Run → Debug 'app'
3. Step through code with:
   - F8: Step over
   - F7: Step into
   - Shift+F8: Step out

### Logcat

View logs in Android Studio:
- View → Tool Windows → Logcat

Filter logs:
```bash
# Using adb
adb logcat | grep "PlaystoreClone"

# Or in Android Studio, type filter text
```

### Database Inspector

In Android Studio:
1. View → Tool Windows → App Inspection
2. Database Inspector
3. Select app and database
4. View/query data

## Building for Release

### 1. Create Keystore

```bash
# Generate keystore (one time only)
keytool -genkey -v -keystore release.keystore ^
  -keyalg RSA -keysize 2048 -validity 10000 ^
  -alias playstore_clone
```

### 2. Sign Release Build

Add to `app/build.gradle.kts`:

```kotlin
signingConfigs {
    create("release") {
        storeFile = file("../release.keystore")
        storePassword = System.getenv("KEYSTORE_PASSWORD")
        keyAlias = System.getenv("KEY_ALIAS")
        keyPassword = System.getenv("KEY_PASSWORD")
    }
}

buildTypes {
    release {
        signingConfig = signingConfigs.getByName("release")
        isMinifyEnabled = true
        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
}
```

### 3. Build Release APK

```bash
set KEYSTORE_PASSWORD=your_keystore_password
set KEY_ALIAS=playstore_clone
set KEY_PASSWORD=your_key_password

./gradlew assembleRelease
```

## Troubleshooting

### Build Failures

#### "JAVA_HOME is set to an invalid directory"

```bash
# Check Java installation
java -version

# Set JAVA_HOME correctly (Windows)
set JAVA_HOME=C:\Program Files\Java\jdk-21

# Or (Linux/macOS)
export JAVA_HOME=$(/usr/libexec/java_home)
```

#### "Gradle sync failed"

1. File → Invalidate Caches
2. Restart Android Studio
3. Try again

#### Out of Memory Error

```bash
# Increase Gradle heap size
# In gradle.properties:
org.gradle.jvmargs=-Xmx4096m
```

### Emulator Issues

```bash
# List available emulators
emulator -list-avds

# Launch emulator
emulator -avd Pixel_5_API_33

# Check for GPU support
emulator -avd Pixel_5_API_33 -gpu auto
```

### ADB Issues

```bash
# Restart ADB
adb kill-server
adb start-server

# List connected devices
adb devices

# Clear app data
adb shell pm clear com.example.playstoreclone
```

## Performance Testing

### Profiler

1. Run → Profile 'app'
2. Select profiler (CPU, Memory, Network)
3. Interact with app and analyze

### Benchmark

Built-in benchmarks can be added to:
```kotlin
@RunWith(AndroidJUnit4::class)
class AppDetailBenchmark {
    @get:Rule
    val benchmarkRule = BenchmarkRule()
    
    @Test
    fun testDetailScreenRendering() = benchmarkRule.measureRepeated {
        // Your test code
    }
}
```

## Resources

- [Android Developer Documentation](https://developer.android.com/docs)
- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose/documentation)
- [Hilt Documentation](https://dagger.dev/hilt/)
- [Room Database Guide](https://developer.android.com/training/data-storage/room)
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)

## Next Steps

1. ✅ Set up development environment
2. ✅ Build and run the project
3. Explore the codebase
4. Make changes and test
5. Submit pull requests

Happy coding! 🚀

