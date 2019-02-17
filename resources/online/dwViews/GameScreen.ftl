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
    	  <!--CSS for Game Screen-->
            <style>
                    .footer {
                        position: absolute;
                    right: 0;
                    bottom: 10;
                    left: 0;
                        font: italic;
                        text-align: center;
                    }
                </style>
    	<div class="container">
<br/>
		<div id="playing-cards" class="container">
<div class="card-deck">
        <div class="card border-success" style="width: 18rem;">
            <div class="card-header text-center text-white bg-success mb-3" style="max-width: 18rem;">
                <h5>Player</h5>
                <p id="numCard1"></p>
            </div>
            <div class="card-name text-center">
                    <h5 id="cardName1"></h5>
                </div>
                <div class="card-body">
                  
                  <p class="card-attributes">
                      <ul>
                        <li id="attributeA1"></li>
                        <li id="attributeA2"></li>
                        <li id="attributeA3"></li>
                        <li id="attributeA4"></li>
                        <li id="attributeA5"></li>
                      </ul>
                  </p>
                </div>
              </div>

              <div class="card border-danger" style="width: 18rem;">
                    <div class="card-header text-center text-white bg-danger mb-3" style="max-width: 18rem;">
                        <h5>AI Player 1</h5>
                        <p id="numCard2"></p>
                    </div>
                    <div class="card-name text-center">
                        <h5 id="cardName2"></h5>
       
                        </div>
                        <div class="card-body">
                          
                          <p class="card-attributes">
                              <ul>
                                  <li id="attributeB1"></li>
                        <li id="attributeB2"></li>
                        <li id="attributeB3"></li>
                        <li id="attributeB4"></li>
                        <li id="attributeB5"></li>
                              </ul>
                          </p>
                        </div>
                      </div>

                      <div class="card border-danger" style="width: 18rem;">
                            <div class="card-header text-center text-white bg-danger mb-3" style="max-width: 18rem;">
                                <h5>AI Player 2</h5>
                                <p id="numCard3"></p>
                            </div>
                            <div class="card-name text-center">
                                <h5 id="cardName3"></h5>
                                </div>
                                <div class="card-body">
                                  
                                  <p class="card-attributes">
                                      <ul>
                                         <li id="attributeC1"></li>
                        <li id="attributeC2"></li>
                        <li id="attributeC3"></li>
                        <li id="attributeC4"></li>
                        <li id="attributeC5"></li>
                                      </ul>
                                  </p>
                                </div>
                              </div>

                              <div class="card border-danger" style="width: 18rem;">
                                    <div class="card-header text-center text-white bg-danger mb-3" style="max-width: 18rem;">
                                        <h5>AI Player 3</h5>
                                        <p id="numCard4"></p>
                                    </div>
                                    <div class="card-name text-center">
                                        <h5 id="cardName4"></h5>
                                        </div>
                                        <div class="card-body">
                                          
                                          <p class="card-attributes">
                                              <ul>
                                                <li id="attributeD1"></li>
                        <li id="attributeD2"></li>
                        <li id="attributeD3"></li>
                        <li id="attributeD4"></li>
                        <li id="attributeD5"></li>
                                              </ul>
                                          </p>
                                        </div>
                                      </div>

                                      <div class="card border-danger" style="width: 18rem;">
                                            <div class="card-header text-center text-white bg-danger mb-3" style="max-width: 18rem;">
                                                <h5>AI Player 4</h5>
                                                <p id="numCard5"></p>
                                            </div>
                                            <div class="card-name text-center">
                                                    <h5 id="cardName5"></h5>
                                                </div>
                                                <div class="card-body">
                                                  
                                                  <p class="card-attributes">
                                                      <ul>
                                                          <li id="attributeE1"></li>
                        <li id="attributeE2"></li>
                        <li id="attributeE3"></li>
                        <li id="attributeE4"></li>
                        <li id="attributeE5"></li>
                                                      </ul>
                                                  </p>
                                                </div>
                                              </div>
