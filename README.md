**Mancala**

Mancala refers to a family of two-player turn-based strategy board games played with small stones, beans, or seeds and rows of holes or pits in the earth, a board or other playing surface. The objective is usually to capture all or some set of the opponent's pieces.
([Wikipedia](https://en.wikipedia.org/wiki/Mancala))

**Usage**

To run and test the game, you can reach the swagger using following link:

> http://localhost:8080/swagger-ui/index.html

* To create the game, you can use `POST /game` API. In return, you could get game id in the format of UUID.

```json
{
   "id":"f6b29046-8bbc-4c4b-b323-fa787c23e7b3",
   "board":{
      "pits":[
         {
            "index":0,
            "stones":6
         },
         {
            "index":1,
            "stones":6
         },
         {
            "index":2,
            "stones":6
         },
         {
            "index":3,
            "stones":6
         },
         {
            "index":4,
            "stones":6
         },
         {
            "index":5,
            "stones":6
         },
         {
            "index":6,
            "stones":0
         },
         {
            "index":7,
            "stones":6
         },
         {
            "index":8,
            "stones":6
         },
         {
            "index":9,
            "stones":6
         },
         {
            "index":10,
            "stones":6
         },
         {
            "index":11,
            "stones":6
         },
         {
            "index":12,
            "stones":6
         },
         {
            "index":13,
            "stones":0
         }
      ]
   },
   "state":"IN_PROGRESS",
   "turn":"PLAYER_1",
   "winner":null
}
```

* To pick stones and sow them in trajectory pits, the API `PUT /game/{id}/pick/{selectedPit}` should be called.
  * Note: selectedPit is started from 1 to 14. 
  * PLAYER_1: 1 to 7 (7 is the big pit).
  * PLAYER_2: 8 to 14 (14 is the big pit).

  In return, the state of current Game will be found.

**Structure**

Game consists of a board of 14 pits (6 small pits for each player and one big pit).
Initially, the small pits will be filled with 6 stones (configurable using final variable defined in the Board class). 
There are a set of rules which implemented using Command pattern and processed by PickChainExecutor.

* **PickValidatorExecutor**: Check the state, selected pit (in terms of empty pit and big pit).
* **PickCommandExecutor**: Pick the stones, sow them in trajectory pits, capture if it is possible, and change turn.
* **PickFinalizeExecutor**: Check end of the game, determine the winner and close the game.

We might be faced with a lot of exceptions which handled by GameExceptionHandler.
