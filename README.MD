# 📖 Quran Compass & Notes 🕌 

An Android application that combines essential tools for Muslims including a complete **Quran reader**, an organized **note-taking tool**, and a **Qibla compass** for finding the correct prayer direction. This app is designed with a sleek, user-friendly interface to enhance the spiritual and personal experience of the user.

## 🌟 Features

- **Quran Section** 📜  
  Access all **surahs** and **ajza** with a smooth and organized reader. Engage with the Quran through a responsive and clean design that allows easy navigation between chapters and verses.

- **Note-taking Tool** 📝  
  A dedicated space to capture your thoughts, reminders, and inspirations. Filter notes by **importance** for easy access to your most valuable insights.

- **Qibla Compass** 🧭  
  Utilize the compass feature to locate the **Qibla** direction accurately, ensuring you’re always oriented for prayer no matter where you are.

## 🛠️ Tech Stack & Architecture

This app leverages the latest technology to create a seamless, multi-platform experience:
  
- **Foreground Service**: To maintain the **Media Player Controller notification** for Quran audio playback.
- **Compose Multiplatform**: Ensures a **shared UI across platforms**, making the app more adaptable and maintainable.
- **Voyager**: Handles **intuitive navigation**.
- **Koin DI**: Simplifies **dependency injection** for modular and testable code.
- **MVI Architecture Pattern**: Offers a **unidirectional data flow** and state management.
- **Clean Architecture**: Organizes code into distinct layers, ensuring a clear separation of concerns.
- **ViewModel & Coroutine**: Enhances UI logic handling and asynchronous operations.
- **SQLDelight**: Provides **robust data persistence** and management.
- **Moko**: Manages shared resources, such as strings, colors, and themes across platforms.

## 🎨 Screenshots

| Home Screen  | Quran Section | Notes Filter |
|--------------|---------------|--------------|
| ![Home Screen](pics/banner.png) | ![Quran Section](link_to_quran_section_image) | ![Notes Filter](link_to_notes_filter_image) |

| Qibla Compass | Media Player | Settings |
|---------------|--------------|----------|
| ![Qibla Compass]() | ![Media Player](link_to_media_player_image) | ![Settings](link_to_settings_image) |

## 🚀 Getting Started

To start exploring and customizing this project:

1. **Clone the repository**  
   ```bash
   git clone https://github.com/yourusername/quran-compass-notes.git
   cd quran-compass-notes
