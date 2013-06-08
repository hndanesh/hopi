<!DOCTYPE html>
<html>
    <head>
        <title>Hopi - Carpool 2.0</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="keywords" content="Real Estate Template" />
        <meta name="description" content="Proper - Real Estate Responsive HTML5 Template">
        <meta name="author" content="yobio.indiewebstyle.com">
        <!-- Bootstrap -->
        <link href="/styles/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="/styles/bootstrap-responsive.min.css" rel="stylesheet">
        <link href="/styles/proper/style.css" rel="stylesheet" media="screen">
        <link href="/scripts/proper/colorbox/colorbox.css" rel="stylesheet" media="screen">
        <!-- FONTAWESOME STYLE -->
        <link rel="stylesheet" href="/styles/proper/font/FortAwesome/css/font-awesome.css"/>

        <link rel="shortcut icon" href="/images/logo.png">
        <script type="text/javascript"
				src="https://maps.googleapis.com/maps/api/js?v=3.exp&key=AIzaSyBoEiR3oXox0_yzyCDMiPcmornfZInH-U8&sensor=false&libraries=places">
        <script type="text/javascript"></script>
    </head>
    <body >
        <!-- Start Wrapper-->
        <div id="wrapper">
            <div id="header-wrapper">
                <div class="container">
                    <div class="panel">
                    </div>
					<div class="span9 ">
                        <a class="brand" href="index.html" ><h1 class="font-caly"><img src="images/logo.png" width="70" alt="logo"> Hopi 
                    </div>
					<div >
                        <div class="language_curency" style="display:none"> <!-- &nbsp;--> 
							lang: 
							<a href="#">En</a> 
							| 
							<a href="#">Ind</a> 
							| 
							<a href="#">MY</a>
							|
							<a href="#">Chi</a>
						</div>
                    </div>
                </div>
            </div><!-- End header-wrapper -->	
            <!-- start Navigation -->
            

            <!--Start Content-->
            <div id="content">
                <div class="container">
                    <div class="row-fluid">
                        <div class="span9">
                            <div class="panel right-line">
                                <div class="row-fluid">
                                    <div class="span6">
                                         
                                    </div>
                                </div>
                                <div class="googleMaparea right-space">
                                    <div id="googleMap" class=""></div>
                                </div>
                            </div>
                        </div>
                        <div class="span3">
                            <div id="searchLocationPanel" class="panel" >
                                <!--Start Form Search Location-->
                                <h3 class="main-heading bottom-line line-before">
									<span class="main-circle-icon">
									<i class="icon-search"></i>
									</span>  Find Location
								</h3>   
                                    <label>From: </label>
                                    <input id="from" type="text" />
                                    <label>To: </label>
                                    <input id="to" type="text" />
									<label>&nbsp</label>
                                    <button type="submit" class="input-block-level btn-proper btn btn-large" onclick="javascript:submit()"> Search</button>
                                <!--End Form Search Properties-->
                            </div>
                            <!--Get Result Search 
                            <div  class="panel"  overflow:auto; height:300px;" >
                            	<h3 class="main-heading bottom-line line-before">Driver List</h3>
                            </div>-->
                        </div>
                    </div>
                </div>
            </div>

            <!-- start Footer -->
            <div id="footer-wrapper">
		<!-- start footer-ribon -->
		<div class="container" >
		  <div class="footer-ribon">
		    <span>Get in Touch</span>
		  </div>
		</div><!-- end footer-ribon -->
                <div id="footer-top">
                    <div class="container">
                        <div class="row-fluid">
                            <div class="span3">
                                <div class="panel">
                                    <h4><i class="icon-time"></i> Contact</h4>
                                    <p>Nullam justo nunc, dignissim at convallis posuere, sodales eu orci.</p>
                                    <ul class="unstyled">
                                        <li><i class="icon-home"></i> 123, Los Angeles, CA, 54321</li>
                                        <li><i class="icon-phone"></i> +239-3823-3434</li>
                                        <li><i class="icon-envelope"></i> someone@company.com</li>
                                    </ul>


                                    <a href="#" class="btn-proper btn"><i class="icon-facebook"></i></a>
                                    <a href="#" class="btn-proper btn"><i class="icon-twitter"></i></a>
                                    <a href="#" class="btn-proper btn"><i class="icon-google-plus"></i></a>
                                    <a href="#" class="btn-proper btn"><i class="icon-pinterest"></i></a>


                                </div>
                            </div>
                            <div class="span3">
                                <div class="panel">
                                    <h4><i class="icon-suitcase"> </i> Pages Link</h4>
                                    <ul class="unstyled list-link">
                                        <li><a href="#">Condimentum - Condimentum gravida</a></li>
                                        <li><a href="#">Etiam at - Condimentum gravida</a></li>
                                        <li><a href="#">Fusce vel - Condimentum gravida</a></li>
                                        <li><a href="#">Vivamus - Condimentum gravida</a></li>
                                        <li><a href="#">Pellentesque - Condimentum gravida</a></li>
                                        <li><a href="#">Fusce vel - Condimentum gravida</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="span3">
                                <div class="panel">
                                    <h4><i class="icon-screenshot"></i> Subscribe</h4>

                                    <form >
                                        <label>Full Name</label>
                                        <input type="text" name="" value="" placeholder="Enter Your Full Name" required=""/>
                                        <label>Email</label>
                                        <input type="email" name="" value="" placeholder="Enter Your Email" required=""/>
                                        <div>
                                            <button class="btn-proper btn">Send</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="span3">
                                <div class="panel">
                                    <h4><i class="icon-trophy"></i> Recent News</h4>
                                    <ul class="unstyled list-link">
                                        <li><a href="#">Sed eu leo orci, condimentum gravida metus</a></li>
                                        <li><a href="#">Etiam at nulla ipsum, in rhoncus purus</a></li>
                                        <li><a href="#">Fusce vel magna faucibus felis dapibus facilisis</a></li>
                                        <li><a href="#">Vivamus scelerisque dui in massa</a></li>
                                        <li><a href="#">Pellentesque eget adipiscing dui semper</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="back-to-top" class="text-center"><a href="#"><i class="icon-angle-up"></i></a></div>
                </div>
                <div id="footer-twit" class="text-center">
                    <div class="container">
                        <div class="panel">
                            <p><i class="icon-twitter icon-4x"></i> <i class="icon-quote-left qoute"></i> Vivamus diam diam, fermentum sed dapibus eget, egestas sed eros. Lorem fermentum ipsum dolor sit amet, ipsum dolor sit amet ... <i class="icon-quote-right qoute"></i></p>
                        </div>
                    </div>
                </div>
                <div id="footer" class="text-center">
                    <div class="panel">
                        <p>Copyright &copy; <a href="#">Hopi</a> - <a href="index.html">Home</a> | <a href="about_us.html">About Us</a> | <a href="faq.html">FAQ</a> | <a href="contact.html">Contact Us</a></p>
                    </div>
                </div>
            </div><!-- end  Footer -->
        </div><!-- End Wrapper-->

        <!-- Defaut Js Bootsrap
         ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="assets/js/jquery.js"></script>
        <script src="assets/js/bootstrap-transition.js"></script>
        <script src="assets/js/bootstrap-alert.js"></script>
        <script src="assets/js/bootstrap-modal.js"></script>
        <script src="assets/js/bootstrap-dropdown.js"></script>
        <script src="assets/js/bootstrap-scrollspy.js"></script>
        <script src="assets/js/bootstrap-tab.js"></script>
        <script src="assets/js/bootstrap-tooltip.js"></script>
        <script src="assets/js/bootstrap-popover.js"></script>
        <script src="assets/js/bootstrap-button.js"></script>
        <script src="assets/js/bootstrap-collapse.js"></script>
        <script src="assets/js/bootstrap-carousel.js"></script>
        <script src="assets/js/bootstrap-typeahead.js"></script>


        <!-- Grid -->
        <script src="scripts/proper/Grid-A-Licious-master/jquery.grid-a-licious.min.js"></script>

        <!-- Slider -->
        <script src="scripts/proper/carouFredSel-6.2.1/jquery.carouFredSel-6.2.1-packed.js"></script>
        <!-- optionally include helper plugins carouFredSel -->
        <script src="scripts/proper/carouFredSel-6.2.1/helper-plugins/jquery.mousewheel.min.js"></script>
        <script src="scripts/proper/carouFredSel-6.2.1/helper-plugins/jquery.touchSwipe.min.js"></script>
        <script src="scripts/proper/carouFredSel-6.2.1/helper-plugins/jquery.transit.min.js"></script>
        <script src="scripts/proper/carouFredSel-6.2.1/helper-plugins/jquery.ba-throttle-debounce.min.js"></script>

        <!-- JS GMAP3  -->
        
        <script src='scripts/proper/gmap3/gmap3.min.js'></script>

        <script src="scripts/proper/colorbox/jquery.colorbox-min.js"></script>
        <script src="scripts/proper/proper.js"></script>
    </body>
</html>
