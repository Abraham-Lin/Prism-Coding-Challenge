# Prism-Coding-Challenge
Coding Challenge for Prism Skylabs

First and foremost, I would like to thank you guys at Prism for giving me this oppurtunity to do your coding challenge. It was pretty fun and difficult, but I definetely 
enjoyed myself. In this README, I will explain my process for both the "Vacationing Salesman" and "Deck of Cards" problem.

I'd like to note that I started a bit late (around 20 minutes or so), so I was a bit pressed on time. That being said, I was only able to test CardDeck.java extensively, and was left with very little time to finish up DistanceCalculator.java. I am confident that my reasoning is correct, so if you guys find anything wrong, shoot me an email and I'll be more than happy to explain to you what I did more extensively. 

I used Java for both problems as it is one of the first languages I have learned, and, thus, one of the languages I'm most comfortable with.

Deck of Cards:

This problem seemed to be pretty straight forward and something right out of my OOP class. I first started off creating my "CardDeck" object that would represent my entire deck, and inside the "CardDeck" object, I created a nested "Cards" class as each deck is made of many cards. 

Within the Cards, there were only two constructors. One that takes in a suit and face value, and another that takes in a suit and integer value. I thought this was appropriate since there was only a minimum amount of data needed to identify each card. The only problem with this implementation is that you need to keep track of the integer value (ie: King = 13 and Ace = 1) yourself, but this was resolved quite easily with some conditional statements. 

Outside in the CardDeck object, I stored all my cards within an Array named "deck". This array kept track of all the cards within my deck. Additionally there is a removedCards array that keeps track of all the cards I removed when calling the "nextCard()" function.

Within the CardDeck object, there are mainly three functions I use. The first is makeDeck() which is only called when a new CardDeck object is instantiated. This function 
serves to fill up the deck Array with the standard 52 cards. The next function is nextCard() which draws the next card at index 0 within our deck array. When a card is drawed, it is taken away from the deck array, and is stored in the removedCard array so it can be accessed later when shuffling. The last function is shuffle, which will
first add all removed cards back into the deck array (than clears the removedCard array so we do not get doubles), and than shuffles the deck using Collections.shuffle().



Vacationing Salesman:

This challenge seemed a lot more difficult, as I had to figure out which APIs I would consider using in order to find the location and distances of certain points. However,
this problem did not seem like it required the use of objects, so I did not have to consider object orientated design within my implementation.

First things first, I created a static getDistance() function, which essentially calcualtes the distance between two points (or in this case GeoCodingResult), and display the given number in either kilometer value or mile value based on the input given by the user. I won't delve deeply into how I constructed this function since much of it was taken from Stackoverflow and the Google Developers page, but I think it is fairly self explanatory. (Please contact me if you would like me to explain it).

Moving on to the main, I saw three things that needed to be achieved here. Firstly, we must figure out whether the user wants the results outputted in kilometers or miles. This was achieved easily using a scanner that prompted the user for an input. That input would later be stored in a string called metric, which is referenced in both the main and the static function getDistance().

Secondly, we need to figure out what cities the user wants to go to. I thought about doing this again with a scanner, but I felt it redundant to prompt the user several times for a location update. So instead, I used a destination.txt file which the user states which locations they want to go to in order, and using a FileReader/Bufferedreader, read the destination.txt file in, and stored the destinations in the destinations ArrayList. The downside with this is that the user must update the destination.txt file themselves, and the filePath string must be hardcoded in. Nevertheless, I felt that with the limited time I had, this was the most efficient way to solve the problem.

Lastly, we need to translate these location strings into actual places we could use to calculate distance. This is where I got a little nervous, as it was my first time using the Google Maps API, so I was pretty sure I was doing EVERYTHING wrong. The code itself I think is not too complicated. Basically, I iterate through my destinations array obtained from the BufferedReader and using Google Maps API, find a GeoCodingResult mapped to the specified location. I than use that location to calculate the distance between two points, and append the distance to the distances ArrayList.

Although I did not get the chance to test the script, it can be tested by just compiling the Java file, and running the Java file like you normally would.
(ie: Javac DistanceCalculator.java       Java DistanceCalculator)

Overall, I think the coding challenge went well. I hope this README explained my process well enough. Please contact me if you have anything to inquire about. I have documented most of the links I used within the comments of the Java files.

Thanks!
