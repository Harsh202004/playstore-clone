# Quick Start Guide

Get the Play Store Clone up and running in minutes!

## ⚡ 5-Minute Setup

### Step 1: Install Requirements

```bash
# Check Java
java -version

# Must be Java 11 or higher
```

### Step 2: Open Project

1. Open Android Studio
2. File → Open
3. Select the `playstore clone` folder
4. Wait for Gradle sync

### Step 3: Run on Emulator

1. Tools → AVD Manager → Create Virtual Device
2. Select Pixel 5, Android 13+
3. Click ► (play button) to start emulator
4. In Android Studio: Run → Run 'app'
5. Select the running emulator
6. Click OK

**That's it! The app should launch in 30 seconds!** 🎉

## 📱 What You'll See

### Home Screen
- List of popular apps
- "Top Rated" section at top
- Search bar to find apps
- Install/Buy buttons on each app

### Search
Type in search bar to find apps by:
- App name
- Developer
- Description

### App Details
Tap any app to see:
- Full app description
- Ratings and reviews
- App size, version, category
- Install/Buy button
- User reviews

## 🔍 Explore the Code

### Key Files to Check

1. **HomeScreen** - Main listing page
   ```
   app/src/main/java/com/example/playstoreclone/ui/screen/HomeScreen.kt
   ```

2. **AppCard** - App display component
   ```
   app/src/main/java/com/example/playstoreclone/ui/components/AppCard.kt
   ```

3. **AppRepository** - Data management
   ```
   app/src/main/java/com/example/playstoreclone/data/repository/AppRepository.kt
   ```

4. **AppDatabase** - Local storage
   ```
   app/src/main/java/com/example/playstoreclone/data/database/AppDatabase.kt
   ```

### Project Structure
```
app/src/main/java/com/example/playstoreclone/
├── data/               # Database & data management
├── di/                 # Dependency injection setup
├── ui/                 # Screens, components, theme
│   ├── screen/        # Full screens
│   ├── components/    # Reusable widgets
│   ├── viewmodel/     # State management
│   ├── navigation/    # App navigation
│   └── theme/         # Material Design theme
└── MainActivity.kt     # App entry point
```

## 🛠️ Common Tasks

### Add a New App to the List

1. Open `data/mock/MockData.kt`
2. Add to `mockApps` list:
```kotlin
AppModel(
    id = "11",
    name = "My App",
    developer = "Developer Name",
    description = "App description",
    // ... other fields
)
```
3. Rebuild and run

### Change App Colors

1. Open `ui/theme/Theme.kt`
2. Modify `DarkColorScheme` or `LightColorScheme`:
```kotlin
primary = Color(0xFF6200EE)    // Change this
secondary = Color(0xFF03DAC6)
```
3. Rebuild

### Modify App Card Layout

1. Open `ui/components/AppCard.kt`
2. Edit the `Box` content
3. Rebuild and run (hot reload works!)

### Add Search Filtering

1. Open `ui/viewmodel/HomeViewModel.kt`
2. Add new function:
```kotlin
fun filterByRating(minRating: Float) {
    // Filter logic here
}
```

## 🧪 Test the App

### Run Unit Tests
```bash
./gradlew test
```

### Run UI Tests
```bash
./gradlew connectedAndroidTest
```

### Debug an Issue

1. Click the line number to add breakpoint
2. Run → Debug 'app'
3. Step through code with F8/F7

## 📚 Learn More

### Android Documentation
- [Jetpack Compose Docs](https://developer.android.com/jetpack/compose)
- [Android Developer Guide](https://developer.android.com/guide)
- [Hilt Dependency Injection](https://dagger.dev/hilt/)

### Kotlin Documentation
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
- [Kotlin Flow](https://kotlinlang.org/docs/flow.html)

## 🐛 Troubleshooting

### Build Error: "JAVA_HOME is invalid"

```bash
# Windows - Set Java path
set JAVA_HOME=C:\Program Files\Java\jdk-21

# macOS/Linux
export JAVA_HOME=$(/usr/libexec/java_home)
```

Then rebuild.

### Emulator Won't Start

```bash
# List available emulators
emulator -list-avds

# Launch specific emulator
emulator -avd Pixel_5_API_33
```

### App Crashes on Startup

1. Clean build: `./gradlew clean`
2. Sync Gradle: File → Sync Now
3. Rebuild

### Changes Not Showing Up

- Compose hot reload: Ctrl+Shift+R (Windows) or Cmd+Option+R (Mac)
- If that doesn't work: Rebuild (Ctrl+F9)

## 🎯 Next Steps

1. ✅ **Understand the structure** - Read ARCHITECTURE.md
2. ✅ **Explore features** - Read FEATURES.md
3. ✅ **Make modifications** - Try changing colors/text
4. ✅ **Add features** - Implement new screens
5. ✅ **Deploy** - Generate release APK

## 📋 Checklists

### First Time Setup
- [ ] Installed Java 11+
- [ ] Opened project in Android Studio
- [ ] Gradle synced successfully
- [ ] Created Android emulator
- [ ] App runs and shows home screen

### Development Workflow
- [ ] Made code change
- [ ] Used hot reload (Ctrl+Shift+R)
- [ ] Verified changes in emulator
- [ ] Tested on physical device (optional)

### Before Deployment
- [ ] All features tested
- [ ] No console errors
- [ ] App icon finalized
- [ ] Version updated in build.gradle
- [ ] App name updated in strings.xml

## 💡 Pro Tips

1. **Hot Reload** - Compose UI changes appear instantly
2. **Database Inspector** - View/query Room data in real-time
3. **Profiler** - Check performance in Run → Profile
4. **Logcat** - View app logs (View → Tool Windows → Logcat)
5. **Android Emulator** - Run faster with GPU acceleration enabled

## 🚀 Ready to Build?

You're all set! Here's what to do:

1. Open the project in Android Studio
2. Click Run (▶) button
3. Select your emulator
4. Explore the app!

Questions? Check the documentation files:
- README.md - Project overview
- ARCHITECTURE.md - Technical details
- FEATURES.md - Feature documentation
- SETUP.md - Detailed setup guide

Happy coding! 🎉

---

**Questions?** [Create an issue](https://github.com/your-repo/issues)
**Want to contribute?** [Submit a PR](https://github.com/your-repo/pulls)

