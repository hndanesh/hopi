<%@ page import="java.util.List"%>
<%@ page import="com.hackathon.hopi.model.Ride"%>
<%@ page import="com.google.appengine.api.datastore.KeyFactory"%>
<html>
<head>
<style>
      #map_canvas {
        width: 500px;
        height: 400px;
      }
</style>
<script src="/scripts/jquery-1.8.2.min.js"></script>
<script src="/scripts/bootstrap.min.js"></script>
<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&key=AIzaSyBoEiR3oXox0_yzyCDMiPcmornfZInH-U8&sensor=false&libraries=places">
</script>
<script type="text/javascript">
    var jq = jQuery.noConflict();
    var lat=40.779502;
    var lng=-73.967857;
    var geocoder;	
    var currentPosition;
    var map;
    var loc='';
    var query='';
    var limit =0;

    function initializeMap() {
        var mapOptions = {
            center: new google.maps.LatLng(lat, lng),
            zoom: 8,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };

        map = new google.maps.Map(document.getElementById("map-canvas"),
            mapOptions);

		google.maps.event.trigger(map, "resize");

		var input = /** @type {HTMLInputElement} */(document.getElementById('searchTextField'));
        var autocomplete = new google.maps.places.Autocomplete(input);

            autocomplete.bindTo('bounds', map);

            var infowindow = new google.maps.InfoWindow();
            var marker = new google.maps.Marker({
                map: map
            });

            google.maps.event.addListener(autocomplete, 'place_changed', function() {
                infowindow.close();
                marker.setVisible(false);
                input.className = '';
                var place = autocomplete.getPlace();
                if (!place.geometry) {
                    // Inform the user that the place was not found and return.
                    input.className = 'notfound';
                    return;
            }

            // If the place has a geometry, then present it on a map.
            if (place.geometry.viewport) {
                map.fitBounds(place.geometry.viewport);
            } else {
                map.setCenter(place.geometry.location);
                map.setZoom(17);  // Why 17? Because it looks good.
            }
            marker.setIcon(/** @type {google.maps.Icon} */({
                url: place.icon,
                size: new google.maps.Size(71, 71),
                origin: new google.maps.Point(0, 0),
                anchor: new google.maps.Point(17, 34),
                scaledSize: new google.maps.Size(35, 35)
            }));
            marker.setPosition(place.geometry.location);
            marker.setVisible(true);

            if (place.address_components) {
                address = [
                    (place.address_components[0] && place.address_components[0].short_name || ''),
                    (place.address_components[1] && place.address_components[1].short_name || ''),
                    (place.address_components[2] && place.address_components[2].short_name || '')
                ].join(' ');

                if((place.name).indexOf(',') > 0)
                    loc=(place.name).substring(0,(place.name).indexOf(','))
                else
                    loc=place.name;
            }

            infowindow.setContent('<div><strong>' + place.name + '</strong><br>' + address);
                infowindow.open(map, marker);
            });



        }


     window.onload = function() {
        if(loc==''){
            geocoder = new google.maps.Geocoder();
            getLocation();
        }
        initializeMap();
    }

    function getLocation() {
        if(navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(geoSuccess, geoError);
        }
    }

    function geoSuccess(position) {
        lat = position.coords.latitude;
        lng = position.coords.longitude;
        codeLatLng(lat, lng);
    }

    function geoError() {
        jq("#current-location").html('<a href="#locationModal" data-toggle="modal" onclick="initializeMap()"><fmt:message key="socialEventList.location.unknown"/></a>');
    }

    function codeLatLng(lat, lng) {
        var latlng = new google.maps.LatLng(lat, lng);
        geocoder.geocode({'latLng': latlng}, function(results, status) {
          if(status == google.maps.GeocoderStatus.OK) {
              if(results[1]) {
                  //formatted address
                  loc = results[0].address_components[1].short_name;
                  jq("#current-location").html('<a href="#locationModal" data-toggle="modal" onclick="initializeMap()">'+loc+'</a>');
              } else {
                  jq("#current-location").html('<a href="#locationModal" data-toggle="modal" onclick="initializeMap()"><fmt:message key="socialEventList.location.unknown"/></a>');
              }
          } else {
              jq("#current-location").html('<a href="#locationModal" data-toggle="modal" onclick="initializeMap()"><fmt:message key="socialEventList.location.unknown"/></a>');
          }
        });
    }
    
    // Function for adding a marker to the page.
    function addMarker(location) {
        marker = new google.maps.Marker({
            position: location,
            map: map
        });
    }

    // Testing the addMarker function
    function TestMarker() {
           CentralPark = new google.maps.LatLng(37.7699298, -122.4469157);
           addMarker(CentralPark);
    }
	</script>
</head>
<body>
	<h1>Ride List</h1>

	Function :
	<a href="add">Add Ride</a>
	<hr />

	<h2>All Rides</h2>
	<table border="1">
		<thead>
			<tr>
				<td>ID</td>
				<td>From</td>
				<td>To</td>
				<td>Plate No</td>
				<td>Driver Name</td>
				<td>Phone No</td>
				<td>Fare</td>
				<td>Time</td>
			</tr>
		</thead>

		<%
			if(request.getAttribute("rideList")!=null){
				
				List<Ride> rides = (List<Ride>)request.getAttribute("rideList");
				
				if(!rides.isEmpty()){
					 for(Ride r : rides){
						 
		%>
		<tr>
			<td><%=r.getId()%></td>
			<td><%=r.getFrom()%></td>
			<td><%=r.getTo()%></td>
			<td><%=r.getPlateNo()%></td>
			<td><%=r.getUserName()%></td>
			<td><%=r.getPhoneNo()%></td>
			<td><%=r.getFare()%></td>
			<td><%=r.getTime()%></td>
		</tr>
		<%	
			
					}
		    
				}
			
		   	}
		%>

	</table>
	<input id="searchTextField" type="text" style="width:94%; padding-right:20px; margin-bottom-20px;">
	<div id="map-canvas"></div>
</body>
</html>