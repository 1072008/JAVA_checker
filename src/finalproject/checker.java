package finalproject;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Scanner;

public class checker {

	public int[][] situation = new int[9][10];
	public int chessid;
	public char sx = 0, sy = 0;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new checker();
		Frame f = new Frame();
		f.setLayout(null);
		f.setSize(750, 1000);

		f.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				System.exit(0);
			}

		});

		f.add(new mycanvas());
		f.setVisible(true);

	}

//-------------------------------------------------------------[連線區]-----------------------------------------------------------------
	public checker() throws IOException {
		super();
		gethttp();
	}

	void gethttp() throws IOException {
		int ID = 200008;
		String num;
		String mess = null;
		char code = 0;
		URL url = new URL("http://140.138.147.44:6004/you_r_fired/start?SID=" + ID); // url:網址
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.connect();// 連線
		BufferedInputStream is = new BufferedInputStream(conn.getInputStream());// 抓取
		byte[] tmp = new byte[1024];
		int len = 0;// 確認用
		final Charset UTF_8 = Charset.forName("BIG5");// 轉換
		while ((len = is.read(tmp)) != -1) {
			String value = new String(tmp, UTF_8);
			mess = value;
			char x = value.charAt(0);
			code = x;
			System.out.print(value);
			System.out.println(code);
		}
		if (mess != "Error") {
			num = mess;
			URL url1 = new URL("http://140.138.147.44:6004/you_r_fired/select_room?SID=" + ID + "&room_id=" + code); // url:網址
			HttpURLConnection conn1 = (HttpURLConnection) url1.openConnection();
			conn1.connect();// 連線
			BufferedInputStream is1 = new BufferedInputStream(conn1.getInputStream());// 抓取
			byte[] tmp1 = new byte[1024];
			int len1 = 0;// 確認用
			final Charset UTF_81 = Charset.forName("BIG5");// 轉換
			while ((len1 = is1.read(tmp1)) != -1) {
				String value1 = new String(tmp1, UTF_81);
				mess = value1;
				System.out.print(value1);
			}
		} else {

		}
		// if(mess=="success")
		// {
		URL url2 = new URL("http://140.138.147.44:6004/you_r_fired/wait?SID=" + ID + "&room_id=" + code); // url:網址
		HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
		conn2.connect();// 連線
		BufferedInputStream is2 = new BufferedInputStream(conn2.getInputStream());// 抓取
		byte[] tmp2 = new byte[1024];
		int len2 = 0;// 確認用
		final Charset UTF_82 = Charset.forName("BIG5");// 轉換
		while ((len2 = is2.read(tmp2)) != -1) {
			String value2 = new String(tmp2, UTF_82);
			mess = value2;
			char t = value2.charAt(3);
			chessid = t;
			sx = value2.charAt(4);
			sy = value2.charAt(5);
			situation[sx][sy] = chessid;
			System.out.print(value2);
		}
		// }
		// else
		// {

		// }

		is.close();
	}

}
//--------------------------------------------------------[畫圖區]---------------------------------------------------------------

class mycanvas extends Canvas {

