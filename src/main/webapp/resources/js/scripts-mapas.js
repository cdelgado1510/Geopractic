var osmLink = '<a href=\'http://www.openstreetmap.org\'>OpenStreetMap</a>' ;
var mapaAbm;
var mapaVisor;
var punto ;
var capaDibujo;

// Crea un mapa de visor en la ubicacion establecida
function cargarVisor(lugar, geoJson){

	if (mapaVisor != null) {
		mapaVisor.off();
	}
	
	//Carga el mapa
	mapaVisor = L.map(lugar).setView([-25.3, -57.64], 16);

    //Crea la capa de OpenStreetMap
    L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    	attribution: osmLink,
    	maxZoom: 16,
	}).addTo(mapaVisor);	

    var geoJSONLayer;
    if (geoJson.properties.Geometria == 'Zona') {
    	geoJSONLayer = L.geoJson(geoJson,
    	    	{
    		    	style: function(feature) {
    		    		switch(feature.properties.Geometria) {
    		    			case 'Zona':
    			    			return {
    			    				color: "#" + feature.properties.contorno,
    			    				weight: 5,
    			    				fillColor: "#" + feature.properties.relleno
    			    			}
    			    		break;
    		    		}
    		    	},
    		    	onEachFeature: function(feature, layer) {
    		    		layer.bindPopup(feature.properties.Descripcion);
    		    	}
    	    	})
	} else {
    	geoJSONLayer = L.geoJson(geoJson,
    	    	{
		    		pointToLayer: function(feature, latlng) {
		    			punto = L.marker(latlng, {
		    				icon: L.icon({
		    					iconUrl: '/geopractic/resources/css/images/' + feature.properties.icono,
		    					iconSize: [25, 41]
		    				})
		    			}).bindPopup(feature.properties.Descripcion);
		    			return punto;
		    			}
    	    	})
	}

    geoJSONLayer.addTo(mapaVisor);
    mapaVisor.fitBounds(geoJSONLayer.getBounds());
}

//Carga lus puntos de visita de un recorrido
function cargarVisitas(lugar, geoJson){

	if (mapaVisor != null) {
		mapaVisor.off();
	}
	
	//Carga el mapa
	mapaVisor = L.map(lugar).setView([-25.3, -57.64], 16);

    //Crea la capa de OpenStreetMap
	var capaMapa = L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    	attribution: osmLink,
    	maxZoom: 16,
	}).addTo(mapaVisor);	

	var impresion = L.easyPrint({
        tileLayer: capaMapa,
        title: 'Exportar mapa',
        sizeModes: ['Current', 'A4Landscape'],
        defaultSizeTitles: {Current: 'Mapa Completo', A4Landscape: 'Tamaño A4'},
        filename: 'recorrido',
        exportOnly: true,
        hideControlContainer: true
    }).addTo(mapaVisor);

    var geoJSONLayer;
    
    geoJSONLayer = L.geoJson(geoJson,
        	{	
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
        	})

    geoJSONLayer.addTo(mapaVisor);
    if (geoJson) {
    	if (geoJson.features.length > 0) {
    		mapaVisor.fitBounds(geoJSONLayer.getBounds());	
    	}	
	}
}


