# 🎬 Visual Guide - Play Store Clone Features

Visual walkthrough of all features in your Play Store Clone app.

## 📱 Screen Flow Diagram

```
┌─────────────────────────────────────────┐
│           PLAY STORE CLONE              │
│              (Home Screen)              │
├─────────────────────────────────────────┤
│  📍 Location: HomeScreen.kt             │
│                                         │
│  ┌────────────────────────────────────┐ │
│  │ 🔍 [Search Bar........................] │ │
│  └────────────────────────────────────┘ │
│                                         │
│  ⭐⭐⭐⭐⭐ TOP RATED SECTION           │
│  ┌────────────────────────────────────┐ │
│  │ [Instagram Icon] Instagram        ⬇│ │
│  │ Meta Platforms    ★★★★★           │ │
│  │ Photo sharing app [INSTALL]       │ │
│  └────────────────────────────────────┘ │
│  ┌────────────────────────────────────┐ │
│  │ [TikTok Icon] TikTok             ⬇│ │
│  │ TikTok Inc        ★★★★★          │ │
│  │ Short video app   [INSTALL]      │ │
│  └────────────────────────────────────┘ │
│                                         │
│  🔥 POPULAR APPS SECTION                │
│  ┌────────────────────────────────────┐ │
│  │ [WhatsApp Icon] WhatsApp          ⬇│ │
│  │ WhatsApp LLC      ★★★★☆          │ │
│  │ Messaging app     [INSTALL]      │ │
│  └────────────────────────────────────┘ │
│  ┌────────────────────────────────────┐ │
│  │ [Spotify Icon] Spotify            ⬇│ │
│  │ Spotify AB        ★★★★★          │ │
│  │ Music streaming   [INSTALL]      │ │
│  └────────────────────────────────────┘ │
│  ... (more apps in scrollable list)     │
└─────────────────────────────────────────┘
         ↓                           ↑
    TAP APP            (Search typed here
    ↓                    or tap back arrow)
```

## 📖 App Detail Screen

```
┌─────────────────────────────────────────┐
│        APP DETAIL SCREEN                │
│         (AppDetailScreen.kt)            │
├─────────────────────────────────────────┤
│ ◀ ┌───────────────────────────────────┐ │
│   │     [Cover Image Banner]          │ │
│   │     (App cover photo)             │ │
│   │                                   │ │
│   └───────────────────────────────────┘ │
│                                         │
│  ┌──────────────────────────────────┐  │
│  │ 🎨 [App Icon] Instagram          │  │
│  │ Meta Platforms, Inc.             │  │
│  │ ⭐ 4.5 (15,000,000 reviews)     │  │
│  └──────────────────────────────────┘  │
│                                         │
│  DESCRIPTION:                           │
│  Connect with friends and family and    │
│  meet new people on your social media   │
│  network. Share photos, videos, and     │
│  stay updated with friends...           │
│                                         │
│  DETAILS:                               │
│  Size: 118 MB                           │
│  Category: Social                       │
│  Version: 350.0.0                       │
│  Last Updated: 2024-04-10              │
│                                         │
│  ┌──────────────────────────────────┐  │
│  │  [INSTALL] (or [BUY - $9.99])    │  │
│  └──────────────────────────────────┘  │
│                                         │
│  REVIEWS (15,000,000):                 │
│  ┌──────────────────────────────────┐  │
│  │ ⭐⭐⭐⭐⭐ John Doe               │  │
│  │ Amazing app! Love the new        │  │
│  │ features.                        │  │
│  └──────────────────────────────────┘  │
│  ┌──────────────────────────────────┐  │
│  │ ⭐⭐⭐⭐ Jane Smith              │  │
│  │ Good app but sometimes crashes.  │  │
│  └──────────────────────────────────┘  │
└─────────────────────────────────────────┘
```

## 🔍 Search Flow

```
User Action                 System Response
────────────────────────────────────────────────
                                             
[Type "Insta"]          →   Real-time Filter
                            Results update instantly
                                             
│                                            │
│ ┌────────────────────────────────┐        │
│ │ 🔍 Insta........................  │        │
│ └────────────────────────────────┘        │
│                                            │
│ SEARCH RESULTS:                            │
│ ┌────────────────────────────────┐        │
│ │ Instagram                  ⬇ │        │
│ │ Meta Platforms ★★★★★          │        │
│ │                [INSTALL]      │        │
│ └────────────────────────────────┘        │
│                                            │
│ "Photoshop" is also found:               │
│ ┌────────────────────────────────┐        │
│ │ Photoshop                  ⬇ │        │
│ │ Adobe Inc      ★★★★★          │        │
│ │                  [BUY] - $9.99 │        │
│ └────────────────────────────────┘        │
                                             
[Clear search]          →   Back to full list
```

