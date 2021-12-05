import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
//======================================================Don't modify below===============================================================//
enum PieceType {king, queen, bishop, knight, rook, pawn, none}
enum PlayerColor {black, white, none}

// Name: Dong Joo Lee
// StudentID#: 2013-12815
public class ChessBoard {
	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	private JPanel chessBoard;
	private JButton[][] chessBoardSquares = new JButton[8][8];
	private Piece[][] chessBoardStatus = new Piece[8][8];
	private ImageIcon[] pieceImage_b = new ImageIcon[7];
	private ImageIcon[] pieceImage_w = new ImageIcon[7];
	private JLabel message = new JLabel("Enter Reset to Start");

	ChessBoard() {
		initPieceImages();
		initBoardStatus();
		initializeGui();
	}

	public final void initBoardStatus() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) chessBoardStatus[j][i] = new Piece();
		}
	}

	public final void initPieceImages() {
		pieceImage_b[0] = new ImageIcon(new ImageIcon("./img/king_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[1] = new ImageIcon(new ImageIcon("./img/queen_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[2] = new ImageIcon(new ImageIcon("./img/bishop_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[3] = new ImageIcon(new ImageIcon("./img/knight_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[4] = new ImageIcon(new ImageIcon("./img/rook_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[5] = new ImageIcon(new ImageIcon("./img/pawn_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[6] = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));

		pieceImage_w[0] = new ImageIcon(new ImageIcon("./img/king_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[1] = new ImageIcon(new ImageIcon("./img/queen_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[2] = new ImageIcon(new ImageIcon("./img/bishop_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[3] = new ImageIcon(new ImageIcon("./img/knight_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[4] = new ImageIcon(new ImageIcon("./img/rook_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[5] = new ImageIcon(new ImageIcon("./img/pawn_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[6] = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
	}

	public ImageIcon getImageIcon(Piece piece) {
		if (piece.color.equals(PlayerColor.black)) {
			if (piece.type.equals(PieceType.king)) return pieceImage_b[0];
			else if (piece.type.equals(PieceType.queen)) return pieceImage_b[1];
			else if (piece.type.equals(PieceType.bishop)) return pieceImage_b[2];
			else if (piece.type.equals(PieceType.knight)) return pieceImage_b[3];
			else if (piece.type.equals(PieceType.rook)) return pieceImage_b[4];
			else if (piece.type.equals(PieceType.pawn)) return pieceImage_b[5];
			else return pieceImage_b[6];
		} else if (piece.color.equals(PlayerColor.white)) {
			if (piece.type.equals(PieceType.king)) return pieceImage_w[0];
			else if (piece.type.equals(PieceType.queen)) return pieceImage_w[1];
			else if (piece.type.equals(PieceType.bishop)) return pieceImage_w[2];
			else if (piece.type.equals(PieceType.knight)) return pieceImage_w[3];
			else if (piece.type.equals(PieceType.rook)) return pieceImage_w[4];
			else if (piece.type.equals(PieceType.pawn)) return pieceImage_w[5];
			else return pieceImage_w[6];
		} else return pieceImage_w[6];
	}

	public final void initializeGui() {
		gui.setBorder(new EmptyBorder(5, 5, 5, 5));
		JToolBar tools = new JToolBar();
		tools.setFloatable(false);
		gui.add(tools, BorderLayout.PAGE_START);
		JButton startButton = new JButton("Reset");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initiateBoard();
			}
		});

		tools.add(startButton);
		tools.addSeparator();
		tools.add(message);

		chessBoard = new JPanel(new GridLayout(0, 8));
		chessBoard.setBorder(new LineBorder(Color.BLACK));
		gui.add(chessBoard);
		ImageIcon defaultIcon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
		Insets buttonMargin = new Insets(0, 0, 0, 0);
		for (int i = 0; i < chessBoardSquares.length; i++) {
			for (int j = 0; j < chessBoardSquares[i].length; j++) {
				JButton b = new JButton();
				b.addActionListener(new ButtonListener(i, j));
				b.setMargin(buttonMargin);
				b.setIcon(defaultIcon);
				if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) b.setBackground(Color.WHITE);
				else b.setBackground(Color.gray);
				b.setOpaque(true);
				b.setBorderPainted(false);
				chessBoardSquares[j][i] = b;
			}
		}

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) chessBoard.add(chessBoardSquares[j][i]);

		}
	}

	public final JComponent getGui() {
		return gui;
	}

	public static void main(String[] args) {
		Runnable r = new Runnable() {
			@Override
			public void run() {
				ChessBoard cb = new ChessBoard();
				JFrame f = new JFrame("Chess");
				f.add(cb.getGui());
				f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				f.setLocationByPlatform(true);
				f.setResizable(false);
				f.pack();
				f.setMinimumSize(f.getSize());
				f.setVisible(true);
			}
		};
		SwingUtilities.invokeLater(r);
	}

	//================================Utilize these functions========================================//

	class Piece {
		PlayerColor color;
		PieceType type;

		Piece() {
			color = PlayerColor.none;
			type = PieceType.none;
		}

		Piece(PlayerColor color, PieceType type) {
			this.color = color;
			this.type = type;
		}
	}

	public void setIcon(int x, int y, Piece piece) {
		chessBoardSquares[y][x].setIcon(getImageIcon(piece));
		chessBoardStatus[y][x] = piece;
	}

	public Piece getIcon(int x, int y) {
		return chessBoardStatus[y][x];
	}

	public void markPosition(int x, int y) {
		chessBoardSquares[y][x].setBackground(Color.pink);
	}

	public void unmarkPosition(int x, int y) {
		if ((y % 2 == 1 && x % 2 == 1) || (y % 2 == 0 && x % 2 == 0))
			chessBoardSquares[y][x].setBackground(Color.WHITE);
		else chessBoardSquares[y][x].setBackground(Color.gray);
	}

	public void setStatus(String inpt) {
		message.setText(inpt);
	}

	public void initiateBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) setIcon(i, j, new Piece());
		}
		setIcon(0, 0, new Piece(PlayerColor.black, PieceType.rook));
		setIcon(0, 1, new Piece(PlayerColor.black, PieceType.knight));
		setIcon(0, 2, new Piece(PlayerColor.black, PieceType.bishop));
		setIcon(0, 3, new Piece(PlayerColor.black, PieceType.queen));
		setIcon(0, 4, new Piece(PlayerColor.black, PieceType.king));
		setIcon(0, 5, new Piece(PlayerColor.black, PieceType.bishop));
		setIcon(0, 6, new Piece(PlayerColor.black, PieceType.knight));
		setIcon(0, 7, new Piece(PlayerColor.black, PieceType.rook));
		for (int i = 0; i < 8; i++) {
			setIcon(1, i, new Piece(PlayerColor.black, PieceType.pawn));
			setIcon(6, i, new Piece(PlayerColor.white, PieceType.pawn));
		}
		setIcon(7, 0, new Piece(PlayerColor.white, PieceType.rook));
		setIcon(7, 1, new Piece(PlayerColor.white, PieceType.knight));
		setIcon(7, 2, new Piece(PlayerColor.white, PieceType.bishop));
		setIcon(7, 3, new Piece(PlayerColor.white, PieceType.queen));
		setIcon(7, 4, new Piece(PlayerColor.white, PieceType.king));
		setIcon(7, 5, new Piece(PlayerColor.white, PieceType.bishop));
		setIcon(7, 6, new Piece(PlayerColor.white, PieceType.knight));
		setIcon(7, 7, new Piece(PlayerColor.white, PieceType.rook));
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) unmarkPosition(i, j);
		}
		onInitiateBoard();
	}