//Crea un mapa de Abm en la ubicacion establecida
function cargarAbm(lugar){

	//Obtiene el elemento el mapa
	document.getElementById(lugar).innerHTML = "<div id='mapaAb' style='width: 450px; height: 400px;'></div>";

	//Carga el mapa
	mapaAbm = L.map('mapaAb').setView([-25.3, -57.64], 16);

    //Crea la capa de OpenStreetMap
    L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    	attribution: osmLink,
    	maxZoom: 16,
	}).addTo(mapaAbm);	

    capaDibujo = new L.FeatureGroup();
	capaDibujo.addTo(mapaAbm);

    L.drawLocal.draw.toolbar.buttons.polygon = 'Dibujar poligono';
    L.drawLocal.draw.toolbar.buttons.marker = 'Marcar punto';
    L.drawLocal.draw.toolbar.undo.title = 'Eliminar el ultimo punto';
    L.drawLocal.draw.toolbar.undo.text = 'Borrar el ultimo punto';
    L.drawLocal.draw.toolbar.actions.title = 'Cancelar dibujo';
    L.drawLocal.draw.toolbar.actions.text = 'Cancelar';
	L.drawLocal.draw.toolbar.finish.title = 'Finalizar dibujo';
	L.drawLocal.draw.toolbar.finish.text = 'Finalizar';
	L.drawLocal.draw.handlers.marker.tooltip.start = 'Click para ubicar marcador.';
	L.drawLocal.draw.handlers.polygon.tooltip.start = 'Click para iniciar a dibujar la zona.';
	L.drawLocal.draw.handlers.polygon.tooltip.cont = 'Click para continuar dibujando la zona.';
	L.drawLocal.draw.handlers.polygon.tooltip.end = 'Click en el primer punto para cerrar la zona.';
	
	var controlDibujo = new L.Control.Draw({
  		 draw: {
  			 polyline: false,
  			 rectangle: false,
  			 circle: false,
  			 marker: true,
  			 circlemarker: false,
  			 polygon: true
  		 },
  		 edit: false
  	});
 	mapaAbm.addControl(controlDibujo);

  	function configurarMapa(evento) {

  		var popup = L.popup();
		var tipoGeo = PF('txtTipo').getSelectedValue();

	    if (tipoGeo.indexOf('Zona') !== -1) {
	    	var contornoZonaAbm = document.getElementById('formAbm:contornoZona').value;
	    	var rellenoZonaAbm = document.getElementById('formAbm:rellenoZona').value;
	    	
	    	controlDibujo.setDrawingOptions({
	    		polygon: {
	    			allowIntersection: false,
	    			drawError: {
	    				color: '#e1e100', 
	    				message: 'Zona de dibujo invalida.'
	    		},
	    			shapeOptions: {
	    				color: "#" + contornoZonaAbm ,
	    				weight: 5,
	    				fillColor: "#" + rellenoZonaAbm,
	    			}
	    		}
	    	});
	    } else if (tipoGeo.indexOf('Punto') !== -1){
	    	var imageIconoAbm = document.getElementById('formAbm:iconoTipo').value;
	    	controlDibujo.setDrawingOptions({
	    		marker: {
	    	    	icon: L.icon(
	    	    		{iconUrl: '/geopractic/resources/css/images/' + imageIconoAbm,
						 iconSize: [25, 41]
						}
	    	    	)
	    	    }
	    	});
	    } else {
           popup.setLatLng(evento.latlng)
                .setContent("Debe seleccionar un tipo de Georreferencia")
		        .openOn(mapaAbm);
	    }
		 
	} //Fin de funcion marcarMapa

  	 mapaAbm.on(L.Draw.Event.CREATED, function (e) {
			var capa = e.layer;
			var tipo = e.layerType;
			var tipoGeo = PF('txtTipo').getSelectedValue();
			var popup = L.popup();
			capaDibujo.clearLayers();

         if (tipo === 'marker') {
         	if (tipoGeo.indexOf('Punto') !== -1) {
         		capaDibujo.addLayer(capa);

         		var marcador = capaDibujo.toGeoJSON();

         		document.getElementById('formAbm:txtLongitud_input').value = marcador.features[0].geometry.coordinates[0].toString().replace(/[.]/, ",");
         	    document.getElementById('formAbm:txtLatitud_input').value = marcador.features[0].geometry.coordinates[1].toString().replace(/[.]/, ",");
         	    document.getElementById('formAbm:txtLongitud_hinput').value = marcador.features[0].geometry.coordinates[0];
         	    document.getElementById('formAbm:txtLatitud_hinput').value = marcador.features[0].geometry.coordinates[1];
     	    }  else {
     	         popup.setLatLng(evento.latlng)
                 .setContent("Tiene que dibujar un punto.")
    		        .openOn(mapaAbm);
     	    }
         }

			if (tipo === 'polygon') {
				if (tipoGeo.indexOf('Zona') !== -1) {
					limpiarPuntosZona();
					capaDibujo.addLayer(capa);
					var zonaMarcada = capaDibujo.toGeoJSON();
					var coordenadas = zonaMarcada.features[0].geometry.coordinates[0];
					for(let i = 0; i < coordenadas.length; i++){
						cargarPuntoZona([{name:'latitud', value:coordenadas[i][1]},{name:'longitud', value:coordenadas[i][0]}]);
					}
     	    }  else {
     	         popup.setLatLng(e.latlng)
                 .setContent("Tiene que dibujar una zona.")
    		        .openOn(mapaAbm);
     	    }
         }
	});

	mapaAbm.on('mouseover', configurarMapa);
}

function marcarPunto() {
	var imageIconoAbm = document.getElementById('formAbm:iconoTipo').value;

	var lat = document.getElementById('formAbm:txtLatitud_hinput').value;
	var lon = document.getElementById('formAbm:txtLongitud_hinput').value;

	capaDibujo.clearLayers();
	punto = L.marker();
    punto.setIcon(
    	L.icon({iconUrl: '/geopractic/resources/css/images/' + imageIconoAbm,
				iconSize: [25, 41]
		        }));
    punto.setLatLng([lat , lon]);
    punto.addTo(capaDibujo);
    mapaAbm.panTo([lat , lon]);

}

function marcarZona(geojson) {

	var contornoZonaAbm = document.getElementById('formAbm:contornoZona').value;
	var rellenoZonaAbm = document.getElementById('formAbm:rellenoZona').value;

	capaDibujo.clearLayers();
    var geoJSONLayer = L.geoJson(geojson,{
    		    	style: function(feature) {
    		    		switch(feature.properties.Geometria) {
    		    			case 'Zona':
    			    			return {
    			    				color: "#" + contornoZonaAbm ,
    			    				weight: 5,
    			    				fillColor: "#" + rellenoZonaAbm
    			    			}
    			    		break;
    		    		}
    		    	}
    }) 

    geoJSONLayer.addTo(mapaAbm);
    mapaAbm.fitBounds(geoJSONLayer.getBounds());
}