## 🎨 UI Component Hierarchy

```
PlaystoreCloneTheme (Material Design 3)
│
├── NavigationHost (Jetpack Navigation)
│   │
│   ├── HomeScreen
│   │   ├── SearchBar
│   │   │   └── TextField with Search Icon
│   │   │
│   │   ├── LazyColumn (scrollable list)
│   │   │   │
│   │   │   ├── Section: Top Rated
│   │   │   │   └── AppCard (multiple)
│   │   │   │       ├── App Icon (AsyncImage)
│   │   │   │       ├── App Name (Text)
│   │   │   │       ├── Developer (Text)
│   │   │   │       ├── Rating (Icon + Stars)
│   │   │   │       └── Install Button
│   │   │   │
│   │   │   └── Section: Popular Apps
│   │   │       └── AppCard (multiple)
│   │   │
│   │   └── Search Results (conditional)
│   │
│   └── AppDetailScreen
│       ├── Back Button
│       ├── Cover Image (AsyncImage)
│       ├── App Info Card
│       │   ├── App Icon
│       │   ├── App Name
│       │   ├── Developer
│       │   └── Rating
│       ├── Description (Text)
│       ├── Details Section
│       │   ├── DetailRow (Size)
│       │   ├── DetailRow (Category)
│       │   ├── DetailRow (Version)
│       │   └── DetailRow (Last Updated)
│       ├── Install/Buy Button
│       ├── Reviews Section
│       │   ├── Section Title
│       │   └── ReviewItem (multiple)
│       │       ├── User Name
│       │       ├── Rating (Stars)
│       │       └── Comment
│       │
│       └── LazyColumn (scrollable)
```

## 🏗️ Data Flow Architecture

```
                    USER ACTION
                         │
                         ▼
                    UI Screen
                   (Composable)
                         │
                         ▼
    ┌────────────────────────────────────┐
    │     ViewModel                      │
    │  (HomeViewModel /                  │
    │   AppDetailViewModel)              │
    │                                    │
    │  - _allApps: MutableStateFlow      │
    │  - _searchResults: StateFlow       │
    │  - isLoading: StateFlow            │
    │                                    │
    │  Functions:                        │
    │  - searchApps()                    │
    │  - loadApps()                      │
    │  - getAppDetail()                  │
    └────────────────────────────────────┘
                         │
                         ▼
    ┌────────────────────────────────────┐
    │     Repository                     │
    │  (AppRepository)                   │
    │                                    │
    │  - getAllApps()                    │
    │  - searchApps(query)               │
    │  - getAppById(id)                  │
    │  - getReviewsByAppId()             │
    └────────────────────────────────────┘
                         │
                         ▼
    ┌────────────────────────────────────┐
    │     Room Database                  │
    │  (AppDatabase)                     │
    │                                    │
    │  ┌──────────────────────────────┐  │
    │  │  AppDao                      │  │
    │  │  - Query apps                │  │
    │  │  - Search apps               │  │
    │  │  - Insert apps               │  │
    │  └──────────────────────────────┘  │
    │                                    │
    │  ┌──────────────────────────────┐  │
    │  │  ReviewDao                   │  │
    │  │  - Query reviews             │  │
    │  │  - Insert reviews            │  │
    │  └──────────────────────────────┘  │
    │                                    │
    │  Tables:                           │
    │  - apps (AppModel)                 │
    │  - reviews (ReviewModel)           │
    └────────────────────────────────────┘
                         │
                         ▼
    ┌────────────────────────────────────┐
    │     Local Storage                  │
    │  (SQLite via Room)                 │
    │                                    │
    │  playstore_db                      │
    │  └── Contains app & review data    │
    └────────────────────────────────────┘
```

## 📊 State Management Flow