//======================================================Don't modify above==============================================================//	


	//======================================================Implement below=================================================================//
	enum MagicType {MARK, CHECK, CHECKMATE}

	;
	//	enum TurnSeq {BLACK, WHITE}
	private int selX, selY;
	private boolean check, checkmate, end;


	// Additional fields
	boolean beforeReset = true;
	PlayerColor turnOwner;
	ArrayList<Integer> recentReachableLists;
	//	TurnSeq turn = TurnSeq.BLACK;
//    private int[] markedList = new int[64];

	class ButtonListener implements ActionListener {
		int x;
		int y;

		ButtonListener(int x, int y) {
			this.x = x;
			this.y = y;
		}

		// (x, y) click한 다음
		public void actionPerformed(ActionEvent e) {    // Only modify here
			if (beforeReset) {
			    return;
			}

			// (x, y) is where the click event occured
			if (end) {
				System.out.println("KING DEAD");
				// do nothing
			} else if (check) {
				// 따로 필요한가? 아래의 else에서 빠져서
			} else {
				/* 턴 시작 */
//				System.out.println("Im here 3");

				// 1. 턴에 맞는 말을 선택한 이후
				if (selX < 8 && selY < 8) {
					if (isReachable(x, y)) {
						// (selX, selY)에 있던 말을 (x, y)로 옮긴다.
						movePiece(selX, selY, x, y);
						if (recentReachableLists != null) unmarkLocations(recentReachableLists);
						recentReachableLists = null;
						selX = 8;
						selY = 8;
						changeTurn();
					} else {
						// 이상한 곳 선택하면 턴은 안바꾸고 mark 해제
						if (recentReachableLists != null) unmarkLocations(recentReachableLists);
						recentReachableLists = null;
						selX = 8;
						selY = 8;

						updateBoard();
					}
					// (selX, selY)에 의해 표시됐던 모든 marked를 unmarked로 바꾼다.

					// 2. 말 선택 이전
				} else {
//					System.out.println("Im here 4");
					// 턴에 맞는 말을 선택한 경우
					if (turnOwner == getIcon(x, y).color) {
						recentReachableLists = getReachableLocations(x, y);
						if (recentReachableLists != null) markLocations(recentReachableLists);
						selX = x;
						selY = y;
					} else {
						// 턴에 안맞는 말을 선택하거나 빈칸을 먼저 선택한 경우
						// do nothing
					}
				}
				/* 턴 끝 */
			}
//
//			switch (state) {
//				case NULL:
//					break;
//				case MARK: // 이미 말이 선택된 경우
//					break;
//				case CHECK: //
//					break;
//				case CHECKMATE:
//					break;
//				default:
//					break;
//			}

			/*

			턴 시작

			1. 선택을 기다린다.
				1) 턴에 맞는 말을 선택한 경우
					A. 각 말의 종류에 따라 갈 수 있는 장소를 표시한다.
						a. 갈 수 있는 장소를 클릭한 경우: Move.
							a) 상대 말이 위치한 경우
							b) 빈 칸인 경우
						b. 갈 수 없는 장소를 클릭한 경우: Do nothing.
					B. 이동이 끝난 뒤, check/checkmate/end를 update한다.
				2) 턴이 아닌 말을 선택한 경우 or 빈 칸을 선택한 경우
					A. Do nothing.

			2. 알맞게 setStatus한다.
				1) 이전 턴에 확정된 check/checkmate/end
				2) blkTrn
			턴 끝

			*/

		}
	}

	// Reset한 다음
	void onInitiateBoard() {
		// 각종 변수들 초기화
		selX = 8;
		selY = 8;
		check = false;
		checkmate = false;
		end = false;
		turnOwner = PlayerColor.black;
		recentReachableLists = null;
		beforeReset = false;
		updateBoard();
	}


