package com.partos.chess.logic.helpers

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.partos.chess.R
import com.partos.chess.models.Piece

class BoardHelper {

    fun createBoardArray(rootView: View): Array<Array<ImageView>> {
        val image00 = rootView.findViewById<ImageView>(R.id.board_image_0_0)
        val image01 = rootView.findViewById<ImageView>(R.id.board_image_0_1)
        val image02 = rootView.findViewById<ImageView>(R.id.board_image_0_2)
        val image03 = rootView.findViewById<ImageView>(R.id.board_image_0_3)
        val image04 = rootView.findViewById<ImageView>(R.id.board_image_0_4)
        val image05 = rootView.findViewById<ImageView>(R.id.board_image_0_5)
        val image06 = rootView.findViewById<ImageView>(R.id.board_image_0_6)
        val image07 = rootView.findViewById<ImageView>(R.id.board_image_0_7)
        val array1 = arrayOf(image00, image01, image02, image03, image04, image05, image06, image07)

        val image10 = rootView.findViewById<ImageView>(R.id.board_image_1_0)
        val image11 = rootView.findViewById<ImageView>(R.id.board_image_1_1)
        val image12 = rootView.findViewById<ImageView>(R.id.board_image_1_2)
        val image13 = rootView.findViewById<ImageView>(R.id.board_image_1_3)
        val image14 = rootView.findViewById<ImageView>(R.id.board_image_1_4)
        val image15 = rootView.findViewById<ImageView>(R.id.board_image_1_5)
        val image16 = rootView.findViewById<ImageView>(R.id.board_image_1_6)
        val image17 = rootView.findViewById<ImageView>(R.id.board_image_1_7)
        val array2 = arrayOf(image10, image11, image12, image13, image14, image15, image16, image17)

        val image20 = rootView.findViewById<ImageView>(R.id.board_image_2_0)
        val image21 = rootView.findViewById<ImageView>(R.id.board_image_2_1)
        val image22 = rootView.findViewById<ImageView>(R.id.board_image_2_2)
        val image23 = rootView.findViewById<ImageView>(R.id.board_image_2_3)
        val image24 = rootView.findViewById<ImageView>(R.id.board_image_2_4)
        val image25 = rootView.findViewById<ImageView>(R.id.board_image_2_5)
        val image26 = rootView.findViewById<ImageView>(R.id.board_image_2_6)
        val image27 = rootView.findViewById<ImageView>(R.id.board_image_2_7)
        val array3 = arrayOf(image20, image21, image22, image23, image24, image25, image26, image27)

        val image30 = rootView.findViewById<ImageView>(R.id.board_image_3_0)
        val image31 = rootView.findViewById<ImageView>(R.id.board_image_3_1)
        val image32 = rootView.findViewById<ImageView>(R.id.board_image_3_2)
        val image33 = rootView.findViewById<ImageView>(R.id.board_image_3_3)
        val image34 = rootView.findViewById<ImageView>(R.id.board_image_3_4)
        val image35 = rootView.findViewById<ImageView>(R.id.board_image_3_5)
        val image36 = rootView.findViewById<ImageView>(R.id.board_image_3_6)
        val image37 = rootView.findViewById<ImageView>(R.id.board_image_3_7)
        val array4 = arrayOf(image30, image31, image32, image33, image34, image35, image36, image37)


        val image40 = rootView.findViewById<ImageView>(R.id.board_image_4_0)
        val image41 = rootView.findViewById<ImageView>(R.id.board_image_4_1)
        val image42 = rootView.findViewById<ImageView>(R.id.board_image_4_2)
        val image43 = rootView.findViewById<ImageView>(R.id.board_image_4_3)
        val image44 = rootView.findViewById<ImageView>(R.id.board_image_4_4)
        val image45 = rootView.findViewById<ImageView>(R.id.board_image_4_5)
        val image46 = rootView.findViewById<ImageView>(R.id.board_image_4_6)
        val image47 = rootView.findViewById<ImageView>(R.id.board_image_4_7)
        val array5 = arrayOf(image40, image41, image42, image43, image44, image45, image46, image47)


        val image50 = rootView.findViewById<ImageView>(R.id.board_image_5_0)
        val image51 = rootView.findViewById<ImageView>(R.id.board_image_5_1)
        val image52 = rootView.findViewById<ImageView>(R.id.board_image_5_2)
        val image53 = rootView.findViewById<ImageView>(R.id.board_image_5_3)
        val image54 = rootView.findViewById<ImageView>(R.id.board_image_5_4)
        val image55 = rootView.findViewById<ImageView>(R.id.board_image_5_5)
        val image56 = rootView.findViewById<ImageView>(R.id.board_image_5_6)
        val image57 = rootView.findViewById<ImageView>(R.id.board_image_5_7)
        val array6 = arrayOf(image50, image51, image52, image53, image54, image55, image56, image57)


        val image60 = rootView.findViewById<ImageView>(R.id.board_image_6_0)
        val image61 = rootView.findViewById<ImageView>(R.id.board_image_6_1)
        val image62 = rootView.findViewById<ImageView>(R.id.board_image_6_2)
        val image63 = rootView.findViewById<ImageView>(R.id.board_image_6_3)
        val image64 = rootView.findViewById<ImageView>(R.id.board_image_6_4)
        val image65 = rootView.findViewById<ImageView>(R.id.board_image_6_5)
        val image66 = rootView.findViewById<ImageView>(R.id.board_image_6_6)
        val image67 = rootView.findViewById<ImageView>(R.id.board_image_6_7)
        val array7 = arrayOf(image60, image61, image62, image63, image64, image65, image66, image67)


        val image70 = rootView.findViewById<ImageView>(R.id.board_image_7_0)
        val image71 = rootView.findViewById<ImageView>(R.id.board_image_7_1)
        val image72 = rootView.findViewById<ImageView>(R.id.board_image_7_2)
        val image73 = rootView.findViewById<ImageView>(R.id.board_image_7_3)
        val image74 = rootView.findViewById<ImageView>(R.id.board_image_7_4)
        val image75 = rootView.findViewById<ImageView>(R.id.board_image_7_5)
        val image76 = rootView.findViewById<ImageView>(R.id.board_image_7_6)
        val image77 = rootView.findViewById<ImageView>(R.id.board_image_7_7)
        val array8 = arrayOf(image70, image71, image72, image73, image74, image75, image76, image77)

        return arrayOf(array1, array2, array3, array4, array5, array6, array7, array8)
    }

