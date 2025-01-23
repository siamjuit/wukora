# Wukora - Hackathon Buddy Finder 🐵

Welcome to **Wukora**! Wukora is a buddy-finding platform designed to connect hackathon enthusiasts, built with **Flutter** for a seamless, cross-platform experience and powered by **Spring Boot** for robust, scalable backend services. Inspired by constellations and the mythical character Sun Wukong, Wukora helps users discover teammates, manage hackathon teams, and enhance collaborative experiences.

## 🌟 Features

- **Personalized Buddy Recommendations**: Connect with users based on shared skills, interests, and goals.
- **Real-Time Messaging**: Instant chat features to coordinate with teammates and discuss project ideas.
- **Hackathon Listings**: Stay updated on upcoming hackathons, deadlines, and themes.
- **Team Formation & Management**: Organize and manage your hackathon team members effectively.
- **Customizable Profiles**: Showcase your skills, experience, and tech stack to attract like-minded collaborators.
- **Thematic UI**: Inspired by constellations and Sun Wukong, Wukora brings a unique, adventurous design to your hackathon experience.

## 🛠️ Tech Stack

- **Frontend**:  
  - **Flutter**: Create visually stunning, responsive, and cross-platform mobile applications.
- **Backend**:  
  - **Spring Boot**: Robust backend framework for building scalable and secure APIs.
  - **Database**: Use **MongoDB** for flexible and efficient data storage.
  - **Authentication**: Secure login using **OAuth2** (Google and email sign-in).
  - **File Storage**: Store user avatars and project assets.
  - **WebSocket Integration**: Real-time chat and live updates.

## 📱 Getting Started

### Prerequisites

To get started with Wukora, you’ll need:  
- [Flutter](https://docs.flutter.dev/get-started/install) installed  
- [Android Studio](https://developer.android.com/studio) or Xcode (for iOS development)  
- [Spring Boot](https://spring.io/projects/spring-boot) setup  
- [MongoDB](https://www.mongodb.com/) installed or a cloud database instance  

### Setup

1. **Clone the Repository**

   ```bash
   git clone https://github.com/yourusername/wukora.git
   cd wukora
   ```

2. **Frontend Setup**  
   Navigate to the `client` directory:  

   ```bash
   cd client
   flutter pub get
   ```

   Run the app on your preferred device:  

   ```bash
   flutter run
   ```

3. **Backend Setup**  
   Navigate to the `server` directory:  

   ```bash
   cd server
   ```

   Install dependencies and run the Spring Boot application:  

   ```bash
   ./mvnw spring-boot:run
   ```

4. **Database Configuration**  
   Configure your MongoDB connection in the `application.properties` file located in the `server` directory:  

   ```properties
   spring.data.mongodb.uri=mongodb://localhost:27017/wukora
   ```

## 🚀 Features in Progress

- **Dark Mode** for an enhanced user experience  
- **Advanced Filtering** for recommendations based on hackathon themes and specific technologies  
- **Achievements & Badges** to gamify the experience  

## 🤝 Contributing

We welcome contributions! Please fork the repository and submit a pull request.
