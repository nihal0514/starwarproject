


<img src="https://github.com/nihal0514/starwarproject/assets/77735813/aba2cb01-cbd7-4ec7-be01-86fc01aef091" width="300" height="500">
<img src="https://github.com/nihal0514/starwarproject/assets/77735813/54449aa4-822e-4fc4-8b6e-43858c1f3997" width="300" height="500">
<img src="https://github.com/nihal0514/starwarproject/assets/77735813/263c17a0-68b7-40df-891e-e6ba9ab45d0e" width="300" height="500">


I have developed an Android application that leverages the Star Wars API to fetch and display information about Star Wars characters and associated movies.

Below is a brief overview of the key components and features:

**Project Overview**:
**Purpose**:
The primary purpose of the project is to provide an interactive and informative experience for users interested in Star Wars characters and movies. The application fetches data from the Star Wars API, presenting it in a user-friendly manner.



**Core Functionalities**: UI Enhancement:

The user interface is designed to be visually appealing and intuitive, employing RecyclerViews and adapters for efficient data presentation.
Dependency Injection (Dagger ):

Dagger  is utilized for dependency injection, promoting a modular and testable codebase. It simplifies the management of dependencies across the application.
Room Database:

A Room database is implemented to store and retrieve character data locally, enhancing data persistence and reducing the need for frequent network requests.
Retrofit and Star Wars API:

Retrofit is employed to handle network requests, specifically interfacing with the Star Wars API. This ensures efficient communication with the API to fetch character and movie information.
Pagination:

The project implements pagination to handle large datasets efficiently, enhancing the performance and responsiveness of the application.
ViewModel Architecture:

The ViewModel architecture is adopted to manage UI-related data and handle interactions between the UI components and the underlying data sources.

Project Components:


**Model Classes**:

Character.kt: Represents a Star Wars character with additional properties.
Movie.kt: Represents a Star Wars movie with relevant information.


**Adapters**:

CharacterListAdapter.kt: RecyclerView adapter for displaying characters.
MovieAdapter.kt: RecyclerView adapter for displaying movies.


**Room Database**:

CharacterDao.kt: Data Access Object (DAO) for Room, handling character-related database operations.
CharacterDB.kt: Room database definition for storing character data.


**Dependency Injection (DI)**:

ApplicationComponent.kt: Dagger  component for application-level dependencies.
DatabaseModule.kt: Dagger  module providing dependencies related to the Room database.
NetworkModule.kt: Dagger  module providing dependencies related to Retrofit and the Star Wars API.
ViewModelModule.kt: Dagger  module providing dependencies for ViewModels.


**UI Fragments**:

CharacterMovieFragment.kt: Fragment for displaying characters and associated movies.
HomeFragment.kt: Home fragment for the main screen.


**Pagination**:

CharacterPagingSource.kt: PagingSource implementation for character data pagination.


**Repository**:

StarWarRepository.kt: Repository class handling data sources and providing a clean API to the ViewModel.
Retrofit:

StarWarApi.kt: Retrofit interface for making API calls to the Star Wars API.


**ViewModel**:

CharacterViewModel.kt: ViewModel responsible for managing UI-related data and interacting with the repository.


**Application Class**:

StarWarApplication.kt: Custom Application class initializing Dagger  and other application-level configurations.
Conclusion:
The project showcases a well-organized Android application that combines modern Android development practices, effective use of libraries, and a clean architecture to deliver a seamless and engaging experience for Star Wars enthusiasts.