    fun createMovesArray(rootView: View): Array<Array<ImageView>> {
        val move00 = rootView.findViewById<ImageView>(R.id.board_move_0_0)
        val move01 = rootView.findViewById<ImageView>(R.id.board_move_0_1)
        val move02 = rootView.findViewById<ImageView>(R.id.board_move_0_2)
        val move03 = rootView.findViewById<ImageView>(R.id.board_move_0_3)
        val move04 = rootView.findViewById<ImageView>(R.id.board_move_0_4)
        val move05 = rootView.findViewById<ImageView>(R.id.board_move_0_5)
        val move06 = rootView.findViewById<ImageView>(R.id.board_move_0_6)
        val move07 = rootView.findViewById<ImageView>(R.id.board_move_0_7)
        val array1 = arrayOf(move00, move01, move02, move03, move04, move05, move06, move07)

        val move10 = rootView.findViewById<ImageView>(R.id.board_move_1_0)
        val move11 = rootView.findViewById<ImageView>(R.id.board_move_1_1)
        val move12 = rootView.findViewById<ImageView>(R.id.board_move_1_2)
        val move13 = rootView.findViewById<ImageView>(R.id.board_move_1_3)
        val move14 = rootView.findViewById<ImageView>(R.id.board_move_1_4)
        val move15 = rootView.findViewById<ImageView>(R.id.board_move_1_5)
        val move16 = rootView.findViewById<ImageView>(R.id.board_move_1_6)
        val move17 = rootView.findViewById<ImageView>(R.id.board_move_1_7)
        val array2 = arrayOf(move10, move11, move12, move13, move14, move15, move16, move17)

        val move20 = rootView.findViewById<ImageView>(R.id.board_move_2_0)
        val move21 = rootView.findViewById<ImageView>(R.id.board_move_2_1)
        val move22 = rootView.findViewById<ImageView>(R.id.board_move_2_2)
        val move23 = rootView.findViewById<ImageView>(R.id.board_move_2_3)
        val move24 = rootView.findViewById<ImageView>(R.id.board_move_2_4)
        val move25 = rootView.findViewById<ImageView>(R.id.board_move_2_5)
        val move26 = rootView.findViewById<ImageView>(R.id.board_move_2_6)
        val move27 = rootView.findViewById<ImageView>(R.id.board_move_2_7)
        val array3 = arrayOf(move20, move21, move22, move23, move24, move25, move26, move27)

        val move30 = rootView.findViewById<ImageView>(R.id.board_move_3_0)
        val move31 = rootView.findViewById<ImageView>(R.id.board_move_3_1)
        val move32 = rootView.findViewById<ImageView>(R.id.board_move_3_2)
        val move33 = rootView.findViewById<ImageView>(R.id.board_move_3_3)
        val move34 = rootView.findViewById<ImageView>(R.id.board_move_3_4)
        val move35 = rootView.findViewById<ImageView>(R.id.board_move_3_5)
        val move36 = rootView.findViewById<ImageView>(R.id.board_move_3_6)
        val move37 = rootView.findViewById<ImageView>(R.id.board_move_3_7)
        val array4 = arrayOf(move30, move31, move32, move33, move34, move35, move36, move37)


        val move40 = rootView.findViewById<ImageView>(R.id.board_move_4_0)
        val move41 = rootView.findViewById<ImageView>(R.id.board_move_4_1)
        val move42 = rootView.findViewById<ImageView>(R.id.board_move_4_2)
        val move43 = rootView.findViewById<ImageView>(R.id.board_move_4_3)
        val move44 = rootView.findViewById<ImageView>(R.id.board_move_4_4)
        val move45 = rootView.findViewById<ImageView>(R.id.board_move_4_5)
        val move46 = rootView.findViewById<ImageView>(R.id.board_move_4_6)
        val move47 = rootView.findViewById<ImageView>(R.id.board_move_4_7)
        val array5 = arrayOf(move40, move41, move42, move43, move44, move45, move46, move47)


        val move50 = rootView.findViewById<ImageView>(R.id.board_move_5_0)
        val move51 = rootView.findViewById<ImageView>(R.id.board_move_5_1)
        val move52 = rootView.findViewById<ImageView>(R.id.board_move_5_2)
        val move53 = rootView.findViewById<ImageView>(R.id.board_move_5_3)
        val move54 = rootView.findViewById<ImageView>(R.id.board_move_5_4)
        val move55 = rootView.findViewById<ImageView>(R.id.board_move_5_5)
        val move56 = rootView.findViewById<ImageView>(R.id.board_move_5_6)
        val move57 = rootView.findViewById<ImageView>(R.id.board_move_5_7)
        val array6 = arrayOf(move50, move51, move52, move53, move54, move55, move56, move57)


        val move60 = rootView.findViewById<ImageView>(R.id.board_move_6_0)
        val move61 = rootView.findViewById<ImageView>(R.id.board_move_6_1)
        val move62 = rootView.findViewById<ImageView>(R.id.board_move_6_2)
        val move63 = rootView.findViewById<ImageView>(R.id.board_move_6_3)
        val move64 = rootView.findViewById<ImageView>(R.id.board_move_6_4)
        val move65 = rootView.findViewById<ImageView>(R.id.board_move_6_5)
        val move66 = rootView.findViewById<ImageView>(R.id.board_move_6_6)
        val move67 = rootView.findViewById<ImageView>(R.id.board_move_6_7)
        val array7 = arrayOf(move60, move61, move62, move63, move64, move65, move66, move67)


        val move70 = rootView.findViewById<ImageView>(R.id.board_move_7_0)
        val move71 = rootView.findViewById<ImageView>(R.id.board_move_7_1)
        val move72 = rootView.findViewById<ImageView>(R.id.board_move_7_2)
        val move73 = rootView.findViewById<ImageView>(R.id.board_move_7_3)
        val move74 = rootView.findViewById<ImageView>(R.id.board_move_7_4)
        val move75 = rootView.findViewById<ImageView>(R.id.board_move_7_5)
        val move76 = rootView.findViewById<ImageView>(R.id.board_move_7_6)
        val move77 = rootView.findViewById<ImageView>(R.id.board_move_7_7)
        val array8 = arrayOf(move70, move71, move72, move73, move74, move75, move76, move77)

        return arrayOf(array1, array2, array3, array4, array5, array6, array7, array8)
    }

