var osmLink = '<a href=\'http://www.openstreetmap.org\'>OpenStreetMap</a>' ;
var mapaVisor;
var lineaDePuntos;
var sliderControl;
var ruteoAsignado;
var recorridoRastreador;

// Crea un mapa de visor para control recorrido
function cargarVisor(recorridoGps, ruteoDia){

	var iconoRecorridoRastreador;

    //Verifica si el recorrido del GPS ya esta inicializado
    if (recorridoRastreador) {
    	recorridoRastreador.remove();
	}

	recorridoRastreador = L.geoJson(recorridoGps ,
    	{
			pointToLayer: function(feature, latlng) {
				
				var descripcionRecorrido = "<p><b>Fecha: </b>" + feature.properties.Fecha + "</p>"
				+ "<p><b>Hora: </b>" + feature.properties.Hora + "</p>"
				+ "<p><b>Velocidad en Km: </b>" + feature.properties.Velocidad + "</p>"
				+ "<p><b>Latitud: </b>" + feature.geometry.coordinates[1] + "</p>"
				+ "<p><b>Longitud: </b>" + feature.geometry.coordinates[0] + "</p>";

				//Establece el icono de velocidad
				if (feature.properties.Velocidad > 8) {
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
				punto = L.marker(latlng, {
					icon: iconoRecorridoRastreador
				}).bindPopup(descripcionRecorrido);
			return punto;
			}
	});

    //Verifica si el ruteo asignado ya esta inicializado
    if (ruteoAsignado) {
    	ruteoAsignado.remove();
	}

	ruteoAsignado = L.geoJson(ruteoDia,
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

	recorridoRastreador.addTo(mapaVisor);
	ruteoAsignado.addTo(mapaVisor);

	if (recorridoGps.features.length > 0) {
		mapaVisor.fitBounds(recorridoRastreador.getBounds());	
	}
	
	if (ruteoDia.features.length > 0) {
		mapaVisor.fitBounds(ruteoAsignado.getBounds());
	}

    //Genera una linea utilizando todos los puntos
    var coordenadasLinea = [];
    for(var i in recorridoGps.features){
      var puntoGeoJson = recorridoGps.features[i];
      var coord = puntoGeoJson.geometry.coordinates;
      coordenadasLinea.push([coord[1],coord[0]]);
    }

    //Verifica si el listado de puntos ya esta inicializado
    if (lineaDePuntos) {
    	lineaDePuntos.remove();
	}

    lineaDePuntos = L.polyline.antPath(coordenadasLinea,{
    	  "delay": 756,
    	  "dashArray": [
    	    15,
    	    63
    	  ],
    	  "weight": 6,
    	  "color": "#FF2900",
    	  "pulseColor": "#FFFFFF",
    	  "paused": false,
    	  "reverse": false,
    	  "hardwareAccelerated": false
    	});

    lineaDePuntos.addTo(mapaVisor);

    //Verifica si el slider ya esta inicializado.
    if (sliderControl) {
    	sliderControl.remove();
	}

    if (document.getElementById('formControl:chkSoloMarcador_input').checked) {
      sliderControl = L.control.sliderControl({position: "topright", layer: recorridoRastreador,rezoom: 16,follow: 1});	
	} else {
	  sliderControl = L.control.sliderControl({position: "topright", layer: recorridoRastreador,rezoom: 16,range: true});
	}

	mapaVisor.addControl(sliderControl);
    sliderControl.startSlider();

} //Fin de funcion cargarVisor