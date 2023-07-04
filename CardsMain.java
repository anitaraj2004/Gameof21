package Gameof21;

import java.awt.Color;

import TurtleGraphics.KeyboardReader;

import TurtleGraphics.StandardPen;

public class CardsMain 
{
	public static void main(String[] args) 
	{
		DrawCards object = new DrawCards();
		StandardPen pen = new StandardPen();
		KeyboardReader reader = new KeyboardReader();
		int playerCard1, playerCard2, playerTotal, dealerCard2, dealerTotal, gameresponse, totaltokens, wager, playerHit, playerAce=0, dealerHit, dealerAce=0, playerHitcounter=0, dealerHitcounter=0;
		char hit='n', playagain;
		
		//extra credit included: player and dealer cannot exceed five cards (three hits)
		
		//introduction
		System.out.println("Hello! Welcome to the Game of 21 (Blackjack).");

		//does the player want to play or not?
		System.out.print("Press 0 to quit the game and 1 to continue to the game. ");
		gameresponse=reader.readInt();
		
		//if player does not wish to play, game ends
		if(gameresponse==0)
		{
			System.out.println("\nSee you next time!");
			return;
		}
	
		//if player wishes to play, continue
		//print rules
		System.out.println("\nHere are some rules you need to know.");
		System.out.println("There are four suits in a deck labeled 2 through 10.");
		System.out.println("Face cards are worth 10, and the ace is 1 or 11, changing automatically for best hand.");
		System.out.println("The player begins with two face cards up while the dealer has one up and one down.");
		System.out.println("The player can then hit, meaning another card is drawn.");
		System.out.println("The player can continue to hit as long as he/she has less than 21.");
		System.out.println("Once the player is done hitting, the dealer will hit in an attempt to beat the player to 21.");
		System.out.println("The person closest to 21, without exceeding, will win.");
		System.out.println("Each player is also limited to five cards (three hits).");
		System.out.println("A starting hand of 21 is an automatic win.");
		System.out.println("As soon as you exceed 21, the dealer wins if he/she remains under or at 21. He/she will not have to hit for a third card.");
		System.out.println("If both player and dealer exceed 21/land on the same value, the game is a tie, but the player wins the wager.");
		
		//ask for wager
		System.out.print("\nHow many tokens do you have? ");
		totaltokens=reader.readInt();
		
		while(totaltokens<1)
		{
			System.out.print("Sorry, you need to enter a new total tokens amount. ");
			totaltokens=reader.readInt();
		}
		
		while(totaltokens>0)
		{
			playerAce=dealerAce=playerHitcounter=dealerHitcounter=0;
			
			System.out.print("\nHow many tokens do you wager out of "+totaltokens+" tokens? ");
			wager=reader.readInt();

			while(wager<1||wager>totaltokens)
			{
				System.out.print("Sorry, you need to enter a new wager. ");
				wager=reader.readInt();
			}

			System.out.println("\nYour cards and the dealer's cards are shown in the drawing window.\n");

			//write dealer name
			pen.home();
			pen.up();
			pen.setDirection(180);
			pen.move(600);
			pen.down();
			pen.setColor(Color.black);
			String dealername="Dealer's Cards:";
			pen.drawString(dealername);

			//print dealer cards (back and normal)
			pen.up();
			pen.setDirection(270);
			pen.move(250);
			pen.setDirection(0);
			pen.move(150);
			object.DrawBorder(pen);
			object.DrawCardBack(pen);
			object.MoveCardBack(pen);
			pen.setDirection(0);
			pen.move(50);
			dealerCard2=object.CardGenerator(pen);
			if(dealerCard2==11)
			{
				dealerAce++;
			}
			dealerTotal=dealerCard2;

			//write player's name
			pen.home();
			pen.up();
			pen.setDirection(180);
			pen.move(600);
			pen.setDirection(90);
			pen.move(350);
			pen.down();
			pen.setColor(Color.black);
			String playername="Player's Cards:";
			pen.drawString(playername);

			//print player's cards (two normal)
			pen.up();
			pen.setDirection(270);
			pen.move(250);
			pen.setDirection(0);
			pen.move(150);
			object.DrawBorder(pen);
			playerCard1=object.CardGenerator(pen);
			if(playerCard1==11)
			{
				playerAce++;
			}
			pen.setDirection(0);
			pen.move(270);
			playerCard2=object.CardGenerator(pen);
			if(playerCard2==11)
			{
				playerAce++;
			}
			playerTotal=playerCard1+playerCard2;

			if(playerTotal!=21)
			{
				System.out.print("Your current total is "+playerTotal+". Would you like to hit ('y' for yes, 'n' for no)? ");
				hit=reader.readChar();
			}
			
			//ask player for hits
			while(playerTotal<21 && hit!='n' && playerHitcounter<3)
			{			
				if(hit=='y')
				{
					pen.up();
					pen.setDirection(0);
					pen.move(270);
					playerHit=object.CardGenerator(pen);

					//dealing with aces
					if(playerHit==11)
					{
						playerAce++;
					}

					playerTotal+=playerHit;

					//treating ace value as 1
					if((playerTotal>21)&&(playerAce>0))
					{
						playerTotal=playerTotal-10;
						playerAce--;
					}

					//increase player counter to keep track of amount of cards drawn
					playerHitcounter++;

					//as long as player total is less than 21, continue to print this statement
					if(playerTotal<21)
					{
						System.out.print("Your current total is "+playerTotal+". Would you like to hit ('y' for yes, 'n' for no)? ");
						hit=reader.readChar();
					}
				}	
			}
			
			//dealer's hits
			//move to back card
			pen.up();
			pen.home();
			pen.setDirection(180);
			pen.move(600);
			pen.setDirection(270);
			pen.move(250);

			//draw white over back card
			pen.setWidth(410);
			pen.down();
			pen.setColor(Color.white);
			pen.setDirection(0);
			pen.move(5);
			pen.setWidth(2);

			//draw new card
			pen.home();
			pen.up();
			pen.setDirection(180);
			pen.move(600);
			pen.setDirection(270);
			pen.move(250);
			pen.setDirection(0);
			pen.move(150);
			dealerHit=object.CardGenerator(pen);
			pen.up();
			pen.setDirection(0);
			pen.move(545);

			//dealing with aces
			if(dealerHit==11)
			{
				dealerAce++;
			}

			dealerTotal=dealerTotal+dealerHit;

			//treating ace value as 1
			if((dealerTotal>21)&&(dealerAce>0))
			{
				dealerTotal=dealerTotal-10;
				dealerAce--;
			}

			if(playerTotal<=21)
			{
				while(dealerTotal<21 && dealerTotal<=playerTotal && dealerHitcounter<3)
				{
					dealerHit=object.CardGenerator(pen);
					pen.up();
					pen.setDirection(0);
					pen.move(270);

					//dealing with aces
					if(dealerHit==11)
					{
						dealerAce++;
					}

					dealerTotal=dealerTotal+dealerHit;

					//treating ace value as 1
					if((dealerTotal>21)&&(dealerAce>0))
					{
						dealerTotal=dealerTotal-10;
						dealerAce--;
					}

					//increase dealer counter to keep track of amount of cards drawn
					dealerHitcounter++;
				}
			}

			if(playerTotal==21||(playerTotal<21 && playerTotal>dealerTotal)||(playerTotal<21 && dealerTotal>21))
			{
				System.out.println("\nGreat job! You win the game!");
				totaltokens=totaltokens+wager;
				System.out.println("\nYou have "+totaltokens+" token(s).");
			} 

			else if(playerTotal==dealerTotal)
			{
				System.out.println("\nGreat job! You win the game!");
				totaltokens=totaltokens+wager;
				System.out.println("\nYou have "+totaltokens+" token(s).");
			}

			else
			{
				System.out.println("\nSorry, you lost. Better luck next time!.");
				totaltokens=totaltokens-wager;
				System.out.println("\nYou have "+totaltokens+" token(s).");
			}

			System.out.println("Your total is "+playerTotal+" and the dealer total is "+dealerTotal+".");
			
			//play again option
			if(totaltokens>0)
			{
				System.out.print("\nWould you like to play again ('y' for yes, 'n' for no)? ");
				playagain=reader.readChar();
				
				if(playagain=='n')
				{
					System.out.println("\nThank you for playing the Game of 21 (Blackjack)!");
					return;
				}
				
				if(playagain=='y')
				{
					pen.up();
					pen.setDirection(180);
					pen.move(600);
					pen.down();
					pen.setColor(Color.white);
					pen.setWidth(2000);
					pen.setDirection(0);
					pen.move(1000);
					pen.setWidth(2);
				}
			}
		}
		
		System.out.println("\nThank you for playing the Game of 21 (Blackjack)!");
		
		/*
		//draw three card backs
		pen.home();
		object.DrawBorder(pen);
		object.DrawCardBack(pen);
		object.MoveCardBack(pen);
		object.DrawBorder(pen);
		object.DrawCardBack(pen);
		object.MoveCardBack(pen);
		object.DrawBorder(pen);
		object.DrawCardBack(pen);
		/*
		
		/*
		//draw three spades
		pen.home();
		object.DrawBorder(pen);
		object.DrawSpade(pen);
		object.MoveSpade(pen);
		object.DrawBorder(pen);
		object.DrawSpade(pen);
		object.MoveSpade(pen);
		object.DrawBorder(pen);
		object.DrawSpade(pen);
		*/
		
		/*
		//draw three hearts
		pen.home();
		object.DrawBorder(pen);
		object.DrawHeart(pen);
		object.MoveHeart(pen);
		object.DrawBorder(pen);
		object.DrawHeart(pen);
		object.MoveHeart(pen);
		object.DrawBorder(pen);
		object.DrawHeart(pen);
		*/
		
		/*
		//draw three diamonds
		pen.home();
		object.DrawBorder(pen);
		object.DrawDiamond(pen);
		object.MoveDiamond(pen);
		object.DrawBorder(pen);
		object.DrawDiamond(pen);
		object.MoveDiamond(pen);
		object.DrawBorder(pen);
		object.DrawDiamond(pen);
		*/
		
		/*
		//draw three clubs
		pen.home();
		object.DrawBorder(pen);
		object.DrawClub(pen);
		object.MoveClub(pen);
		object.DrawBorder(pen);
		object.DrawClub(pen);
		object.MoveClub(pen);
		object.DrawBorder(pen);
		object.DrawClub(pen);
		*/
		
		/*
		//draw card with club
		pen.up();
		pen.setDirection(180);
		pen.move(500);
		pen.setDirection(90);
		pen.move(150);
		object.DrawBorder(pen);
		object.DrawClub(pen);	
		
		//draw card with diamond
		pen.up();
		pen.setDirection(0);
		pen.move(300);
		pen.setDirection(270);
		pen.move(89);
		object.DrawBorder(pen);
		object.DrawDiamond(pen); 
		
		//draw card with heart
		pen.up();
		pen.setDirection(270);
		pen.move(340);
		pen.setDirection(180);
		pen.move(112);
		object.DrawBorder(pen);
		object.DrawHeart(pen);
		
		//draw card with spade
		pen.up();
		pen.setDirection(0);
		pen.move(340);
		pen.setDirection(270);
		pen.move(102);
		object.DrawBorder(pen);
		object.DrawSpade(pen);
		*/
	}
}