import java.util.*

class Solution {
    fun solution(game_board: Array<IntArray>, table: Array<IntArray>): Int {
        val pieces = mutableSetOf<Piece>()
        (0 until table.size).forEach { i ->
            (0 until table[0].size).forEach {j ->
                if (table[i][j] == 1) {
                    initPieceFrom(Pair(i, j), table).let {
                        pieces.add(it)
                    }
                }
            }
        }

        var answer = 0
        game_board.indices.forEach { i ->
            game_board[0].indices.forEach{ j ->
                if (game_board[i][j] == 0) {
                    val blocks = getBlocks(i to j, game_board, seekNum = 0)
                    var fitPiece: Piece? = null
                    for (piece in pieces) {
                        if (isPieceInsertable(piece, blocks)) {
                            fitPiece = piece
                            break
                        }
                    }
                    fitPiece?.let {
                        pieces -= it
                        markBlocks(blocks, i to j, game_board)
                        answer += it.blocks.size
                    }
                }
            }
        }

        return answer
    }

    data class Piece (
        val pos: Pair<Int, Int>,
        var blocks: Set<Pair<Int, Int>>
    ) {
        var count = 0

        fun rotateRight() {
            blocks = blocks.map {
                Pair(it.second, -it.first)
            }.toSet()
            count++
        }

        fun isAllChecked(): Boolean {
            return count == 4
        }

        fun resetCount() {
            count = 0
        }

        // Only consider pos as indicator
        override fun equals(other: Any?): Boolean {
            return when (other) {
                is Piece -> {
                    (pos == other.pos)
                }
                else -> {
                    false
                }
            }
        }

        override fun hashCode(): Int {
            return pos.hashCode()
        }
    }

    fun initPieceFrom(start: Pair<Int, Int>, table: Array<IntArray>)
        = Piece(start, getBlocks(start, table, mark=true))

    fun getBlocks(
        start: Pair<Int, Int>,
        table: Array<IntArray>,
        seekNum: Int = 1,
        mark: Boolean = false
    ): Set<Pair<Int, Int>> {
        val blocks = mutableSetOf<Pair<Int, Int>>()
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.offer(Pair(0, 0))

        while (queue.isNotEmpty()) {
            val curr = queue.poll()
            blocks.add(curr)
            if (mark) {
                table[curr.first + start.first][curr.second + start.second] =
                    if (seekNum == 1) 0 else 1
            }

            for (neighbor in listOf(
                    Pair(curr.first + 1, curr.second),
                    Pair(curr.first - 1, curr.second),
                    Pair(curr.first, curr.second + 1),
                    Pair(curr.first, curr.second - 1),
            )) {
                val neighborRow = neighbor.first + start.first
                val neighborCol = neighbor.second + start.second

                if (blocks.contains(neighbor)) { // already visited, skip
                    continue
                }

                // Not in table, skip
                if (!(
                    0 <= neighborRow && neighborRow < table.size
                    && 0 <= neighborCol && neighborCol < table[0].size
                )) {
                    continue
                }

                // Not a block, continue
                if (table[neighborRow][neighborCol] != seekNum) {
                    continue
                }

                queue.offer(neighbor)
            }
        }

        return blocks
    }

    fun isPieceInsertable(piece: Piece, blocks: Set<Pair<Int, Int>>): Boolean {
        while (!piece.isAllChecked()) {
            if (piece.blocks == blocks) {
                piece.resetCount()
                return true
            }
            piece.rotateRight()
        }

        piece.resetCount()
        return false
    }

    fun markBlocks(blocks: Set<Pair<Int, Int>>, start: Pair<Int, Int>, board: Array<IntArray>) {
        blocks.forEach { (di, dj) ->
            board[start.first + di][start.second + dj] = 1
        }
    }
}
