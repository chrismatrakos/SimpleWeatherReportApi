### PROJECT 1, Simple Weather report App using Javascript and Angular.  
 
### Description  
A simple weather APP  that can provide weather information for a specific city.  

### Run locally  
Clone the repo locally and cd at "WeatherReportApi" folder.  
At the root directory of folder select and double click the file      
```bash
index.html
```  
The above file will open the browse and start the application.  

### How to use  
Type in the search city area a city you want to get weather report.  
If city is unique the weather information will appear in the form.  
If there are many names with same city a dropwdown list will appear to make city selection   
more explicit.  
You can switch between Celsious and Fahrenheit temperatures. (Click the C or F symbol next to temperature).  
Data are stored in local storage, if you refresh or close and open the browser the last searched city info will be displayed.  

### PROJECT 2, Java Server using Spring.  

### Description  
Simple Java server using spring and rest.  
The server calls an external api to fetch all the data.  
The API used is ``` http://api.countrylayer.com/v2/all ```.  
I have created an access token since its required for each query.  

### Setup  
Java jdk install check with  
 ```bash
 java -v
```  

JAVA_HOME path is set to env variables, ex: C:\user\ProgramFiles\jdk_1.xx\bin  

### Run locally   
Clone the repo locally.  
Go to the demo folder "SimpleWeatherReportApi\CountriesServer\demo".  
From the root dir of demo folder run the command ```mvnw spring-boot:run ```  
That command will load all dependencies and start the server.  
Occe the server is running open browser and go to ```localhost:8080/hello```  
to check its working, a pring message will be displayed.    

### How to use  
All countries data are fetched from 
```bash
 localhost:8080/countries
```  
A specific country is fetched from  
```bash
 localhost:8080/countries/countryName
```  
Replace countryName with the actual name in the above url.  

