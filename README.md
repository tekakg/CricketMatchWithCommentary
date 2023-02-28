# CricketMatchWithCommentary
# Postman commands: 
1)http://localhost:8083/match-info
body : {   
   "overs": 20,
   "playerCount":4,
   "team1Name": "Australia",
   "team2Name": "India",
   "team1Players" : [1,4,3,7],
   "team2Players" : [11,22,30,6]
}

2)http://localhost:8083/set-player-info
body: {   
  "name":"yusuf pathan",
  "role":"batsman"
}

3)http://localhost:8083/
Lets Start the Match

4)http://localhost:8083/scoreboard/16
It returns the scoreboard of a particular match corresponding to the matchId.

5)http://localhost:8083/match-history/16
It returns the match Details along with ball by ball commentary corresponding to the matchId.

6)http://localhost:8083/team1/16
It returns all information about the team corresponding to the matchId.

7)http://localhost:8083/player-info-using-name/aditya
It returns all the information regarding the player using playername.

8)http://localhost:8083/player-info-using-role/batsman
It will return all the players whose role is batsman using playerrole.


