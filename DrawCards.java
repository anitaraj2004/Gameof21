package Gameof21;

import java.awt.Color;
import java.util.Random;
import TurtleGraphics.StandardPen;

public class DrawCards
{
	public void DrawBorder(StandardPen pen)
	{
		//StandardPen pen = new StandardPen();
		
		//outside of card
		pen.setColor(Color.black);
		pen.down();
		pen.setDirection(90);
		pen.move(200);
		pen.setDirection(180);
		pen.move(150);
		pen.setDirection(270);
		pen.move(200);
		pen.setDirection(0);
		pen.move(150);
	}
		
	public void DrawCardBack(StandardPen pen)
	{
		//move to card center
		pen.setColor(Color.red);
		pen.up();
		pen.setDirection(180);
		pen.move(36);
		pen.setDirection(90);
		pen.move(65);
		pen.down();
		
		//draw design
		for(int outside=1;outside<=20;outside++)
		{
			for(int inside=1;inside<=20;inside++)
			{
				pen.turn(inside);
				pen.move(10);
			}
		}
		
		//move to bottom of card
		pen.up();
		pen.setDirection(270);
		pen.move(81);
		pen.setDirection(0);
		pen.move(123);
		pen.setColor(Color.black);
		
		//draw bottom border design
		pen.down();
		for(int x=1;x<=3;x++)
		{
			pen.setDirection(90);
			for(int count=1;count<=180;count++)
			{
				pen.move(0.435);
				pen.turn(1);
			}
		}
		
		//move to top of card
		pen.up();
		pen.setDirection(90);
		pen.move(198);
		
		//draw top border design
		pen.down();
		for(int x=1;x<=3;x++)
		{
			pen.setDirection(270);
			for(int count=1;count<=180;count++)
			{
				pen.move(0.435);
				pen.turn(1);
			}
		}
		
		//move to middle of card
		pen.up();
		pen.setDirection(270);
		pen.move(115);
		pen.setDirection(180);
		pen.move(102);
		pen.setColor(Color.black);
		pen.down();
		
		//draw first triangle design in center
		EquilateralTriangle(pen, 55);
		
		//move for second triangle
		pen.up();
		pen.setDirection(90);
		pen.move(30);
		pen.down();
		
		//draw second triangle design in center
		ReverseEquilateralTriangle(pen, 55);
	}
	
	public void MoveCardBack(StandardPen pen)
	{
		//move to where next card will be drawn
		pen.up();
		pen.setDirection(0);
		pen.move(325);
		pen.setDirection(270);
		pen.move(115);
	}

	public int DrawNumber(StandardPen pen)
	{
		Random generator = new Random();
		
		//move to bottom right to draw number
		pen.setDirection(150);
		pen.up();
		pen.move(25);
		pen.down();
		String printValue="";
		int number=generator.nextInt(13)+1;
		if (number==1)
		{
			printValue="A";
			number=11;
		}
		else if(number<=10)
		{
			printValue=printValue+number;
		}
		else if (number==11)
		{
			printValue="J";
			number=10;
		}
		else if (number==12)
		{
			printValue="Q";
			number=10;
		}
		else
		{
			printValue="K";
			number=10;
		}
		pen.drawString(printValue);
		
		//move to top left to draw the same number
		pen.up();
		pen.setDirection(325);
		pen.move(20);
		pen.setDirection(90);
		pen.move(200);
		pen.setDirection(180);
		pen.move(150);
		pen.setDirection(305);
		pen.move(25);
		pen.down();
		pen.drawString(printValue);
		pen.up();
		
		return number;
	}
	
	public int CardGenerator(StandardPen pen)
	{
		DrawCards object = new DrawCards();
		Random generator = new Random();
		int card=generator.nextInt(4)+1;
		int cardnumber=-1;
		
		if(card==1)
		{
			object.DrawBorder(pen);
			cardnumber=object.DrawNumber(pen);
			object.DrawClub(pen);
			object.MoveClub(pen);
		}
		if(card==2)
		{
			object.DrawBorder(pen);
			cardnumber=object.DrawNumber(pen);
			object.DrawDiamond(pen);
			object.MoveDiamond(pen);
		}
		if(card==3)
		{
			object.DrawBorder(pen);
			cardnumber=object.DrawNumber(pen);
			object.DrawHeart(pen);
			object.MoveHeart(pen);
		}
		if(card==4)
		{
			object.DrawBorder(pen);
			cardnumber=object.DrawNumber(pen);
			object.DrawSpade(pen);
			object.MoveSpade(pen);
		}
		
		return cardnumber;
	}

