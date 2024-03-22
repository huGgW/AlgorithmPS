def solution(routes):
    routes.sort(key=lambda ls: ls[1])
    
    pin = 0
    while routes:
        beg, end = routes.pop(0)
        pin += 1
        pinCoord = end
        while routes and routes[0][0] <= pinCoord <= routes[0][1]:
            routes.pop(0)
            
    return pin