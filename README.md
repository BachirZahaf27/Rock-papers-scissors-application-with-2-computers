# Rock papers scissors application with 2 computers

## Description :
  This Rock papers scissors application is made using the `Java remote method invocation`, and by implementing this method we can make this game played by `2       client` on diffrent machines, 1st Client on same machine as the Server machine & then the 2nd Client in a diffrent machine, as you can see in the pic below:
  
  ![Diagm](https://user-images.githubusercontent.com/61596276/172461002-7fa2dc90-5337-4409-84fd-ada7910bbc1a.png)

## How to run the application :

1) Run the server in mchine 1.
2) Run the Client 1 in mchine 1.
3) Enter the word `localhost` (!!!! THIS IS IMPORTANT IF YOU WANT TO RUN THE APPLICATION !!!!)
4) Run the Client 2 in mchine 2.
5) Enter the `ip adress` of machine 1  (!!!! THIS IS IMPORTANT IF YOU WANT TO RUN THE APPLICATION !!!!)
6) and enjoy playing ^_^


# Explication en français:
L'objectif est de réaliser un jeu de rmi en réseau. Deux joueurs peuvent jouer sur deux ordinateurs distincts.
Nous proposons de faire cet exercice avec une architecture client-serveur. La communication se fait en mode connecté (RMI). Les joueurs communiquent uniquement avec le serveur.
Chaque joueur possède une interface graphique lui permettant de jouer ; lorsque les deux joueurs ont joué, le joueur voit aussi le jeu de l'adversaire.
Le serveur calcule le gagnant de chaque tour et l'indique aux joueurs, qui affichent les scores au fur et à mesure.

Après un tour de jeu, l'affichage peut ressembler à l'image ci-dessous:

![chifoumi](https://user-images.githubusercontent.com/61596276/172462946-686a15d3-5a2a-4f69-947f-56b6fb556141.jpg)


Lorsque les deux joueurs ont joué et qu'ils attendent la réponse du serveur, l'interface peut ressembler à l'image ci-dessous:

![chifoumi2](https://user-images.githubusercontent.com/61596276/172462974-17d2e4e0-2ea1-4c26-b95e-d73f03223d9a.jpg)

Le gagnant est celui qui atteint en premier le score de 10. Chaque joueur voit alors afficher qu'il est soit gagnant, soit perdant.
Il y a beaucoup de façons de faire cet exercice, mais il n'est pas facile à réaliser.

# Explanation in English:

The objective is to realize a game of rmi in network. Two players can play on two separate computers.
We propose to do this exercise with a client-server architecture. Communication takes place in connected mode (RMI). Players only communicate with the server.
Each player has a graphical interface allowing him to play; when both players have played, the player also sees the opponent's play.
The server calculates the winner of each round and reports it to the players, who display the scores as they go.

After a round of play, the display may look like the image below:

![chifoumi](https://user-images.githubusercontent.com/61596276/172462946-686a15d3-5a2a-4f69-947f-56b6fb556141.jpg)

When both players have played and they are waiting for the response from the server, the interface may look like the image below:

![chifoumi2](https://user-images.githubusercontent.com/61596276/172462974-17d2e4e0-2ea1-4c26-b95e-d73f03223d9a.jpg)

The winner is the one who first reaches the score of 10. Each player then sees that he is either a winner or a loser.
There are many ways to do this exercise, but it is not easy to perform.


  
  
  
