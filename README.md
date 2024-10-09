DocuScanner

Welcome to DocuScanner, a lightweight Android application that allows you to scan documents using your device’s camera, save them locally, and manage them efficiently. With DocuScanner, you can easily digitize your documents, rename them, delete unwanted scans, and share them with others.

Table of Contents

	•	Features
	•	Screenshots
	•	Architecture
	•	Tech Stack
	•	Setup and Installation
	•	Usage
	•	Acknowledgments

Features

	•	Document Scanning: Use your device’s camera to scan physical documents and convert them into digital images.
	•	ML Kit Integration: Utilizes Google’s ML Kit Document Scanner for accurate and high-quality scanning.
	•	Local Storage: Saves scanned documents into the device’s memory for offline access.
	•	Document Management:
	•	Rename Documents: Change the name of your scanned documents for better organization.
	•	Delete Documents: Remove unwanted scans from your device.
	•	Share Documents: Easily share your scanned documents via email, messaging apps, or other platforms.
	•	User-Friendly Interface: Simple and intuitive UI for a seamless user experience.

Screenshots

<img src="https://github.com/user-attachments/assets/153b367e-cdeb-411d-8a54-b539e80987ec" width="300" />
<img src="https://github.com/user-attachments/assets/a6b96d73-4073-476e-a985-7b09fdabe78b" width="300" />
<img src="https://github.com/user-attachments/assets/1c4d0b87-a94b-4d66-a15d-51d7f9b9e41e" width="300" />
<img src="https://github.com/user-attachments/assets/ec8f9ea4-1ea2-4a2a-a078-b11d4be5bfa6" width="300" />
<img src="https://github.com/user-attachments/assets/7bb064cf-5b1d-439c-b8ac-5db21988c421" width="300" />

Architecture

DocuScanner is built following the MVVM (Model-View-ViewModel) architectural pattern, ensuring a separation of concerns and promoting testability.

	•	View: Contains UI components and handles user interactions.
	•	ViewModel: Acts as a bridge between the View and Model, managing UI-related data and business logic.
	•	Model: Includes data classes and handles data operations, including interactions with the Room database.

Tech Stack

	•	Kotlin: Programming language used for Android development.
	•	ML Kit Document Scanner: Google’s Machine Learning Kit used for scanning documents and images.
	•	Room Database: Provides local data persistence for storing document information.
	•	Android Jetpack Components: Including ViewModel, LiveData, and Navigation components.
	•	Coroutines: For asynchronous programming and background tasks.
	•	Material Design: Implements Material Design components for a modern UI.

Setup and Installation

Prerequisites

	•	Android Studio Flamingo or higher.
	•	Android SDK with minimum API level 21.
	•	Internet Connection: Required for initial setup and dependency downloads.

 Installation Steps

    1.	Clone the Repository
    2.	Open the Project in Android Studio
    	•	Launch Android Studio.
	    •	Click on File > Open and navigate to the cloned repository.
    	
    3.	Build the Project
	    •	Android Studio will download all the necessary dependencies.
	    •	Wait for the Gradle build to finish.
    4.	Run the App
	    •	Connect an Android device or start an emulator.
	    •	Click on the Run button or use the shortcut Shift + F10.

Usage

	1.	Launch the App
	•	Open DocuScanner on your Android device.
	2.	Scan a Document
	•	Tap on the Scan button to initiate the scanning process.
	•	Align the document within the camera frame.
	•	Capture the image; the ML Kit Document Scanner will process and enhance the scan.
	3.	Save the Document
	•	After scanning, the document is saved into the device’s memory.
	•	You can view all saved documents in the Documents list.
	4.	Manage Documents
	•	Rename: Tap on a document to rename it for better organization.
	•	Delete: Swipe left or tap on the delete icon to remove a document.
	•	Share: Tap on the share icon to send the document via email, messaging apps, or other platforms.

 Acknowledgments

	•	ML Kit: For providing powerful machine learning capabilities.
	•	Android Developers Documentation: For comprehensive guides and best practices.
	•	Open Source Community: For resources and inspiration.



