def solution(s: str) -> int:
    n: int = len(s)
    answer:int = n

    # 절반 이상은 의미없음
    for group in range(1, n//2 + 1):
        wordcnt: int = 0
        before: str = ""
        repeatcnt = 1

        # 건너뛰면서 비교 (연속 아닐 시 기존 연속인 경우 추가)
        for i in range(0, n - (n % group), group):
            if s[i:i+group] == before:
                repeatcnt += 1
            else: # 연속이 아닌 경우
                # 이전거 처리
                wordcnt += (group if i != 0 else 0) + (0 if repeatcnt == 1 else len(str(repeatcnt)))
                # 새롭게 초기화
                repeatcnt = 1
                before = s[i:i+group]
        wordcnt += group + (0 if repeatcnt == 1 else len(str(repeatcnt)))
        wordcnt += (n % group)

        answer = wordcnt if wordcnt < answer else answer

    return answer