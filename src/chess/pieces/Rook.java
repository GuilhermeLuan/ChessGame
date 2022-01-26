package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class Rook extends ChessPiece {
    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString(){
        return "R";
    }

    @Override
    public boolean[][] possibleMoves(){
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0,0);

        /*ABOVE
            * Same column but decrease row.
         */
        p.setValues(position.getRow() - 1, position.getColumn());

        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            //While position p exist and there is no piece
            mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() - 1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            //If postion p exist and there is a opponent
            mat[p.getRow()][p.getColumn()] = true;
        }

        /*LEFT
         * Same row but decrease column.
         */
        p.setValues(position.getRow(), position.getColumn() - 1);

        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            //While position p exist and there is no piece
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() + 1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            //If postion p exist and there is a opponent
            mat[p.getRow()][p.getColumn()] = true;
        }

        /*RIGHT
         * Same row but increase column.
         */

        p.setValues(position.getRow(), position.getColumn() + 1);

        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            //While position p exist and there is no piece
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() + 1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            //If postion p exist and there is a opponent
            mat[p.getRow()][p.getColumn()] = true;
        }

        /*BELOW
         * Same column but increase row.
         */
        p.setValues(position.getRow() + 1, position.getColumn());

        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            //While position p exist and there is no piece
            mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() + 1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            //If postion p exist and there is a opponent
            mat[p.getRow()][p.getColumn()] = true;
        }

        return mat;
    }
}
