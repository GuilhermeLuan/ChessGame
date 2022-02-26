package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessMatch {

    private Board board;

    private int turn;
    private Color currentPlayer;
    private boolean check;

    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();

    public ChessMatch(){
        //Creates a bord with 8 rows and 8 columns and calls the initial setup.
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
        initialSetup();
    }

    public int getTurn(){
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean getCheck(){
        return check;
    }

    //Return a Matrix
    public ChessPiece[][] getPieces(){
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];

        for(int i=0; i<board.getRows(); i++){
            for(int j=0; j< board.getColumns(); j++){
                //For each Piece in the board I'm going to do a downcast to ChessPiece
                mat[i][j] = (ChessPiece) board.piece(i,j);
            }
        }
        return mat;
    }
    //Posições possiveis apartir de uma posição de origem
    public boolean[][] possibleMoves(ChessPosition sourcePostion){
        Position position = sourcePostion.toPosition();
        validateSourcePosition(position); //valida
        return board.piece(position).possibleMoves();
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);
        //teste se o movimento colocou o jogador em check
        if (testCheck(currentPlayer)){
            undoMove(source, target, capturedPiece);
            throw new ChessException("You can't put yourself in check");
        }

        //testa se o oponente esta em ceck.
        check = testCheck(opponent(currentPlayer));

        nextTurn();
        return (ChessPiece) capturedPiece;
    }

    //tira da origem e bota no destino
    private Piece makeMove(Position source, Position target) {
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);

        if(capturedPiece != null){
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }

        return capturedPiece;
    }

    //desfaz a jogada
    private void undoMove(Position source, Position target, Piece capturedPiece){
        Piece p = board.removePiece(target);
        board.placePiece(p, source);

        if(capturedPiece != null){
            board.placePiece(capturedPiece, target);
            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add(capturedPiece);

        }
    }


    //Validate the source position
    private void validateSourcePosition(Position position) {
        if (!board.thereIsAPiece(position)){
            throw new ChessException("There is no piece on source position");
        }
        if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()){
            throw  new ChessException("There is no piece is not yours");
        }
        if(!board.piece(position).isThereAnyPossibleMove()){
            throw new ChessException("There is no possible move for the chosen piece");
        }
    }
    //Validate the target postion
    private void validateTargetPosition(Position source, Position target) {
        /*
        For validate source position regarding target position,
        I have to test if the target position is the possible move of source position
         */
        if(!board.piece(source).possibleMove(target)){
            throw new ChessException("The chosen piece can't move to target position");
        }
    }

    //Troca o turno
    private void nextTurn(){
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private Color opponent(Color color){
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private ChessPiece king(Color color){
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color).toList();
        for (Piece p : list){
            if (p instanceof King){
                return (ChessPiece) p;
            }
        }
        throw new IllegalStateException("There is no" + color + "king on the board");
    }

    private boolean testCheck(Color color) {
        //posição em formato de matrix
        Position kingPosition = king(color).getChessPosition().toPosition();
        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == opponent(color)).toList();
        for(Piece p : opponentPieces) {
            boolean[][] mat = p.possibleMoves();
            if (mat[kingPosition.getRow()][kingPosition.getColumn()]){
                return true;
            }
        }
        return false;
    }

    private void placeNewPiece(char colomn, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(colomn, row).toPosition());
        piecesOnTheBoard.add(piece);
    }

    private void initialSetup(){
        //Place the pieces on the board
        placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));

    }

}