	public void DrawClub(StandardPen pen)
	{
		//set color and position, move to location of first circle
		pen.setColor(Color.black);
		pen.up();
		pen.setDirection(315);
		pen.move(120);
		pen.down();
		pen.setDirection(0);
		
		//draw first circle, move to location of second circle
		DrawCircle(pen);
		pen.up();
		pen.setDirection(180);
		pen.move(40);
		pen.down();
		pen.setDirection(0);
		
		//draw second circle, move to location of third circle
		DrawCircle(pen);
		pen.up();
		pen.setDirection(0);
		pen.move(20);
		pen.setDirection(90);
		pen.move(29);
		pen.down();

		//draw third circle, move to location of stem
		DrawCircle(pen);
		pen.up();
		pen.setDirection(270);
		pen.move(64.25);
		pen.setDirection(180);
		pen.move(28);
		pen.down();
		
		//draw stem
		DrawTriangle(pen, 60);
	}
	
	public void DrawDiamond (StandardPen pen)
	{
		//draw first triangle
		pen.setColor(Color.red);
		pen.up();
		pen.setDirection(288);
		pen.move(80);
		pen.setDirection(270);
		pen.move(10);
		pen.down();
		DrawTriangle(pen, 80);
		
		//draw reverse triangle
		pen.up();
		pen.setDirection(270);
		pen.move(76);
		DrawReverseTriangle(pen, 80);
	}
	
	public void DrawHeart (StandardPen pen)
	{
		//draw first heart of circle
		pen.setColor(Color.red);
		pen.up();
		pen.setDirection(305);
		pen.move(85);
		pen.down();
		DrawCircle(pen);
		
		//draw second heart of circle
		pen.up();
		pen.setDirection(0);
		pen.move(35);
		pen.down();
		DrawCircle(pen);
		
		//draw reverse triangle
		pen.up();
		pen.setDirection(270);
		pen.move(44);
		pen.setDirection(180);
		pen.move(19);
		DrawReverseTriangle(pen, 69);
	}
	
	public void DrawSpade (StandardPen pen)
	{
		//draw first circle
		pen.setColor(Color.black);
		pen.up();
		pen.setDirection(293);
		pen.move(120);
		pen.setDirection(90);
		pen.move(30);
		pen.down();
		DrawCircle(pen);
		
		//draw second circle
		pen.up();
		pen.setDirection(0);
		pen.move(35);
		pen.down();
		DrawCircle(pen);
		
		//draw reverse triangle
		pen.up();
		pen.setDirection(90);
		pen.move(12);
		pen.setDirection(180);
		pen.move(52);
		DrawTriangle(pen, 69);
		
		//draw stem
		pen.up();
		pen.setDirection(270);
		pen.move(84.5);
		pen.setDirection(180);
		pen.move(26.5);
		pen.down();
		DrawTriangle(pen, 60);
	}
	
	public void DrawCircle(StandardPen pen)
	{
		for(int count=1;count<=360;count++)
		{
			pen.move(20);
			pen.turn(180);
			pen.move(20);
			pen.turn(1);
		}
	}
	
	public void DrawTriangle(StandardPen pen, int size)
	{
		pen.setDirection(0);
	
		for(int lines=size;lines>=1;lines-=3)
		{
			pen.down();
			pen.move(lines);
			pen.up();
			pen.setDirection(180);
			pen.move(lines);
			pen.setDirection(45);
			pen.move(2);
			pen.setDirection(0);
		}
	}
	
	public void DrawReverseTriangle(StandardPen pen, int size)
	{
		pen.setDirection(0);
		
		for(int lines=1;lines<=size;lines+=3)
		{
			pen.down();
			pen.move(lines);
			pen.up();
			pen.setDirection(180);
			pen.move(lines);
			pen.setDirection(135);
			pen.move(2);
			pen.setDirection(0);
		}
	}
	
	public void EquilateralTriangle(StandardPen pen, int size) 
	{
		pen.setDirection(60);
		pen.move(size);
		pen.setDirection(300);
		pen.move(size);
		pen.setDirection(180);
		pen.move(size);
	}
	
	public void ReverseEquilateralTriangle(StandardPen pen, int size) 
	{
		pen.setDirection(300);
		pen.move(size);
		pen.setDirection(60);
		pen.move(size);
		pen.setDirection(180);
		pen.move(size);
	}
	
	public void MoveClub(StandardPen pen)
	{
		pen.up();
		pen.setDirection(0);
		pen.move(77);
		pen.setDirection(270);
		pen.move(89);
	}
	
	public void MoveDiamond(StandardPen pen)
	{
		pen.up();
		pen.setDirection(270);
		pen.move(95);
		pen.setDirection(0);
		pen.move(117);
	}
	
	public void MoveHeart(StandardPen pen)
	{
		pen.up();
		pen.setDirection(0);
		pen.move(109);
		pen.setDirection(270);
		pen.move(99);
	}
	
	public void MoveSpade(StandardPen pen)
	{
		pen.up();
		pen.setDirection(0);
		pen.move(78);
		pen.setDirection(270);
		pen.move(88.5);
	}
}