//standart load
$( document ).ready(function() {
    /*tooltip*/
    //init_tooltip();
    /*tab*/
    //init_tabs();
    /*link back to top animation*/
    //init_back_to_top();
    /*google map Config*/
    //init_gmap3();	
	initialize();
    /*backgroung Config*/
   // init_bgswitch();
    /*light box Config*/
   // init_color_box();
    /*Slider Config*/
   // init_slider();
    /*Grid Config*/
   // init_grid();
});


function init_tooltip(){
    $('.tootip').tooltip();
}

function init_color_box(){
    $(".ajax-demo").colorbox({
        width: 290,
        scrolling:false,
        fixed:true,
        height: 200,
        opacity:0.1
    });
}

function init_bgswitch(){
    var bg_pattern = getCookie('bg_pattern');
    if (bg_pattern != null){
        $("body").css('background-image', 'url("'+bg_pattern+'")');
    }
}

function init_back_to_top(){
    $('#back-to-top a').click(function () {
        $('body,html').animate({
            scrollTop: 0
        }, 800);
        return false;
    });
}






function dateFormat(input){
	var date = new Date(input*1000);
	
	var day = date.getDay();
	
	var month = date.getMonth();
	
	var year = date.getYear();
	// hours part from the timestamp
	var hours = date.getHours();
	// minutes part from the timestamp
	var minutes = date.getMinutes();
	// seconds part from the timestamp
	var seconds = date.getSeconds();

	// will display time in 10:30:23 format
	var formattedTime = day + '/' + month +'/'+ year +' '+ hours + ':' + minutes + ':' + seconds;
	
	return formattedTime;
}