</div>
</div><br/><br/><br/>
<div class="mx-auto" style="width: 200px;">
        <div class="card border-primary mb-3" style="width: 18rem;">
                <div class="card-header text-center text-white bg-primary mb-3" style="max-width: 18rem;">
                    <h5>The active player is </h5>
                </div>
                <div class="card-body text-center">
                        <h5>Game Announcement</h5>
                    </div>
                  </div><br/><br/>
      </div>	
		
		</div>
		
		<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {
				newGame();
                player1Card();
                player2Card();
                player3Card();
                player4Card();
                player5Card();
                
			
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				
				// For example, lets call our sample methods
			//	helloJSONList();
			//	helloWord("Student");
				
			}
			
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------
			
			//----Global Variables----//
			
			
			var roundNum;
			var numOfPlayers;
			var categorySelected;
			
		
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
			
			 /**
             * start the game data request
             * */
            function newGame() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/newGame"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                };
                xhr.send();
            }
                  
                  function player1Card() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/response"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    document.getElementById('numCard1').innerHTML="Cards: " +JSON.stringify(responseText.playersToJson[0].numberOfCards);
                    document.getElementById('cardName1').innerHTML = JSON.stringify(responseText.playersToJson[0].cardName);
                    document.getElementById('attributeA1').innerHTML ="Size: "+ JSON.stringify(responseText.playersToJson[0].size);
                    document.getElementById('attributeA2').innerHTML ="Speed: "+ JSON.stringify(responseText.playersToJson[0].speed);
                    document.getElementById('attributeA3').innerHTML ="Range: "+ JSON.stringify(responseText.playersToJson[0].range);
                    document.getElementById('attributeA4').innerHTML ="Firepower: "+ JSON.stringify(responseText.playersToJson[0].firepower);
                    document.getElementById('attributeA5').innerHTML ="Cargo: "+ JSON.stringify(responseText.playersToJson[0].cargo);
                };
                xhr.send();
            }

            function player2Card() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/response"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    document.getElementById('numCard2').innerHTML="Cards: " +JSON.stringify(responseText.playersToJson[1].numberOfCards);
                    document.getElementById('cardName2').innerHTML = JSON.stringify(responseText.playersToJson[1].cardName);
                    document.getElementById('attributeB1').innerHTML ="Size: "+ JSON.stringify(responseText.playersToJson[1].size);
                    document.getElementById('attributeB2').innerHTML ="Speed: "+ JSON.stringify(responseText.playersToJson[1].speed);
                    document.getElementById('attributeB3').innerHTML ="Range: "+ JSON.stringify(responseText.playersToJson[1].range);
                    document.getElementById('attributeB4').innerHTML ="Firepower: "+ JSON.stringify(responseText.playersToJson[1].firepower);
                    document.getElementById('attributeB5').innerHTML ="Cargo: "+ JSON.stringify(responseText.playersToJson[1].cargo);
                };
                xhr.send();
            }

            function player3Card() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/response"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    document.getElementById('numCard3').innerHTML="Cards: " +JSON.stringify(responseText.playersToJson[2].numberOfCards);
                    document.getElementById('cardName3').innerHTML = JSON.stringify(responseText.playersToJson[2].cardName);
                    document.getElementById('attributeC1').innerHTML ="Size: "+ JSON.stringify(responseText.playersToJson[2].size);
                    document.getElementById('attributeC2').innerHTML ="Speed: "+ JSON.stringify(responseText.playersToJson[2].speed);
                    document.getElementById('attributeC3').innerHTML ="Range: "+ JSON.stringify(responseText.playersToJson[2].range);
                    document.getElementById('attributeC4').innerHTML ="Firepower: "+ JSON.stringify(responseText.playersToJson[2].firepower);
                    document.getElementById('attributeC5').innerHTML ="Cargo: "+ JSON.stringify(responseText.playersToJson[2].cargo);
                };
                xhr.send();
            }

            function player4Card() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/response"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    document.getElementById('numCard4').innerHTML="Cards: " +JSON.stringify(responseText.playersToJson[3].numberOfCards);
                    document.getElementById('cardName4').innerHTML = JSON.stringify(responseText.playersToJson[3].cardName);
                    document.getElementById('attributeD1').innerHTML ="Size: "+ JSON.stringify(responseText.playersToJson[3].size);
                    document.getElementById('attributeD2').innerHTML ="Speed: "+ JSON.stringify(responseText.playersToJson[3].speed);
                    document.getElementById('attributeD3').innerHTML ="Range: "+ JSON.stringify(responseText.playersToJson[3].range);
                    document.getElementById('attributeD4').innerHTML ="Firepower: "+ JSON.stringify(responseText.playersToJson[3].firepower);
                    document.getElementById('attributeD5').innerHTML ="Cargo: "+ JSON.stringify(responseText.playersToJson[3].cargo);
                };
                xhr.send();
            }

            function player5Card() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/response"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    document.getElementById('numCard5').innerHTML="Cards: " +JSON.stringify(responseText.playersToJson[4].numberOfCards);
                    document.getElementById('cardName5').innerHTML = JSON.stringify(responseText.playersToJson[4].cardName);
                    document.getElementById('attributeE1').innerHTML ="Size: "+ JSON.stringify(responseText.playersToJson[4].size);
                    document.getElementById('attributeE2').innerHTML ="Speed: "+ JSON.stringify(responseText.playersToJson[4].speed);
                    document.getElementById('attributeE3').innerHTML ="Range: "+ JSON.stringify(responseText.playersToJson[4].range);
                    document.getElementById('attributeE4').innerHTML ="Firepower: "+ JSON.stringify(responseText.playersToJson[4].firepower);
                    document.getElementById('attributeE5').innerHTML ="Cargo: "+ JSON.stringify(responseText.playersToJson[4].cargo);
                };
                xhr.send();
            }

			
			
			
			function startRound () {
				
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/startRound")
						if(!xhr){
					  		alert("CORS not supported");
						}
						xhr.send();
			}
				
						
						
			function resolveRound () {
							
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/resolveRound")
						if(!xhr){
							alert("CORS not supported");
									}
					xhr.send();
			
			}
			
			function getRoundNumber(){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getRoundNumber");
				if(!xhr){
			  		alert("CORS not supported");
		  		}
				xhr.send();
				xhr.onload = function(e){
			  		roundNumber =  xhr.response;
			  	}
			} 
				
				
			function getRoundWinner(){
					//  create a CORS request, this is the message we are going to send (a get request in this case)
					var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getRoundWinner"); // Request type and URL+parameters
					// Message is not sent yet, but we can check that the browser supports CORS
					if (!xhr) {
						alert("CORS not supported");
					}
					
					// We have done everything we need to prepare the CORS request, so send it
					xhr.send()
					
					xhr.onload = function(e){
				  		document.getElementById("(insert element you want)").innerHTML = "" + roundNum + ":  " + xhr.response;
				  	}
			} 
				
			function numberOfPlayers() {
		                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/numOfPlayers"); // Request type and URL
		                if (!xhr) {
		                    alert("CORS not supported");
		                }
		                xhr.onload = function(e) {
		                    var responseText = JSON.parse(xhr.response); // the text of the response
		                    numOfPlayers = parseInt(responseText[0]);
						}
			}
		                    
		                    
		                    
		     function namesOfPlayers() {
		              var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/namesOfPlayers"); // Request type and URL
		                    if (!xhr) {
		                            alert("CORS not supported");
		                        }
		                    xhr.onload = function() {
		                           var  responseText = JSON.parse(xhr.response); // the text of the response
		                            var n = parseInt(responseText[0]); //number of players
		                            for (var i=1; i<(n+1); i++) {
		                                $(".nameOfPlayer"+i).text(responseText[i]);
		                            }
		                        };
		                        xhr.send();
		                    }
					
		    function activePlayer() {
		                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/activePlayer"); // Request type and URL
		                if (!xhr) {
		                    alert("CORS not supported");
		                }
		                xhr.onload = function(e) {
		                    var responseText = JSON.parse(xhr.response); // the text of the response
		                    $("p").parent().removeClass("active");
		                    console.log("response old active player: " + activePlayerVar);
		                    activePlayerVar = responseText;
		                    $("p:contains('"+ activePlayerVar +"')").parent().toggleClass("active");
		                    console.log("response active player: " + activePlayerVar);
						}
			}
		                    
		                    
		    function cardCatNames() {
		                 var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/cardCat"); // Request type and URL
		                 if (!xhr) {
		                 alert("CORS not supported");
		                        }
		                 xhr.onload = function(e) {
		                 var responseText = JSON.parse(xhr.response); // the text of the response
		                 for(var i=0; i<responseText.length; i++) {
		                 $("#nameOfCat"+(i+1)).text(responseText[i]);
		                 $("#nameOfCat"+(i+1)+"Btn").text(responseText[i]);
		                            }
		                        };
		                        xhr.send();
		                    }
		        		   
		      function getCardValues() {
		                       var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getCardValues"); // Request type and URL
		                       if (!xhr) {
		                           alert("CORS not supported");
		                       }
		                       xhr.onload = function(e) {
		                           var responseText = JSON.parse(xhr.response); // the text of the response
		                           for (var i=0; i<responseText.length; i++) {
		                               $("#cat"+(i+1)+"Value").text(parseInt(responseText[i]));
		                           }
		                       };
		                       xhr.send();
		                   }
				
		                
		    function setCategory(clicked_id) {
				      document.getElementById('Btn')
				        categorySelected = clicked_id;
				          console.log("this clicked id is: " + categorySelected);
			}
		   
		            	
		    function getchosenCategory() {
		         var xhr = createCORSRequest('PUT', "http://localhost:7777/toptrumps/categorySelection"); // Request type and URL+parameters
		                if (!xhr) {
		                        alert("CORS not supported");
		                    }
		                    xhr.onload = function(e) {
		                        var responseText = xhr.response; // the text of the response
		                        $('#chosenCategory').text(responseText); //change user interface with chosen category
		                    };
		                    xhr.send();
		                }        	
		            	
		    
		    function communityPileSize() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/communityPileSize"); // Request type and URL
                	if (!xhr) {
                		alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    $('#communityPileSize').text(parseInt(responseText[0]));
                };
                xhr.send();
			}            	
		</script>
		</body>
</html>