//	void initiateMarkedList() {
//		for (int i = 0; i < 64; i++) {
//			markedList[i] = -1;
//		}
//	}

	// Board에 정보 반영
	void updateBoard() {
		String message = (turnOwner == PlayerColor.black)? "BLACK" : "WHITE";
		message += "'s TURN";
		if (check) {
			message += " / CHECK";
		} else if (checkmate) {
			message += " / CHECKMATE";
		}
	    setStatus(message);
		checkEnd();

//		2. 알맞게 setStatus한다.
//		1) 이전 턴에 확정된 check/checkmate/end
//		2) blkTrn

	}

	private void checkEnd() {
	    boolean blkKingAlive = false;
	    boolean whtKingAlive = false;
	    for (int i = 0; i < 8; i++) {
	        for (int j = 0; i < 8; j++) {
				if (getIcon(i, j).type == PieceType.king) {
				    if (getIcon(i, j).color == PlayerColor.black) blkKingAlive = true;
				    else whtKingAlive = true;
				}
			}
		}
	    if (blkKingAlive == false || whtKingAlive == false) {
	    	end = true;
		}
	}

	private void changeTurn() {
		if (turnOwner == PlayerColor.black) {
			turnOwner = PlayerColor.white;
		} else if (turnOwner == PlayerColor.white) {
			turnOwner = PlayerColor.black;
		}
	}

	private void unmarkLocations(ArrayList<Integer> reachableLocations) {
		for (int i = 0; i < reachableLocations.size(); i++) {
			int currLocation = reachableLocations.get(i);
			int currX = currLocation / 10;
			int currY = currLocation % 10;
			unmarkPosition(currX, currY);
		}
	}

	private void markLocations(ArrayList<Integer> reachableLocations) {
		for (int i = 0; i < reachableLocations.size(); i++) {
			int currLocation = reachableLocations.get(i);
			int currX = currLocation / 10;
			int currY = currLocation % 10;
			markPosition(currX, currY);
		}
	}

	private ArrayList getReachableLocations(int currX, int currY) {
		ArrayList<Integer> locationList = new ArrayList<>();
		int[][] tmpBoard = new int[8][8];
		Piece currPiece = getIcon(currX, currY);

		switch (currPiece.type) {
			case king: {
//				System.out.println("킹 선택");
				int[] varX = {-1, -1, 0, 1, 1, 1, 0, -1};
				int[] varY = {0, 1, 1, 1, 0, -1, -1, -1};
				for (int i = 0; i < 8; i++) {
					int nextX = currX + varX[i];
					int nextY = currY + varY[i];
					if (nextX >= 0 && nextX < 8) {
						if (nextY >= 0 && nextY < 8) {
							if (getIcon(nextX, nextY).color != currPiece.color) {
								locationList.add(nextX * 10 + nextY);
							}
						}
					}
				}
				break;
			}

			case queen: {
				int[] varX = {-1, -1, 0, 1, 1, 1, 0, -1};
				int[] varY = {0, 1, 1, 1, 0, -1, -1, -1};
				for (int i = 0; i < 8; i++) {
					boolean blocked = false;
					int nextX = currX + varX[i];
					int nextY = currY + varY[i];

					// (nextX, nextY) is not blocked yet and also it is in the scope of the board.
					while (!blocked && nextX >= 0 && nextX < 8 && nextY >= 0 && nextY < 8) {
						// 빈칸을 만나는 동안엔 계속 add.
						if (getIcon(nextX, nextY).color == PlayerColor.none) {
							locationList.add(nextX * 10 + nextY);
						} else {
							// 내 말이든 상대 말이든 마주쳤다.
							blocked = true; // 이제 이방향으론 더이상 진행은 못하고
							if (getIcon(nextX, nextY).color != currPiece.color) {
								locationList.add(nextX * 10 + nextY);
							}
						}
						nextX += varX[i];
						nextY += varY[i];
					}
				}
				break;
			}

			case bishop: {
				int[] varX = {-1, 1, 1, -1};
				int[] varY = {1, 1, -1, -1};
				for (int i = 0; i < 4; i++) {
					boolean blocked = false;
					int nextX = currX + varX[i];
					int nextY = currY + varY[i];
					while (!blocked && nextX >= 0 && nextX < 8 && nextY >= 0 && nextY < 8) {
						// 빈칸을 만나는 동안엔 계속 add.
						if (getIcon(nextX, nextY).color == PlayerColor.none) {
							locationList.add(nextX * 10 + nextY);
						} else {
							// 내 말이든 상대 말이든 마주쳤다.
							blocked = true; // 이제 이방향으론 더이상 진행은 못하고
							if (getIcon(nextX, nextY).color != currPiece.color) {
								locationList.add(nextX * 10 + nextY);
							}
						}
						nextX += varX[i];
						nextY += varY[i];
					}
				}
				break;
			}

			case knight: {
				int[] varX = {-2, -1, +1, +2, +2, +1, -1, -2};
				int[] varY = {+1, +2, +2, +1, -1, -2, -2, -1};
				for (int i = 0; i < 8; i++) {
					int nextX = currX + varX[i];
					int nextY = currY + varY[i];
					if (nextX >= 0 && nextX < 8) {
						if (nextY >= 0 && nextY < 8) {
							if (getIcon(nextX, nextY).color != currPiece.color) {
								locationList.add(nextX * 10 + nextY);
							}
						}
					}
				}
				break;
			}

			case rook: {
				int[] varX = {-1, 0, +1, 0};
				int[] varY = {0, +1, 0, -1};
				for (int i = 0; i < 4; i++) {
					boolean blocked = false;
					int nextX = currX + varX[i];
					int nextY = currY + varY[i];
					while (!blocked && nextX >= 0 && nextX < 8 && nextY >= 0 && nextY < 8) {
						// 빈칸을 만나는 동안엔 계속 add.
						if (getIcon(nextX, nextY).color == PlayerColor.none) {
							locationList.add(nextX * 10 + nextY);
						} else {
							// 내 말이든 상대 말이든 마주쳤다.
							blocked = true; // 이제 이방향으론 더이상 진행은 못하고
							if (getIcon(nextX, nextY).color != currPiece.color) {
								locationList.add(nextX * 10 + nextY);
							}
						}
						nextX += varX[i];
						nextY += varY[i];
					}
				}
				break;
			}

			case pawn: {
				if (currPiece.color == PlayerColor.black) {
					int nextX = currX + 1;
					int nextY = currY;
					if (getIcon(nextX, nextY).color == PlayerColor.none) {
						locationList.add(nextX * 10 + nextY);
						if (currX == 1) {
							// 시작지점이고 앞칸이 비어있었다면, 두칸 전진도 가능하다.
							if (getIcon(nextX + 1, nextY).color == PlayerColor.none) {
								locationList.add((nextX + 1) * 10 + nextY);
							}
						}
					}
					nextY -= 1;
					if (nextY >= 0 && getIcon(nextX, nextY).color == PlayerColor.white) {
						locationList.add(nextX * 10 + nextY);
					}
					nextY += 2;
					if (nextY < 8 && getIcon(nextX, nextY).color == PlayerColor.white) {
						locationList.add(nextX * 10 + nextY);
					}

				} else {
					int nextX = currX - 1;
					int nextY = currY;
					if (getIcon(nextX, nextY).color == PlayerColor.none) {
						locationList.add(nextX * 10 + nextY);
						if (currX == 6) {
							// 시작지점이고 앞칸이 비어있었다면, 두칸 전진도 가능하다.
							if (getIcon(nextX - 1, nextY).color == PlayerColor.none) {
								locationList.add((nextX - 1) * 10 + nextY);
							}
						}
					}
					nextY -= 1;
					if (nextY >= 0 && getIcon(nextX, nextY).color == PlayerColor.black) {
						locationList.add(nextX * 10 + nextY);
					}
					nextY += 2;
					if (nextY < 8 && getIcon(nextX, nextY).color == PlayerColor.black) {
						locationList.add(nextX * 10 + nextY);
					}

				}
				break;
			}

			case none:
				break;
		}

		return locationList;
	}

	private void movePiece(int currX, int currY, int nextX, int nextY) {
//		System.out.println("from (" + currX + ", " + currY + ")");
		Piece currPiece = getIcon(currX, currY);
		Piece newPiece = new Piece();
		setIcon(nextX, nextY, currPiece);
		setIcon(currX, currY, newPiece);
//		System.out.println("to (" + nextX + ", " + nextY + ")");
	}

	// 선택한 지점이 이동할 수 있는 곳인지 반환하는 함수
	private boolean isReachable(int targetX, int targetY) {
		if (chessBoardSquares[targetY][targetX].getBackground() == Color.pink) {
			return true;
		} else {
			return false;
		}
	}
}