    fun initBoardPieces(board: Array<Array<ImageView>>, context: Context) {
        board[0][0].setImageDrawable(context.getDrawable(R.drawable.rook_black))
        board[0][7].setImageDrawable(context.getDrawable(R.drawable.rook_black))
        board[0][1].setImageDrawable(context.getDrawable(R.drawable.knight_black))
        board[0][6].setImageDrawable(context.getDrawable(R.drawable.knight_black))
        board[0][2].setImageDrawable(context.getDrawable(R.drawable.bishop_black))
        board[0][5].setImageDrawable(context.getDrawable(R.drawable.bishop_black))
        board[0][2].setImageDrawable(context.getDrawable(R.drawable.bishop_black))
        board[0][3].setImageDrawable(context.getDrawable(R.drawable.queen_black))
        board[0][4].setImageDrawable(context.getDrawable(R.drawable.king_black))
        board[1][0].setImageDrawable(context.getDrawable(R.drawable.pawn_black))
        board[1][1].setImageDrawable(context.getDrawable(R.drawable.pawn_black))
        board[1][2].setImageDrawable(context.getDrawable(R.drawable.pawn_black))
        board[1][3].setImageDrawable(context.getDrawable(R.drawable.pawn_black))
        board[1][4].setImageDrawable(context.getDrawable(R.drawable.pawn_black))
        board[1][5].setImageDrawable(context.getDrawable(R.drawable.pawn_black))
        board[1][6].setImageDrawable(context.getDrawable(R.drawable.pawn_black))
        board[1][7].setImageDrawable(context.getDrawable(R.drawable.pawn_black))


        board[7][0].setImageDrawable(context.getDrawable(R.drawable.rook_white))
        board[7][7].setImageDrawable(context.getDrawable(R.drawable.rook_white))
        board[7][1].setImageDrawable(context.getDrawable(R.drawable.knight_white))
        board[7][6].setImageDrawable(context.getDrawable(R.drawable.knight_white))
        board[7][2].setImageDrawable(context.getDrawable(R.drawable.bishop_white))
        board[7][5].setImageDrawable(context.getDrawable(R.drawable.bishop_white))
        board[7][2].setImageDrawable(context.getDrawable(R.drawable.bishop_white))
        board[7][3].setImageDrawable(context.getDrawable(R.drawable.queen_white))
        board[7][4].setImageDrawable(context.getDrawable(R.drawable.king_white))
        board[6][0].setImageDrawable(context.getDrawable(R.drawable.pawn_white))
        board[6][1].setImageDrawable(context.getDrawable(R.drawable.pawn_white))
        board[6][2].setImageDrawable(context.getDrawable(R.drawable.pawn_white))
        board[6][3].setImageDrawable(context.getDrawable(R.drawable.pawn_white))
        board[6][4].setImageDrawable(context.getDrawable(R.drawable.pawn_white))
        board[6][5].setImageDrawable(context.getDrawable(R.drawable.pawn_white))
        board[6][6].setImageDrawable(context.getDrawable(R.drawable.pawn_white))
        board[6][7].setImageDrawable(context.getDrawable(R.drawable.pawn_white))

    }

