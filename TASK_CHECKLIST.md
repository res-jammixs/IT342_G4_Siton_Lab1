# Task Checklist - IT342_G4_Siton_Lab1

## ✅ DONE - All Tasks Completed

### 📦 Phase 1: Project Setup
- ✅ **Initial commit** - Repository initialization
- ✅ **feature(setup):** Initialized project structure with React frontend and Spring Boot backend
- ✅ **feature(readme):** Updated README file with project documentation
- ✅ **setup(gitignore):** Configured .gitignore file
- ✅ **Merge pull request #1:** Merged gitignore setup

### 🔧 Phase 2: Backend Setup (Feb 7, 2026)
- ✅ **feature(backend):** Initial Spring Boot setup with folder structure
- ✅ **Database Setup:** Connected to local XAMPP MySQL server
- ✅ **API Endpoint:** POST /api/auth/register - User registration
- ✅ **API Endpoint:** POST /api/auth/login - User authentication
- ✅ **API Endpoint:** POST /api/auth/logout - Logout with token invalidation
- ✅ **API Endpoint:** GET /api/user/me - Protected user profile endpoint
- ✅ **Security:** BCrypt password encryption implemented
- ✅ **Validation:** Email uniqueness validation
- ✅ **Validation:** Spring Boot Validation with @Valid, @NotBlank, @Email, @Size annotations
- ✅ **Error Handling:** Global exception handler for consistent error responses
- ✅ **Error Handling:** Proper exception handling with meaningful error messages
- ✅ **JWT Alternative:** Token-based authentication using UUID tokens
- ✅ **Merge pull request #2:** Merged backend setup feature

### 🌐 Phase 3: Web Frontend Setup (Feb 7, 2026)
- ✅ **feature(frontend):** Connected React frontend to Spring Boot backend
- ✅ **User Paths:** Modified and configured routing
- ✅ **Register Screen:** User registration form with validation
- ✅ **Login Screen:** User login interface
- ✅ **Dashboard/Profile:** Protected user dashboard
- ✅ **Logout Functionality:** Proper logout with token cleanup
- ✅ **setup(readme):** Removed redundant README from web folder
- ✅ **Merge pull request #3:** Merged frontend setup
- ✅ **Merge pull request #4:** Merged frontend setup (second phase)

### 📄 Phase 4: Documentation (Feb 7, 2026)
- ✅ **feature(docs):** Added initial FRS document
- ✅ **FRS Content:** Complete Functional Requirements Specification

### 🐛 Phase 5: Bug Fixes (Feb 7, 2026)
- ✅ **bugfix(web):** Fixed error "failure to fetch data" in auth and dashboard
- ✅ **API Integration:** Resolved backend connection issues
- ✅ **Merge pull request #5:** Merged gitignore updates
- ✅ **Merge pull request #6:** Merged web bugfix

### 📱 Phase 6: Mobile Application Setup (Feb 14, 2026)
- ✅ **feature(mobile):** Android Kotlin mobile app setup
- ✅ **Mobile UI:** Register screen with XML layout
- ✅ **Mobile UI:** Login screen with authentication
- ✅ **Mobile UI:** Dashboard/Profile screen (protected)
- ✅ **Mobile UI:** Logout functionality
- ✅ **Backend Connection:** Connected mobile app to Spring Boot backend
- ✅ **Merge pull request #8:** Merged mobile feature

### 🔧 Phase 7: Mobile Bug Fixes (Feb 14, 2026)
- ✅ **bugfix(mobile):** Fixed connection error to backend MySQL
- ✅ **CORS Configuration:** Updated allowedOriginPatterns to accept mobile requests
- ✅ **Server Configuration:** Set server.address=0.0.0.0 for network binding
- ✅ **Network Issue:** Resolved "Failed to connect to /10.0.2.2:8080" error
- ✅ **Merge pull request #9:** Merged mobile bugfix

### ✅ Phase 8: Task Management (Feb 14, 2026)
- ✅ **feature(checklist):** Updated task checklist
- ✅ **Merge pull request #7:** Merged task checklist feature

### 📸 Phase 9: Final Documentation (Feb 15, 2026)
- ✅ **feature(docs):** Updated diagrams with complete architecture
- ✅ **Screenshots:** Added mobile UI screenshots to documentation
- ✅ **Screenshots:** Added web UI screenshots to documentation
- ✅ **FRS Update:** Complete FRS with both Web and Mobile UI
- ✅ **Merge pull request #10:** Merged documentation updates

### ⚡ Phase 10: Enhanced Validation & Error Handling (Feb 15, 2026)
- ✅ **Enhancement:** Added Spring Boot Validation dependency
- ✅ **Validation:** Implemented @Valid annotations on request DTOs
- ✅ **Validation:** Added @NotBlank, @Email, @Size constraints
- ✅ **Error Handling:** Created GlobalExceptionHandler for consistent API responses
- ✅ **Error Handling:** Validation error messages with field-level details

---

## 🎯 Requirements Verification

### 1️⃣ Backend – Spring Boot ✅
- ✅ Spring Boot Validation with annotations (@Valid, @NotBlank, @Email, @Size)
- ✅ Global exception handler (GlobalExceptionHandler)
- ✅ POST /api/auth/register - Complete with validation
- ✅ POST /api/auth/login - Complete with BCrypt verification
- ✅ POST /api/auth/logout - Implemented with token invalidation
- ✅ GET /api/user/me - Protected endpoint with token authentication
- ✅ Database connection (MySQL via XAMPP)
- ✅ Password encryption (BCrypt)
- ✅ Input validation and error handling
- ✅ Consistent API responses
- ✅ CORS configuration for web and mobile

### 2️⃣ Web Frontend – React ✅
- ✅ Register screen with form validation
- ✅ Login screen with authentication
- ✅ Dashboard/Profile screen (protected route)
- ✅ Logout functionality
- ✅ Connected to Spring Boot backend
- ✅ Error handling and user feedback

### 3️⃣ Mobile Application – Android Kotlin ✅
- ✅ Register screen with UI
- ✅ Login screen with authentication
- ✅ Dashboard/Profile screen (protected)
- ✅ Logout functionality
- ✅ Connected to Spring Boot backend
- ✅ Proper network configuration (10.0.2.2 for emulator)

### 4️⃣ Documentation ✅
- ✅ Complete FRS PDF in /docs folder
- ✅ Web UI screenshots included
- ✅ Mobile UI screenshots included
- ✅ Architecture diagrams
- ✅ README.md with project overview
- ✅ TASK_CHECKLIST.md (this file)

### 5️⃣ Repository Structure ✅
```
IT342_G4_Siton_Lab1/
├─ /web          ✅ React frontend
├─ /mobile       ✅ Android Kotlin app
├─ /backend      ✅ Spring Boot API
├─ /docs         ✅ FRS and documentation
├─ README.md     ✅ Project overview
└─ TASK_CHECKLIST.md ✅ This checklist
```

---

## 📊 Project Summary

**Total Commits:** 20+ commits  
**Pull Requests Merged:** 10 PRs  
**Project Status:** ✅ **COMPLETE**  
**All Requirements:** ✅ **MET**

### Key Achievements:
- ✅ Full-stack application (Web + Mobile + Backend)
- ✅ Secure authentication with BCrypt
- ✅ Token-based session management
- ✅ CORS-enabled API for multi-platform support
- ✅ Complete documentation with screenshots
- ✅ Error handling and validation throughout
- ✅ Successfully connected mobile emulator to local backend

---

## 🔄 In-Progress
_(No tasks currently in progress)_

---

## 📋 TODO
_(All tasks completed)xa