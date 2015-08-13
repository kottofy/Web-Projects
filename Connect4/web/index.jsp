<%--
    Document   : index.jsp
    Date       : May 2, 2011
    Author     : kottofy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Interactive Connect-4 Game</title>

        <style type="text/css">
            TD {width:50px; height:50px;background-color: white}
            BODY {font-family: Arial; background-color:lightyellow}
            .hidden {visibility:hidden}
            .visible {visibility:visible}
        </style>

        <script type="text/javascript" src="jquery.js"></script>

        <script type="text/javascript">
            var blackCircleURL = "img/blackChecker.jpg";
            var redCircleURL   = "img/redChecker.jpg";
            var whiteImgURL    = "img/whitecell.GIF";
            var ajaxCount      = 0;
            var pollInterval   = 4000;
            var challenge;
            var move;
            var player;
            
            //            var jqxhr = $.ajax({ url: "example.php" })
            //            .success(function() { alert("success"); })
            //            .error(function() { alert("error"); })
            //            .complete(function() { alert("complete"); });

            /*
             * Send status request to GameXMLServlet
             */
            function poll(){
                //                window.returnValue = false;
                //                window.close();
            }

            /*
             *Update page content from ajax response
             */
            function handleAjaxResponse(data){
                
            }

            function handleError(jqXHR, textStatus, errorThrown){
                alert("error: "+textStatus);
            }

            /*
             * Begin polling
             */
            function beginPolling() {
                window.setInterval(poll, pollInterval);
            }

            //function to accept a challenge
            function acceptChallenge()
            {
                player = $("#playerField").val();

                $.ajax
                (
                {
                    type: 'POST',
                    url: "Connect4",
                    context: document.body,
                    data: "player="+player,
                    success: acceptSuccess
                }
            );
            }
 
            function acceptSuccess(data)
            {
                $('#player').html(
                $(data).find('playerName').text());

                $('#issueButton').hide();
                $('#acceptButton').hide();
                $('#playerField').hide();

                $('#challenger0').html(
                $(data).find('challenger0Name').text());
                $('#acceptButton0').attr("class", "visible");

                $('#challenger1').html(
                $(data).find('challenger1Name').text());
                $('#acceptButton1').attr("class", "visible");

                $('#challenger2').html(
                $(data).find('challenger2Name').text());
                $('#acceptButton2').attr("class", "visible");
            }

            //function to issue a challenge to a challenger
            function issueChallenge()
            {
                player = $("#playerField").val();

                $.ajax
                (
                {
                    type: 'POST',
                    url: "Connect4",
                    context: document.body,
                    data: "player="+player,
                    success: issueSuccess
                }
            );

                //Step 1: enter player name, submit ==> challenger
                //Step 2: pole to see if game is accepted
                //Also, display challengers
                //if challenger is accepted
                //player becomes responder
                //delete created game
                //Step 3: play on!

            }

            function issueSuccess(data)
            {
                $('#player').html(
                $(data).find('playerName').text());

                $('#issueButton').hide();
                $('#acceptButton').hide();
                $('#playerField').hide();
            }

            //function to send a move
            function sendMove(move)
            {
                //get player...color

                //if move is okay
                //commit to database
                //else
                //reject move
            }

            //player
            function acceptGame0()
            {
                challenger0 = $("#challenger0").val();

                $.ajax
                (
                {
                    type: 'GET',
                    url: "Connect4",
                    context: document.body,
                    data: "challenger0="+challenger0,
                    success: acceptGame0Success
                }
            );
            }
            
            function acceptGame0Success(data)
            {
                $('#challenger0').html(
                $(data).find('challenger0Name').text());

                $('challenger1').hide();
                $('challenger2').hide();

                $('#acceptButton1').attr("class", "hide");
                $('#acceptButton2').attr("class", "hide");
            }

            function acceptGame1()
            {
                //challenger0 = $("#challenger0").val();

                $.ajax
                (
                {
                    type: 'GET',
                    url: "Connect4",
                    context: document.body,
                    //data: "challenger0="+challenger0,
                    success: acceptGame1Success
                }
            );
            }
            

            function acceptGame2()
            {

            }


            // $(document).ready(beginPolling);
            $(document).ready(poll);
        </script>

    </head>
    <body>
        <h1>Connect-4 Game</h1>
        <div style="float:left; padding-right:50px">
            <p style="color:green;font-weight:bold">Challengers:</p>
            <p>
                <span id="challenger0"></span>
                <input class="hidden" type ="button" id="acceptButton0" value="Accept" onClick="acceptGame0()"><br/>

                <span id="challenger1"></span>
                <input class="hidden" type ="button" id="acceptButton1" value="Accept" onClick="acceptGame1()"><br/>

                <span id="challenger2"></span>
                <input class="hidden" type ="button" id="acceptButton2" value="Accept" onClick="acceptGame2()"><br/>

                Your player name:<br/>
                <input type="text" size="12" id="playerField"><br/>

                <span id="player"></span>
                <input type="button" value="Issue Challenge" onClick="issueChallenge()" id="issueButton" >
                <input type="button" value="Accept Challenge" onClick="acceptChallenge()" id="acceptButton" >
            </p>
        </div>
        <p> <b>Your opponent:</b><span id="opponent">No opponent yet</span><br/>
            <b>Your color:</b> <span id="color"> </span><br>
            <b>Next move:</b> <span id="nextMove"> </span><br>
            <b>Game status: </b> <span id="status">Initializing</span><br></p>

        <table border="1">
            <thead>

            </thead>
            <tbody>
                <tr>
                    <!--<td> <img id="cell_5_0" src="" width="48" height="48"></td>
                    <td><img id="cell_5_1" src="" width="48" height="48"></td>-->
                    <td id="cell_5_0"><img src="whitecell.GIF" width="48" height="48"></td>
                    <td id="cell_5_1"><img id="cell_5_1" src="whitecell.GIF" width="48" height="48"></td>
                    <td id="cell_5_2"><img id="cell_5_2" src="whitecell.GIF" width="48" height="48"></td>
                    <td id="cell_5_3"><img id="cell_5_0" src="whitecell.GIF" width="48" height="48"></td>
                    <td id="cell_5_4"><img id="cell_5_0" src="whitecell.GIF" width="48" height="48"></td>
                    <td id="cell_5_5"><img id="cell_5_0" src="whitecell.GIF" width="48" height="48"></td>
                    <td id="cell_5_6"><img id="cell_5_0" src="whitecell.GIF" width="48" height="48"></td>
                </tr>
                <tr>
                    <td id="cell_4_0"></td>
                    <td id="cell_4_1"></td>
                    <td id="cell_4_2"></td>
                    <td id="cell_4_3"></td>
                    <td id="cell_4_4"></td>
                    <td id="cell_4_5"></td>
                    <td id="cell_4_6"></td>
                </tr>
                <tr>
                    <td id="cell_3_0"></td>
                    <td id="cell_3_1"></td>
                    <td id="cell_3_2"></td>
                    <td id="cell_3_3"></td>
                    <td id="cell_3_4"></td>
                    <td id="cell_3_3"></td>
                    <td id="cell_3_6"></td>
                </tr>
                <tr>
                    <td id="cell_2_0"></td>
                    <td id="cell_2_1"></td>
                    <td id="cell_2_2"></td>
                    <td id="cell_2_3"></td>
                    <td id="cell_2_4"></td>
                    <td id="cell_2_2"></td>
                    <td id="cell_2_6"></td>
                </tr>
                <tr>
                    <td id="cell_1_0"></td>
                    <td id="cell_1_1"></td>
                    <td id="cell_1_2"></td>
                    <td id="cell_1_3"></td>
                    <td id="cell_1_4"></td>
                    <td id="cell_1_1"></td>
                    <td id="cell_1_6"></td>
                </tr>
                <tr>
                    <td id="cell_0_1"></td>
                    <td id="cell_0_2"></td>
                    <td id="cell_0_2"></td>
                    <td id="cell_0_3"></td>
                    <td id="cell_0_4"></td>
                    <td id="cell_0_5"></td>
                    <td id="cell_0_6"></td>
                </tr>
                <tr>
                    <td><input type="button" value="Play" onClick="sendMove(0)"></td>
                    <td><input type="button" value="Play" onClick="sendMove(1)"></td>
                    <td><input type="button" value="Play" onClick="sendMove(2)"></td>
                    <td><input type="button" value="Play" onClick="sendMove(3)"></td>
                    <td><input type="button" value="Play" onClick="sendMove(4)"></td>
                    <td><input type="button" value="Play" onClick="sendMove(5)"></td>
                    <td><input type="button" value="Play" onClick="sendMove(6)"></td>
                </tr>
            </tbody>
        </table>

        <p>Number of polling requests sent: <span id="pollCount"></span>
    </body>
</html>