var map;
function initialize() {
		
		var directionsDisplay;
		var directionsService = new google.maps.DirectionsService();
		
		directionsDisplay = new google.maps.DirectionsRenderer();
		var location = new google.maps.LatLng(-34.397, 150.644);
		 
		var mapOptions = {
				zoom: 8,
				center: location,
				mapTypeId: google.maps.MapTypeId.ROADMAP
		};
  
	  	map = new google.maps.Map(document.getElementById('googleMap'), mapOptions);
	  	directionsDisplay.setMap(map);
	  	
	  	//google.maps.event.addListenerOnce(map, 'tilesloaded', driverAddress);
	  	
		var fromText = /** @type {HTMLInputElement} */(document.getElementById('from'));
        var toText = /** @type {HTMLInputElement} */(document.getElementById('to'));
        
        var autocompletefrom = new google.maps.places.Autocomplete(fromText);
        var autocompleteto = new google.maps.places.Autocomplete(toText);
		
		var infowindow = new google.maps.InfoWindow();
	    var marker = new google.maps.Marker({
		  map: map
	    });
  
		autocompletefrom.bindTo('bounds', map);
       // autocompleteto.bindTo('bounds', map);
		
			//-----------autocompletefrom
			google.maps.event.addListener(autocompletefrom, 'place_changed', function() {
				infowindow.close();
				marker.setVisible(false);
				fromText.className = '';
				var place = autocompletefrom.getPlace();
				if (!place.geometry) {
				  // Inform the user that the place was not found and return.
				  fromText.className = 'notfound';
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
	
				var address = '';
				if (place.address_components) {
				  address = [
					(place.address_components[0] && place.address_components[0].short_name || ''),
					(place.address_components[1] && place.address_components[1].short_name || ''),
					(place.address_components[2] && place.address_components[2].short_name || '')
				  ].join(' ');
				}
	
				infowindow.setContent('<div><strong>' + place.name + '</strong><br>' + address);
				infowindow.open(map, marker);
			});
			
			
			//-----------autocompleteTo
			google.maps.event.addListener(autocompleteto, 'place_changed', function() {
				infowindow.close();
				marker.setVisible(false);
				toText.className = '';
				var place = autocompleteto.getPlace();
				if (!place.geometry) {
				  // Inform the user that the place was not found and return.
				  toText.className = 'notfound';
				  return;
				}
				
			  	var request = {
			  			origin:fromText.value,
			  			destination:toText.value,
			  			travelMode: google.maps.DirectionsTravelMode.DRIVING
				};
					
				directionsService.route(request, function(response, status) {
				    if (status == google.maps.DirectionsStatus.OK) {
				    	directionsDisplay.setDirections(response);
				    }
				});
				
			});
}

function addmarker(lat, lng) {
	
	var location = new google.maps.LatLng(lat, lng);
		
    var marker = new google.maps.Marker({
        position: location,
        title: 'new marker',
        draggable: true,
        map: map
    });
    map.setCenter(marker.getPosition());
    
}

var driverRated='';
var userId='';
var jq;
function submit(){
	var a = 42.745389;
	var b = 12.738402;
	var location = new google.maps.LatLng(a, b);
	
	/*var mapOptions = {
			zoom: 8,
			center: location,
			mapTypeId: google.maps.MapTypeId.ROADMAP
	};

  	map = new google.maps.Map(document.getElementById('googleMap'), mapOptions);*/
  	
	jq = jQuery.noConflict();
	
	userId=jq("#userId").val();
	var pending='';
	jq.ajax({
			type : "GET",
			url : "/ride/getpending",
			data : {id: userId},
			success : function(data){
				if(data!=''){
					pending=data;
					driverRated=data.userName;
					jq("#searchLocationPanel").html('<form class="proper-singup" action="javascript:rate()">'+
                            '<h1 class="">Rate this Ride</h1>'+
                            'Driver : '+data.userName+'<br/>'+
                            'From : '+data.from+'<br/>'+
                            'To : '+data.to+'<br/>'+
                            'Time : '+data.time+'<br/>'+
                            '<hr>'+
                            '<label>Punctuality</label>'+
                            '<input type="radio" id="punctuality" value="0">Poor<br>'+
                            '<input type="radio" name="punctuality" value="2">Average<br>'+
                            '<input type="radio" name="punctuality" value="3">Good<br>'+
                            '<input type="radio" name="punctuality" value="5">Excellent<br>'+
                            '<label>Driving</label>'+
                            '<input type="radio" id="driving" value="0">Poor<br>'+
                            '<input type="radio" name="driving" value="2">Average<br>'+
                            '<input type="radio" name="driving" value="3">Good<br>'+
                            '<input type="radio" name="driving" value="5">Excellent<br>'+
                            '<label>Conduct</label>'+
                            '<input type="radio" id="conduct" value="0">Poor<br>'+
                            '<input type="radio" name="conduct" value="2">Average<br>'+
                            '<input type="radio" name="conduct" value="3">Good<br>'+
                            '<input type="radio" name="conduct" value="5">Excellent<br>'+
                            '<hr/><button type="submit" class="btn-proper btn">Submit</button>'+
                            '</form>');
					}
				},
				 complete : function(){
					 if(pending==''){
    jq.ajax({
        type : "GET",
		url : "/ride/get",
		success : function(data){
			if(data.length>0){
				isEmpty = false;
			}else{
				isEmpty = true;
			}
			
			if (!isEmpty){
				jq("#titleDiv").html('<h3 class="bottom-line  line-before main-heading">'+
						'<span class="main-circle-icon"><i class="icon-list"></i></span>  Rides'+
						'</h3>');
				jq("#searchLocationPanel").html(
							'<div id="idDiv" class="list-items" style="overflow:hidden;"></div>'
						);
				
				for(var i=0;i<data.length;i++){
					
					//console.log(data[i]);
					jq("#idDiv").append(
							
								'<div class="item" style="padding-left: 10px;">'+
									'<div class="img-preview">'+
										'<img src="images/agents/thum_list/a2.jpg" alt="Corin Langpost" />'+
									'</div>'+
									'<div class="item-desk">'+
										'<input type="hidden" id="id" value="'+ data[i].id +'"/>'+
										'<input type="hidden" id="from" value="'+ data[i].fromLat +'"/>'+
										'<input type="hidden" id="to" value="'+ data[i].fromLng +'"/>'+
										'<div class="title" ><h3><a href="#">Name: '+ data[i].userName +'</a></h3></div>'+
										'<div class="title" ><h3><a href="#">Plate No: '+ data[i].plateNo +'</a></h3></div>'+
										'<div class="location" style="font-size: 10px;">H/p: '+ data[i].phoneNo +'</div>'+
										'<div class="location" style="font-size: 10px;">Time: '+ data[i].time +'</div>'+
										'<div class="location" style="font-size: 10px;">Fare: '+ data[i].fare +' RM</div>'+
										'<a href="#" class="btn-proper btn-mini btn">Show</a>'+
									'</div>'+
									'<div class="clearfix"></div>'+
								'</div>');
					a += i; 
					b += i;
					addmarker(a, b);
				}
			}
		}
    });
					 }
				 }
	});
}


function init_gmap3(){
    $(function(){
        var icon_driver = "images/map/car2.png"
        var icon_passenger = "images/map/passenger3.png"
        var img_p = "images/properties/p1.jpg"
        	
        var fromText = /** @type {HTMLInputElement} */(document.getElementById('from'));
        var toText = /** @type {HTMLInputElement} */(document.getElementById('to'));
        var autocompletefrom = new google.maps.places.Autocomplete(fromText);
        var autocompleteto = new google.maps.places.Autocomplete(toText);
        
        
        $("#googleMap").gmap3({
            map:{
                // CENTRAL MAP DEFAULT
                address:"JAKARTA, INDONESIA",
                options:{
                    zoom:12,
                    scaleControl: false,
                    scrollwheel: false,
                    mapTypeId: google.maps.MapTypeId.ROADMAP,
                    mapTypeControl: true,
                    mapTypeControlOptions: {
                        style: google.maps.MapTypeControlStyle.DROPDOWN_MENU
                    }
                }
            },
            marker:{
                // DATA LOCATION
                values:[
                {
                    latLng:[-6.202165,106.827965], 
                    data:{
                        img_preview: img_p, 
                        properties_name:"023 Central Park [Rent]", 
                        properties_desc:"Lorem Ipsum Go Green",
                        properties_link:"#", 
                        zip:001233, 
                        city:"Jakarta"
                    }, 
                    options:{
                        icon: icon_passenger
                    }
                },

                {
                    latLng:[-6.16206,106.82642], 
                    data:{
                        img_preview: img_p, 
                        properties_name:"023 Central Park [Sell]", 
                        properties_desc:"Lorem Ipsum Go Green",
                        properties_link:"#", 
                        zip:001233, 
                        city:"Jakarta"
                    }, 
                    options:{
                        icon: icon_driver
                    }
                },

                {
                    latLng:[-6.185099,106.847878], 
                    data:{
                        img_preview: img_p, 
                        properties_name:"023 Central Park [Rent]", 
                        properties_desc:"Lorem Ipsum Go Green",
                        properties_link:"#", 
                        zip:001233, 
                        city:"Jakarta"
                    }, 
                    options:{
                        icon: icon_passenger
                    }
                },

                {
                    latLng:[-6.281856,106.776123], 
                    data:{
                        img_preview: img_p, 
                        properties_name:"023 Central Park [Sell]", 
                        properties_desc:"Lorem Ipsum Go Green",
                        properties_link:"#", 
                        zip:001233, 
                        city:"Jakarta"
                    }, 
                    options:{
                        icon: icon_passenger
                    }
                },

                {
                    latLng:[-6.228617,106.874313], 
                    data:{
                        img_preview: img_p, 
                        properties_name:"023 Central Park [Rent]", 
                        properties_desc:"Lorem Ipsum Go Green",
                        properties_link:"#", 
                        zip:001233, 
                        city:"Jakarta"
                    }, 
                    options:{
                        icon: icon_driver
                    }
                },

                {
                    latLng:[-6.217695,106.801529], 
                    data:{
                        img_preview: img_p, 
                        properties_name:"023 Central Park [Sell]", 
                        properties_desc:"Lorem Ipsum Go Green",
                        properties_link:"#", 
                        zip:001233, 
                        city:"Jakarta"
                    }, 
                    options:{
                        icon: icon_driver
                    }
                },
                {
                    latLng:[-6.255919,106.965637], 
                    data:{
                        img_preview: img_p, 
                        properties_name:"023 Central Park [Rent]", 
                        properties_desc:"Lorem Ipsum Go Green",
                        properties_link:"#", 
                        zip:001233, 
                        city:"Jakarta"
                    }, 
                    options:{
                        icon: icon_passenger
                    }
                }
                ],
                events:{
                    mouseover: function(marker, event, context){
                        $(this).gmap3(
                        {
                            clear:"overlay"
                        },

                        {
                            overlay:{
                                latLng: marker.getPosition(),
                                options:{
                                    content:  "<div class='info-location'>" +
                                    "<div class='text'><h4>" 
                                    + context.data.properties_name +
                                    "</h4>"+
                                    "<img src='"+ context.data.img_preview +"' width=90> $300.999 <br/>"+
                                    context.data.properties_desc +
                                    "<br/><a href='"+context.data.properties_link +"'class='btn btn-proper btn-small'>See Detail</a></div>" +
                                    "</div>" +
                                    "<div class='arrow-location'></div>",
                                    offset: {
                                        x:-46,
                                        y:-73
                                    }
                                }
                            }
                        });
                    }
                    
                }
            }
			
        });
        
    });
    
}


function init_slider(){
    //	Responsive layout, resizing the items
    if ($('#fluid-slider').length){
        $('#fluid-slider').carouFredSel({
            responsive: true,
            width: '100%',
            prev: '#fluid-slider-prev',
            next: '#fluid-slider-next',
            scroll: 1,
            mousewheel: true,
            swipe: {
                onMouse: true,
                onTouch: true
            },
            auto: {
                pauseOnHover: 'resume',
                timeoutDuration : 6000
            
            },
            items: {
                width: 400,
                //	height: '30%',	//	optionally resize item-height
                height: "75%",
                visible: {
                    min: 2,
                    max: 6
                }
            }
        });
    }
    
    if ($('#news-carousel').length){
        var _scroll = {
            delay: 1000,
            easing: 'linear',
            items: 1,
            duration: 0.07,
            timeoutDuration: 0,
            pauseOnHover: 'immediate'
        };
        $('#news-carousel').carouFredSel({
            width: '100%',
        
            align: false,
            items: {
                width: 'variable',
                height: 55,
                visible: 1
            },
            scroll: _scroll
        });
    }
    if ($('#carousel').length){
        $('#carousel').carouFredSel({
            width: '100%',
            responsive: true,
            items: {
                width: 300,
                height: "23%",
                visible: {
                    min: 1,
                    max: 1
                }
            },
            scroll: {
                pauseOnHover: 'resume',
                fx: 'fade',
                easing: 'linear',
                items: 1,
                duration: 1000,
                timeoutDuration: 6000
            },
            mousewheel: true,
            swipe: {
                onMouse: true,
                onTouch: true
            },
            prev: '#prev',
            next: '#next',
            pagination: {
                container: '#pager',
                deviation: 1
            }
        });
    }
    
    
    if ($('#list_images_property').length){
        $('#list_images_property').carouFredSel({
            auto: false,
            responsive: true,
            width: '100%',
            prev: '#prev2',
            next: '#next2',
            mousewheel: true,
            swipe: {
                onMouse: true,
                onTouch: true
            },
            scroll: 2,
            items: {
                width: 200,
                //	height: '30%',	//	optionally resize item-height
                visible: {
                    min: 2,
                    max: 6
                }
            }
        });
        $('#list_images_property a').click(function(){
            $('.preloader').show(0).delay(4000).fadeOut("slow");
            var bigImage = $(this).data("bigimage");
            var bigTitle = $(this).data("title");
            var bigDesc = $(this).data("desc");
            $(".big-image .desc-image h3").text(bigTitle);
            $(".big-image .desc-image p").text(bigDesc);
            $("#big-image-preview").attr('src', bigImage) 
            console.log(bigTitle);
            return false; 
        });
    }
    
}


function init_grid(){
    $(".grid-item").gridalicious({
        width: 250,
        gutter: 10,
        animate: true,
        effect: 'fadeInOnAppear'
    }); 
    
    $(".grid-galeries").gridalicious({
        width: 240,
        gutter: 10,
        animate: true,
        effect: 'fadeInOnAppear'
    });
}



function init_tabs(){
    $('.bot-tab a').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    });
}


//================================
//function SetCookie 
//================================
function setCookie(c_name,value,exdays)
{
    var exdate=new Date();
    exdate.setDate(exdate.getDate() + exdays);
    var c_value=escape(value) + ((exdays==null) ? "" : "; expires="+exdate.toUTCString());
    document.cookie=c_name + "=" + c_value;
}

function getCookie(c_name)
{
    var c_value = document.cookie;
    var c_start = c_value.indexOf(" " + c_name + "=");
    if (c_start == -1)
    {
        c_start = c_value.indexOf(c_name + "=");
    }
    if (c_start == -1)
    {
        c_value = null;
    }
    else
    {
        c_start = c_value.indexOf("=", c_start) + 1;
        var c_end = c_value.indexOf(";", c_start);
        if (c_end == -1)
        {
            c_end = c_value.length;
        }
        c_value = unescape(c_value.substring(c_start,c_end));
    }
    return c_value;
}


