# Weather App

The following project is an Android application that fetches and displays weather information from
OpenWeather API (https://openweathermap.org/). 

## Architecture Summary

The architecture for this application can be divided into 3 sections: Data layer, 
Domain/Business-logic layer & UI.

### Data Layer

The data layer contains models that represent the raw weather information received from OpenWeather 
and the operations that can be done to interact with this data (for this app, mainly just fetching). 
Data flow in this architecture is unidirectional, flowing from the source (OpenWeather API) 
eventually to the UI where it will be displayed for the user to interact with. Access to the data 
layer is controlled by a Repository (WeatherReportRepository). 

### Domain / Business Logic layer

The domain layer controls how the app interacts with the data layer (ie, when to load weather information)
and how to react to events from the UI layer (ie, user searches for a location). This logic is
mainly stored between the ViewModel (WeatherReportViewModel) and Use Cases. The Use cases interact 
with the data layer to retrieve and convert raw data to presentable information. The view model 
coordinates the use cases and responds to user interaction. Data is retrieved from the repository, 
processed and exposed for consumption to the UI layer via the ViewModel. On events, the ViewModel 
also process interactions from the UI layer and reacts accordingly. 

### UI Layer

Lastly, the UI layer consumes data exposed by the ViewModel and displays it as UI. Presentation 
logic, determines the specifics of how data should be displayed (ie, colors, orientation, UI components). 
When the user interacts with the UI, the UI layer relays those events to the ViewModel, where 
depending on the business logic, a reaction occurs. Most of this logic can be found in the 
MainActivity and the sub Composables representing different portions of the UI.


## Screens

### Initial Opening

On initial open, location permissions is requested to determine current weather at users location.


https://user-images.githubusercontent.com/10109904/232233093-09737f68-85a1-41ae-9dd8-72cd73d00da8.mov



### Enable Location Permission + Load Weather for current location


After location permission is provided, weather of current location is retrieved from OpenWeather.


https://user-images.githubusercontent.com/10109904/232233150-4e08664d-769a-4cd4-b8bc-2bc67f6d489c.mov

### Location Search

On the main page, the user can search for the current weather for the entered location.


https://user-images.githubusercontent.com/10109904/232233252-fcfd509b-15c5-41fa-9274-365ec7afc043.mov


### Weather Forcast

If the user clicks on the current weather, they are taken to a detail page displaying more weather information for future times.


https://user-images.githubusercontent.com/10109904/232233292-dc374150-adde-4461-8363-712316a98310.mov








