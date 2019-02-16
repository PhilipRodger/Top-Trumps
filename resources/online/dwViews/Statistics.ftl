<html>


	<head>
		<!-- Web page title -->
    	<title>Top Trumps</title>
    	
    	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
    	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

		<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
		<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css">
    	<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
    	<script>vex.defaultOptions.className = 'vex-theme-os';</script>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css"/>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css"/>
    	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

	</head>

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->
        <!--CSS for statistics screen-->
    	<style>
                .footer {
                    position: absolute;
                right: 0;
                bottom: 50;
                left: 0;
                    font: italic;
                    text-align: center;
                }
                .row.bottom {
				
                position:absolute;
                right: 0;
                bottom: 100;
                left: 150;
			}
            table, th, td {
                    text-align: center;
            }
            
        </style>
    	<div class="container">
		<table class="table" id="table1">
  <thead>
    <tr>
      <th scope="col">Number of Games</th>
      <th scope="col">Number of Human Wins</th>
      <th scope="col">Number of AI Wins</th>
      <th scope="col">Average Draws Per Game</th>
      <th scope="col">Longest Game</th>
    </tr>
  </thead>
  <tbody>
    <tr>
        <td><strong id="totalGames"></strong></td>
        <td><strong id="humanWins"></strong></td>
        <td><strong id="AIWins"></strong></td>
        <td><strong id="averageDraws"></strong></td>
        <td><strong id="longestGame"></strong></td>
    </tr>
  </tbody>
</table>
        </div>
        <div class="row bottom">
                <div class="col"></div>
                <div class="col centerButtons">
                    <a class="btn btn-lg btn-light" href="/toptrumps/" id="homeButton">Back to Game Selection Screen</a>
                </div>
                <div class="col"></div>
            </div>
        
        <div class="footer">Designed and Engineered by The Dabbin' Cavern in Glasgow, Scotland
            </div>
		
		<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {
			
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				$("#table1").hide().fadeToggle(1000);
				totalGames();
                humanWins();
                AIWins();
                averageDraws();
                longestGame();
				
				
			}
			
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------

            /**
            * Method for getting total games played from database
            **/
		function totalGames() {
            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/totalGames");
            if (!xhr) {
                alert("CORS is not supported");
            }
            xhr.onload = function(e) {
                var responseText = JSON.parse(xhr.response);
                $('#totalGames').text(parseInt(responseText));
            };
            xhr.send();
        }

         /**
            * Method for getting total human wins from database
            **/
		function humanWins() {
            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/humanWins");
            if (!xhr) {
                alert("CORS is not supported");
            }
            xhr.onload = function(e) {
                var responseText = JSON.parse(xhr.response);
                $('#humanWins').text(parseInt(responseText));
            };
            xhr.send();
        }

         /**
            * Method for getting total AI wins from database
            **/
		function AIWins() {
            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/AIWins");
            if (!xhr) {
                alert("CORS is not supported");
            }
            xhr.onload = function(e) {
                var responseText = JSON.parse(xhr.response);
                $('#AIWins').text(parseInt(responseText));
            };
            xhr.send();
        }

         /**
            * Method for getting average draws per game from database
            **/
		function averageDraws() {
            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/averageDraws");
            if (!xhr) {
                alert("CORS is not supported");
            }
            xhr.onload = function(e) {
                var responseText = JSON.parse(xhr.response);
                $('#averageDraws').text(parseInt(responseText));
            };
            xhr.send();
        }

         /**
            * Method for getting longest game played from database
            **/
		function longestGame() {
            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/longestGame");
            if (!xhr) {
                alert("CORS is not supported");
            }
            xhr.onload = function(e) {
                var responseText = JSON.parse(xhr.response);
                $('#longestGame').text(parseInt(responseText));
            };
            xhr.send();
        }
			// This is a reusable method for creating a CORS request. Do not edit this.
			function createCORSRequest(method, url) {
  				var xhr = new XMLHttpRequest();
  				if ("withCredentials" in xhr) {

    				// Check if the XMLHttpRequest object has a "withCredentials" property.
    				// "withCredentials" only exists on XMLHTTPRequest2 objects.
    				xhr.open(method, url, true);

  				} else if (typeof XDomainRequest != "undefined") {

    				// Otherwise, check if XDomainRequest.
    				// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
    				xhr = new XDomainRequest();
    				xhr.open(method, url);

 				 } else {

    				// Otherwise, CORS is not supported by the browser.
    				xhr = null;

  				 }
  				 return xhr;
			}
		
		</script>
		
		<!-- Here are examples of how to call REST API Methods -->
		<script type="text/javascript">
		
		
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloJSONList() {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloJSONList"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloWord(word) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloWord?Word="+word); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}

		</script>
		
		</body>
</html>