from typing import *
from dataclasses import dataclass, field
import heapq

@dataclass(order=True)
class MusicInfo:
    sort_index: Tuple[int, int] = field(init = False)
    id: int
    play: int
    
    def __post_init__(self):
        self.sort_index = (-self.play, self.id)

        
def solution(genres: List[str], plays: List[int]) -> List[int]:
    genreHeap: Dict[str, List[MusicInfo]] = {}
    genreSum: Dict[str, int] = {}
                    
    for i in range(len(genres)):
        genre = genres[i]
        play = plays[i]
        genreSum[genre] = genreSum.get(genre, 0) + play
        genreHeap[genre] = genreHeap.get(genre, []) + [MusicInfo(i, play)]
        
    answer = []
    for genre, _ in sorted(genreSum.items(), key=lambda t: t[1], reverse=True):
        musics = genreHeap[genre]
        heapq.heapify(musics)
        
        for i in range(2):
            if musics:
                answer.append(heapq.heappop(musics).id)
                
    return answer