```
User                                System
────────────────────────────────────────

[Search "Instagram"]
        │
        ▼
  viewModel.searchApps("Instagram")
        │
        ▼
  repository.searchApps(query)
        │
        ▼
  Room Query (SearchBar component)
        │
        ▼
  Flow emits matching apps
        │
        ▼
  ViewModel receives via Flow
        │
        ▼
  _searchResults.value = results
        │
        ▼
  StateFlow emits new state
        │
        ▼
  UI collects state
        │
        ▼
  Composable recomposes
        │
        ▼
  LazyColumn displays results
        │
        ▼
[Display "Instagram" in results]
```

## 🌙 Theme System

```
Material Design 3 ColorScheme
│
├── Light Theme
│   ├── Primary: #6200EE (Purple)
│   ├── Secondary: #03DAC6 (Teal)
│   ├── Background: Light
│   └── Surface: Light Gray
│
└── Dark Theme
    ├── Primary: #6200EE (Purple)
    ├── Secondary: #03DAC6 (Teal)
    ├── Background: Dark
    └── Surface: Dark Gray

Applied to:
├── AppCard backgrounds
├── Button colors
├── Text colors
├── Icons
└── Overall UI theme
```

## 📦 Dependency Injection Setup

```
@HiltAndroidApp
PlaystoreCloneApplication
        │
        ▼
   Hilt Container
        │
        ├─ DatabaseModule
        │  ├─ provideAppDatabase()
        │  │  └── AppDatabase instance
        │  │
        │  ├─ provideAppDao()
        │  │  └── AppDao instance
        │  │
        │  └─ provideReviewDao()
        │     └── ReviewDao instance
        │
        ├─ AppRepository
        │  └── @Inject dependencies
        │
        └─ ViewModels
           ├── HomeViewModel
           │  └── @Inject AppRepository
           │
           └── AppDetailViewModel
              └── @Inject AppRepository

@AndroidEntryPoint
MainActivity ────────────────┐
                             │
@HiltViewModel              │
HomeViewModel ◄─────────────┘
                             │
NavHost ──────────────────────
```

## 🔄 Navigation Routes

```
App Start
    │
    ▼
HomeScreen (Route: "home")
    │
    ├─ User taps app card
    │    │
    │    ▼
    └─→ AppDetailScreen (Route: "app_detail/{appId}")
         │
         └─ User taps back
            │
            ▼
         Back to HomeScreen
```

## 📋 Mock Data Structure

```
10 Sample Apps:
├── 1. Instagram
│   ├── Name: "Instagram"
│   ├── Developer: "Meta Platforms, Inc."
│   ├── Rating: 4.5★
│   ├── Reviews: 15,000,000
│   ├── Status: Free
│   └── Category: Social
│
├── 2. TikTok
│   ├── Name: "TikTok"
│   ├── Developer: "TikTok Inc."
│   ├── Rating: 4.6★
│   ├── Reviews: 12,000,000
│   ├── Status: Free
│   └── Category: Social
│
├── 3-10. [Similar structure for other apps]
│
└── Associated Reviews:
    ├── Review 1: Instagram - 5★ - "Amazing app!"
    ├── Review 2: Instagram - 4★ - "Good but crashes"
    └── Review 3: TikTok - 5★ - "Best short video app!"
```

## 🎯 Feature Interaction Map

```
┌──────────────────────────────────────────────┐
│         PLAY STORE CLONE                     │
└──────────────────────────────────────────────┘
          │                          │
          ▼                          ▼
    ┌──────────────┐         ┌──────────────┐
    │ BROWSE APPS  │         │ SEARCH APPS  │
    └──────────────┘         └──────────────┘
          │                          │
          ├─ View top-rated         ├─ Type query
          ├─ Scroll list            ├─ See results
          ├─ See all apps           └─ Tap result
          └─ Tap any app
                      │                    │
                      └────────┬───────────┘
                               ▼
                    ┌──────────────────────┐
                    │  APP DETAIL SCREEN   │
                    └──────────────────────┘
                               │
                      ┌────────┴────────┐
                      ▼                 ▼
                  ┌─────────┐    ┌──────────────┐
                  │ SEE INFO │    │ VIEW REVIEWS │
                  └─────────┘    └──────────────┘
                      │                 │
                      ├─ Description    ├─ User name
                      ├─ Specs          ├─ Rating
                      └─ Install/Buy    └─ Comment
```

---

**This visual guide helps you understand the complete flow and structure of the Play Store Clone!**

