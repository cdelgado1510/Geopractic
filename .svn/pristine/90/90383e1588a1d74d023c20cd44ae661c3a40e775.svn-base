var osmLink = '<a href=\'http://www.openstreetmap.org\'>OpenStreetMap</a>' ;
var mapas = [];
var lineaDePuntos = [];
var ruteoAsignado = [];
var recorridoRastreador = [];


function agregarVisor(cantidad) {

  var clase;
  clase = "ui-g-12 ui-md-6 ui-lg-4"

  //Genera los DIV para los mapas
  for (var contador = 1; contador <= cantidad; contador++) {
	  //Crea el contenedor para el mapa
	  var divContenedor = document.createElement("DIV");
	  divContenedor.className = clase;
	  
	  //Crear el mapa
	  var nombre = "visor" + contador;
	  var divMapa = document.createElement("DIV");
	  divMapa.setAttribute("id",nombre);
	  divMapa.className = "mapa";
	  //Agrega los objetos
	  divContenedor.appendChild(divMapa);
	  document.getElementById("paneles").appendChild(divContenedor);
  } //Fin de for

} //Fin de agregarVisor

function iniciarMapas(cantidad) {
	for(contador = 1; contador <= cantidad ; contador++) {
		mapas.push(L.map("visor" + contador).setView([-25.3, -57.64], 16));
	} //Fin de for
	
	lineaDePuntos = new Array(cantidad);
	ruteoAsignado = new Array(cantidad);
	recorridoRastreador = new Array(cantidad);
} //Fin de iniciarMapas

function iniciarOSM() {
	for(i = 0; i < mapas.length ; i++) {				
		var osm = L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
			attribution: osmLink,
			maxZoom: 18
		});
		osm.addTo(mapas[i]);
	} //Fin de for
} //Fin de iniciarOSM

function cargarRuteo(ruteoDia, visor) {
    //Verifica si el ruteo asignado ya esta inicializado
    if (ruteoAsignado[visor]) {
    	ruteoAsignado[visor].remove();
	}

	ruteoAsignado[visor] = L.geoJson(ruteoDia,
		{
    		style: function(feature) {
    			switch(feature.geometry.type) {
    			case 'Polygon':
    				return {
	    				color: "#" + feature.properties.contorno,
	    				weight: 5,
	    				fillColor: "#" + feature.properties.relleno
	    			}
    				break;
    			}
    		},
   			pointToLayer: function(feature, latlng) {
   				var iconoRecorridoRuteo;
   				var archivoIcono;
   				//Establece el icono de cumplimiento
   				if (feature.properties.Visitado == false && feature.properties.Cumplito == false ) {
   					archivoIcono = feature.properties.icono.replace('-green.png','-red.png')
   					iconoRecorridoRuteo = L.icon({
   						iconUrl: '/geopractic/resources/css/images/'+archivoIcono,
   						iconSize: [25, 41],
   						iconAnchor: [13, 41]
   						});
   				}
   				if (feature.properties.Visitado == true && feature.properties.Cumplito == false ) {
   					archivoIcono = feature.properties.icono.replace('-green.png','-yellow.png')
   					iconoRecorridoRuteo = L.icon({
   						iconUrl: '/geopractic/resources/css/images/'+archivoIcono,
   						iconSize: [25, 41],
   						iconAnchor: [13, 41]
   						});
   				}
   				if (feature.properties.Visitado == true && feature.properties.Cumplito == true ) {
   					archivoIcono = feature.properties.icono;
   					iconoRecorridoRuteo = L.icon({
   						iconUrl: '/geopractic/resources/css/images/'+archivoIcono,
   						iconSize: [25, 41],
   						iconAnchor: [13, 41]
   						});
   				}
   				punto = L.marker(latlng, {
    				icon: iconoRecorridoRuteo
    			});
    			return punto;
    		},
    		onEachFeature: function(feature, layer) {
    			var descripcionRuta = "<p><b>Lugar: </b>" + feature.properties.Lugar + "</p>"
				+ "<p><b>Monto: </b>" + feature.properties.Monto + "</p>"
				+ "<p><b>Forma de pago: </b>" + feature.properties.FormaDePago + "</p>"
				+ "<p><b>Motivo: </b>" + feature.properties.Motivo + "</p>";
	    		layer.bindPopup(descripcionRuta);
	    	}
		}
	);
	
	ruteoAsignado[visor].addTo(mapas[visor]);

	if (ruteoDia.features.length > 0) {
		mapas[visor].fitBounds(ruteoAsignado[visor].getBounds());
	}
}

function agregarPunto(lat, lon, visor, descripcion, fecha, hora, velocidad){
	
	console.log("Agregando punto"); 
	if (recorridoRastreador[visor] != undefined) {
		lineaDePuntos[visor].addLatLng(L.latLng(lat, lon));
        mapas[visor].removeLayer(recorridoRastreador[visor]);
    } else {
    	lineaDePuntos[visor] = L.polyline([L.latLng(lat, lon)], {
    	  "delay": 756,
    	  "dashArray": [
    	    10,
    	    20
    	  ],
    	  "weight": 6,
    	  "color": "#FF2900",
    	  "pulseColor": "#FFFFFF",
    	  "paused": true,
    	  "reverse": false,
    	  "hardwareAccelerated": true
    	});
		lineaDePuntos[visor].addTo(mapas[visor])
	};

	var iconoRecorridoRastreador
	var descripcionUbicacion = "<p><b>" + descripcion + "</b></p>"
	+ "<p><b>Fecha/Hora: </b>" + fecha + ":" + hora + "</p>";

	//Establece el icono
	if (velocidad > 8) {
		iconoRecorridoRastreador = L.icon({
			iconUrl: '/geopractic/resources/css/images/marker-green.png',
			iconSize: [25, 41],
			iconAnchor: [13, 41]
			});
	} else {
		iconoRecorridoRastreador = L.icon({
			iconUrl: '/geopractic/resources/css/images/marker-red.png',
			iconSize: [25, 41],
			iconAnchor: [13, 41]
			});
	}


	recorridoRastreador[visor] = L.marker([lat, lon],{
		icon: iconoRecorridoRastreador
	}).bindPopup(descripcionUbicacion);
	recorridoRastreador[visor].addTo(mapas[visor]);
	recorridoRastreador[visor].openPopup();
	mapas[visor].setView([lat, lon]);
}