    fun drawPiece(piece: Piece, board: Array<Array<ImageView>>, context: Context) {
        when (piece.type) {
            0 -> {
                if (piece.color == 0) {
                    board[piece.positionY][piece.positionX].setImageDrawable(context.getDrawable(R.drawable.pawn_white))
                } else {
                    board[piece.positionY][piece.positionX].setImageDrawable(context.getDrawable(R.drawable.pawn_black))
                }
            }
            1 -> {
                if (piece.color == 0) {
                    board[piece.positionY][piece.positionX].setImageDrawable(context.getDrawable(R.drawable.bishop_white))
                } else {
                    board[piece.positionY][piece.positionX].setImageDrawable(context.getDrawable(R.drawable.bishop_black))
                }
            }

            2 -> {
                if (piece.color == 0) {
                    board[piece.positionY][piece.positionX].setImageDrawable(context.getDrawable(R.drawable.knight_white))
                } else {
                    board[piece.positionY][piece.positionX].setImageDrawable(context.getDrawable(R.drawable.knight_black))
                }
            }
            3 -> {
                if (piece.color == 0) {
                    board[piece.positionY][piece.positionX].setImageDrawable(context.getDrawable(R.drawable.rook_white))
                } else {
                    board[piece.positionY][piece.positionX].setImageDrawable(context.getDrawable(R.drawable.rook_black))
                }
            }
            4 -> {
                if (piece.color == 0) {
                    board[piece.positionY][piece.positionX].setImageDrawable(context.getDrawable(R.drawable.queen_white))
                } else {
                    board[piece.positionY][piece.positionX].setImageDrawable(context.getDrawable(R.drawable.queen_black))
                }
            }
            5 -> {
                if (piece.color == 0) {
                    board[piece.positionY][piece.positionX].setImageDrawable(context.getDrawable(R.drawable.king_white))
                } else {
                    board[piece.positionY][piece.positionX].setImageDrawable(context.getDrawable(R.drawable.king_black))
                }
            }
        }
    }

    fun isPiece(image: ImageView): Boolean {
        if (image.drawable != null) {
            return true
        }
        return false
    }

    fun resetBoard(
        piecesList: ArrayList<Piece>,
        boardTaken: Array<Array<ImageView>>,
        context: Context
    ) {
        for (array in boardTaken) {
            for (image in array) {
                image.setImageDrawable(null)
            }
        }
        for (piece in piecesList) {
            if (piece.isActive) {
                drawPiece(piece, boardTaken, context)
            }
        }
    }

    fun getSquareColor(positionY: Int, positionX: Int): Int {
        if (positionY % 2 == 0) {
            return if (positionX % 2 == 0) {
                0
            } else {
                1
            }
        } else {
            return if (positionX % 2 == 0) {
                1
            } else {
                0
            }
        }
    }
}