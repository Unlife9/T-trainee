function vypis(udaje){
    console.log(udaje);
  }
  
  $.getJSON( "http://api.openweathermap.org/data/2.5/weather",
             {lat:48.7514,lon:21.9212,units:"metric",APPID:"8641355d0bdfa52a49f4e9a42560adf0"},
             spracuj);
             
function spracuj(udaje){
    const pocasieText = `Teplota: ${udaje.main.temp}, Tlak: ${udaje.main.pressure}, Oblačnosť: ${udaje.clouds.all}%, Rýchlosť vetra: ${udaje.wind.speed}, Vlhkosť: ${udaje.main.humidity}`;
    $("#pocasie").html(pocasieText);
   }
  