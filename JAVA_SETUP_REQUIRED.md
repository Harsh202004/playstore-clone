# ⚠️ IMPORTANT: JAVA SETUP REQUIRED

## Issue Detected
The application cannot be built automatically because **Java is not properly installed or configured** on your system.

---

## ✅ Solution: Install Java and Set Up Environment

### Step 1: Download and Install Java

#### Option A: Oracle JDK 21 (Recommended)
1. Go to: https://www.oracle.com/java/technologies/downloads/
2. Download **JDK 21** for Windows
3. Run the installer
4. Note the installation path (typically: `C:\Program Files\Java\jdk-21.x.x`)

#### Option B: OpenJDK (Free Alternative)
1. Go to: https://adoptopenjdk.net/
2. Download **OpenJDK 21 LTS with Hotspot**
3. Run the installer
4. Note the installation path

### Step 2: Set JAVA_HOME Environment Variable

#### Method 1: Command Line (One-time, current session only)
Open PowerShell and run:
```powershell
$env:JAVA_HOME = "C:\Program Files\Java\jdk-21"
```

#### Method 2: Permanent (Recommended)
1. Right-click on **This PC** → Properties
2. Click **Advanced system settings**
3. Click **Environment Variables**
4. Under "System variables", click **New**
5. Variable name: `JAVA_HOME`
6. Variable value: `C:\Program Files\Java\jdk-21` (or your actual path)
7. Click **OK** → **OK** → **OK**
8. **Restart PowerShell/Command Prompt**

### Step 3: Verify Installation

Open a new PowerShell window and run:
```powershell
java -version
```

You should see output like:
```
java version "21.0.x" 2023-xx-xx LTS
Java(TM) SE Runtime Environment (build 21.0.x+x-LTS-xxx)
Java HotSpot(TM) 64-Bit Server VM (build 21.0.x+x-LTS-xxx, mixed mode, sharing)
```

---

## 🚀 Once Java is Installed and Configured

### Option 1: Build and Run from Command Line

1. Open PowerShell
2. Navigate to the project:
   ```powershell
   cd "C:\Users\Harsha R Hutagi\OneDrive\Desktop\playstore clone"
   ```

3. Build the debug APK:
   ```powershell
   .\gradlew assembleDebug
   ```

4. The APK will be created at:
   ```
   app\build\outputs\apk\debug\app-debug.apk
   ```

### Option 2: Build and Run from Android Studio (Easiest!)

1. **Open the project in Android Studio**
   - File → Open
   - Select: `C:\Users\Harsha R Hutagi\OneDrive\Desktop\playstore clone`

2. **Create an Android Emulator** (if not already done)
   - Tools → AVD Manager
   - Click "Create Virtual Device"
   - Select "Pixel 5"
   - Select Android API 13 or higher
   - Click "Finish"

3. **Run the app**
   - Run → Run 'app'
   - Select your emulator
   - Click OK

Android Studio will automatically handle:
- ✅ Building the project
- ✅ Generating the APK
- ✅ Installing on the emulator
- ✅ Launching the app

**This is the recommended approach!**

### Option 3: Install APK Manually

If you already have a debug APK:

1. Open PowerShell
2. Navigate to the APK location:
   ```powershell
   cd "C:\Users\Harsha R Hutagi\OneDrive\Desktop\playstore clone\app\build\outputs\apk\debug"
   ```

3. Connect your device or start an emulator

4. Install the APK:
   ```powershell
   adb install app-debug.apk
   ```

5. Launch the app:
   ```powershell
   adb shell am start -n com.example.playstoreclone/.MainActivity
   ```

---

## 📝 Environment Variable Verification

To verify your Java setup, run:

```powershell
# Check Java version
java -version

# Check JAVA_HOME
echo $env:JAVA_HOME

# Check if Java is in PATH
where java
```

All three commands should return values without errors.

---

## ❓ Troubleshooting

### "JAVA_HOME is set to an invalid directory"
- The path in JAVA_HOME doesn't exist
- **Solution**: Verify the actual Java installation path and update JAVA_HOME

### "'java' is not recognized"
- Java is not in your PATH
- **Solution**: Install Java and set JAVA_HOME, then restart

### Gradle still won't build
- Sometimes Gradle cache is corrupted
- **Solution**: Run `.\gradlew clean` first, then `.\gradlew assembleDebug`

---

## ✅ RECOMMENDED: Use Android Studio

The easiest way to run the app is:

1. **Install Android Studio** (if not done)
   - Download from: https://developer.android.com/studio

2. **Open the project**
   - File → Open → Select the project folder

3. **Click Run**
   - Run → Run 'app'
   - Select emulator or device

**Android Studio handles all Java configuration automatically!**

---

## 🎯 Next Steps

1. **Install Java** (if not already installed)
2. **Set JAVA_HOME** environment variable
3. **Restart your terminal/IDE**
4. **Try running again**

Or simply:
1. **Open Android Studio**
2. **Open the project**
3. **Click Run**

---

## 📞 Need More Help?

Check the documentation:
- **SETUP.md** - Detailed setup guide
- **QUICKSTART.md** - Quick start guide
- **README.md** - Project overview

---

**Once Java is installed and configured, the app will build and run successfully! 🚀**