	public mycanvas() {
		super();
		setSize(750, 1000);
		Point p1 = new Point();
		Point p2 = new Point();
		int check = 0;
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				/*if (check % 2 == 0) {

					if (situation[sx][sy] != 0) {

					}
				} else {

				}*/
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				// System.out.print("("+e.getX()+","+e.getY()+")");
				p1.setLocation(e.getPoint());
				p2.setLocation(e.getPoint());
			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

		});
	}

	String[] typename1 = { "將", "士", "士", "象", "象", "車", "車", "馬", "馬", "砲", "砲", "卒", "卒", "卒", "卒", "卒" };
	String[] typename2 = { "帥", "仕", "仕", "相", "相", "俥", "俥", "傌", "傌", "炮", "炮", "兵", "兵", "兵", "兵", "兵" };
	// public void

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		board(g);
	}

	public void board(Graphics g) {
		// -------------Board---------------
		Color color3 = new Color(245, 222, 179);
		g.setColor(color3);
		g.fillRect(0, 0, 750, 1000);
		g.setColor(Color.BLACK);
		int x1 = 50, y1 = 50;
		int x2 = 50, y2 = 770;
		g.drawLine(x1, y1, x2, y2);
		g.drawLine(690, 50, 690, 770);
		x1 += 80;
		x2 += 80;
		for (int i = 0; i < 7; i++) {
			g.drawLine(x1, y1, x2, 370);
			g.drawLine(x1, 450, x2, 770);
			// board(g,x1-25,y1-25);
			x1 += 80;
			x2 += 80;
		}
		int x11 = 50, y11 = 50, x21 = 690, y21 = 50;
		for (int i = 0; i < 10; i++) {
			g.drawLine(x11, y11, x21, y21);
			y11 += 80;
			y21 += 80;
		}
		g.drawLine(290, 50, 450, 210);
		g.drawLine(450, 50, 290, 210);
		g.drawLine(290, 610, 450, 770);
		g.drawLine(450, 610, 290, 770);
		// -----------Original Checkers--------------
		int t;
		x1 = 50;
		y1 = 50;
		for (int i = 0; i < 9; i++) {

			if (i == 0)
				t = 5;
			else if (i == 1)
				t = 7;
			else if (i == 2)
				t = 3;
			else if (i == 3)
				t = 1;
			else if (i == 4)
				t = 0;
			else if (i == 5)
				t = 2;
			else if (i == 6)
				t = 4;
			else if (i == 7)
				t = 8;
			else if (i == 8)
				t = 6;
			else
				t = 0;

			drawpiece(g, x1 - 25, y1 - 25, t);
			drawpiece(g, x1 - 25, y1 + 695, t + 16);

			x1 += 80;
			x2 += 80;
		}
		int xx = 105, yy = 185;
		for (int i = 0; i < 2; i++) {
			t = 9;
			piece(g, xx, yy, t);
			xx = 585;
			t++;
		}
		xx = 105;
		yy = 585;
		for (int i = 0; i < 2; i++) {
			t = 25;
			piece(g, xx, yy, t);
			xx = 585;
			t++;
		}
		xx = 25;
		yy = 265;
		for (int i = 0; i < 5; i++) {
			t = 11;
			piece(g, xx, yy, t);
			xx += 160;
			t++;
		}
		xx = 25;
		yy = 505;
		for (int i = 0; i < 5; i++) {
			t = 27;
			piece(g, xx, yy, t);
			xx += 160;
			t++;
		}

	}

	public void piece(Graphics g, int x, int y, int t) {
		drawpiece(g, x, y, t);
	}

	public void drawpiece(Graphics g, int x, int y, int t) {
		Color color1 = new Color(138, 54, 15);
		Font chessfont = new Font("微軟正黑體", Font.BOLD, 20);
		g.setColor(color1);
		g.fillOval(x - 3, y - 3, 56, 56);
		Color color = new Color(218, 165, 105);
		g.setColor(color);
		g.fillOval(x, y, 50, 50);
		g.setFont(chessfont);
		g.setColor(Color.BLACK);
		if (t > 15) {
			g.drawString(typename2[t - 16], x + 20, y + 20);
		} else {
			g.setColor(Color.red);
			g.drawString(typename1[t], x + 20, y + 25);
		}

	}

}

//------------------------------------------------------------[邏輯區]---------------------------------------------------------------------
class chess {
	public int s_x, s_y, type;
	public chess()
	{
		switch(type)
		{
		case 1:
			King king=new King();
		case 2:
			Advisors advisors=new Advisors();
		case 3:
			Elephants elephants=new Elephants();
		case 4:
			Rooks rooks=new Rooks();
		case 5:
			Knights knights=new Knights();
		case 6:
			Cannons cannons=new Cannons();
		case 7:
			Pawns pawns=new Pawns();
		}
	}
}

class King extends chess {
	int s_x = 5;

	public King() {

	}
}

class Advisors extends chess {

}

class Elephants extends chess {

}

class Rooks extends chess {

}

class Knights extends chess {

}

class Cannons extends chess {

}

class Pawns extends chess
{
	
}
