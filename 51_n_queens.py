class Solution:
    def solveNQueens(self, n):
        res = []
        board = [["."] * n for _ in range(n)]
        
        cols = set()
        diag = set()        # r - c
        anti_diag = set()   # r + c
        
        def backtrack(r):
            if r == n:
                res.append(["".join(row) for row in board])
                return
            
            for c in range(n):
                if c in cols or (r - c) in diag or (r + c) in anti_diag:
                    continue
                
                # place queen
                board[r][c] = "Q"
                cols.add(c)
                diag.add(r - c)
                anti_diag.add(r + c)
                
                backtrack(r + 1)
                
                # backtrack
                board[r][c] = "."
                cols.remove(c)
                diag.remove(r - c)
                anti_diag.remove(r + c)
        
        backtrack(0)
        return res