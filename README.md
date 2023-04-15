# RBC Weather App

The following project is an Android application that fetches and displays weather information from
OpenWeather API (https://openweathermap.org/). 

## Architecture Summary

The architecture for this application can be divided into 3 sections: Data layer, Domain/Business-logic layer & UI.

### Data Layer

The data layer contains models that represent the raw weather information received from OpenWeather and the
operations that can be done to interact with this data (for this app, mainly just fetching). Data is this architecture is
unidirectional, flowing from the source (OpenWeather API) eventually to the UI where it will be displayed for the user to interaction with.
Access to the data layer is controlled by a Repository (WeatherReportRepository). 

### Domain / Business Logic layer

The domain layer controls who the app interacts with the data layer (ie, when to load weather information)
and how to react to events from the UI layer (ie, user searches for a location). This logic is mainly stored in the
ViewModel (WeatherReportViewModel). Data is retrieved from the repository, processed and exposed for consumption by the UI layer.
On events, the ViewModel also process interactions from the UI layer and reacts accordingly. 

### UI Layer

Lastly, the UI layer consumes data exposed by the ViewModel and displays it as UI. Presentation logic, determine the specifics 
for how data should be displayed (ie, colors, orientation, UI components) are housed in the layer. When the user interacts with the UI,
the UI layer relays those events to the ViewModel, where depending on the business logic, a reaction occurs. Most of this logic can be found in the 
MainActivity and the sub Composables representing different portions of the UI.
